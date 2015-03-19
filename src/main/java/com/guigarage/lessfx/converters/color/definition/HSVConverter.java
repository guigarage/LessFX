package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-04
 */
public class HSVConverter extends LessStyleConverter<String, Color> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^hsv\\(([0-9]{1,3}),\\s*((?:[0-1]\\.[0-9]*)|(?:[0-9]{1,3}%)),\\s*((?:[0-1]\\.[0-9]*)|(?:[0-9]{1,3}%))\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final HSVConverter INSTANCE = new HSVConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static HSVConverter getInstance() {
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

        int h = Integer.valueOf(matcher.group(1));
        if (h > 360 || h < 0) {
            return null;
        }

        double color[] = new double[2];

        for (int i = 2; i <= 3; i++) {
            String group = matcher.group(i);
            if (group.charAt(group.length() - 1) == '%') { // value given in percentage
                color[i - 2] = Double.valueOf(group.substring(0, group.length() - 1)) / 100;
            } else { // value between 0 and 1
                color[i - 2] = Double.valueOf(group);
            }
            if (color[i - 2] > 1.0) {
                return null;
            }
        }

        return Color.hsb(h, color[0], color[1], 1.0f);
    }
}
