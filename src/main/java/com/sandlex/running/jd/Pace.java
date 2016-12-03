package com.sandlex.running.jd;

/**
 * author: Alexey Peskov
 */
public class Pace {

    private String name;
    private int time; //seconds per 1000 meters

    public Pace(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
}
