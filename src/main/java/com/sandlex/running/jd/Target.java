package com.sandlex.running.jd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * author: Alexey Peskov
 */
public class Target {

    private List<Target> children;
    private float distance;
    private int time;

    public Target() {
        this.children = new ArrayList<Target>();
    }

    public Target(String str) {
        if (str.contains(" min ")) {
            time = Integer.valueOf(str.substring(0, str.indexOf(" min ")));
            distance = time * Pace.valueOf(str.substring(str.indexOf(" min ") + 5, str.length() - 1)).getPace();
        }
    }

    public void addChild(Target child) {
        distance += child.distance;
        time += child.time;
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
