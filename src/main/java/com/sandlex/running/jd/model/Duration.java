package com.sandlex.running.jd.model;

import lombok.Value;

import java.util.regex.Pattern;

@Value
class Duration implements Measure {

    private static final String SEPARATOR = ":";
    private static final String FORMAT = "hh:mm:ss or mm:ss";

    int hours;
    int minutes;
    int seconds;

    Duration(String input) {
        if (!Pattern.matches("^[0-9:]+$", input)) {
            throw new IllegalArgumentException("Duration can contain only numbers and colons");
        }

        String[] parts = input.split(SEPARATOR);
        if (parts.length < 2 || parts.length > 3) {
            throw new IllegalArgumentException("Cannot parse duration: " + input + ". Expected format: " + FORMAT);
        }

        int minutesIndex;
        int secondsIndex;
        if (parts.length == 3) {
            hours = Integer.parseInt(parts[0]);
            if (hours >= 60) {
                throw new IllegalArgumentException("Incorrect hours value: " + hours + ". Expected value 0..59");
            }
            minutesIndex = 1;
            secondsIndex = 2;
        } else {
            hours = 0;
            minutesIndex = 0;
            secondsIndex = 1;
        }
        minutes = Integer.parseInt(parts[minutesIndex]);
        if (minutes >= 60) {
            throw new IllegalArgumentException("Incorrect minutes value: " + minutes + ". Expected value 0..59");
        }
        seconds = Integer.parseInt(parts[secondsIndex]);
        if (seconds >= 60) {
            throw new IllegalArgumentException("Incorrect seconds value: " + seconds + ". Expected value 0..59");
        }
    }

}
