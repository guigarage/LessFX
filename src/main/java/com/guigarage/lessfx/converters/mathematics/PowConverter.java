package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-02-14
 */
public class PowConverter extends LessStyleConverter<String, Size> {
    private final static String REGEX1 = "^pow\\(([0-9]*\\.?[0-9]*)";
    private final static String REGEX2 = "\\,\\s?(-?[0-9]*\\.?[0-9]*)";
    private final static String REGEX3 = "\\)$";

    private static class Holder {
        static final PowConverter INSTANCE = new PowConverter();
    }

    public static PowConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Size convert(ParsedValue<String, Size> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1+this.getUnitRegex()+REGEX2+this.getUnitRegex()+REGEX3);

        if (matcher == null) {
            return null;
        }

        Double val = Math.pow(Double.valueOf(matcher.group(1)), Double.valueOf(matcher.group(3)));
        return new Size(val, this.getUnit(matcher.group(2)));
    }
}
