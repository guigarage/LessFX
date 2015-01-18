package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class FloorConverter extends LessStyleConverter<String, Integer> {
    private static class Holder {
        static final FloorConverter INSTANCE = new FloorConverter();
    }

    public static LessStyleConverter<String, Integer> getInstance() {
        return Holder.INSTANCE;
    }
    
    public FloorConverter() {
        super();
    }

    @Override
    public Integer convert(ParsedValue<String, Integer> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), "^floor\\((-?[0-9]+.?[0-9]*)\\)$");
        Number val;

        // nonsensical
        if (matcher == null) {
            return null;
        } else {
            val = new Double(matcher.group(1));
        }
        // LessCSS reference says that "floor()" is returning an integer unlike Java
        return (int)Math.floor(val.doubleValue());
    }
}
