package com.sandlex.runcalc;

import com.sandlex.runcalc.model.PaceBlock;
import com.sandlex.runcalc.model.Distance;
import com.sandlex.runcalc.model.Schema;
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

        Estimation estimation = Solver.solve(paceBlock, schema, Distance.System.METRIC);

        assertThat(estimation.getKilometers()).isEqualTo(24.20);
        assertThat(estimation.getFormattedTime()).isEqualTo("01:42:11");
    }

    @Test
    void shouldCalculateRepeatable() {
        PaceBlock paceBlock = new PaceBlock("E=4:30,M=4:09");
        Schema schema = new Schema("2 * (2.2E + 1:32:17M)");

        Estimation estimation = Solver.solve(paceBlock, schema, Distance.System.METRIC);

        assertThat(estimation.getKilometers()).isEqualTo(48.40);
        assertThat(estimation.getFormattedTime()).isEqualTo("03:24:22");
    }

    @Test
    void shouldCalculateCombination() {
        PaceBlock paceBlock = new PaceBlock("E=4:30,M=4:09");
        Schema schema = new Schema("2.2E + 1:32:17M + 2 * (2.2E + 1:32:17M)");

        Estimation estimation = Solver.solve(paceBlock, schema, Distance.System.METRIC);

        assertThat(estimation.getKilometers()).isEqualTo(72.60);
        assertThat(estimation.getFormattedTime()).isEqualTo("05:06:33");
        assertThat(estimation.getSeconds()).isEqualTo(18393);
        assertThat(estimation.getTimeInSeconds()).isEqualTo(18393);
        assertThat(estimation.getDistanceInUnits()).isEqualTo(72.60);
        assertThat(estimation.toString()).isEqualTo(String.format("Estimated distance - %.2f%s, time - 05:06:33", 72.60, "km"));
    }

}
