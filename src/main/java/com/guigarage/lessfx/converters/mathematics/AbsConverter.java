package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-02-06
 */
public class AbsConverter extends LessStyleConverter<String, Size> {
    private static class Holder {
        static final AbsConverter INSTANCE = new AbsConverter();
    }

    public static AbsConverter getInstance() {
        return Holder.INSTANCE;
    }

    private AbsConverter() {

    }

    @Override
    public Size convert(ParsedValue<String, Size> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), "^abs\\((-?[0-9]+\\.?[0-9]*)"+this.getUnitRegex()+"\\)$");

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        // first parameter: value. Second parameter: unit
        return new Size(Math.abs(Double.valueOf(matcher.group(1))), this.getUnit(matcher.group(2)));
    }
}
