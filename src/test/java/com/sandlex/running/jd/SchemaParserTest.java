package com.sandlex.running.jd;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SchemaParserTest {

    @Test
    void shouldDetectIncorrectRoundBracketsOrder() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SchemaParser.checkNestedRoundBrackets("2E + 3 * )2E + 3E)"))
                .withMessage("Nested round brackets not supported");
    }

    @Test
    void shouldDetectNestedRoundBrackets() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SchemaParser.checkNestedRoundBrackets("2E + 3 * (2E + 2 * (3E))"))
                .withMessage("Nested round brackets not supported");
    }

    @Test
    void shouldExtractSubstringsCase1() {
        List<String> strings = SchemaParser.extractTopLevelSubstringsSeparatedByPlusSign("2E + 3 * (2E)");

        assertThat(strings).containsExactly("2E", "3*(2E)");
    }

    @Test
    void shouldExtractSubstringsCase2() {
        List<String> strings = SchemaParser.extractTopLevelSubstringsSeparatedByPlusSign("2E + 3 * (2E + 3E) + 2E");

        assertThat(strings).containsExactly("2E", "2E", "3*(2E+3E)");
    }

    @Test
    void shouldExtractSubstringsCase3() {
        List<String> strings = SchemaParser.extractTopLevelSubstringsSeparatedByPlusSign("3 * (2E + 3rest) + 2E");

        assertThat(strings).containsExactly("2E", "3*(2E+3rest)");
    }

    @Test
    void shouldExtractSubstringsCase5() {
        List<String> strings = SchemaParser.extractTopLevelSubstringsSeparatedByPlusSign("2E + 3 * (2E + 3rest) + (2E + 3Q) * 7");

        assertThat(strings).containsExactly("2E", "3*(2E+3rest)", "(2E+3Q)*7");
    }

}
