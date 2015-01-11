package com.guigarage.lessfx.converters;

import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Robin Küster on 10.01.2015.
 */
public class SinusConverter extends MathematicsConverter {
    public SinusConverter() {

    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Pattern pattern = Pattern.compile(this.getRegex("sin"));
        Matcher matcher = pattern.matcher(value.getValue());
        if (matcher.find()) {
            Double val = Math.sin(new Double(matcher.group(1)));
            if (matcher.group(2) != null) {
                if (matcher.group(2).equals("deg")) {
                    val = Math.sin(new Double(matcher.group(1)) * (Math.PI / 180));
                } else if (matcher.group(2).equals("grad")) {
                    val = Math.sin(new Double(matcher.group(1)) * (Math.PI / 200));
                } else {
                    return null;
                }
            }
            return val;
        }
        return null;
    }
}
