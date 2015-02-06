package com.guigarage.lessfx.converters;

import com.sun.javafx.css.SizeUnits;
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

    protected SizeUnits getUnit(String unit) {
        if (unit == null) {
            return null;
        }
        switch (unit.toLowerCase()) {
            case "%":
                return SizeUnits.PERCENT;
            case "in":
                return SizeUnits.IN;
            case "cm":
                return SizeUnits.CM;
            case "mm":
                return SizeUnits.MM;
            case "em":
                return SizeUnits.EM;
            case "ex":
                return SizeUnits.EX;
            case "pt":
                return SizeUnits.PT;
            case "pc":
                return SizeUnits.PC;
            case "px":
                return SizeUnits.PX;
            case "grad":
                return SizeUnits.GRAD;
            case "deg":
                return SizeUnits.DEG;
            case "rad":
                return SizeUnits.RAD;
            case "turn":
                return SizeUnits.TURN;
            default:
                return null;
        }
    }

    protected String getUnitRegex() {
        return "(%|in|cm|mm|em|ex|pt|pc|px|grad|deg|rad|turn)?";
    }
}
