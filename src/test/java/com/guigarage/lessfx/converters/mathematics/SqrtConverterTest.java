package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqrtConverterTest extends MathematicsTest {
    private SqrtConverter converter;

    @Before
    public void initialize() {
        converter = SqrtConverter.getInstance();
    }

    @Test
     public void testDouble() {
        String input = "sqrt(3.5)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.sqrt(3.5), 0.0001);
    }

    @Test
    public void testInteger() {
        String input = "sqrt(3)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), Math.sqrt(3), 0.0001);
    }

    @Test
    public void testNegDouble() {
        String input = "sqrt(-3.0)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testMultipleParameters() {
        String input = "sqrt(3.0, 4)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testOneParameter() {
        this.testInteger();
    }

    @Test
    public void testNegInteger() {
        String input = "sqrt(-3)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Test
    public void testUnits() {
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

    @Override
    public void testNaN() {
        String input = "sqrt(Not a number!)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input = "sqrt()";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

}