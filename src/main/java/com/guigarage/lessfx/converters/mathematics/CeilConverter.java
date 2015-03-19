package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-01-11
 */
public class CeilConverter extends LessStyleConverter<String, Integer> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^ceil\\((-?[0-9]+.?[0-9]*)\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final CeilConverter INSTANCE = new CeilConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static CeilConverter getInstance() {
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
    public Integer convert(ParsedValue<String, Integer> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);
        Number val;

        // nonsensical input
        if (matcher == null) {
            return null;
        } else {
            val = new Double(matcher.group(1));
        }
        // LessCSS reference says that "ceil()" is returning an integer unlike Java
        return (int)Math.ceil(val.doubleValue());
    }
}
