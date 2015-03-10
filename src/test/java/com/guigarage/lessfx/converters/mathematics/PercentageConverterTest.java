package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercentageConverterTest extends MathematicsTest {
    private PercentageConverter converter;

    @Before
    public void initialize() {
        converter = (PercentageConverter)PercentageConverter.getInstance();
    }

    @Test
    public void testInteger() {
        String input = "percentage(1)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, "100%");
    }

    @Test
    public void testNegInteger() {
        String input = "percentage(-1)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, "-100%");
    }

    @Test
    public void testDouble() {
        String input = "percentage(0.5)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, "50%");
    }

    @Test
    public void testNegDouble() {
        String input = "percentage(-0.5)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result, "-50%");
    }

    @Override
    public void testMultipleParameters() {
        String input = "percentage(3, 4)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testOneParameter() {
        this.testInteger();
    }

    @Override
    public void testUnits() {
        String input = "percentage(3cm)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testNaN() {
        String input = "percentage(not a number!)";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testEmpty() {
        String input = "percentage()";
        ParsedValue<String, String> value = new ParsedValueImpl<>(input, converter);
        String result = converter.convert(value, null);

        assertNull(result);
    }

}