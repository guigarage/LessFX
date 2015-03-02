package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @since 2015-03-02
 */
public class IsPixelConverter extends LessStyleConverter<String, Boolean> {
    private final static String REGEX = "^ispixel\\(\\-?[0-9]*\\.?[0-9]*px\\)$";

    private static class Holder {
        static final IsPixelConverter INSTANCE = new IsPixelConverter();
    }

    public static IsPixelConverter getInstance() {
        return Holder.INSTANCE;
    }

    public IsPixelConverter() {

    }

    @Override
    public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
        return getMatcher(value.getValue(), REGEX) != null;
    }
}
