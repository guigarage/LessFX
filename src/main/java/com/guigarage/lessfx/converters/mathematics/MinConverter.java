package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-12
 */
public class MinConverter extends LessStyleConverter<String, Number> {
    private final static String REGEX1 = "^min\\((.+)\\)$";
    private final static String REGEX2 = "^(\\-?[0-9]+\\.*[0-9]*)$";

    private static class Holder {
        static final MinConverter INSTANCE = new MinConverter();
    }

    public static MinConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1);

        if (matcher == null) {
            return null;
        }

        String values[] = matcher.group(1).replaceAll("\\s", "").split(",");

        double min = Double.MAX_VALUE;

        for (String s : values) {
            matcher = this.getMatcher(s, REGEX2);

            if (matcher == null) {
                return null;
            }

            if (Double.parseDouble(s) < min) {
                min = Double.parseDouble(s);
            }
        }

        return min;
    }
}
