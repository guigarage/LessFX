package com.guigarage.lessfx.converters;

import com.sun.javafx.css.SizeUnits;
import javafx.css.StyleConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Robin Küster
 * @since 2015-01-14
 */
public abstract class LessStyleConverter<F, T> extends StyleConverter<F, T> {
    protected LessStyleConverter() {
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

    protected SizeUnits getUnit(String unit) {
        if (unit == null) {
            return null;
        } else if (unit.equals("")){
            return null;
        } else {
            return SizeUnits.valueOf(unit.equals("%") ? "PERCENT" : unit.toUpperCase());
        }
    }

    protected String getUnitRegex() {
        return "(%|in|cm|mm|em|ex|pt|pc|px|grad|deg|rad|turn)?";
    }
}
