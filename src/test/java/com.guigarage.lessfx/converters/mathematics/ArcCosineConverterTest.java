package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ArcCosineConverterTest {
    private ArcCosineConverter converter;

    @Before
    public void initialize() {
        this.converter = ArcCosineConverter.getInstance();
    }

    @Test
    public void testPi() {
        String input = "acos("+Math.PI+")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.acos(Math.PI), result.doubleValue(), 0.00001);
    }

    @Test
    public void testNegativePi() {
        String input = "acos("+-Math.PI+")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.acos(-Math.PI), result.doubleValue(), 0.00001);
    }

    @Test
    public void testOOR() {
        String input = "acos(2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Double.isNaN(result.doubleValue()), true);
    }

    @Test
    public void testEmpty() {
        String input = "acos()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testNaN() {
        String input = "acos(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

}