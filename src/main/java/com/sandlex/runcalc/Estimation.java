package com.sandlex.runcalc;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * This class represents a workout calculation result. Includes calculated calculated distance and duration and
 * some additional convenience methods for retrieving them.
 */
@RequiredArgsConstructor
public class Estimation {

    int seconds;
    BigDecimal distance = BigDecimal.ZERO;

    void addSeconds(int increment) {
        this.seconds += increment;
    }

    void addDistance(BigDecimal increment) {
        this.distance = this.distance.add(increment);
    }

    /**
     * Returns calculated workout duration as a formatted string.
     * @return string in a format: hh:mm:ss
     */
    public String getFormattedTime() {
        long sec = seconds;
        long hours = TimeUnit.SECONDS.toHours(sec);
        sec -= TimeUnit.HOURS.toSeconds(hours);
        long minutes = TimeUnit.SECONDS.toMinutes(sec);
        sec -= TimeUnit.MINUTES.toSeconds(minutes);

        return String.format("%02d:%02d:%02d", hours, minutes, sec);
    }

    /**
     * Returns calculated workout duration as a number of seconds.
     * @return integer number of seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Returns calculated workout distance as a BigDecimal number of kilometers (miles), for example: 42.195 - 42 kilometers 195 meters.
     * @return BigDecimal with precision 3 numbers after the decimal point, 4th position is rounded up
     */
    public BigDecimal getDistance() {
        return distance.setScale(3, RoundingMode.UP);
    }

    /**
     * Returns string representation of the estimation.
     * @return string in a format: "Estimated distance - 42.195, time - 02:54:32"
     */
    @Override
    public String toString() {
        return String.format("Estimated distance - %.3f, time - %s", getDistance(), getFormattedTime());
    }
}
