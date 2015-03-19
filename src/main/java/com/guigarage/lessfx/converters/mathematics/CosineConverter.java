package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author  Robin Küster
 * @version 1.0-SNAPSHOT
 * @since   2015-01-11
 */
public class CosineConverter extends LessStyleConverter<String, Number> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^cos\\((-?[0-9]+\\.?[0-9]*)(deg|grad)?\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final CosineConverter INSTANCE = new CosineConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static CosineConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts the given CSS function call to a Java object.
     *
     * @param value ParsedValue containing the function call
     * @param font Font used for functions that depend on the font size
     * @return The result of the function call as a Java object or null if function call failed
     */
    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);

        // nonsensical input
        if (matcher == null) {
            return null;
        }
        Double val = null;

        // no units or rad
        if (matcher.group(2) == null || matcher.group(2).equals(SizeUnits.RAD.toString())) {
            val = Math.cos(Double.valueOf(matcher.group(1)));
        } else if (matcher.group(2).equals(SizeUnits.DEG.toString())) { // degrees
            val = Math.cos(Double.valueOf(matcher.group(1)) * (Math.PI / 180));
        } else if (matcher.group(2).equals(SizeUnits.GRAD.toString())) { // gradient
            val = Math.cos(Double.valueOf(matcher.group(1)) * (Math.PI / 200));
        } else if (matcher.group(2).equals(SizeUnits.TURN.toString())) {
            val = Math.cos(Double.valueOf(matcher.group(1)) * (2 * Math.PI));
        }
        return val;
    }
}
