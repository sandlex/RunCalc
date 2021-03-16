package com.sandlex.runcalc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class Estimation {

    @Getter
    int seconds;

    BigDecimal distance = BigDecimal.ZERO;

    void addSeconds(int increment) {
        this.seconds += increment;
    }

    void addDistance(BigDecimal increment) {
        this.distance = this.distance.add(increment);
    }

    public String getFormattedTime() {
        long sec = seconds;
        long hours = TimeUnit.SECONDS.toHours(sec);
        sec -= TimeUnit.HOURS.toSeconds(hours);
        long minutes = TimeUnit.SECONDS.toMinutes(sec);
        sec -= TimeUnit.MINUTES.toSeconds(minutes);

        return String.format("%02d:%02d:%02d", hours, minutes, sec);
    }

    public BigDecimal getDistance() {
        return distance.setScale(3, RoundingMode.UP);
    }

    @Override
    public String toString() {
        return String.format("Estimated distance - %.3f, time - %s", getDistance(), getFormattedTime());
    }
}
