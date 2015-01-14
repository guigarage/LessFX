package com.guigarage.lessfx.converters.mathematics;

import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PiConverterTest {
    private PiConverter converter;

    @Before
    public void initialization() {
        this.converter = new PiConverter();
    }

    @Test
    public void testPi() {
        String input = "pi()";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(3.141592653589793, result.doubleValue(), 0.0001);
    }

    @Test
    public void testPiWithValue() {
        String input = "pi(2)";
        ParsedValue<String, Number> value = new ParsedValueImpl<>(input, converter);
        Number result = converter.convert(value, null);

        assertNull(result);
    }

}