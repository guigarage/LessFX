package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ArcSinusConverterTest {
    private ArcSinusConverter converter;

    @Before
    public void initialize() {
        this.converter = ArcSinusConverter.getInstance();
    }

    @Test
    public void testHalfPi() {
        String input = "asin("+Math.PI/2+")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.asin(Math.PI/2), result.doubleValue(), 0.00001);
    }

    @Test
    public void testNegativeHalfPi() {
        String input = "asin("+Math.PI/-2+")";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.asin(Math.PI/-2), result.doubleValue(), 0.00001);
    }

    @Test
    public void testOOR() {
        String input = "asin(2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Double.isNaN(result.doubleValue()), true);
    }

    @Test
    public void testEmpty() {
        String input = "asin()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testNaN() {
        String input = "asin(not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

}