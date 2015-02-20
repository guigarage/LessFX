package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.MathematicsTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModConverterTest extends MathematicsTest {
    private ModConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = ModConverter.getInstance();
    }

    @Override
     public void testInteger() {
        String input = "mod(3, 2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3 % 2, 0.000001);
    }

    @Override
    public void testDouble() {
        String input = "mod(3.4, 2.1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3.4 % 2.1, 0.000001);
    }

    @Override
    public void testNegInteger() {
        String inputs[] = new String[] {
                "mod(-3, 2)",
                "mod(3, -2)",
                "mod(-3, -2)"

        };

        double outputs[] = new double[] {
                -3 % 2,
                3 % -2,
                -3 % -2
        };

        for(int i = 0; i < 3; i++) {
            String input = inputs[i];
            ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
            Size result = converter.convert(value, null);

            assertNotNull(result);
            assertEquals(result.getValue(), outputs[i], 0.000001);
        }
    }

    @Override
    public void testNegDouble() {
        String inputs[] = new String[] {
                "mod(-3.2, 2.2)",
                "mod(3.2, -2.2)",
                "mod(-3.2, -2.2)"

        };

        double outputs[] = new double[] {
                -3.2 % 2.2,
                3.2 % -2.2,
                -3.2 % -2.2
        };

        for(int i = 0; i < 3; i++) {
            String input = inputs[i];
            ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
            Size result = converter.convert(value, null);

            assertNotNull(result);
            assertEquals(result.getValue(), outputs[i], 0.000001);
        }
    }

    @Override
    public void testMultipleParameters() {
        this.testInteger();
    }

    @Override
     public void testUnits() {
        String input = "mod(3px, 2cm)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(result.getValue(), 3 % 2, 0.000001);
        assertEquals(SizeUnits.PX, result.getUnits());
    }

    @Override
    public void testNaN() {
        String input = "mod(Not a number!)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmpty() {
        String input = "mod()";
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