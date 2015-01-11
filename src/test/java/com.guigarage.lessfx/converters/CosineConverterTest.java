package com.guigarage.lessfx.converters;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CosineConverterTest {
    private CosineConverter converter;
    @Before
    public void initialize() {
        this.converter = new CosineConverter();
    }

    @Test
    public void testIntegerValue() {
        String input = "cos(60)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(60), result.doubleValue(), 0.0001);
    }

    @Test
    public void testDoubleValue() {
        String input = "cos(3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(3.14159), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeIntegerValue() {
        String input = "cos(-20)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(-20), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeDoubleValue() {
        String input = "cos(-3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(-3.14159), result.doubleValue(), 0.0001);
    }

    @Test
    public void testDegValue() {
        String input = "cos(20deg)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(20 * (Math.PI / 180)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeDegValue() {
        String input  = "cos(-20deg)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(-20 * (Math.PI / 180)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testGradValue() {
        String input = "cos(20grad)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(20 * (Math.PI / 200)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeGradValue() {
        String input  = "cos(-20grad)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(-20 * (Math.PI / 200)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testStringValue() {
        String input = "cos(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testUnitValue() {
        String input = "cos(10px)";
        String inputWithSpace = "cos(10 px)";

        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);

        value = new ParsedValueImpl<>(inputWithSpace, converter);
        result = converter.convert(value, null);

        assertNull(result);
    }
}