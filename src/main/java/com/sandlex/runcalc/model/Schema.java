package com.sandlex.runcalc.model;

import com.sandlex.runcalc.SchemaParser;
import lombok.Value;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Value
public class Schema {

    List<Calculable> phases;

    public Schema(String input) {
        String schema = StringUtils.deleteWhitespace(input);

        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("Schema can't be empty");
        }

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
                    return new Phase(str);
                })
                .collect(Collectors.toList());
    }

    private boolean isRepetition(String input) {
        //TODO support 2*3E format or check that "*" is used only with conjunction of ( or )
        return input.contains("(");
    }

}
