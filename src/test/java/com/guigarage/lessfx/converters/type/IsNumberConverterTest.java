package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.TypeConverterTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class IsNumberConverterTest extends TypeConverterTest {
    private IsNumberConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = IsNumberConverter.getInstance();
    }

    @Override
    public void testColorHex() {
        String input = "isnumber(#ff0)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("One-digit hex color", result);

        input = "isnumber(#ffff00)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertFalse("Two-digit hex color", result);
    }

    @Override
    public void testColorKeyword() {
        String input = "isnumber(blue)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Keyword color", result);
    }

    @Override
    public void testString() {
        String input = "isnumber(\"String!\")";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("String", result);
    }

    @Override
    public void testInteger() {
        String input = "isnumber(1)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Integerr", result);
    }

    @Override
    public void testPixel() {
        String input = "isnumber(1px)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Px", result);
    }

    @Override
    public void testEm() {
        String input = "isnumber(1em)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Em", result);
    }

    @Override
    public void testPercentage() {
        String input = "isnumber(1%)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Percentage", result);
    }
}