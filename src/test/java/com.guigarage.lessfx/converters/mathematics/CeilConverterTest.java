package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CeilConverterTest {
    private CeilConverter converter;

    @Before
    public void initialize() {
        this.converter = (CeilConverter)CeilConverter.getInstance();
    }

    @Test
    public void testIntegerValue() {
        String input = "ceil(3)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.doubleValue(), 3.0, 0.000001);
    }

    @Test
    public void testNegativeIntegerValue() {
        String input = "ceil(-3)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.doubleValue(), -3.0, 0.000001);
    }

    @Test
    public void testDoubleValue() {
        String input = "ceil(3.4)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.doubleValue(), 4.0, 0.000001);
    }

    @Test
    public void testNegativeDoubleValue() {
        String input = "ceil(-3.6)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.doubleValue(), -3.0, 0.000001);
    }

    @Test
    public void testStringValue() {
        String input = "ceil(not a number!)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testValueWithUnit() {
        String input = "ceil(20.9px)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testEmptyValue() {
        String input = "ceil()";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }
}