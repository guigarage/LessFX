package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.MiscTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageSizeConverterTest extends MiscTest {
    ImageSizeConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = ImageSizeConverter.getInstance();
    }

    @Override
    public void testColor() {
        String input = "image-size(#FFFFFF)";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("Color", result);
    }

    @Override
    public void testOneParam() {
        this.testImage();
    }

    @Override
    public void testMultipleParam() {
        String input = "image-size(#FFFFFF)";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("Color", result);
    }

    @Override
    public void testString() {
        String input = "image-size(\"NonexistingFile.png\")";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("String", result);
    }

    @Override
    public void testEmptyParam() {
        String input = "image-size()";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("Empty", result);
    }

    @Override
    public void testInteger() {
        String input = "image-size(1)";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("Integer", result);
    }

    @Override
    public void testNegInteger() {
        String input = "image-size(-1)";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("Negative integer", result);
    }

    @Override
    public void testDouble() {
        String input = "image-size(1.2)";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("Double", result);
    }

    @Override
    public void testNegDouble() {
        String input = "image-size(1.2)";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("Negative double", result);
    }

    @Override
    public void testUnit() {
        String input = "image-size(1.2px)";
        ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
        Size[] result = converter.convert(value, null);

        assertNull("Units", result);
    }

    @Test
    public void testImage() {
        String images[] = new String[] {
                "BMPTest.bmp", "GIFTest.gif", "JPGTest.jpg", "PNGTest.png"
        };

        for (String image : images) {
            String input = "image-size(\"TestImages/" + image + "\")";
            ParsedValue<String, Size[]> value = new ParsedValueImpl<>(input, converter);
            Size[] result = converter.convert(value, null);

            assertNotNull(image + " Not Null", result);
            assertEquals(image + " Width", result[0].getValue(), 20.0, 0.1);
            assertEquals(image + " Height", result[1].getValue(), 20.0, 0.1);
        }
    }
}