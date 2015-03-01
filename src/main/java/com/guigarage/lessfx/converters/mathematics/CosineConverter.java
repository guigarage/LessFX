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
    private final static String REGEX = "^cos\\((-?[0-9]+\\.?[0-9]*)(deg|grad)?\\)$";

    private static class Holder {
        static final CosineConverter INSTANCE = new CosineConverter();
    }

    public static LessStyleConverter<String, Number> getInstance() {
        return Holder.INSTANCE;
    }

    private CosineConverter() {

    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);

        // nonsensical input
        if (matcher == null) {
            return null;
        }
        Double val = null;

        // no units or rad
        if (matcher.group(2) == null || matcher.group(2).equals("rad")) {
            val = Math.cos(Double.valueOf(matcher.group(1)));
        } else if (matcher.group(2).equals("deg")) { // degrees
            val = Math.cos(Double.valueOf(matcher.group(1)) * (Math.PI / 180));
        } else if (matcher.group(2).equals("grad")) { // gradient
            val = Math.cos(Double.valueOf(matcher.group(1)) * (Math.PI / 200));
        } else if (matcher.group(2).equals("turn")) {
            val = Math.cos(Double.valueOf(matcher.group(1)) * (2 * Math.PI));
        }
        return val;
    }
}
