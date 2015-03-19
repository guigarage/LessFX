package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-01
 */
public class GetUnitConverter extends LessStyleConverter<String, SizeUnits> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^get\\-unit\\((?:\\-?[0-9]*(?:\\.[0-9])?)([a-zA-Z]*)\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final GetUnitConverter INSTANCE = new GetUnitConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static GetUnitConverter getInstance() {
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
    public SizeUnits convert(ParsedValue<String, SizeUnits> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);


        // nonsensical input
        if (matcher == null) {
            return null;
        }

        return this.getUnit(matcher.group(1));
    }
}
