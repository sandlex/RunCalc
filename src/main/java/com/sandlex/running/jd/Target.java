package com.sandlex.running.jd;

import java.util.List;

/**
 * author: Alexey Peskov
 */
public class Target {

    private List<Pace> paces;
    private float distance;
    private int time;

    public Target(List<Pace> paces) {
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
