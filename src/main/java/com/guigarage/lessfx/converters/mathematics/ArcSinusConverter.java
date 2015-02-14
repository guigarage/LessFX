package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-02-06
 */
public class ArcSinusConverter extends LessStyleConverter<String, Number> {
    private static class Holder {
        static final ArcSinusConverter INSTANCE = new ArcSinusConverter();
    }

    public static ArcSinusConverter getInstance() {
        return Holder.INSTANCE;
    }

    private ArcSinusConverter() {

    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = getMatcher(value.getValue(), "^asin\\((-?[0-9]*\\.?[0-9]*)\\)$");

        // nonsensical input
        if (matcher == null) {
            return null;
        } else if (matcher.group(1).equals("")) {
            return null;
        }

        return Math.asin(Double.parseDouble(matcher.group(1)));
    }
}
