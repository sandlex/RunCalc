package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaceTest {

    @Test
    void shouldHandleInvalidFormat() {
        assertThatThrownBy(() -> new Pace("T10 03:33"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cannot parse pace: T10 03:33. Expected format: pace=mm:ss");
    }

    @Test
    void shouldCreatePace() {
        Pace pace = new Pace("T10=03:33");

        assertThat(pace.getPaceName().getValue()).isEqualTo("T10");
        assertThat(pace.getPaceValue().getMinutes()).isEqualTo(3);
        assertThat(pace.getPaceValue().getSeconds()).isEqualTo(33);
    }
}
