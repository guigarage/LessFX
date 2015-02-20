package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ArcSinusConverterTest extends MathematicsTest {
    private ArcSinusConverter converter;

    @Before
    public void initialize() {
        this.converter = ArcSinusConverter.getInstance();
    }

    @Test
    public void testOOR() {
        String input = "asin(2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Double.isNaN(result.doubleValue()), true);
    }

    @Override
    public void testEmpty() {
        String input = "asin()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testInteger() {
        String input = "asin(1)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.asin(1), result.doubleValue(), 0.00001);
    }

    @Override
    public void testDouble() {
        String input = "asin("+Math.PI/2+")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.asin(Math.PI/2), result.doubleValue(), 0.00001);
    }

    @Override
    public void testNegInteger() {
        String input = "asin(-1)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.asin(-1), result.doubleValue(), 0.00001);
    }

    @Override
    public void testNegDouble() {
        String input = "asin("+Math.PI/-2+")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.asin(Math.PI/-2), result.doubleValue(), 0.00001);
    }

    @Override
    public void testMultipleParameters() {
        String input = "asin(-1, 3)";
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
        String input = "asin(1px)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testNaN() {
        String input = "asin(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

}