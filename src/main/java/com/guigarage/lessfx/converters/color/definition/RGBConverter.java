package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-03
 */
public class RGBConverter extends LessStyleConverter<String, Color> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^rgb\\(([0-9]{1,3}%?),\\s*([0-9]{1,3}%?),\\s*([0-9]{1,3}%?)\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final RGBConverter INSTANCE = new RGBConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static RGBConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts the given CSS function call to a Java object.
     *
     * @param value ParsedValue containing the function call
     * @param font Font used for functions that depend on the font size
     * @return The result of the function call as a Java object or null if function call failed
     */
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
