package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-03
 */
public class RGBConverter extends LessStyleConverter<String, Color> {
    private final static String REGEX = "^rgb\\(([0-9]{1,3}%?),\\s*([0-9]{1,3}%?),\\s*([0-9]{1,3}%?)\\)$";

    private static class Holder {
        static final RGBConverter INSTANCE = new RGBConverter();
    }

    public static RGBConverter getInstance() {
        return Holder.INSTANCE;
    }

    public RGBConverter() {

    }

    @Override
    public Color convert(ParsedValue<String, Color> value, Font font) {
        Matcher matcher = getMatcher(value.getValue(), REGEX);

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        double color[] = new double[3];

        for (int i = 1; i <= 3; i++) {
            String group = matcher.group(i);
            if (group.charAt(group.length() - 1) == '%') { // value given in percentage
                color[i-1] = Double.valueOf(group.substring(0, group.length() - 1)) / 100;
            } else { // value given in unsigned 8bit integers
                color[i-1] = Double.valueOf(group) / 255;
            }
            if (color[i-1] > 1.0) {
                return null;
            }
        }

        return new Color(color[0], color[1], color[2], 1.0);
    }
}
