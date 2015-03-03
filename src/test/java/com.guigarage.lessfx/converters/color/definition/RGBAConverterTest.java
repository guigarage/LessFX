package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.ColorDefinitionTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import org.junit.Before;

import static org.junit.Assert.*;

public class RGBAConverterTest extends ColorDefinitionTest {
    RGBAConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = RGBAConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "rgba(128, 128, 128, 1.0)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("Integer not null", result);
        assertEquals("R integer", 0.5, result.getRed(), 0.01);
        assertEquals("G integer", 0.5, result.getGreen(), 0.01);
        assertEquals("B integer", 0.5, result.getBlue(), 0.01);
        assertEquals("A integer", 1.0, result.getOpacity(), 0.01);
    }

    @Override
    public void testPercentage() {
        String input = "rgba(50%, 50%, 50%, 1.0)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("% not null", result);
        assertEquals("R %", 0.5, result.getRed(), 0.01);
        assertEquals("G %", 0.5, result.getGreen(), 0.01);
        assertEquals("B %", 0.5, result.getBlue(), 0.01);
        assertEquals("A %", 1.0, result.getOpacity(), 0.01);
    }

    @Override
    public void testMissingParameters() {
        String input = "rgba(50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Missing parameters", result);
    }

    @Override
    public void testTooManyParameters() {
        String input = "rgba(50%, 50%, 50%, 50%, 1.0)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Too many parameters", result);
    }

    @Override
    public void testEmptyParameters() {
        String input = "rgba()";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Empty parameters", result);
    }

    @Override
    public void testOutOfRange() {
        String input = "rgba(110%, 110%, 110%, 2.0)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Out of range percentage", result);

        input = "rgba(300, 300, 300, 2.0)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertNull("Out of range integer", result);
    }
}