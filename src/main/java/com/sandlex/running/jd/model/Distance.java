package com.sandlex.running.jd.model;

class Distance {

    enum System {
        METRIC(1),
        IMPERIAL(1.6);

        private final double ratio;

        System(double ratio) {
            this.ratio = ratio;
        }
    }

    private final double value;

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

        value = distance * system.ratio;
    }

    double getValue(System system) {
        return value * system.ratio;
    }
}
