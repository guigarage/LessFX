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
public class ModConverter extends LessStyleConverter<String, Size> {
    private static class Holder {
        static final ModConverter INSTANCE = new ModConverter();
    }

    public static ModConverter getInstance() {
        return Holder.INSTANCE;
    }

    private ModConverter() {

    }

    @Override
    public Size convert(ParsedValue<String, Size> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), "^mod\\((-?[0-9]*\\.?[0-9]*)"+this.getUnitRegex()+"\\,\\s?(-?[0-9]*\\.?[0-9]*)"+this.getUnitRegex()+"\\)$");

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