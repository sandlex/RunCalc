package com.sandlex.running.jd;

import java.util.SortedMap;

/**
 * author: Alexey Peskov
 */
public class Target {

    private SortedMap<String, Integer> paces;
    private float distance;
    private int time;

    public Target(SortedMap<String, Integer> paces) {
        this.paces = paces;
    }

    public void addPhase(String phase) {
        // determine pace
        // calculate distance (convert miles into meters, do not convert meters)
        // calculate time
        // handle " min " case
        // accumulate
    }

}
