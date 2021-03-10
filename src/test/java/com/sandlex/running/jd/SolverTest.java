package com.sandlex.running.jd;

import com.sandlex.running.jd.model.Distance;
import com.sandlex.running.jd.model.PaceBlock;
import com.sandlex.running.jd.model.Schema;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SolverTest {

    @ParameterizedTest
    @ValueSource(strings = {"2T10 + 3HM + 4M", "2T10 + 2 * (2HM + 01:00M)"})
    void shouldDetectUnknownPace(String schemaInput) {
        PaceBlock paceBlock = new PaceBlock("T10=3:35,HM=3:40");
        Schema schema = new Schema(schemaInput);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Solver.solve(paceBlock, schema, Distance.System.METRIC))
                .withMessage("Unknown pace: M");
    }

}
