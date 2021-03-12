package com.sandlex.runcalc.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DurationTest {

    @Test
    void shouldHandleRestrictedCharacter() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Duration("-03:33"))
                .withMessage("Duration can contain only numbers and colons");
    }

    @Test
    void shouldHandleInvalidFormatTooShort() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Duration("0333"))
                .withMessage("Cannot parse duration: 0333. Expected format: hh:mm:ss or mm:ss");
    }

    @Test
    void shouldHandleInvalidFormatTooLong() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Duration("03:33:33:33"))
                .withMessage("Cannot parse duration: 03:33:33:33. Expected format: hh:mm:ss or mm:ss");
    }

    @Test
    void shouldHandleHighNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Duration("03:63"))
                .withMessage("Incorrect seconds value: 63. Expected value 0..59");
    }

    @Test
    void shouldHandleValidDoubleNumber() {
        Duration duration = new Duration("05:30");

        assertThat(duration.getHours()).isZero();
        assertThat(duration.getMinutes()).isEqualTo(5);
        assertThat(duration.getSeconds()).isEqualTo(30);
    }

    @Test
    void shouldHandleValidTripleNumber() {
        Duration duration = new Duration("002:30:00");

        assertThat(duration.getHours()).isEqualTo(2);
        assertThat(duration.getMinutes()).isEqualTo(30);
        assertThat(duration.getSeconds()).isZero();
    }

}
