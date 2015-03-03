package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.ColorDefinitionTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import org.junit.Before;

import static org.junit.Assert.*;

public class HSLConverterTest extends ColorDefinitionTest {
    HSLConverter converter;
    
    @Before
    public void setUp() throws Exception {
        converter = HSLConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "hsl(180, 0.5, 0.5)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("Integer not null", result);
        assertEquals("R integer", (double)63 / 255, result.getRed(), 0.01);
        assertEquals("G integer", (double)191 / 255, result.getGreen(), 0.01);
        assertEquals("B integer", (double)191 / 255, result.getBlue(), 0.01);
    }

    @Override
    public void testPercentage() {
        String input = "hsl(180, 50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("% not null", result);
        assertEquals("R %", (double)63 / 255, result.getRed(), 0.01);
        assertEquals("G %", (double)191 / 255, result.getGreen(), 0.01);
        assertEquals("B %", (double)191 / 255, result.getBlue(), 0.01);
    }

    @Override
    public void testMissingParameters() {
        String input = "hsl(50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Missing parameters", result);
    }

    @Override
    public void testTooManyParameters() {
        String input = "hsl(50%, 50%, 50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Too many parameters", result);
    }

    @Override
    public void testEmptyParameters() {
        String input = "hsl()";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Empty parameters", result);
    }

    @Override
    public void testOutOfRange() {
        String input = "hsl(370, 110%, 110%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Out of range percentage", result);

        input = "hsl(370, 1.1, 1.1)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertNull("Out of range integer", result);
    }
}