package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ArcCosineConverterTest extends MathematicsTest {
    private ArcCosineConverter converter;

    @Before
    public void initialize() {
        this.converter = ArcCosineConverter.getInstance();
    }

    @Test
    public void testPi() {

    }

    @Test
    public void testNegativePi() {

    }

    @Test
    public void testOOR() {
        String input = "acos(2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Double.isNaN(result.doubleValue()), true);
    }

    @Override
    public void testEmpty() {
        String input = "acos()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testInteger() {
        String input = "acos(1)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.acos(1), result.doubleValue(), 0.00001);
    }

    @Override
    public void testDouble() {
        String input = "acos("+Math.PI+")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.acos(Math.PI), result.doubleValue(), 0.00001);
    }

    @Override
    public void testNegInteger() {
        String input = "acos(-1)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.acos(-1), result.doubleValue(), 0.00001);
    }

    @Override
    public void testNegDouble() {
        String input = "acos("+-Math.PI+")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.acos(-Math.PI), result.doubleValue(), 0.00001);
    }

    @Override
    public void testMultipleParameters() {
        String input = "acos(-1, 3)";
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
        String input = "acos("+-Math.PI+"px)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testNaN() {
        String input = "acos(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

}