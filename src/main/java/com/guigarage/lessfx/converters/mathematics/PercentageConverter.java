package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.text.DecimalFormat;
import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class PercentageConverter extends LessStyleConverter<String, String> {
    public PercentageConverter() {
        super();
    }

    @Override
    public String convert(ParsedValue<String, String> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), "^percentage\\((-?[0-9]*.?[0-9]*)\\)$");

        // either nonsensical input (matcher == null) or empty parameter list
        if (matcher == null || matcher.group(1).equals("")) {
            return null;
        }
        Double val = Double.valueOf(matcher.group(1)) * 100;
        return new DecimalFormat("#.######").format(val) + "%";
    }
}
