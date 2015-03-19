package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-02-06
 */
public class AbsConverter extends LessStyleConverter<String, Size> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^abs\\((-?[0-9]+\\.?[0-9]*)";

    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final AbsConverter INSTANCE = new AbsConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static AbsConverter getInstance() {
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
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1+this.getUnitRegex()+REGEX2);

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        // first parameter: value. Second parameter: unit
        return new Size(Math.abs(Double.valueOf(matcher.group(1))), this.getUnit(matcher.group(2)));
    }
}
