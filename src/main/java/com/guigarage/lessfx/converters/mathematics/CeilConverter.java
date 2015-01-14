package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class CeilConverter extends LessStyleConverter<String, Integer> {
    public CeilConverter() {
        super();
    }

    @Override
    public Integer convert(ParsedValue<String, Integer> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), "^ceil\\((-?[0-9]*.?[0-9]*)\\)$");
        Number val;

        // Either nonsensical string (matcher == null) or empty parameter list (group(1) is an empty string)
        if (matcher == null || matcher.group(1).equals("")) {
            return null;
        } else {
            val = new Double(matcher.group(1));
        }
        // LessCSS reference says that "ceil()" is returning an integer unlike Java
        return (int)Math.ceil(val.doubleValue());
    }
}
