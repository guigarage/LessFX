package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-02
 */
public class IsNumberConverter extends LessStyleConverter<String, Boolean> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^isnumber\\((\\-?[0-9]*(?:\\.[0-9]*)?)";

    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final IsNumberConverter INSTANCE = new IsNumberConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static IsNumberConverter getInstance() {
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
        return getMatcher(value.getValue(), REGEX1+getUnitRegex()+REGEX2) != null;
    }
}
