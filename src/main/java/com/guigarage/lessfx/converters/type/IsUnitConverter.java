package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-18
 */
public class IsUnitConverter extends LessStyleConverter<String, Boolean> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^isunit\\(-?[0-9]*\\.?[0-9]*(";

    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "),\\s((?:\\\"";

    /**
     * 3rd part of the regular expression to parse the function call.
     */
    private final static String REGEX3 = "\\\")|(?:\\'";

    /**
     * 4th part of the regular expression to parse the function call.
     */
    private final static String REGEX4 = "\\\')|(?:";

    /**
     * 5th part of the regular expression to parse the function call.
     */
    private final static String REGEX5 = "))\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final IsUnitConverter INSTANCE = new IsUnitConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static IsUnitConverter getInstance() {
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
    public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1 + this.getUnitRegex() + REGEX2 + this.getUnitRegex() + REGEX3 + this.getUnitRegex() + REGEX4 + this.getUnitRegex() + REGEX5);

        // nonsensical input
        if (matcher == null) {
            return false;
        }

        // strip off " and ' as well as comparing both units in lower case
        return matcher.group(1).toLowerCase().equals(matcher.group(2).replaceAll("\"", "").replaceAll("\'", "").toLowerCase());
    }
}
