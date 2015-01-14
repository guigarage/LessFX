package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author  Robin Küster
 * @since   2015-01-11
 */
public class SinusConverter extends LessStyleConverter<String, Number> {
    public SinusConverter() {
        super();
    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = getMatcher(value.getValue(), "^sin\\((-?[0-9]*\\.?[0-9]*)(deg|grad)?\\)$");

        // either nonsensical input (matcher == null) or parameter list empty
        if (matcher == null || matcher.group(1).equals("")) {
            return null;
        }
        Double val = null;

        // no unit (radiant by default)
        if (matcher.group(2) == null) {
            val = Math.sin(Double.valueOf(matcher.group(1)));
        } else if (matcher.group(2).equals("deg")) { // degrees
            val = Math.sin(Double.valueOf(matcher.group(1)) * (Math.PI / 180));
        } else if (matcher.group(2).equals("grad")) { // gradient
            val = Math.sin(Double.valueOf(matcher.group(1)) * (Math.PI / 200));
        }
        return val;
    }
}
