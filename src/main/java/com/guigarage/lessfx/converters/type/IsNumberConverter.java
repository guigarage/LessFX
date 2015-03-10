package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @since 2015-03-02
 */
public class IsNumberConverter extends LessStyleConverter<String, Boolean> {
    private final static String REGEX1 = "^isnumber\\((\\-?[0-9]*(?:\\.[0-9]*)?)";
    private final static String REGEX2 = "\\)$";

    private static class Holder {
        static final IsNumberConverter INSTANCE = new IsNumberConverter();
    }

    public static IsNumberConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
        return getMatcher(value.getValue(), REGEX1+getUnitRegex()+REGEX2) != null;
    }
}
