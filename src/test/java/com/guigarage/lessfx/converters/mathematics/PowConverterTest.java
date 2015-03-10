package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PowConverterTest extends MathematicsTest {
    private PowConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = PowConverter.getInstance();
    }

    @Test
    public void testInteger() {
        String input = "pow(3, 2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.pow(3, 2), result.getValue(), 0.0001);
    }

    @Test
    public void testNegInteger() {
        String input = "pow(3, -2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.pow(3, -2), result.getValue(), 0.0001);
    }

    @Test
    public void testDouble() {
        String input = "pow(3.2, 2.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.pow(3.2, 2.2), result.getValue(), 0.0001);
    }

    @Test
    public void testNegDouble() {
        String input = "pow(3.2, -2.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.pow(3.2, -2.2), result.getValue(), 0.0001);
    }

    @Override
    public void testMultipleParameters() {
        this.testInteger();
    }

    @Test
    public void testUnit() {
        String input = "pow(3.2cm, 2.2rad)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.pow(3.2, 2.2), result.getValue(), 0.0001);
        assertEquals(SizeUnits.CM, result.getUnits());
    }

    @Test
    public void testNeg1Double() {
        String input = "pow(-3.2, 2.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testNaN() {
        String input = "pow(not a number!)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input = "pow()";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testOneParameter() {
        String input = "pow(3)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testUnits() {
        String input = "pow(3.2cm, 2.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(Math.pow(3.2, 2.2), result.getValue(), 0.0001);
    }

}