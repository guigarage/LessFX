package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.TypeConverterTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class IsPixelConverterTest extends TypeConverterTest {
    private IsPixelConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = IsPixelConverter.getInstance();
    }

    @Override
    public void testColorHex() {
        String input = "ispixel(#ff0)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("One-digit hex color", result);

        input = "ispixel(#ffff00)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertFalse("Two-digit hex color", result);
    }

    @Override
    public void testColorKeyword() {
        String input = "ispixel(blue)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Keyword color", result);
    }

    @Override
    public void testString() {
        String input = "ispixel(\"String!\")";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("String", result);
    }

    @Override
    public void testInteger() {
        String input = "ispixel(1)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Integerr", result);
    }

    @Override
    public void testPixel() {
        String input = "ispixel(1px)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Px", result);
    }

    @Override
    public void testEm() {
        String input = "ispixel(1em)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Em", result);
    }

    @Override
    public void testPercentage() {
        String input = "ispixel(1%)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Percentage", result);
    }
}