package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FloorConverterTest {
    private FloorConverter converter;

    @Before
    public void initialize() {
        this.converter = (FloorConverter)FloorConverter.getInstance();
    }

    @Test
    public void testIntegerValue() {
        String input = "floor(3)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, 3, 0.1);
    }

    @Test
    public void testNegativeIntegerValue() {
        String input = "floor(-3)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, -3, 0.1);
    }

    @Test
    public void testDoubleValue() {
        String input = "floor(3.6)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, 3, 0.1);
    }

    @Test
    public void testNegativeDoubleValue() {
        String input = "floor(-3.4)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, -4, 0.1);
    }

    @Test
    public void testStringValue() {
        String input = "floor(not a number!)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testValueWithUnit() {
        String input = "floor(20.9px)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testEmptyValue() {
        String input = "floor()";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }
}