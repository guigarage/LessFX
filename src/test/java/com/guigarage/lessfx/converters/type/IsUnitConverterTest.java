package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.TypeConverterTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class IsUnitConverterTest extends TypeConverterTest {
    private IsUnitConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = IsUnitConverter.getInstance();
    }

    @Override
    public void testColorHex() {
        String input = "isunit(#FFFFFF)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Color Hex", result);
    }

    @Override
    public void testColorKeyword() {
        String input = "isunit(blue)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Color keyword", result);
    }

    @Override
    public void testString() {
        String input = "isunit(\"#FFFFFF\")";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("String", result);
    }

    @Override
    public void testInteger() {
        String input = "isunit(1)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Int", result);
    }

    @Override
    public void testPixel() {
        String input = "isunit(1px, px)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Pixel", result);
    }

    @Override
    public void testEm() {
        String input = "isunit(1em, \"em\")";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Em", result);
    }

    @Override
    public void testPercentage() {
        String input = "isunit(1%, '%')";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("Percentage", result);
    }
}