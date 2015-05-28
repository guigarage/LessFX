package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class MinConverterTest extends MathematicsTest {
    private MinConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = MinConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "min(3, 2, 4, 5, 6)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(2.0, result);
    }

    @Override
    public void testDouble() {
        String input = "min(3.2, 2.2, 4.2, 5.2, 6.2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(2.2, result);
    }

    @Override
    public void testNegInteger() {
        String input = "min(-3, -2, -4, -5, -6)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-6.0, result);
    }

    @Override
    public void testNegDouble() {
        String input = "min(-3.2, -2.2, -4.2, -5.2, -6.2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-6.2, result);
    }

    @Override
    public void testMultipleParameters() {
        this.testInteger();
    }

    @Override
    public void testOneParameter() {
        String input = "min(-3)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-3.0, result);
    }

    @Override
    public void testUnits() {
        String input = "min(-3px)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(-3.0, result);
    }

    @Override
    public void testNaN() {
        String input = "min(\"Not a number!\")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input = "min()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }
}