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

    void addPhase(String phase) {
        if (phase.contains("min")) {
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
