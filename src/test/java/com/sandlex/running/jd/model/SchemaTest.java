package com.sandlex.running.jd.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SchemaTest {

    @Test
    void shouldDetectIncorrectParenthesisOrder() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Schema("2E + 3 * )2E + 3E)"))
                .withMessage("Something is wrong with schema, check parenthesis");
    }

    @Test
    void shouldDetectNestedParenthesisOrder() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Schema("2E + 3 * (2E + 2 * (3E))"))
                .withMessage("Schema cannot contain nested repetitions");
    }

}
