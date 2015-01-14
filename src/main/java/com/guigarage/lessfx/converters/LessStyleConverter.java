package com.guigarage.lessfx.converters;

import javafx.css.StyleConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Robin Küster
 * @since 2015-01-14
 */
public class LessStyleConverter<F, T> extends StyleConverter<F, T> {
    public LessStyleConverter() {
        super();
    }

    protected Matcher getMatcher(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher;
        } else {
            return null;
        }
    }
}
