package com.guigarage.lessfx.converters;

import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class CeilConverter extends StyleConverter<String, Integer> {
    public CeilConverter() {

    }

    @Override
    public Integer convert(ParsedValue<String, Integer> value, Font font) {
        Pattern pattern = Pattern.compile("^ceil\\((-?[0-9]*.?[0-9]*)\\)$");
        Matcher matcher = pattern.matcher(value.getValue());
        if (matcher.find()) {
            Number val;
            if (matcher.group(1).equals("")) {
                return null;
            } else {
                val = new Double(matcher.group(1));
            }
            // LessCSS reference says that "ceil()" is returning an integer unlike Java
            return (int)Math.ceil(val.doubleValue());
        }
        return null;
    }
}
