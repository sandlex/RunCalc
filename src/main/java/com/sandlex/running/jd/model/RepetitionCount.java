package com.sandlex.running.jd.model;

import lombok.Value;

@Value
class RepetitionCount {

    int value;

    RepetitionCount(String input) {
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Repetition count must be an integer number: " + input);
        }

        if (value <= 0) {
            throw new IllegalArgumentException("Repetition count must be a positive number: " + input);
        }
    }

}
