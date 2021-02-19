package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DistanceTest {

    @Test
    void shouldHandleNonNumericValue() {
        assertThatThrownBy(() -> new Distance(Distance.System.METRIC, "1km"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Distance must be a number");
    }

    @Test
    void shouldHandleNegativeValue() {
        assertThatThrownBy(() -> new Distance(Distance.System.METRIC, "-42"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Distance must be a positive value");
    }

    @Test
    void shouldParseMetricAndGetImperialValue() {
        Distance distance = new Distance(Distance.System.METRIC, "10.6");

        assertThat(distance.getValue(Distance.System.IMPERIAL)).isEqualTo(16.96);
    }

    @Test
    void shouldParseImperialAndGetMetricValue() {
        Distance distance = new Distance(Distance.System.IMPERIAL, "10");

        assertThat(distance.getValue(Distance.System.METRIC)).isEqualTo(16);
    }

}
