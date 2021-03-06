package com.sandlex.runcalc.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PaceBlockTest {

    @Test
    void shouldHandleBlankInput() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaceBlock(""))
                .withMessage("Pace block can't be empty. Expected format: pace1=mm:ss,pace2=mm:ss");
    }

    @Test
    void shouldHandleEmptyValues() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaceBlock(","))
                .withMessage("Paces block doesn't contain any values. Expected format: pace1=mm:ss,pace2=mm:ss");
    }

    @Test
    void shouldHandleDuplicatedValues() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaceBlock("T10=03:33,T10=03:35"))
                .withMessage("Pace used more than once: T10");
    }

    @Test
    void shouldCreatePaceBlock() {
        PaceBlock paceBlock = new PaceBlock("T10=03:33,HM=03:37");

        assertThat(paceBlock.getPaces()).hasSize(2);
    }

    @Test
    void shouldCreatePaceBlockFromOne() {
        PaceBlock paceBlock = new PaceBlock("T10=03:33");

        assertThat(paceBlock.getPaces()).hasSize(1);
    }

}
