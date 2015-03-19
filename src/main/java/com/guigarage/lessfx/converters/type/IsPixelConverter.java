package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-02
 */
public class IsPixelConverter extends LessStyleConverter<String, Boolean> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^ispixel\\(\\-?[0-9]*\\.?[0-9]*px\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final IsPixelConverter INSTANCE = new IsPixelConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static IsPixelConverter getInstance() {
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
        return getMatcher(value.getValue(), REGEX) != null;
    }
}
