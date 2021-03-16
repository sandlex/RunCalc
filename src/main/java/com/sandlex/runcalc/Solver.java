package com.sandlex.runcalc;

import com.sandlex.runcalc.model.PaceValue;
import com.sandlex.runcalc.model.Calculable;
import com.sandlex.runcalc.model.Distance;
import com.sandlex.runcalc.model.Duration;
import com.sandlex.runcalc.model.Measure;
import com.sandlex.runcalc.model.Pace;
import com.sandlex.runcalc.model.PaceBlock;
import com.sandlex.runcalc.model.PaceName;
import com.sandlex.runcalc.model.Phase;
import com.sandlex.runcalc.model.Repetition;
import com.sandlex.runcalc.model.Schema;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@UtilityClass
class Solver {

    Estimation solve(PaceBlock paceBlock, Schema schema) {
        Map<PaceName, PaceValue> paces = paceBlock.getPaces().stream().collect(toMap(Pace::getPaceName, Pace::getPaceValue));

        schema.getPhases().forEach(calculable -> Solver.validate(paces, calculable));

        Estimation estimation = new Estimation();
        schema.getPhases().forEach(calculable -> Solver.calculate(paces, calculable, estimation));

        return estimation;
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

    private void calculate(Map<PaceName, PaceValue> paces, Calculable calculable, Estimation estimation) {
        if (calculable instanceof Phase) {
            Phase phase = (Phase) calculable;
            Measure measure = phase.getMeasure();
            if (measure instanceof Distance) {
                BigDecimal distance = ((Distance) measure).getValue();
                estimation.addDistance(distance);
                PaceValue paceValue = paces.get(phase.getPaceName());
                estimation.addSeconds(distance.multiply(BigDecimal.valueOf(paceValue.getInSeconds())).intValue());
            } else if (measure instanceof Duration) {
                int seconds = ((Duration) measure).getInSeconds();
                estimation.addSeconds(seconds);
                PaceValue paceValue = paces.get(phase.getPaceName());
                estimation.addDistance(BigDecimal.valueOf((double) seconds / paceValue.getInSeconds()));
            }
        } else if (calculable instanceof Repetition) {
            Repetition repetition = (Repetition) calculable;
            Estimation repetitionEstimation = new Estimation();
            repetition.getPhases().forEach(rep -> Solver.calculate(paces, rep, repetitionEstimation));
            estimation.addDistance(repetitionEstimation.distance.multiply(BigDecimal.valueOf(repetition.getRepetitionCount().getValue())));
            estimation.addSeconds(repetitionEstimation.seconds * repetition.getRepetitionCount().getValue());
        }
    }

}
