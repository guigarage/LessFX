package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class CosineConverterTest extends MathematicsTest {
    private CosineConverter converter;
    @Before
    public void initialize() {
        this.converter = CosineConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "cos(60)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(60), result.doubleValue(), 0.0001);
    }

    @Override
    public void testDouble() {
        String input = "cos(3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(3.14159), result.doubleValue(), 0.0001);
    }

    @Override
    public void testNegInteger() {
        String input = "cos(-20)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(-20), result.doubleValue(), 0.0001);
    }

    @Override
    public void testNegDouble() {
        String input = "cos(-3.14159)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.cos(-3.14159), result.doubleValue(), 0.0001);
    }

    @Override
    public void testMultipleParameters() {
        String input = "cos(60, 47)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testOneParameter() {
        this.testInteger();
    }

    @Override
    public void testNaN() {
        String input = "cos(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
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

    @Override
    public void testEmpty() {
        String input = "cos()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }
}