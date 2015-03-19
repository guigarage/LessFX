package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-02-06
 */
public class ArcSinusConverter extends LessStyleConverter<String, Number> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^asin\\((-?[0-9]*\\.?[0-9]*)\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final ArcSinusConverter INSTANCE = new ArcSinusConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static ArcSinusConverter getInstance() {
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
        Matcher matcher = getMatcher(value.getValue(), REGEX);

        // nonsensical input
        if (matcher == null) {
            return null;
        } else if (matcher.group(1).equals("")) {
            return null;
        }

        return Math.asin(Double.parseDouble(matcher.group(1)));
    }
}
