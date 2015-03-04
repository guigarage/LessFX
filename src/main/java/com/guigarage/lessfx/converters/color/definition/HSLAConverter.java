package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-04
 */
public class HSLAConverter extends LessStyleConverter<String, Color> {
    private final static String REGEX = "^hsla\\(([0-9]{1,3}),\\s*((?:[0-1]\\.[0-9]*)|(?:[0-9]{1,3}%)),\\s*((?:[0-1]\\.[0-9]*)|(?:[0-9]{1,3}%)),\\s*((?:[0-1]\\.[0-9]*)|(?:[0-9]{1,3}%))\\)$";

    private static class Holder {
        static final HSLAConverter INSTANCE = new HSLAConverter();
    }

    public static HSLAConverter getInstance() {
        return Holder.INSTANCE;
    }

    public HSLAConverter() {

    }

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
                color[i-2] = Double.valueOf(group.substring(0, group.length() - 1)) / 100;
            } else { // value between 0 and 1
                color[i-2] = Double.valueOf(group);
            }
            if (color[i-2] > 1.0) {
                return null;
            }
        }

        double c = (1 - Math.abs(2*color[1] - 1)) * color[0];
        double h2 = h / 60;
        double x = c * (1-Math.abs((h2 % 2) - 1));

        double r, g, b;

        if (h2 < 1) {
            r = c;
            g = x;
            b = 0;
        } else  if (h2 >= 1 && h2 < 2) {
            r = x;
            g = c;
            b = 0;
        } else if (h2 >= 2 && h2 < 3) {
            r = 0;
            g = c;
            b = x;
        } else if (h2 >= 3 && h2 < 4) {
            r = 0;
            g = x;
            b = c;
        } else if (h2 >= 4 && h2 < 5) {
            r = x;
            g = 0;
            b = c;
        } else if (h2 >= 5 && h2 < 6) {
            r = c;
            g = 0;
            b = x;
        } else {
            r = g = b = 0;
        }

        double m = color[1] - 0.5 * c;

        String alphaS = matcher.group(4);
        double alpha;

        if (alphaS.charAt(alphaS.length() - 1) == '%') {
            alpha = Double.valueOf(alphaS.substring(0, alphaS.length() - 1)) / 100;
        } else {
            alpha = Double.valueOf(alphaS);
        }

        if (alpha > 1.0) {
            return null;
        }

        return new Color(r + m, g + m, b + m, alpha);
    }
}
