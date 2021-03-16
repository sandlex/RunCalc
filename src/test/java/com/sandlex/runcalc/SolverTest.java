package com.sandlex.runcalc;

import com.sandlex.runcalc.model.PaceBlock;
import com.sandlex.runcalc.model.Schema;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Java6Assertions.assertThat;

class SolverTest {

    @ParameterizedTest
    @ValueSource(strings = {"2T10 + 3HM + 4M", "2T10 + 2 * (2HM + 01:00M)"})
    void shouldDetectUnknownPace(String schemaInput) {
        PaceBlock paceBlock = new PaceBlock("T10=3:35,HM=3:40");
        Schema schema = new Schema(schemaInput);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Solver.solve(paceBlock, schema))
                .withMessage("Unknown pace: M");
    }

    @Test
    void shouldCalculateMetricMarathon() {
        PaceBlock paceBlock = new PaceBlock("M=4:00");
        Schema schema = new Schema("42.2M");

        Estimation estimation = Solver.solve(paceBlock, schema);

        assertThat(estimation.getDistance()).isEqualTo(new BigDecimal("42.200"));
        assertThat(estimation.getFormattedTime()).isEqualTo("02:48:48");
    }

    @Test
    void shouldCalculateImperialMarathon() {
        PaceBlock paceBlock = new PaceBlock("M=6:00");
        Schema schema = new Schema("26.22M");

        Estimation estimation = Solver.solve(paceBlock, schema);

        assertThat(estimation.getDistance()).isEqualTo(new BigDecimal("26.220"));
        assertThat(estimation.getFormattedTime()).isEqualTo("02:37:19");
    }

    @Test
    void shouldCalculateRepeatable() {
        PaceBlock paceBlock = new PaceBlock("E=4:30,M=4:09");
        Schema schema = new Schema("2 * (2.2E + 1:32:17M)");

        Estimation estimation = Solver.solve(paceBlock, schema);

        assertThat(estimation.getDistance()).isEqualTo(new BigDecimal("48.874"));
        assertThat(estimation.getFormattedTime()).isEqualTo("03:24:22");
    }

    @Test
    void shouldCalculateCombination() {
        PaceBlock paceBlock = new PaceBlock("E=4:30,M=4:09");
        Schema schema = new Schema("2.2E + 1:32:17M + 2 * (2.2E + 1:32:17M)");

        Estimation estimation = Solver.solve(paceBlock, schema);

        assertThat(estimation.getFormattedTime()).isEqualTo("05:06:33");
        assertThat(estimation.getSeconds()).isEqualTo(18393);
        assertThat(estimation.getDistance()).isEqualTo(new BigDecimal("73.311"));
        assertThat(estimation).hasToString(String.format("Estimated distance - %.3f, time - 05:06:33", 73.311));
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void shouldCalculateComplexCaseByParts(String schemaInput, int seconds, BigDecimal distance) {
        PaceBlock paceBlock = new PaceBlock("WU=5:00,T10=3:40,E=4:30,T5=3:30,Rest=10:00,M=4:00");
        Schema schema = new Schema(schemaInput);

        Estimation estimation = Solver.solve(paceBlock, schema);

        Assertions.assertThat(estimation.getDistance()).isEqualTo(distance);
        Assertions.assertThat(estimation.getSeconds()).isEqualTo(seconds);
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of("15:00WU", 900, new BigDecimal("3.000")),
                Arguments.of("3T10", 660, new BigDecimal("3.000")),
                Arguments.of("1.5E", 405, new BigDecimal("1.500")),
                Arguments.of("5 * (0.4T5 + 00:30Rest)", 570, new BigDecimal("2.250")),
                Arguments.of("1:30:00M", 5400, new BigDecimal("22.500")),
                Arguments.of("15:00WU + 3T10 + 1.5E + 5 * (0.4T5 + 00:30Rest) + 1.5E + 1:30:00M", 8340, new BigDecimal("33.750"))
        );
    }

}
