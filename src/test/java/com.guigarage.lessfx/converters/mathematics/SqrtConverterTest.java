package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqrtConverterTest {
    private SqrtConverter converter;

    @Before
    public void initialize() {
        converter = (SqrtConverter)SqrtConverter.getInstance();
    }

    @Test
     public void testDoubleValue() {
        String input = "sqrt(3.5)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.sqrt(3.5), 0.0001);
    }

    @Test
    public void testIntegerValue() {
        String input = "sqrt(3)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.sqrt(3), 0.0001);
    }

    @Test
    public void testNegativeValue() {
        String input = "sqrt(-3.0)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testNegativeWithUnitValue() {
        String input = "sqrt(-3.0px)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testUnitsValue() {
        String units[] = new String[] {
                "%", "in", "cm", "mm", "em", "ex", "pt", "pc", "px", "grad", "deg", "rad", "turn"
        };

        for (String unit : units) {
            String input = "sqrt(3.0"+unit+")";
            ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
            Size result = converter.convert(value, null);

            assertNotNull(result);
            assertEquals(result.getValue(), Math.sqrt(3.0), 0.0001);
        }
    }

}