package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DistanceTest {

    @Test
    void shouldHandleNonNumericValue() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Distance(Distance.System.METRIC, "1km"))
                .withMessage("Distance must be a number");
    }

    @Test
    void shouldHandleNegativeValue() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Distance(Distance.System.METRIC, "-42"))
                .withMessage("Distance must be a positive value");
    }

    @Test
    void shouldParseMetricAndGetImperialValue() {
        Distance distance = new Distance(Distance.System.METRIC, "10.6");

        assertThat(distance.getKilometers(Distance.System.IMPERIAL)).isEqualTo(16.96);
    }

    @Test
    void shouldParseImperialAndGetMetricValue() {
        Distance distance = new Distance(Distance.System.IMPERIAL, "10");

        assertThat(distance.getKilometers(Distance.System.METRIC)).isEqualTo(16);
    }

}
