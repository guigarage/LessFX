package com.guigarage.lessfx.converters;

import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  Robin Küster
 * @since   2015-01-11
 */
public class SinusConverter extends StyleConverter<String, Number> {
    public SinusConverter() {

    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Pattern pattern = Pattern.compile("^sin\\((-?[0-9]*\\.?[0-9]*)(deg|grad)?\\)$");
        Matcher matcher = pattern.matcher(value.getValue());
        if (matcher.find()) {
            if (matcher.group(1).equals("")) {
                return null;
            }
            Double val = null;
            if (matcher.group(2) == null) {
                val = Math.sin(new Double(matcher.group(1)));
            } else if (matcher.group(2).equals("deg")) {
                val = Math.sin(new Double(matcher.group(1)) * (Math.PI / 180));
            } else if (matcher.group(2).equals("grad")) {
                val = Math.sin(new Double(matcher.group(1)) * (Math.PI / 200));
            }
            return val;
        }
        return null;
    }
}
