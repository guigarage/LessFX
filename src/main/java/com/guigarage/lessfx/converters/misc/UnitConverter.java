package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-12
 */
public class UnitConverter extends LessStyleConverter<String, Size> {
    private final static String REGEX1 = "^unit\\((\\-?[0-9]+\\.?[0-9]*)[a-zA-Z]*,\\s";
    private final static String REGEX2 = "\\)$";

    private static class Holder {
        static final UnitConverter INSTANCE = new UnitConverter();
    }

    public static UnitConverter getInstance() {
        return Holder.INSTANCE;
    }

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
