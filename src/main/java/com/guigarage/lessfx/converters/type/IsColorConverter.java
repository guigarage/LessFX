package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-02
 */
public class IsColorConverter extends LessStyleConverter<String, Boolean> {
    private final static String REGEX1 = "^iscolor\\((.*)\\)$";
    private final static String REGEX2 = "#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})";

    private static class Holder {
        static final IsColorConverter INSTANCE = new IsColorConverter();
    }

    public static IsColorConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
        Matcher matcher = getMatcher(value.getValue(), REGEX1);

        if (matcher == null) {
            return false;
        }

        try {
            if (Color.web(matcher.group(1)) != null) {
                return true;
            } else {
                matcher = getMatcher(matcher.group(1), REGEX2);
                return matcher != null;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
