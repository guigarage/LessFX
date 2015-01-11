package com.guigarage.lessfx.converters;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SinusConverterTest {
    private SinusConverter converter;
    @Before
    public void initialize() {
        this.converter = new SinusConverter();
    }

    @Test
    public void testIntegerValue() {
        String input = "sin(60)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(60), result.doubleValue(), 0.0001);
    }

    @Test
    public void testDoubleValue() {
        String input = "sin(3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(3.14159), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeIntegerValue() {
        String input = "sin(-20)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(-20), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeDoubleValue() {
        String input = "sin(-3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(-3.14159), result.doubleValue(), 0.0001);
    }

    @Test
    public void testDegValue() {
        String input = "sin(20deg)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(20 * (Math.PI / 180)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeDegValue() {
        String input  = "sin(-20deg)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(-20 * (Math.PI / 180)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testGradValue() {
        String input = "sin(20grad)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(20 * (Math.PI / 200)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeGradValue() {
        String input  = "sin(-20grad)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(-20 * (Math.PI / 200)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testStringValue() {
        String input = "sin(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testUnitValue() {
        String input = "sin(10px)";
        String inputWithSpace = "sin(10 px)";

        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);

        value = new ParsedValueImpl<>(inputWithSpace, converter);
        result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testEmptyParameter() {
        String input = "sin()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }
}