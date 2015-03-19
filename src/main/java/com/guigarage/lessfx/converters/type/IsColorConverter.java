package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-02
 */
public class IsColorConverter extends LessStyleConverter<String, Boolean> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^iscolor\\((.*)\\)$";
    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final IsColorConverter INSTANCE = new IsColorConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static IsColorConverter getInstance() {
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
        Matcher matcher = getMatcher(value.getValue(), REGEX1);

        if (matcher == null) {
            return false;
        }

        try {
            if (Color.web(matcher.group(1)) != null) {
                return true;
            } else {
                matcher = getMatcher(matcher.group(1), REGEX2);
                return matcher != null;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
