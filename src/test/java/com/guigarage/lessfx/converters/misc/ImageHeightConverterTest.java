package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.MiscTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageHeightConverterTest extends MiscTest {
    ImageHeightConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = ImageHeightConverter.getInstance();
    }

    @Override
    public void testColor() {
        String input = "image-height(#FFFFFF)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("Color", result);
    }

    @Override
    public void testOneParam() {
        this.testImage();
    }

    @Override
    public void testMultipleParam() {
        String input = "image-height(#FFFFFF)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("Color", result);
    }

    @Override
    public void testString() {
        String input = "image-height(\"NonexistingFile.png\")";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("String", result);
    }

    @Override
    public void testEmptyParam() {
        String input = "image-height()";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("Empty", result);
    }

    @Override
    public void testInteger() {
        String input = "image-height(1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("Integer", result);
    }

    @Override
    public void testNegInteger() {
        String input = "image-height(-1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("Negative integer", result);
    }

    @Override
    public void testDouble() {
        String input = "image-height(1.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("Double", result);
    }

    @Override
    public void testNegDouble() {
        String input = "image-height(1.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("Negative double", result);
    }

    @Override
    public void testUnit() {
        String input = "image-height(1.2px)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, null);

        assertNull("Units", result);
    }

    @Test
    public void testImage() {
        String images[] = new String[] {
                "BMPTest.bmp", "GIFTest.gif", "JPGTest.jpg", "PNGTest.png"
        };

        for (String image : images) {
            String input = "image-height(\"TestImages/" + image + "\")";
            ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
            Size result = converter.convert(value, null);

            assertNotNull(image + " Not Null", result);
            assertEquals(image + " Height", result.getValue(), 20.0, 0.1);
        }
    }
}