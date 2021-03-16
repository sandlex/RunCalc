package com.sandlex.runcalc.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DistanceTest {

    @Test
    void shouldHandleNonNumericValue() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Distance("1km"))
                .withMessage("Distance must be a number");
    }

    @Test
    void shouldHandleNegativeValue() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Distance("-42"))
                .withMessage("Distance must be a positive value");
    }

    @Test
    void shouldParse() {
        Distance distance = new Distance("10.4");

        assertThat(distance.getValue()).isEqualTo(BigDecimal.valueOf(10.4));
    }

}
