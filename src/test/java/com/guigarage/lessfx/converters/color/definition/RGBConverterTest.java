package com.guigarage.lessfx.converters.color.definition;

import com.guigarage.lessfx.converters.ColorDefinitionTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import javafx.scene.paint.Color;
import org.junit.Before;

import static org.junit.Assert.*;

public class RGBConverterTest extends ColorDefinitionTest {
    private RGBConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = RGBConverter.getInstance();
    }

    @Override
    public void testInteger() {
        String input = "rgb(128, 128, 128)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("Integer not null", result);
        assertEquals("R integer", 0.5, result.getRed(), 0.01);
        assertEquals("G integer", 0.5, result.getGreen(), 0.01);
        assertEquals("B integer", 0.5, result.getBlue(), 0.01);
    }

    @Override
    public void testPercentage() {
        String input = "rgb(50%, 50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNotNull("% not null", result);
        assertEquals("R %", 0.5, result.getRed(), 0.01);
        assertEquals("G %", 0.5, result.getGreen(), 0.01);
        assertEquals("B %", 0.5, result.getBlue(), 0.01);
    }

    @Override
    public void testMissingParameters() {
        String input = "rgb(50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Missing parameters", result);
    }

    @Override
    public void testTooManyParameters() {
        String input = "rgb(50%, 50%, 50%, 50%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Too many parameters", result);
    }

    @Override
    public void testEmptyParameters() {
        String input = "rgb()";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Empty parameters", result);
    }

    @Override
    public void testOutOfRange() {
        String input = "rgb(110%, 110%, 110%)";
        ParsedValue<String, Color> value = new ParsedValueImpl<>(input, converter);
        Color result = converter.convert(value, null);

        assertNull("Out of range percentage", result);

        input = "rgb(300, 300, 300)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertNull("Out of range integer", result);
    }
}