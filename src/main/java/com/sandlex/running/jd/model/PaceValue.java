package com.sandlex.running.jd.model;

import lombok.Value;

@Value
class PaceValue {

    private static final String SEPARATOR = ":";
    private static final String FORMAT = "mm:ss";

    int minutes;
    int seconds;

    PaceValue(String input) {
        if (!input.contains(SEPARATOR)) {
            throw new IllegalArgumentException("Cannot parse pace value: " + input + ". Expected format: " + FORMAT);
        }

        String[] parts = input.split(SEPARATOR);
        minutes = Integer.parseInt(parts[0]);
        if (minutes < 0 || minutes >= 60) {
            throw new IllegalArgumentException("Incorrect minutes value: " + minutes + ". Expected value 0..59");
        }
        seconds = Integer.parseInt(parts[1]);
        if (seconds < 0 || seconds >= 60) {
            throw new IllegalArgumentException("Incorrect seconds value: " + seconds + ". Expected value 0..59");
        }
    }
}
