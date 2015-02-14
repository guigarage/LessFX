package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModConverterTest {
    private ModConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = ModConverter.getInstance();
    }

    @Test
     public void testInteger() {
        String input = "mod(3, 2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3 % 2, 0.000001);
    }

    @Test
    public void testDouble() {
        String input = "mod(3.4, 2.1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3.4 % 2.1, 0.000001);
    }

    @Test
    public void testNeg1Integer() {
        String input = "mod(-3, 2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), -3 % 2, 0.000001);
    }

    @Test
     public void testNeg2Integer() {
        String input = "mod(3, -2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3 % -2, 0.000001);
    }

    @Test
    public void testNeg3Integer() {
        String input = "mod(-3, -2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), -3 % -2, 0.000001);
    }

    @Test
    public void testNeg1Double() {
        String input = "mod(-3.4, 2.1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), -3.4 % 2.1, 0.000001);
    }

    @Test
    public void testNeg2Double() {
        String input = "mod(3.4, -2.1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3.4 % -2.1, 0.000001);
    }

    @Test
    public void testNeg3Double() {
        String input = "mod(-3.4, -2.1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), -3.4 % -2.1, 0.000001);
    }

    @Test
     public void testUnitInteger() {
        String input = "mod(3px, 2cm)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3 % 2, 0.000001);
        assertEquals(SizeUnits.PX, result.getUnits());
    }

    @Test
    public void testUnitDouble() {
        String input = "mod(3.2px, 2.2cm)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3.2 % 2.2, 0.000001);
        assertEquals(SizeUnits.PX, result.getUnits());
    }

    @Test
    public void testNaN() {
        String input = "mod(Not a number!)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testZero() {
        String input = "mod(3, 0)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testOneParameter() {
        String input = "mod(3)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }
}