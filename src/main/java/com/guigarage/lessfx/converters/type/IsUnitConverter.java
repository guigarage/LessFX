package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-18
 */
public class IsUnitConverter extends LessStyleConverter<String, Boolean> {
    private final static String REGEX1 = "^isunit\\(-?[0-9]*\\.?[0-9]*(";
    private final static String REGEX2 = "),\\s((?:\\\"";
    private final static String REGEX3 = "\\\")|(?:\\'";
    private final static String REGEX4 = "\\\')|(?:";
    private final static String REGEX5 = "))\\)$";

    private static class Holder {
        static final IsUnitConverter INSTANCE = new IsUnitConverter();
    }

    public static IsUnitConverter getInstance() {
        return Holder.INSTANCE;
    }

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
