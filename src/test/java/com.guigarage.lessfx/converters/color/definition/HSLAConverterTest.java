package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.ColorDefinitionTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import org.junit.Before;

import static org.junit.Assert.*;

public class HSLAConverterTest extends ColorDefinitionTest {
    HSLAConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = HSLAConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "hsla(180, 0.5, 0.5, 0.5)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("Integer not null", result);
        assertEquals("R integer", (double)63 / 255, result.getRed(), 0.01);
        assertEquals("G integer", (double)191 / 255, result.getGreen(), 0.01);
        assertEquals("B integer", (double)191 / 255, result.getBlue(), 0.01);
        assertEquals("alpha integer", 0.5, result.getOpacity(), 0.01);
    }

    @Override
    public void testPercentage() {
        String input = "hsla(180, 50%, 50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("% not null", result);
        assertEquals("R %", (double)63 / 255, result.getRed(), 0.01);
        assertEquals("G %", (double)191 / 255, result.getGreen(), 0.01);
        assertEquals("B %", (double)191 / 255, result.getBlue(), 0.01);
        assertEquals("alpha %", 0.5, result.getOpacity(), 0.01);
    }

    @Override
    public void testMissingParameters() {
        String input = "hsla(50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Missing parameters", result);
    }

    @Override
    public void testTooManyParameters() {
        String input = "hsla(50%, 50%, 50%, 50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Too many parameters", result);
    }

    @Override
    public void testEmptyParameters() {
        String input = "hsla()";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Empty parameters", result);
    }

    @Override
    public void testOutOfRange() {
        String input = "hsla(370, 110%, 110%, 110%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Out of range percentage", result);

        input = "hsla(370, 1.1, 1.1, 1.1)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertNull("Out of range integer", result);
    }
}