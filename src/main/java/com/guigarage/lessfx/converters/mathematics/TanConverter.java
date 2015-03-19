package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-02-13
 */
public class TanConverter extends LessStyleConverter<String, Number> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^tan\\((-?[0-9]+\\.?[0-9]*)";

    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final TanConverter INSTANCE = new TanConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static TanConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts the given CSS function call to a Java object.
     *
     * @param value ParsedValue containing the function call
     * @param font Font used for functions that depend on the font size
     * @return The result of the function call as a Java object or null if function call failed
     */
    @Override
    public Number convert(ParsedValue<String, Number> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1+this.getUnitRegex()+REGEX2);

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        SizeUnits unit;

        // no unit provided. RAD by default
        if (matcher.group(2) == null)  {
            unit = SizeUnits.RAD;
        } else {
            SizeUnits tempUnit = this.getUnit(matcher.group(2));
            if (tempUnit == SizeUnits.RAD || tempUnit == SizeUnits.DEG || tempUnit == SizeUnits.TURN || tempUnit == SizeUnits.GRAD) { // Get important units
                unit = tempUnit;
            } else { // make everything else RAD (assumption)
                unit = SizeUnits.RAD;
            }
        }

        Double val = null;

        if (unit == SizeUnits.RAD) {
            val = Math.tan(Double.valueOf(matcher.group(1)));
        } else if (unit == SizeUnits.DEG) {
            val = Math.tan(Double.valueOf(matcher.group(1)) * (Math.PI / 180));
        } else if (unit == SizeUnits.GRAD) {
            val = Math.tan(Double.valueOf(matcher.group(1)) * (Math.PI / 200));
        } else if (unit == SizeUnits.TURN) {
            val = Math.tan(Double.valueOf(matcher.group(1)) * (2 * Math.PI));
        }

        return val;
    }
}
