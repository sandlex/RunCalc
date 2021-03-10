package com.sandlex.running.jd.model;

import lombok.Value;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class Repetition implements Calculable {

    RepetitionCount repetitionCount;
    List<Phase> phases;

    public Repetition(String input) {
        String[] parts = input.split("\\*");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Incorrect repetition value" + input + ". Expected format: 3 * (2Easy + 01:00Rest)");
        }

        if (parts[0].contains("(")) {
            phases = getParsedPhases(parts[0]);
            repetitionCount = new RepetitionCount(parts[1]);
        } else {
            repetitionCount = new RepetitionCount(parts[0]);
            phases = getParsedPhases(parts[1]);
        }
    }

    private List<Phase> getParsedPhases(String input) {
        input = StringUtils.remove(input, "(");
        input = StringUtils.remove(input, ")");
        return Arrays.stream(input.split("\\+"))
                .map(str -> new Phase(str, Distance.System.METRIC))
                .collect(Collectors.toList());
    }

}
