package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArcTanConverterTest {
    private ArcTanConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = ArcTanConverter.getInstance();
    }

    @Test
    public void testInteger() {
        String input = "atan(3)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.atan(3), result);
    }

    @Test
    public void testNegInteger() {
        String input = "atan(-3)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.atan(-3), result);
    }

    @Test
    public void testDouble() {
        String input = "atan(3.5)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.atan(3.5), result);
    }

    @Test
    public void testNegDouble() {
        String input = "atan(-3.5)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.atan(-3.5), result);
    }

    @Test
    public void testUnit() {
        String input = "atan(3px)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testNaN() {
        String input = "atan(Not a number!)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testMultipleParameters() {
        String input = "atan(3, 43)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

}