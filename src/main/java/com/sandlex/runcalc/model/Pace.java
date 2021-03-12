package com.sandlex.runcalc.model;

import lombok.Value;

@Value
public class Pace {

    private static final String SEPARATOR = "=";
    private static final String FORMAT = "pace=mm:ss";

    PaceName paceName;
    PaceValue paceValue;

    Pace(String input) {
        if (!input.contains(SEPARATOR)) {
            throw new IllegalArgumentException("Cannot parse pace: " + input + ". Expected format: " + FORMAT);
        }

        String[] parts = input.split(SEPARATOR);
        paceName = new PaceName(parts[0]);
        paceValue = new PaceValue(parts[1]);
    }

}
