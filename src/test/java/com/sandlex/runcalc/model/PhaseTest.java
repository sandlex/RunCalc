package com.sandlex.runcalc.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PhaseTest {

    @Test
    void shouldCreateDistancePhase() {
        Phase phase = new Phase("2.5T10");

        assertThat(phase.getMeasure()).isInstanceOf(Distance.class);
        assertThat(((Distance) phase.getMeasure()).getValue()).isEqualTo(2.5);
        assertThat(phase.getPaceName().getValue()).isEqualTo("T10");
    }

    @Test
    void shouldCreateRoundDistancePhase() {
        Phase phase = new Phase("25T10");

        assertThat(phase.getMeasure()).isInstanceOf(Distance.class);
        assertThat(((Distance) phase.getMeasure()).getValue()).isEqualTo(25);
        assertThat(phase.getPaceName().getValue()).isEqualTo("T10");
    }

    @Test
    void shouldCreateDurationPhase() {
        Phase phase = new Phase("01:30:00T10");

        assertThat(phase.getMeasure()).isInstanceOf(Duration.class);
        assertThat(((Duration) phase.getMeasure()).getHours()).isEqualTo(1);
        assertThat(((Duration) phase.getMeasure()).getMinutes()).isEqualTo(30);
        assertThat(((Duration) phase.getMeasure()).getSeconds()).isZero();
        assertThat(phase.getPaceName().getValue()).isEqualTo("T10");
    }

    @Test
    void shouldCreateShortDurationPhase() {
        Phase phase = new Phase("30:00T10");

        assertThat(phase.getMeasure()).isInstanceOf(Duration.class);
        assertThat(((Duration) phase.getMeasure()).getHours()).isZero();
        assertThat(((Duration) phase.getMeasure()).getMinutes()).isEqualTo(30);
        assertThat(((Duration) phase.getMeasure()).getSeconds()).isZero();
        assertThat(phase.getPaceName().getValue()).isEqualTo("T10");
    }

}
