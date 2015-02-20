package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class ArcTanConverterTest extends MathematicsTest {
    private ArcTanConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = ArcTanConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "atan(3)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.atan(3), result);
    }

    @Override
    public void testNegInteger() {
        String input = "atan(-3)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.atan(-3), result);
    }

    @Override
    public void testDouble() {
        String input = "atan(3.5)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.atan(3.5), result);
    }

    @Override
    public void testNegDouble() {
        String input = "atan(-3.5)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.atan(-3.5), result);
    }

    @Override
    public void testUnits() {
        String input = "atan(3px)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testNaN() {
        String input = "atan(Not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input = "atan()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testMultipleParameters() {
        String input = "atan(3, 43)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testOneParameter() {
        this.testInteger();
    }
}