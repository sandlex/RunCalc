package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaceNameTest {

    @Test
    void shouldHandleEmptyInput() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaceName(""))
                .withMessage("Pace name is empty");
    }

    @ParameterizedTest
    @ValueSource(strings = {"10T", "T*10", "T(10", "T 10", "T_10"})
    void shouldHandleRestrictedCharacters(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaceName(input))
                .withMessage("Pace name can contain only letters and numbers and can not start with a number");
    }

    @Test
    void shouldCreatePaceName() {
        PaceName paceName = new PaceName("T10");

        assertThat(paceName.getValue()).isEqualTo("T10");
    }
}
