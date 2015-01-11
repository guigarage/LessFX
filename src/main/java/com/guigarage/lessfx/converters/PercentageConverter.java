package com.guigarage.lessfx.converters;

import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class PercentageConverter extends StyleConverter<String, String> {
    public PercentageConverter() {

    }

    @Override
    public String convert(ParsedValue<String, String> value, Font font) {
        Pattern pattern = Pattern.compile("^percentage\\((-?[0-9]*.?[0-9]*)\\)$");
        Matcher matcher = pattern.matcher(value.getValue());
        if (matcher.find()) {
            if (matcher.group(1).equals("")) {
                return null;
            }
            Double val = Double.valueOf(matcher.group(1)) * 100;
            return new DecimalFormat("#.######").format(val) + "%";
        }
        return null;
    }
}
