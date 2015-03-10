package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.TypeConverterTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class IsColorConverterTest extends TypeConverterTest {
    private IsColorConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = IsColorConverter.getInstance();
    }

    @Override
    public void testColorHex() {
        String input = "iscolor(#ff0)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("One-digit hex color", result);

        input = "iscolor(#ffff00)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertTrue("Two-digit hex color", result);
    }

    @Override
    public void testColorKeyword() {
        String input = "iscolor(blue)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Keyword color", result);
    }

    @Override
    public void testString() {
        String input = "iscolor(\"String!\")";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("String", result);
    }

    @Override
    public void testInteger() {
        String input = "iscolor(1)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Integerr", result);
    }

    @Override
    public void testPixel() {
        String input = "iscolor(1px)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Px", result);
    }

    @Override
    public void testEm() {
        String input = "iscolor(1em)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Em", result);
    }

    @Override
    public void testPercentage() {
        String input = "iscolor(1%)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Percentage", result);
    }
}