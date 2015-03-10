package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SinusConverterTest extends MathematicsTest {
    private SinusConverter converter;
    @Before
    public void initialize() {
        this.converter = SinusConverter.getInstance();
    }

    @Test
    public void testInteger() {
        String input = "sin(60)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(60), result.doubleValue(), 0.0001);
    }

    @Test
    public void testDouble() {
        String input = "sin(3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(3.14159), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegInteger() {
        String input = "sin(-20)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(-20), result.doubleValue(), 0.0001);
    }

    @Test
    public void testNegDouble() {
        String input = "sin(-3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.sin(-3.14159), result.doubleValue(), 0.0001);
    }

    @Override
    public void testMultipleParameters() {
        String input = "sin(-3.14159, 4)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testOneParameter() {
        this.testInteger();
    }

    @Override
    public void testUnits() {
        String units[] = new String[] {
                "%", "in", "cm", "mm", "em", "ex", "pt", "pc", "px"
        };

        for (String unit : units) {
            String input = "cos(10"+unit+")";
            String inputWithSpace = "cos(10 "+unit+")";

            ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
            Number result = converter.convert(value, null);

            assertNull(result);

            value = new ParsedValueImpl<>(inputWithSpace, converter);
            result = converter.convert(value, null);

            assertNull(result);
        }
    }

    @Test
    public void testNaN() {
        String input = "sin(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testUnitse() {
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
    public void testEmpty() {
        String input = "sin()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }
}