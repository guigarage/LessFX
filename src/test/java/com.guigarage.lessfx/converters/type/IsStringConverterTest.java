package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.TypeConverterTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class IsStringConverterTest extends TypeConverterTest {
    private IsStringConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = IsStringConverter.getInstance();
    }

    @Override
    public void testColorHex() {
        String input = "isstring(#ff0)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("One-digit hex color", result);

        input = "isstring(#ffff00)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertFalse("Two-digit hex color", result);
    }

    @Override
    public void testColorKeyword() {
        String input = "isstring(blue)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Keyword color", result);
    }

    @Override
    public void testString() {
        String input = "isstring(\"String!\")";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("String", result);
    }

    @Override
    public void testInteger() {
        String input = "isstring(1)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Integerr", result);
    }

    @Override
    public void testPixel() {
        String input = "isstring(1px)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Px", result);
    }

    @Override
    public void testEm() {
        String input = "isstring(1em)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Em", result);
    }

    @Override
    public void testPercentage() {
        String input = "isstring(1%)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Percentage", result);
    }
}