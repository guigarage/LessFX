package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundConverterTest extends MathematicsTest{
    private RoundConverter converter;

    @Before
    public void initialize() {
        this.converter = RoundConverter.getInstance();
    }

    @Test
    public void testInteger() {
        String input = "round(4)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(4.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegInteger() {
        String input = "round(-4)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-4.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testDouble() {
        String input = "round(4.5)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(5.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegDouble() {
        String input = "round(-4.5)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-4.0, result.doubleValue(), 0.0001);
    }

    @Override
    public void testMultipleParameters() {
        this.testDecimalPlaceValue();
    }

    @Override
    public void testOneParameter() {
        this.testInteger();
    }

    @Override
    public void testUnits() {
        String input = "round(3.434454px, 4)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
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
    public void testNaN() {
        String input = "round(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testEmpty() {
        String input = "round()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }
}