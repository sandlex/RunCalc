package com.sandlex.runcalc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class Estimation {

    @Getter
    long seconds;
    @Getter
    double distance;

    void addSeconds(long increment) {
        this.seconds += increment;
    }

    void addDistance(double increment) {
        this.distance += increment;
    }

    public String getFormattedTime() {
        long sec = seconds;
        long hours = TimeUnit.SECONDS.toHours(sec);
        sec -= TimeUnit.HOURS.toSeconds(hours);
        long minutes = TimeUnit.SECONDS.toMinutes(sec);
        sec -= TimeUnit.MINUTES.toSeconds(minutes);

        return String.format("%02d:%02d:%02d", hours, minutes, sec);
    }

    @Override
    public String toString() {
        return String.format("Estimated distance - %.2f, time - %s", distance, getFormattedTime());
    }
}
