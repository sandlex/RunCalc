package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SchemaTest {

    @Test
    void shouldDetectIncorrectParenthesisOrder() {
        assertThatThrownBy(() -> new Schema("2E + 3 * )2E + 3E)"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Something is wrong with schema, check parenthesis");
    }

    @Test
    void shouldDetectNestedParenthesisOrder() {
        assertThatThrownBy(() -> new Schema("2E + 3 * (2E + 2 * (3E))"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Schema cannot contain nested repetitions");
    }

}
