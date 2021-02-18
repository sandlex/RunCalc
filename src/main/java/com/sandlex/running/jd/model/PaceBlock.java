package com.sandlex.running.jd.model;

import lombok.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Value
public class PaceBlock {

    public static final String SEPARATOR = ",";
    public static final String FORMAT = "pace1=mm:ss,pace2=mm:ss";

    List<Pace> paces;

    public PaceBlock(String input) {
        if (!input.contains(SEPARATOR)) {
            throw new IllegalArgumentException("Cannot parse pace block: " + input + ". Expected format: " + FORMAT);
        }

        paces = new ArrayList<>();
        String[] parts = input.split(SEPARATOR);
        if (parts.length == 0) {
            throw new IllegalArgumentException("Paces block doesn't contain any values. Expected format: " + FORMAT);
        }

        Set<PaceName> usedPaceNames = new HashSet<>();
        for (String part : parts) {
            Pace pace = new Pace(part);
            if (usedPaceNames.contains(pace.getPaceName())) {
                throw new IllegalArgumentException("Pace used more than once: " + pace.getPaceName().getValue());
            }
            paces.add(pace);
            usedPaceNames.add(pace.getPaceName());
        }
    }

}
