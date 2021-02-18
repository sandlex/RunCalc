package com.sandlex.running.jd.model;

import lombok.Value;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

@Value
class PaceName {

    private static final String RESTRICTED_CHARACTERS = "*( )@'\"";
    String value;

    PaceName(String input) {

        if (StringUtils.isEmpty(input)) {
            throw new IllegalArgumentException("Pace name is empty");
        }

        if (Pattern.matches("\\d", String.valueOf(input.charAt(0)))) {
            throw new IllegalArgumentException("Pace name cannot start with a number");
        }

        if (StringUtils.containsAny(input, RESTRICTED_CHARACTERS)) {
            throw new IllegalArgumentException("Pace name cannot contain restricted characters: " + RESTRICTED_CHARACTERS);
        }

        value = input;
    }
}
