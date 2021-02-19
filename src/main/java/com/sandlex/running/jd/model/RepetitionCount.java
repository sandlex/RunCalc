package com.sandlex.running.jd.model;

import lombok.Value;

@Value
class RepetitionCount {

    int value;

    RepetitionCount(String input) {
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid repetition count " + input);
        }
    }

}
