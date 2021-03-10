package com.sandlex.running.jd.model;

import lombok.Value;

import java.util.regex.Pattern;

@Value
public class PaceValue {

    private static final String SEPARATOR = ":";
    private static final String FORMAT = "mm:ss";

    int minutes;
    int seconds;

    PaceValue(String input) {
        if (!Pattern.matches("^[0-9:]+$", input)) {
            throw new IllegalArgumentException("Pace value can contain only numbers and colon");
        }

        if (!input.contains(SEPARATOR)) {
            throw new IllegalArgumentException("Cannot parse pace value: " + input + ". Expected format: " + FORMAT);
        }

        String[] parts = input.split(SEPARATOR);
        minutes = Integer.parseInt(parts[0]);
        if (minutes >= 60) {
            throw new IllegalArgumentException("Incorrect minutes value: " + minutes + ". Expected value 0..59");
        }
        seconds = Integer.parseInt(parts[1]);
        if (seconds >= 60) {
            throw new IllegalArgumentException("Incorrect seconds value: " + seconds + ". Expected value 0..59");
        }
    }

}
