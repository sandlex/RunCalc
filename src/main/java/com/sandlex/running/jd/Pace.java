package com.sandlex.running.jd;

/**
 * author: Alexey Peskov
 */
public enum Pace {

    E(4.45f),
    L(4.45f),
    M(4.14f),
    T(4.00f),
    I(3.41f),
    H(3.41f),
    R(3.25f),
    jg(5.00f),
    rest(5.20f);

    private float pace;

    Pace(float pace) {
        this.pace = pace;
    }

    public float getPace() {
        return pace;
    }
}
