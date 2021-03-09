package com.sandlex.running.jd;


import com.sandlex.running.jd.model.PaceBlock;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
class Activity {

    private final String pacesCfg;
    private final String schemaCfg;
    private PaceBlock paceBlock;
    private List<String> schema;

    Target calculate() {
        paceBlock = new PaceBlock(pacesCfg);
        rebuildSchema();

        Target target;// = new Target(paces);
//        schema.forEach(target::addPhase);

        return null;//target;
    }


    Collection<String> rebuildSchema() {
        schema = new ArrayList<>();

        String[] parts = schemaCfg.split("\\+");
        for (String part : parts) {
            String cleanPart = part.trim();
            if (cleanPart.contains("x")) {
                String[] repeated = cleanPart.split("x");
                for (int i = 0; i < Integer.parseInt(repeated[0].trim()); i++) {
                    String clean1 = repeated[1].trim();
                    if (clean1.contains("w/")) {
                        String[] combined = clean1.split("w/");
                        schema.add(combined[0].trim());
                        schema.add(combined[1].trim());
                    } else {
                        schema.add(clean1);
                    }
                }
            } else {
                schema.add(cleanPart);
            }
        }

        return Collections.unmodifiableCollection(schema);
    }

}
