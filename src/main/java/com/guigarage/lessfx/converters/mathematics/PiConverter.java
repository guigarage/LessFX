package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-01-11
 */
public class PiConverter extends LessStyleConverter<String, Number> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "pi()";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final PiConverter INSTANCE = new PiConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static PiConverter getInstance() {
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
        if (value.getValue().equals(REGEX)) {
            return Math.PI;
        } else {
            return null;
        }
    }
}
