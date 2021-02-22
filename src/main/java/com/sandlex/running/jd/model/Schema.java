package com.sandlex.running.jd.model;

import com.sandlex.running.jd.SchemaParser;
import lombok.Value;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Value
class Schema {

    List<Calculable> phases;

    Schema(String input) {
        String schema = StringUtils.deleteWhitespace(input);
        // Following things should be done outside of this class
        SchemaParser.checkNestedRoundBrackets(schema);

        if (!Pattern.matches("^[a-zA-Z0-9()*.:+]+$", schema)) {
            throw new IllegalArgumentException("Schema contains restricted characters");
        }

        List<String> strings = SchemaParser.extractTopLevelSubstringsSeparatedByPlusSign(schema);
        phases = strings.stream()
                .map(str -> {
                    if (str.contains("(")) {
                        return new Repetition(str);
                    }
                    return new Phase(str, Distance.System.METRIC);
                })
                .collect(Collectors.toList());
    }

}
