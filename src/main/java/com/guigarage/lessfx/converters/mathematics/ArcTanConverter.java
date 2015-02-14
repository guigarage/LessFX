package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-02-14
 */
public class ArcTanConverter extends LessStyleConverter<String, Number> {
    private static class Holder {
        static final ArcTanConverter INSTANCE = new ArcTanConverter();
    }

    public static ArcTanConverter getInstance() {
        return Holder.INSTANCE;
    }

    private ArcTanConverter() {

    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), "^atan\\((-?[0-9]+\\.?[0-9]*)\\)$");

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        return Math.atan(Double.valueOf(matcher.group(1)));
    }
}