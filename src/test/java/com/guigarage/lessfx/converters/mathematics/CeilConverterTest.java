package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class CeilConverterTest extends MathematicsTest {
    private CeilConverter converter;

    @Before
    public void initialize() {
        this.converter = (CeilConverter)CeilConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "ceil(3)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.doubleValue(), 3.0, 0.000001);
    }

    @Override
    public void testNegInteger() {
        String input = "ceil(-3)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.doubleValue(), -3.0, 0.000001);
    }

    @Override
    public void testDouble() {
        String input = "ceil(3.4)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.doubleValue(), 4.0, 0.000001);
    }

    @Override
    public void testNegDouble() {
        String input = "ceil(-3.6)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.doubleValue(), -3.0, 0.000001);
    }

    @Override
    public void testMultipleParameters() {
        String input = "ceil(1, 2)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testOneParameter() {
        this.testInteger();
    }

    @Override
    public void testNaN() {
        String input = "ceil(not a number!)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testUnits() {
        String input = "ceil(20.9px)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input = "ceil()";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }
}