package com.sandlex.running.jd;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author: Alexey Peskov
 */
class Activity {

    private final String pacesCfg;
    private final String schemaCfg;
    private List<Pace> paces;
    private List<String> schema;

    Activity(String pacesCfg, String schemaCfg) {
        this.pacesCfg = pacesCfg;
        this.schemaCfg = schemaCfg;
    }

    Target calculate() {
        rebuildPaces();
        rebuildSchema();

        Target target = new Target(paces);
        for (String phase : schema) {
            target.addPhase(phase);
        }

        return target;
    }

    List<Pace> rebuildPaces() {
        paces = new ArrayList<>();
        String[] parts = pacesCfg.split(",");
        for (String part : parts) {
            if (!part.contains("=")) {
                throw new IllegalArgumentException("Cannot parse pace: " + part);
            }
            String[] pace = part.split("=");
            paces.add(new Pace(pace[0], getValueInSeconds(pace[1])));
        }

        Comparator<Pace> lengthComparator = (p1, p2) -> Integer.compare(p2.getName().length(), p1.getName().length());
        paces.sort(lengthComparator);

        return Collections.unmodifiableList(paces);
    }

    Collection<String> rebuildSchema() {
        schema = new ArrayList<>();

        String[] parts = schemaCfg.split("\\+");
        for (String part : parts) {
            if (part.trim().contains("x")) {
                String[] repeated = part.trim().split("x");
                for (int i = 0; i < Integer.valueOf(repeated[0].trim()); i++) {
                    if (repeated[1].trim().contains("w/")) {
                        String[] combined = repeated[1].trim().split("w/");
                        schema.add(combined[0].trim());
                        schema.add(combined[1].trim());
                    } else {
                        schema.add(repeated[1].trim());
                    }
                }
            } else {
                schema.add(part.trim());
            }
        }

        return Collections.unmodifiableCollection(schema);
    }

    private int getValueInSeconds(String pace) {
        if (!pace.contains(":")) {
            throw new IllegalArgumentException("Cannot parse pace value: " + pace);
        }

        String[] parts = pace.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);

        return minutes * 60 + seconds;
    }
}
