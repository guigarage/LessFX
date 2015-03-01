package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.Locale;
import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class RoundConverter extends LessStyleConverter<String, Number> {
    private final static String REGEX = "^round\\((-?[0-9]+.?[0-9]*),?\\s?([0-9]*)\\)$";

    private static class Holder {
        static final RoundConverter INSTANCE = new RoundConverter();
    }

    public static LessStyleConverter<String, Number> getInstance() {
        return Holder.INSTANCE;
    }
    
    private RoundConverter() {

    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        // value contains a comma but second parameter is empty
        if (matcher.group(0).contains(",") && matcher.group(2).equals("")) {
            return null;
        }

        Double val = Double.valueOf(matcher.group(1));

        // second parameter is empty or zero. Rounding to integer
        if (matcher.group(2).equals("") || matcher.group(2).equals("0")) {
            return Math.round(val);
        } else {
            String strVal = String.format(Locale.UK ,"%."+String.valueOf(Integer.valueOf(matcher.group(2)) + 1)+"g%n", val);
            return Double.valueOf(strVal);
        }
    }
}
