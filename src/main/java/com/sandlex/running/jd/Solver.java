package com.sandlex.running.jd;

import com.sandlex.running.jd.model.Calculable;
import com.sandlex.running.jd.model.Distance;
import com.sandlex.running.jd.model.Duration;
import com.sandlex.running.jd.model.Measure;
import com.sandlex.running.jd.model.Pace;
import com.sandlex.running.jd.model.PaceBlock;
import com.sandlex.running.jd.model.PaceName;
import com.sandlex.running.jd.model.PaceValue;
import com.sandlex.running.jd.model.Phase;
import com.sandlex.running.jd.model.Repetition;
import com.sandlex.running.jd.model.Schema;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toMap;

@UtilityClass
class Solver {

    Result solve(PaceBlock paceBlock, Schema schema, Distance.System system) {
        Map<PaceName, PaceValue> paces = paceBlock.getPaces().stream().collect(toMap(Pace::getPaceName, Pace::getPaceValue));

        schema.getPhases().forEach(calculable -> Solver.validate(paces, calculable));

        Result result = new Result();
        schema.getPhases().forEach(calculable -> Solver.calculate(paces, calculable, result));

        return result;
    }

    private void validate(Map<PaceName, PaceValue> paces, Calculable calculable) {
        if (calculable instanceof Phase) {
            Phase phase = (Phase) calculable;
            if (!paces.containsKey(phase.getPaceName())) {
                throw new IllegalArgumentException("Unknown pace: " + phase.getPaceName());
            }
        } else if (calculable instanceof Repetition) {
            Repetition repetition = (Repetition) calculable;
            repetition.getPhases().forEach(rep -> Solver.validate(paces, rep));
        }
    }

    private void calculate(Map<PaceName, PaceValue> paces, Calculable calculable, Result result) {
        if (calculable instanceof Phase) {
            Phase phase = (Phase) calculable;
            Measure measure = phase.getMeasure();
            if (measure instanceof Distance) {
                double kilometers = ((Distance) measure).getKilometers(Distance.System.METRIC);
                result.addKilometers(kilometers);
                PaceValue paceValue = paces.get(phase.getPaceName());
                result.addSeconds(Math.round(kilometers * paceValue.getInSeconds()));
            } else if (measure instanceof Duration) {
                int seconds = ((Duration) measure).getInSeconds();
                result.addSeconds(seconds);
                PaceValue paceValue = paces.get(phase.getPaceName());
                result.addKilometers(seconds / paceValue.getInSeconds());
            }
        } else if (calculable instanceof Repetition) {
            Repetition repetition = (Repetition) calculable;
            Result repetitionResult = new Result();
            repetition.getPhases().forEach(rep -> Solver.calculate(paces, rep, repetitionResult));
            result.addKilometers(repetitionResult.kilometers * repetition.getRepetitionCount().getValue());
            result.addSeconds(repetitionResult.seconds * repetition.getRepetitionCount().getValue());
        }
    }

    static class Result {
        private long seconds;
        private double kilometers;

        void addSeconds(long increment) {
            this.seconds += increment;
        }

        void addKilometers(double increment) {
            this.kilometers += increment;
        }

        private String getFormattedTime() {
            long hours = TimeUnit.SECONDS.toHours(seconds);
            seconds -= TimeUnit.HOURS.toSeconds(hours);
            long minutes = TimeUnit.SECONDS.toMinutes(seconds);
            seconds -= TimeUnit.MINUTES.toSeconds(minutes);

            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

        @Override
        public String toString() {
            return String.format("Estimated distance - %.2f%s, time - %s", kilometers, "km", getFormattedTime());
        }
    }

}
