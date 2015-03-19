package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-12
 */
public class UnitConverter extends LessStyleConverter<String, Size> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^unit\\((\\-?[0-9]+\\.?[0-9]*)[a-zA-Z]*,\\s";

    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final UnitConverter INSTANCE = new UnitConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static UnitConverter getInstance() {
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
    public Size convert(ParsedValue<String, Size> value, Font font) {
        String finalValue;

        // SizeUnits uses px for unit "null". This makes parsing for 1 parameter unnecessarily
        if (!value.getValue().contains(",")) {
            finalValue = value.getValue().substring(0, value.getValue().length() - 1) + ", px)";
        } else {
            finalValue = value.getValue();
        }

        Matcher matcher = getMatcher(finalValue, REGEX1 + getUnitRegex() + REGEX2);

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        return new Size(Double.parseDouble(matcher.group(1)), SizeUnits.valueOf(matcher.group(2).toUpperCase()));
    }
}
