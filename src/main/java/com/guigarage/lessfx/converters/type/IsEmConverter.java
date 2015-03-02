package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @since 2015-03-02
 */
public class IsEmConverter extends LessStyleConverter<String, Boolean> {
    private final static String REGEX = "^isem\\(\\-?[0-9]*\\.?[0-9]*em\\)$";

    private static class Holder {
        static final IsEmConverter INSTANCE = new IsEmConverter();
    }

    public static IsEmConverter getInstance() {
        return Holder.INSTANCE;
    }

    public IsEmConverter() {

    }

    @Override
    public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
        return getMatcher(value.getValue(), REGEX) != null;
    }
}
