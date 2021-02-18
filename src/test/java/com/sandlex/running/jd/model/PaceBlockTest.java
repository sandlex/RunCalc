package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaceBlockTest {

    @Test
    void shouldHandleIncorrectFormat() {
        assertThatThrownBy(() -> new PaceBlock("T10=03:33;HM=03:37"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cannot parse pace block: T10=03:33;HM=03:37. Expected format: pace1=mm:ss,pace2=mm:ss");
    }

    @Test
    void shouldHandleEmptyValues() {
        assertThatThrownBy(() -> new PaceBlock(","))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Paces block doesn't contain any values. Expected format: pace1=mm:ss,pace2=mm:ss");
    }

    @Test
    void shouldHandleDuplicatedValues() {
        assertThatThrownBy(() -> new PaceBlock("T10=03:33,T10=03:35"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Pace used more than once: T10");
    }

    @Test
    void shouldCreatePaceBlock() {
        PaceBlock paceBlock = new PaceBlock("T10=03:33,HM=03:37");

        assertThat(paceBlock.getPaces()).hasSize(2);
    }

}
