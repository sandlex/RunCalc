package com.sandlex.running.jd.model;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
class Schema {

    List<Calculable> phases = new ArrayList<>();

    Schema(String input) {
        checkNestedRepetitions(input);
    }

    private void checkNestedRepetitions(String input) {
        int level = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                level++;
                if (level > 1) {
                    throw new IllegalArgumentException("Schema cannot contain nested repetitions");
                }
            } else if (ch == ')' && level == 0) {
                throw new IllegalArgumentException("Something is wrong with schema, check parenthesis");
            }
        }
    }

}
