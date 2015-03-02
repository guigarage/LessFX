package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @since 2015-03-02
 */
public class IsStringConverter extends LessStyleConverter<String, Boolean> {
    private final static String REGEX1 = "^isstring\\(\\\".*\\\"\\)$";

    private static class Holder {
        static final IsStringConverter INSTANCE = new IsStringConverter();
    }

    public static IsStringConverter getInstance() {
        return Holder.INSTANCE;
    }

    public IsStringConverter() {

    }

    @Override
    public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
        return getMatcher(value.getValue(), REGEX1) != null;
    }
}
