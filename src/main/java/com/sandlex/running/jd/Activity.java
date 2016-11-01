package com.sandlex.running.jd;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Alexey Peskov
 */
public class Activity {

    private final String source;
    private List<String> schema;

    public Activity(String source) {
        this.source = source;
    }

    void rebuild() {
        schema = new ArrayList<String>();

        String[] parts = source.split("\\+");
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

        System.out.println(schema);
    }


    Target calculate() {
        Target target = new Target();


        return target;
    }
}
