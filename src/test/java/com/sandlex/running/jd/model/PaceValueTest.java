package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaceValueTest {

    @Test
    void shouldHandleMissingMissingSeparator() {
        assertThatThrownBy(() -> new PaceValue("03;33"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cannot parse pace value: 03;33. Expected format: mm:ss");
    }

    @Test
    void shouldHandleNegativeNumber() {
        assertThatThrownBy(() -> new PaceValue("-03:33"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Incorrect minutes value: -03:33. Expected value 0..59");
    }

    @Test
    void shouldHandleHighNumber() {
        assertThatThrownBy(() -> new PaceValue("03:63"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Incorrect seconds value: 03:63. Expected value 0..59");
    }

    @Test
    void shouldCreatePaceValue() {
        PaceValue paceValue = new PaceValue("03:33");

        assertThat(paceValue.getMinutes()).isEqualTo(3);
        assertThat(paceValue.getSeconds()).isEqualTo(33);
    }
}
