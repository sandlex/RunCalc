package com.sandlex.running.jd;

import java.util.Objects;

/**
 * author: Alexey Peskov
 */
public class Target {

    private int distance;
    private int time;

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Target{" +
                "distance=" + distance +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return Objects.equals(distance, target.distance) &&
                Objects.equals(time, target.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, time);
    }
}
