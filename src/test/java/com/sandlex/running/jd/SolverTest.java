package com.sandlex.running.jd;

import com.sandlex.running.jd.model.Distance;
import com.sandlex.running.jd.model.PaceBlock;
import com.sandlex.running.jd.model.Schema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Java6Assertions.assertThat;

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

    @Test
    void shouldCalculate() {
        PaceBlock paceBlock = new PaceBlock("E=4:30,M=4:09");
        Schema schema = new Schema("2.2E + 1:32:17M");

        Solver.Result result = Solver.solve(paceBlock, schema, Distance.System.METRIC);

        assertThat(result.toString()).isEqualTo("Estimated distance - 24.20km, time - 01:42:11");
    }

    @Test
    void shouldCalculateRepeatable() {
        PaceBlock paceBlock = new PaceBlock("E=4:30,M=4:09");
        Schema schema = new Schema("2 * (2.2E + 1:32:17M)");

        Solver.Result result = Solver.solve(paceBlock, schema, Distance.System.METRIC);

        assertThat(result.toString()).isEqualTo("Estimated distance - 48.40km, time - 03:24:22");
    }

}
