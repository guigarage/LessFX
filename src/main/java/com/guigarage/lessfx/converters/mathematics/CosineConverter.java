package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author  Robin Küster
 * @since   2015-01-11
 */
public class CosineConverter extends LessStyleConverter<String, Number> {
    public CosineConverter() {
        super();
    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), "^cos\\((-?[0-9]*\\.?[0-9]*)(deg|grad)?\\)$");

        // Either nonsensical input (matcher == null) or no numerical value
        if (matcher == null || matcher.group(1).equals("")) {
            return null;
        }
        Double val = null;

        // no units
        if (matcher.group(2) == null) {
            val = Math.cos(Double.valueOf(matcher.group(1)));
        } else if (matcher.group(2).equals("deg")) { // degrees
            val = Math.cos(Double.valueOf(matcher.group(1)) * (Math.PI / 180));
        } else if (matcher.group(2).equals("grad")) { // gradient
            val = Math.cos(Double.valueOf(matcher.group(1)) * (Math.PI / 200));
        }
        return val;
    }
}
