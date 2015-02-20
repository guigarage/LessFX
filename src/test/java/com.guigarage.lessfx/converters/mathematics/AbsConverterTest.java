package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AbsConverterTest extends MathematicsTest {
    private AbsConverter converter;

    @Before
    public void initialize() {
        converter = AbsConverter.getInstance();
    }

    @Override
    public void testUnits() {
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

    @Override
    public void testInteger() {
        String input = "abs(3)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.abs(3), 0.0001);
    }

    @Override
    public void testDouble() {
        String input = "abs(3.5)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.abs(3.5), 0.0001);
    }

    @Override
    public void testNegInteger() {
        String input = "abs(-3.0)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.abs(-3.0), 0.1);
    }

    @Override
    public void testNegDouble() {
        String input = "abs(-3.0px)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.abs(-3.0), 0.1);
    }

    @Override
    public void testMultipleParameters() {
        String input ="abs(-3, 4)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testOneParameter() {
        this.testInteger();
    }

    @Override
    public void testNaN() {
        String input = "abs(Not a Number!)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input ="abs()";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }
}