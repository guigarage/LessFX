package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author  Robin Küster
 * @since   2015-01-11
 */
public class SinusConverter extends LessStyleConverter<String, Number> {
    private final static String REGEX = "^sin\\((-?[0-9]+\\.?[0-9]*)(deg|grad|rad|turn)?\\)$";

    private static class Holder {
        static final SinusConverter INSTANCE = new SinusConverter();
    }

    public static SinusConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = getMatcher(value.getValue(), REGEX);

        // nonsensical
        if (matcher == null) {
            return null;
        }
        Double val = null;

        // no unit (radiant by default)
        if (matcher.group(2) == null || matcher.group(2).equals(SizeUnits.RAD.toString())) {
            val = Math.sin(Double.valueOf(matcher.group(1)));
        } else if (matcher.group(2).equals(SizeUnits.DEG.toString())) { // degrees
            val = Math.sin(Double.valueOf(matcher.group(1)) * (Math.PI / 180));
        } else if (matcher.group(2).equals(SizeUnits.GRAD.toString())) { // gradient
            val = Math.sin(Double.valueOf(matcher.group(1)) * (Math.PI / 200));
        } else if (matcher.group(2).equals(SizeUnits.TURN.toString())) {
            val = Math.sin(Double.valueOf(matcher.group(1)) * (2 * Math.PI));
        }
        return val;
    }
}
