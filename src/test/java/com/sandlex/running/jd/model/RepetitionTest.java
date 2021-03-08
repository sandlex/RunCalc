package com.sandlex.running.jd.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RepetitionTest {

    @ParameterizedTest
    @ValueSource(strings = {"*2*()", "2*3*()"})
    void shouldHandleWrongFormat(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Repetition(input))
                .withMessage("Incorrect repetition value" + input + ". Expected format: 3 * (2Easy + 01:00Rest)");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2*(2E+01:00Rest)", "(01:30:00M+30:00E)*2"})
    void shouldHandleValidInput(String input) {
        Repetition repetition = new Repetition(input);

        assertThat(repetition.getRepetitionCount().getValue()).isEqualTo(2);
        assertThat(repetition.getPhases().size()).isEqualTo(2);
    }

}
