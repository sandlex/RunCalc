package com.sandlex.running.jd.model;

import lombok.Value;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

@Value
class PaceName {

    String value;

    PaceName(String input) {

        if (StringUtils.isEmpty(input)) {
            throw new IllegalArgumentException("Pace name is empty");
        }

        if (!Pattern.matches("^[^\\d][a-zA-Z0-9]+$", input)) {
            throw new IllegalArgumentException("Pace name can contain only letters and numbers and can not start with a number");
        }

        value = input;
    }
}
