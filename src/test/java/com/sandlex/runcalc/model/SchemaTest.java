package com.sandlex.runcalc.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SchemaTest {

    @Test
    void shouldHandleEmptySchema() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Schema(""))
                .withMessage("Schema can't be empty");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,0T", "T=10", "T;10"})
    void shouldDetectRestrictedCharacters(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Schema(input))
                .withMessage("Schema contains restricted characters");
    }

    @Test
    void shouldParseNonRepeatedPhases() {
        Schema schema = new Schema("2.1E + 01:30:00MarathonPace");

        assertThat(schema.getPhases()).hasSize(2);
    }

    @Test
    void shouldParseRepeatedPhases() {
        Schema schema = new Schema("2.1E + 3 * (2E + 3E) + 01:30:00MarathonPace");

        assertThat(schema.getPhases()).hasSize(3);
    }

}
