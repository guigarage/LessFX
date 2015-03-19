package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-02-14
 */
public class ModConverter extends LessStyleConverter<String, Size> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^mod\\((-?[0-9]*\\.?[0-9]*)";

    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "\\,\\s?(-?[0-9]*\\.?[0-9]*)";

    /**
     * 3rd part of the regular expression to parse the function call.
     */
    private final static String REGEX3 = "\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final ModConverter INSTANCE = new ModConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static ModConverter getInstance() {
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
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1+this.getUnitRegex()+REGEX2+this.getUnitRegex()+REGEX3);

        if (matcher == null) {
            return null;
        }

        if (Double.valueOf(matcher.group(3)) == 0) {
            return null;
        }

        Double val = Double.valueOf(matcher.group(1)) % Double.valueOf(matcher.group(3));
        return new Size(val, this.getUnit(matcher.group(2)));
    }
}
