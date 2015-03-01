package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-01-15
 */
public class SqrtConverter extends LessStyleConverter<String, Size> {
    private final static String REGEX1 = "^sqrt\\(([0-9]+\\.?[0-9]*)";
    private final static String REGEX2 = "\\)$";

    private static class Holder {
        static final SqrtConverter INSTANCE = new SqrtConverter();
    }

    public static LessStyleConverter<String, Size> getInstance() {
        return Holder.INSTANCE;
    }

    private SqrtConverter() {

    }

    @Override
    public Size convert(ParsedValue<String, Size> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1+this.getUnitRegex()+REGEX2);

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        // first parameter: value. Second parameter: unit
        return new Size(Math.sqrt(Double.valueOf(matcher.group(1))), this.getUnit(matcher.group(2)));
    }
}
