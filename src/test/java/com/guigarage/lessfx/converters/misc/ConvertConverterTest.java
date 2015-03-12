package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.MiscTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;
import org.junit.Before;

import static org.junit.Assert.*;

public class ConvertConverterTest extends MiscTest {
    ConvertConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = ConvertConverter.getInstance();
    }

    @Override
    public void testColor() {
        String input = "convert(#FFFFFF)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testOneParam() {
        this.testColor();
    }

    @Override
    public void testMultipleParam() {
        this.testUnit();
    }

    @Override
    public void testString() {
        String input = "convert(\"String\")";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testEmptyParam() {
        String input = "convert()";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testInteger() {
        String input = "convert(1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testNegInteger() {
        String input = "convert(-1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testDouble() {
        String input = "convert(1.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testNegDouble() {
        String input = "convert(-1.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testUnit() {
        String outputUnits[][] = new String[][] {
                {"cm", "mm", "in", "pt", "pc", "px"},
                {"rad", "deg", "grad", "turn"}
        };

        String inputUnits[] = new String[] {
                "mm", "rad"
        };

        for (int i = 0; i < inputUnits.length; i++) {
            for (int j = 0; j < outputUnits.length; j++) {
                String input = "convert(1"+outputUnits[i][j]+", "+inputUnits[i]+")";
                ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
                Size result = converter.convert(value, Font.getDefault());

                assertNotNull(result);
                assertEquals("Units", inputUnits[i], result.getUnits().toString());

                input = "convert(" + result.getValue() + inputUnits[i] + ", " + outputUnits[i][j] + ")";
                value = new ParsedValueImpl<>(input, converter);
                result = converter.convert(value, Font.getDefault());

                assertNotNull(result);
                assertEquals("Value! Output unit: " + outputUnits[i][j] +"; Input unit: " + inputUnits[i], 1.0, result.getValue(), 0.0001);
                assertEquals("Unit calculated back to rad or mm", outputUnits[i][j], result.getUnits().toString());
            }
        }

    }
}