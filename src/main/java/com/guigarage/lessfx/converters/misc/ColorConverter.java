package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-01
 */
public class ColorConverter extends LessStyleConverter<String, Color> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^color\\(\\\"\\#((?:[0-9a-fA-F]{3})|(?:[0-9a-fA-F]{6}))\\\"\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final ColorConverter INSTANCE = new ColorConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static ColorConverter getInstance() {
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
