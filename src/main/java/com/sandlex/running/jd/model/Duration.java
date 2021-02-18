package com.sandlex.running.jd.model;

import lombok.Value;

@Value
class Duration {

    private static final String SEPARATOR = ":";
    private static final String FORMAT = "mm:ss";

    int hours;
    int minutes;
    int seconds;

    Duration(String input) {
        String[] parts = input.split(SEPARATOR);
        int minutesIndex;
        int secondsIndex;
        if (parts.length == 3) {
            hours = Integer.parseInt(parts[0]);
            if (hours < 0 || hours >= 60) {
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
        if (minutes < 0 || minutes >= 60) {
            throw new IllegalArgumentException("Incorrect minutes value: " + minutes + ". Expected value 0..59");
        }
        seconds = Integer.parseInt(parts[secondsIndex]);
        if (seconds < 0 || seconds >= 60) {
            throw new IllegalArgumentException("Incorrect seconds value: " + seconds + ". Expected value 0..59");
        }

    }
}
