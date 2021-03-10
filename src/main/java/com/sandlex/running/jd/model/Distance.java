package com.sandlex.running.jd.model;

import lombok.RequiredArgsConstructor;

public class Distance implements Measure {

    @RequiredArgsConstructor
    public enum System {
        METRIC(1),
        IMPERIAL(1.6);

        private final double ratio;
    }

    private final double kilometers;

    Distance(System system, String input) {
        double distance;
        try {
            distance = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Distance must be a number");
        }

        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be a positive value");
        }

        kilometers = distance * system.ratio;
    }

    double getValue(System system) {
        return kilometers * system.ratio;
    }
}
