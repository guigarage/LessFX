package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

/**
 * @author Robin Küster
 * @since 2015-03-02
 */
public class IsPercentageConverter extends LessStyleConverter<String, Boolean> {
    private final static String REGEX = "^ispercentage\\((?:\\-?[0-9]*\\.?[0-9]*%)\\)$";

    private static class Holder {
        static final IsPercentageConverter INSTANCE = new IsPercentageConverter();
    }

    public static IsPercentageConverter getInstance() {
        return Holder.INSTANCE;
    }

    public IsPercentageConverter() {

    }

    @Override
    public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
        return getMatcher(value.getValue(), REGEX) != null;
    }
}