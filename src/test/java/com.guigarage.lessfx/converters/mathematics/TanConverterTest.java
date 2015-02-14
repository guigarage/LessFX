package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TanConverterTest {
    private TanConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = TanConverter.getInstance();
    }

    @Test
    public void testIntegerValue() {
        String input = "tan(60)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(60), result.doubleValue(), 0.0001);
    }

    @Test
    public void testDoubleValue() {
        String input = "tan(3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(3.14159), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeIntegerValue() {
        String input = "tan(-20)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(-20), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeDoubleValue() {
        String input = "tan(-3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(-3.14159), result.doubleValue(), 0.0001);
    }

    @Test
    public void testDegValue() {
        String input = "tan(20deg)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(20.0 * (Math.PI / 180)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeDegValue() {
        String input  = "tan(-20deg)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(-20.0 * (Math.PI / 180)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testGradValue() {
        String input = "tan(20grad)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(20.0 * (Math.PI / 200)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegativeGradValue() {
        String input  = "tan(-20grad)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(-20.0 * (Math.PI / 200)), result.doubleValue(), 0.0001);
    }

    @Test
    public void testStringValue() {
        String input = "tan(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testUnitValue() {
        String input = "tan(10px)";
        String inputWithSpace = "tan(10 px)";

        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.tan(10.0), result.doubleValue(), 0.001);

        value = new ParsedValueImpl<>(inputWithSpace, converter);
        result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testEmptyParameter() {
        String input = "tan()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }
}