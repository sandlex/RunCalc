package com.sandlex.running.jd.model;

import com.sandlex.running.jd.SchemaParser;
import lombok.Value;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Value
class Schema {

    List<Calculable> phases = new ArrayList<>();

    Schema(String input) {
        String schema = StringUtils.deleteWhitespace(input);
        // Following things should be done outside of this class
        SchemaParser.checkNestedRoundBrackets(schema);
        SchemaParser.extractTopLevelSubstringsSeparatedByPlusSign(schema);
    }

}
