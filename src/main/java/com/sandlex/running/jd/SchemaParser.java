package com.sandlex.running.jd;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class SchemaParser {

    public void checkNestedRoundBrackets(String input) {
        int level = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                level++;
                if (level > 1) {
                    throw new IllegalArgumentException("Nested round brackets not supported");
                }
            } else if (ch == ')') {
                if (level == 0) {
                    throw new IllegalArgumentException("Nested round brackets not supported");
                } else {
                    level--;
                }
            }
        }
    }

    public List<String> extractTopLevelSubstringsSeparatedByPlusSign(String input) {
        String schema = StringUtils.deleteWhitespace(input);
        List<String> secondLevelParts = new ArrayList<>();
        while (schema.contains("(")) {
            int startPos = schema.indexOf("(");
            while (schema.charAt(startPos) != '+' && startPos != 0) {
                startPos--;
            }
            if (startPos > 0) {
                startPos++;
            }
            int endPos = schema.indexOf(")");
            while (schema.charAt(endPos) != '+' && endPos != schema.length() - 1) {
                endPos++;
            }
            if (endPos == schema.length() - 1) {
                endPos++;
            }

            String secondLevelPart = schema.substring(startPos, endPos);
            secondLevelParts.add(secondLevelPart);
            schema = StringUtils.replaceOnce(schema, secondLevelPart, "");
        }

        List<String> result = new ArrayList<>(Arrays.asList(schema.split("\\+")));
        result.addAll(secondLevelParts);
        return result;
    }
}
