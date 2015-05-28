package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class MaxConverterTest extends MathematicsTest {
    private MaxConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = MaxConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "max(3, 2, 4, 5, 6)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(6.0, result);
    }

    @Override
    public void testDouble() {
        String input = "max(3.2, 2.2, 4.2, 5.2, 6.2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(6.2, result);
    }

    @Override
    public void testNegInteger() {
        String input = "max(-3, -2, -4, -5, -6)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-2.0, result);
    }

    @Override
    public void testNegDouble() {
        String input = "max(-3.2, -2.2, -4.2, -5.2, -6.2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-2.2, result);
    }

    @Override
    public void testMultipleParameters() {
        this.testInteger();
    }

    @Override
    public void testOneParameter() {
        String input = "max(-3)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-3.0, result);
    }

    @Override
    public void testUnits() {
        String input = "max(-3px)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-3.0, result);
    }

    @Override
    public void testNaN() {
        String input = "max(\"Not a number!\")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input = "max()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }
}