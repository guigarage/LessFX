package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-01
 */
public class ColorConverter extends LessStyleConverter<String, Color> {
    private final static String REGEX = "^color\\(\\\"\\#((?:[0-9a-fA-F]{3})|(?:[0-9a-fA-F]{6}))\\\"\\)$";

    private static class Holder {
        static final ColorConverter INSTANCE = new ColorConverter();
    }

    public static ColorConverter getInstance() {
        return Holder.INSTANCE;
    }

    public ColorConverter() {

    }

    @Override
    public Color convert(ParsedValue<String, Color> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);


        // nonsensical input
        if (matcher == null) {
            return null;
        }

        String val = matcher.group(1);
        int r;
        int g;
        int b;

        if (val.length() == 3) { // RGB given in 3 one-digit hexadecimal numbers
            r = Integer.parseInt(String.valueOf(val.charAt(0)), 16);
            r += r*16;

            g = Integer.parseInt(String.valueOf(val.charAt(1)), 16);
            g += g*16;

            b = Integer.parseInt(String.valueOf(val.charAt(2)), 16);
            b += b*16;
        } else if (val.length() == 6) { // RGB given in 6 two-digit hexadecimal numbers
            r = Integer.parseInt(val.substring(0, 2), 16);
            g = Integer.parseInt(val.substring(2, 4), 16);
            b = Integer.parseInt(val.substring(4, 6), 16);
        } else {
            return null;
        }

        return Color.rgb(r, g, b);
    }
}
