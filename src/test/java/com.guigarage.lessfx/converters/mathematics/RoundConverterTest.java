package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundConverterTest {
    private RoundConverter converter;

    @Before
    public void initialize() {
        this.converter = (RoundConverter)RoundConverter.getInstance();
    }

    @Test
    public void testIntegerValue() {
        String input = "round(4)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(4.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeIntegerValue() {
        String input = "round(-4)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-4.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testDoubleValue() {
        String input = "round(4.5)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(5.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeDoubleValue() {
        String input = "round(-4.5)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-4.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testDecimalPlaceValue() {
        String input = "round(4.12345, 3)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(4.123, result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeDecimalPlaceValue() {
        String input = "round(-4.12345, 3)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-4.123, result.doubleValue(), 0.0001);
    }

    @Test
    public void testZeroDecimalPlaceValue() {
        String input = "round(4.12345, 0)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(4.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testNoDecimalValue() {
        String input = "round(4, )";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);

        input = "round(4,)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testStringValue() {
        String input = "round(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testEmptyValue() {
        String input = "round()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }
}