package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class FloorConverterTest extends MathematicsTest {
    private FloorConverter converter;

    @Before
    public void initialize() {
        this.converter = (FloorConverter)FloorConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "floor(3)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, (Integer)3);
    }

    @Override
    public void testNegInteger() {
        String input = "floor(-3)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, (Integer)(-3));
    }

    @Override
    public void testDouble() {
        String input = "floor(3.6)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, (Integer)3);
    }

    @Override
    public void testNegDouble() {
        String input = "floor(-3.4)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, (Integer)(-4));
    }

    @Override
    public void testMultipleParameters() {
        String input = "floor(3, 4)";
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
        String input = "floor(not a number!)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testUnits() {
        String input = "floor(20.9px)";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input = "floor()";
        ParsedValue<String, Integer> value = new ParsedValueImpl<>(input, converter);
        Integer result = converter.convert(value, null);

        assertNull(result);
    }
}