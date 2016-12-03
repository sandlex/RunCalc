package com.sandlex.running.jd;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * author: Alexey Peskov
 */
public class Target {

    private List<Pace> paces;
    private float distanceInMeters;
    private int timeInSeconds;

    public Target(List<Pace> paces) {
        this.paces = paces;
    }

    public float getDistance() {
        return distanceInMeters / 1000;
    }

    public String getTime() {
        int seconds = timeInSeconds;
        long hours = TimeUnit.SECONDS.toHours(seconds);
        seconds -= TimeUnit.HOURS.toSeconds(hours);
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        seconds -= TimeUnit.MINUTES.toSeconds(minutes);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return String.format("Estimated distance - %.2f km, time - %s", getDistance(), getTime());
    }

    void addPhase(String phase) {
        if (phase.contains("lesser of")) {
            Pace pace = getPace(phase.substring(0, phase.indexOf("=")).trim());
            String distanceStr = phase.substring(phase.indexOf("(") + 1, phase.indexOf("km")).trim();
            int meters;
            try {
                meters = Integer.valueOf(distanceStr) * 1000;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot parse distance value: " + distanceStr);
            }
            String timeStr = phase.substring(phase.indexOf("&") + 1, phase.indexOf("min")).trim();
            int seconds;
            try {
                seconds = Integer.valueOf(timeStr) * 60;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot parse time value: " + timeStr);
            }

            distanceInMeters += Math.min(meters, 1000 * seconds / pace.getTime());
            timeInSeconds += distanceInMeters * pace.getTime() / 1000;
        } else if (phase.contains("min")) {
            String[] parts = phase.split("min");
            int phaseTime;
            try {
                phaseTime = Integer.valueOf(parts[0].trim()) * 60;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot parse time value: " + parts[0].trim());
            }
            timeInSeconds += phaseTime;
            Pace pace = getPace(parts[1].trim());
            distanceInMeters += (phaseTime * 1000 / pace.getTime());
        } else {
            Pace pace = getPace(phase);
            String phaseDistanceStr = phase.substring(0, phase.length() - pace.getName().length()).trim();
            int phaseDistance;
            try {
                phaseDistance = Integer.valueOf(phaseDistanceStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot parse distance value: " + phaseDistanceStr);
            }
            if (phaseDistance < 100) {
                //miles to meters
                phaseDistance = phaseDistance * 1600;
            }
            distanceInMeters += phaseDistance;
            timeInSeconds += (pace.getTime() * phaseDistance / 1000);
        }
    }

    private Pace getPace(String phase) {
        for (Pace pace : paces) {
            if (phase.endsWith(pace.getName())) {
                return pace;
            }
        }

        throw new IllegalArgumentException("Cannot determine pace of: " + phase);
    }

}
