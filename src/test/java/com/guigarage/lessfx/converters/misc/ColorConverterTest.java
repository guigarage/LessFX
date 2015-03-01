package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.MiscTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import org.junit.Before;

import static org.junit.Assert.*;

public class ColorConverterTest extends MiscTest {
    ColorConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = ColorConverter.getInstance();
    }

    @Override
    public void testColor() {
        String input[] = new String[] {
                "color(\"#ffffff\")",
                "color(\"#fff\")",
                "color(\"#FFFFFF\")",
                "color(\"#FFF\")"
        };

        for (String anInput : input) {
            ParsedValue<String, Color> value = new ParsedValueImpl<>(anInput, converter);
            Color result = converter.convert(value, null);

            assertNotNull(result);
            assertEquals(1.0d, result.getRed(), 0.0001);
            assertEquals(1.0d, result.getGreen(), 0.0001);
            assertEquals(1.0d, result.getBlue(), 0.0001);
        }
    }

    @Override
    public void testOneParam() {
        this.testColor();
    }

    @Override
    public void testMultipleParam() {
        String input = "color(\"#FFFFFF\", 394)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testString() {
        this.testColor();
    }

    @Override
    public void testEmptyParam() {
        String input = "color()";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testInteger() {
        String input = "color(1)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testNegInteger() {
        String input = "color(-1)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testDouble() {
        String input = "color(1.5)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testNegDouble() {
        String input = "color(-1.5)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull(result);
    }
}