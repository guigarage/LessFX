package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class CeilConverter extends LessStyleConverter<String, Integer> {
    private final static String REGEX = "^ceil\\((-?[0-9]+.?[0-9]*)\\)$";

    private static class Holder {
        static final CeilConverter INSTANCE = new CeilConverter();
    }

    public static LessStyleConverter<String, Integer> getInstance() {
        return Holder.INSTANCE;
    }
    
    private CeilConverter() {

    }

    @Override
    public Integer convert(ParsedValue<String, Integer> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);
        Number val;

        // nonsensical input
        if (matcher == null) {
            return null;
        } else {
            val = new Double(matcher.group(1));
        }
        // LessCSS reference says that "ceil()" is returning an integer unlike Java
        return (int)Math.ceil(val.doubleValue());
    }
}
