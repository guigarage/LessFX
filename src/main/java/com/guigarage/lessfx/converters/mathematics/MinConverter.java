package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-12
 */
public class MinConverter extends LessStyleConverter<String, Number> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^min\\((.+)\\)$";

    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "^(\\-?[0-9]+\\.*[0-9]*)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final MinConverter INSTANCE = new MinConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static MinConverter getInstance() {
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
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1);

        if (matcher == null) {
            return null;
        }

        String values[] = matcher.group(1).replaceAll("\\s", "").split(",");

        double min = Double.POSITIVE_INFINITY;

        for (String s : values) {
            matcher = this.getMatcher(s, REGEX2);

            if (matcher == null) {
                return null;
            }

            if (Double.parseDouble(s) < min) {
                min = Double.parseDouble(s);
            }
        }

        return min;
    }
}
