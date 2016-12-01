package com.sandlex.running.jd;

/**
 * author: Alexey Peskov
 */
public class Pace {

    private String name;
    private int time;

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
