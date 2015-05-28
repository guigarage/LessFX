package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-12
 */
public class MaxConverter extends LessStyleConverter<String, Number> {
    /**
     * 1st part of the regular expression to parse the function call.
     */
    private final static String REGEX1 = "^max\\((.+)\\)$";

    /**
     * 2nd part of the regular expression to parse the function call.
     */
    private final static String REGEX2 = "(?:(-?[0-9]+(?:\\.[0-9]*)?)(?:,\\s)?)";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final MaxConverter INSTANCE = new MaxConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static MaxConverter getInstance() {
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
        Matcher matcher = this.getMatcher(value.getValue(), REGEX1);

        if (matcher == null) {
            return null;
        }

        // The parameters of the function
        String numbers = matcher.group(1);

        // setting the initial value to the smallest possible double value to ensure that the first parameter is
        // also the first one the others are compared to
        double max = Double.NEGATIVE_INFINITY;

        // matcher for the parameters. It matches the parameter one by one
        matcher = this.getMatcher(numbers, REGEX2);

        // check if parameter list isn't invalid.
        if (matcher == null) {
            return null;
        }

        do {
            // get parameter as string
            String stringValue = matcher.group(1);

            // convert to double
            double val = Double.parseDouble(stringValue);

            // check if above last found maximum
            if (val > max) max = val;
        } while (matcher.find()); // check for next parameter

        return max;
    }
}
