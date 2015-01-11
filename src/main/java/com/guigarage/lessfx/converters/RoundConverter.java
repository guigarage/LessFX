package com.guigarage.lessfx.converters;

import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class RoundConverter extends StyleConverter<String, Number> {
    public RoundConverter() {

    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Pattern pattern = Pattern.compile("^round\\((-?[0-9]*.?[0-9]*),?\\s?([0-9]*)\\)$");
        Matcher matcher = pattern.matcher(value.getValue());
        if (matcher.find()) {
            if (matcher.group(1).equals("")) {
                return null;
            }
            if (matcher.group(0).contains(",") && matcher.group(2).equals("")) {
                return null;
            }

            Double val = Double.valueOf(matcher.group(1));
            if (matcher.group(2).equals("") || matcher.group(2).equals("0")) {
                return Math.round(val);
            } else {
                String strVal = String.format(Locale.UK ,"%."+String.valueOf(Integer.valueOf(matcher.group(2)) + 1)+"g%n", val);
                return Double.valueOf(strVal);
            }
        }
        return null;
    }
}
