package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class PiConverter extends LessStyleConverter<String, Number> {
    private static class Holder {
        static final PiConverter INSTANCE = new PiConverter();
    }

    public static LessStyleConverter<String, Number> getInstance() {
        return Holder.INSTANCE;
    }
    
    private PiConverter() {
        super();
    }

    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        if (value.getValue().equals("pi()")) {
            return 3.141592653589793;
        } else {
            return null;
        }
    }
}
