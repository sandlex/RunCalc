package com.sandlex.runcalc.model;

import lombok.Value;

@Value
public class Distance implements Measure {

    double value;

    Distance(String input) {
        double distance;
        try {
            distance = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Distance must be a number");
        }

        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be a positive value");
        }

        value = distance;
    }

}
