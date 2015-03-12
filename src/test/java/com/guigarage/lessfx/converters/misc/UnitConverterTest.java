package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.MiscTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;
import org.junit.Before;

import static org.junit.Assert.*;

public class UnitConverterTest extends MiscTest {
    UnitConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = UnitConverter.getInstance();
    }

    @Override
    public void testColor() {
        String input = "unit(#FFFFFF)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testOneParam() {
        String input = "unit(1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNotNull(result);
        assertEquals(result.getUnits(), SizeUnits.PX);
    }

    @Override
    public void testMultipleParam() {
        String input = "unit(1, mm)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNotNull(result);
        assertEquals(result.getUnits(), SizeUnits.MM);
    }

    @Override
    public void testString() {
        String input = "unit(\"String\")";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testEmptyParam() {
        String input = "unit()";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNull(result);
    }

    @Override
    public void testInteger() {
        this.testOneParam();
    }

    @Override
    public void testNegInteger() {
        String input = "unit(-1)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNotNull(result);
        assertEquals(result.getUnits(), SizeUnits.PX);
    }

    @Override
    public void testDouble() {
        String input = "unit(1.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNotNull(result);
        assertEquals(result.getUnits(), SizeUnits.PX);
    }

    @Override
    public void testNegDouble() {
        String input = "unit(-1.2)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNotNull(result);
        assertEquals(result.getUnits(), SizeUnits.PX);
    }

    @Override
    public void testUnit() {
        String input = "unit(1mm)";
        ParsedValue<String, Size> value = new ParsedValueImpl<>(input, converter);
        Size result = converter.convert(value, Font.getDefault());

        assertNotNull(result);
        assertEquals(result.getUnits(), SizeUnits.PX);
    }
}