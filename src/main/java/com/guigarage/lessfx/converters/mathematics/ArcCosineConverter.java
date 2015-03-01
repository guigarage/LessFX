package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-02-06
 */
public class ArcCosineConverter extends LessStyleConverter<String, Number> {
    private final static String REGEX = "^acos\\((-?[0-9]*\\.?[0-9]*)\\)$";

    private static class Holder {
        static final ArcCosineConverter INSTANCE = new ArcCosineConverter();
    }

    public static ArcCosineConverter getInstance() {
        return Holder.INSTANCE;
    }

    private ArcCosineConverter() {

    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = getMatcher(value.getValue(), REGEX);

        // nonsensical input
        if (matcher == null) {
            return null;
        } else if (matcher.group(1).equals("")) {
            return null;
        }

        return Math.acos(Double.parseDouble(matcher.group(1)));
    }
}
