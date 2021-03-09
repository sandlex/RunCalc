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
                    if (isRepetition(str)) {
                        return new Repetition(str);
                    }
                    return new Phase(str, Distance.System.METRIC);
                })
                .collect(Collectors.toList());
    }

    private boolean isRepetition(String input) {
        //TODO support 2*3E format or check that "*" is used only with conjunction of ( or )
        return input.contains("(");
    }

}
