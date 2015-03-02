package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.MiscTest;
import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.*;

public class GetUnitConverterTest extends MiscTest {
    private GetUnitConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = GetUnitConverter.getInstance();
    }


    @Override
    public void testColor() {
        String input = "get-unit(#FFFFFFpx)";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testOneParam() {
        this.testUnit();
    }

    @Override
    public void testMultipleParam() {
        String input = "get-unit(1px, 45)";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testString() {
        String input = "get-unit(\"test\")";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testEmptyParam() {
        String input = "get-unit()";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testInteger() {
        String input = "get-unit(1)";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testNegInteger() {
        String input = "get-unit(-1)";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testDouble() {
        String input = "get-unit(1.5)";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testNegDouble() {
        String input = "get-unit(-1.5)";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNull(result);
    }

    @Override
    public void testUnit() {
        String input = "get-unit(1px)";
        ParsedValue<String, SizeUnits> value = new ParsedValueImpl<>(input, converter);
        SizeUnits result = converter.convert(value, null);

        assertNotNull(result);
        assertEquals(SizeUnits.PX, result);
    }
}