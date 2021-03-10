package com.sandlex.running.jd;

import com.sandlex.running.jd.model.Calculable;
import com.sandlex.running.jd.model.Distance;
import com.sandlex.running.jd.model.Pace;
import com.sandlex.running.jd.model.PaceBlock;
import com.sandlex.running.jd.model.PaceName;
import com.sandlex.running.jd.model.PaceValue;
import com.sandlex.running.jd.model.Phase;
import com.sandlex.running.jd.model.Repetition;
import com.sandlex.running.jd.model.Schema;
import lombok.experimental.UtilityClass;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@UtilityClass
class Solver {

    String solve(PaceBlock paceBlock, Schema schema, Distance.System system) {
        Map<PaceName, PaceValue> paces = paceBlock.getPaces().stream().collect(toMap(Pace::getPaceName, Pace::getPaceValue));

        schema.getPhases().forEach(calculable -> Solver.validate(paces, calculable));

        return String.format("Estimated distance - %.2f %s, time - %s", 42.195, "km/miles", "01:01:01");
    }

    private void validate(Map<PaceName, PaceValue> paces, Calculable calculable) {
        if (calculable instanceof Phase) {
            Phase phase = (Phase) calculable;
            if (!paces.containsKey(phase.getPaceName())) {
                throw new IllegalArgumentException("Unknown pace: " + phase.getPaceName());
            }
        } else if (calculable instanceof Repetition ){
            Repetition repetition = (Repetition) calculable;
            repetition.getPhases().forEach(rep -> Solver.validate(paces, rep));
        }
    }

}
