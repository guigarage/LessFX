package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-11
 */
public class ConvertConverter extends LessStyleConverter<String, Size> {
    private final static String REGEX = "^convert\\((?:(-?[0-9]*\\.?[0-9]*)([a-zA-Z]*)),\\s(.*)\\)$";
    static final private double DOTS_PER_INCH = 96.0;
    static final private double POINTS_PER_INCH = 72.0;

    private static class Holder {
        static final ConvertConverter INSTANCE = new ConvertConverter();
    }

    public static ConvertConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Size convert(ParsedValue<String, Size> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        // mismatching unit type
        if (matcher.group(2).equals("") || !((isLength(matcher.group(2)) && isLength(matcher.group(3))) || (isAngle(matcher.group(2)) && isAngle(matcher.group(3))))) {
            return new Size(Double.parseDouble(matcher.group(1)), null);
        }

        double inputValue;
        SizeUnits inputUnit;

        inputValue = Double.parseDouble(matcher.group(1));
        inputUnit = SizeUnits.valueOf(matcher.group(2).toUpperCase());

        if (matcher.group(3).equals("pt")) { // Size can convert everything to PT on its own
            return new Size(new Size(inputValue, inputUnit).pixels(font), SizeUnits.PT);
        } else if (matcher.group(3).equals("px")) { // Size can also convert everything to PX on its own
            return new Size(new Size(inputValue, inputUnit).pixels(font), SizeUnits.PX);
        }

        double multiplier = getMultiplier(matcher.group(2));

        // something unexpected happened with the units
        if (multiplier == 0) {
            return null;
        }

        double valueMM = Double.parseDouble(matcher.group(1)) * multiplier;

        multiplier = getMultiplier(matcher.group(3));

        return new Size(valueMM / multiplier, SizeUnits.valueOf(matcher.group(3).toUpperCase()));
    }

    private boolean isLength(String unit) {
        return unit.equals(SizeUnits.CM.toString())
                || unit.equals(SizeUnits.MM.toString())
                || unit.equals(SizeUnits.IN.toString())
                || unit.equals(SizeUnits.PC.toString());
    }

    private double getMultiplier(String unit) {
        switch (unit) {
            case "cm":
                return 10;
            case "in":
                return 25.4;
            case "pc":
                return 4.23333333;
            case "mm":
                return 1;
            case "pt":
                return (1 / POINTS_PER_INCH) * 25.4;
            case "px":
                return (POINTS_PER_INCH / DOTS_PER_INCH / POINTS_PER_INCH) * 25.4;
            case "rad":
                return 1;
            case "deg":
                return Math.PI / 180.0;
            case "grad":
                return Math.PI / 200.0;
            case "turn":
                return 2 * Math.PI;
            default: return 0;
        }
    }

    private boolean isAngle(String unit) {
        return unit.equals(SizeUnits.GRAD.toString())
                || unit.equals(SizeUnits.TURN.toString())
                || unit.equals(SizeUnits.RAD.toString())
                || unit.equals(SizeUnits.DEG.toString());
    }
}
