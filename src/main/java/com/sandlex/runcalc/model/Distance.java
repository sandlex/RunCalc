package com.sandlex.runcalc.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Distance implements Measure {

    BigDecimal value;

    Distance(String input) {
        BigDecimal distance;
        try {
            distance = new BigDecimal(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Distance must be a number");
        }

        if (distance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Distance must be a positive value");
        }

        value = distance;
    }

}
