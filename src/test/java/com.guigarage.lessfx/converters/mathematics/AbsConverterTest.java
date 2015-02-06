package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AbsConverterTest {
    private AbsConverter converter;

    @Before
    public void initialize() {
        converter = AbsConverter.getInstance();
    }

    @Test
    public void testDoubleValue() {
        String input = "abs(3.5)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.abs(3.5), 0.0001);
    }

    @Test
    public void testIntegerValue() {
        String input = "abs(3)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.abs(3), 0.0001);
    }

    @Test
    public void testNegativeValue() {
        String input = "abs(-3.0)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.abs(-3.0), 0.1);
    }

    @Test
    public void testNegativeWithUnitValue() {
        String input = "abs(-3.0px)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.abs(-3.0), 0.1);
    }

    @Test
    public void testUnitsValue() {
        String units[] = new String[] {
                "%", "in", "cm", "mm", "em", "ex", "pt", "pc", "px", "grad", "deg", "rad", "turn"
        };

        for (String unit : units) {
            String input = "abs(-3.0"+unit+")";
            ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
            Size result = converter.convert(value, null);

            assertNotNull(result);
            assertEquals(result.getValue(), Math.abs(-3.0), 0.1);
        }
    }
}