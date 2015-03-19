package com.guigarage.lessfx.converters;

import com.sun.javafx.css.SizeUnits;
import javafx.css.StyleConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-01-14
 */
public abstract class LessStyleConverter<F, T> extends StyleConverter<F, T> {
    protected LessStyleConverter() {
        super();
    }

    /**
     * Matcher with compiled regular expression
     * <p>
     * The matcher already contains the compiled regular expression used by the converters.
     * This method also matches the value.
     * @param value Value the matcher should match
     * @param regex Regular expression that's supposed to be compiled
     * @return The matcher with compiled regular expression and matched value. If the value couldn't be matched, null is returned
     */
    protected Matcher getMatcher(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher;
        } else {
            return null;
        }
    }

    /**
     * Converts unit string to SizeUnits enum
     * @param unit To be converted unit
     * @return Unit represented by the SizeUnits enum.
     */
    protected SizeUnits getUnit(String unit) {
        if (unit == null) {
            return null;
        } else if (unit.equals("")){
            return null;
        } else {
            return SizeUnits.valueOf(unit.equals("%") ? "PERCENT" : unit.toUpperCase());
        }
    }

    /**
     * All possible units in a regular expression.
     * @return A regular expression that matches with every possible unit or an empty string
     */
    protected String getUnitRegex() {
        return "(%|in|cm|mm|em|ex|pt|pc|px|grad|deg|rad|turn)?";
    }
}
