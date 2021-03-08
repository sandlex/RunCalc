package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RepetitionCountTest {

    @Test
    void shouldHandleNonNumericValue() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new RepetitionCount("one"))
                .withMessage("Repetition count must be an integer number: one");
    }

    @Test
    void shouldHandleNonIntegerValue() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new RepetitionCount("1.5"))
                .withMessage("Repetition count must be an integer number: 1.5");
    }

    @Test
    void shouldHandleNegativeValue() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new RepetitionCount("-3"))
                .withMessage("Repetition count must be a positive number: -3");
    }

    @Test
    void shouldHandleValidValue() {
        assertThat(new RepetitionCount("3").getValue()).isEqualTo(3);
    }

}
