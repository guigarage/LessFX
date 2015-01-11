package com.guigarage.lessfx.converters;

import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @since 2015-01-11
 */
public class PiConverter extends StyleConverter<String, Number> {
    public PiConverter() {

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
