package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.ColorDefinitionTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import org.junit.Before;

import static org.junit.Assert.*;

public class HSVConverterTest extends ColorDefinitionTest {
    HSVConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = HSVConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "hsv(180, 0.5, 0.5)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("Integer not null", result);
        assertEquals("H integer", 180.0, result.getHue(), 0.01);
        assertEquals("S integer", 0.5, result.getSaturation(), 0.01);
        assertEquals("V integer", 0.5, result.getBrightness(), 0.01);
    }

    @Override
    public void testPercentage() {
        String input = "hsv(180, 50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("% not null", result);
        assertEquals("H %", 180, result.getHue(), 0.01);
        assertEquals("S %", 0.5, result.getSaturation(), 0.01);
        assertEquals("V %", 0.5, result.getBrightness(), 0.01);
    }

    @Override
    public void testMissingParameters() {
        String input = "hsv(50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Missing parameters", result);
    }

    @Override
    public void testTooManyParameters() {
        String input = "hsv(50%, 50%, 50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Too many parameters", result);
    }

    @Override
    public void testEmptyParameters() {
        String input = "hsv()";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Empty parameters", result);
    }

    @Override
    public void testOutOfRange() {
        String input = "hsv(370, 110%, 110%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Out of range percentage", result);

        input = "hsv(370, 1.1, 1.1)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertNull("Out of range integer", result);
    }
}