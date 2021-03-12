package com.sandlex.runcalc.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaceValueTest {

    @Test
    void shouldHandleMissingMissingSeparator() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaceValue("0333"))
                .withMessage("Cannot parse pace value: 0333. Expected format: mm:ss");
    }

    @Test
    void shouldHandleNegativeNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaceValue("-03:33"))
                .withMessage("Pace value can contain only numbers and colon");
    }

    @Test
    void shouldHandleHighNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaceValue("03:63"))
                .withMessage("Incorrect seconds value: 63. Expected value 0..59");
    }

    @Test
    void shouldCreatePaceValue() {
        PaceValue paceValue = new PaceValue("03:33");

        assertThat(paceValue.getMinutes()).isEqualTo(3);
        assertThat(paceValue.getSeconds()).isEqualTo(33);
    }
}
