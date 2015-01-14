package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercentageConverterTest {
    private PercentageConverter converter;

    @Before
    public void initialize() {
        converter = new PercentageConverter();
    }

    @Test
    public void testIntegerValue() {
        String input = "percentage(1)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, "100%");
    }

    @Test
    public void testNegativeIntergerValue() {
        String input = "percentage(-1)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, "-100%");
    }

    @Test
    public void testDoubleValue() {
        String input = "percentage(0.5)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, "50%");
    }

    @Test
    public void testNegativeDoubleValue() {
        String input = "percentage(-0.5)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, "-50%");
    }

    @Test
    public void testStringValue() {
        String input = "percentage(not a number!)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testEmptyValue() {
        String input = "percentage()";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNull(result);
    }

}