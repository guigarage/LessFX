package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-01
 */
public class GetUnitConverter extends LessStyleConverter<String, SizeUnits> {
    private final static String REGEX = "^get\\-unit\\((?:\\-?[0-9]*(?:\\.[0-9])?)([a-zA-Z]*)\\)$";

    private static class Holder {
        static final GetUnitConverter INSTANCE = new GetUnitConverter();
    }

    public static GetUnitConverter getInstance() {
        return Holder.INSTANCE;
    }

    public GetUnitConverter() {

    }

    @Override
    public SizeUnits convert(ParsedValue<String, SizeUnits> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);


        // nonsensical input
        if (matcher == null) {
            return null;
        }

        return this.getUnit(matcher.group(1));
    }
}
