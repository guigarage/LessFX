package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.TypeConverterTest;
import com.sun.javafx.css.ParsedValueImpl;
import javafx.css.ParsedValue;
import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsColorConverterTest extends TypeConverterTest {
    private final static String[] predefined = new String[]{
            "aliceblue", "antiquewhite", "aqua", "aquamarine",
            "azure", "beige", "bisque", "black",
            "blanchedalmond", "blue", "blueviolet", "brown",
            "burlywood", "cadetblue", "chartreuse", "chocolate",
            "coral", "cornflowerblue", "cornsilk", "crimson",
            "cyan", "darkblue", "darkcyan", "darkgoldenrod",
            "darkgray", "darkgreen", "darkgrey", "darkkhaki",
            "darkmagenta", "darkolivegreen", "darkorange", "darkorchid",
            "darkred", "darksalmon", "darkseagreen", "darkslateblue",
            "darkslategray", "darkslategrey", "darkturquoise", "darkviolet",
            "deeppink", "deepskyblue", "dimgray", "dimgrey",
            "dodgerblue", "firebrick", "floralwhite", "forestgreen",
            "fuchsia", "gainsboro", "ghostwhite", "gold",
            "goldenrod", "gray", "green", "greenyellow",
            "grey", "honeydew", "hotpink", "indianred",
            "indigo", "ivory", "khaki", "lavender",
            "lavenderblush", "lawngreen", "lemonchiffon", "lightblue",
            "lightcoral", "lightcyan", "lightgoldenrodyellow", "lightgray",
            "lightgreen", "lightgrey", "lightpink", "lightsalmon",
            "lightseagreen", "lightskyblue", "lightslategray", "lightslategrey",
            "lightsteelblue", "lightyellow", "lime", "limegreen",
            "linen", "magenta", "maroon", "mediumaquamarine",
            "mediumblue", "mediumorchid", "mediumpurple", "mediumseagreen",
            "mediumslateblue", "mediumspringgreen", "mediumturquoise", "mediumvioletred",
            "midnightblue", "mintcream", "mistyrose", "moccasin",
            "navajowhite", "navy", "oldlace", "olive",
            "olivedrab", "orange", "orangered", "orchid",
            "palegoldenrod", "palegreen", "paleturquoise", "palevioletred",
            "papayawhip", "peachpuff", "peru", "pink",
            "plum", "powderblue", "purple", "red",
            "rosybrown", "royalblue", "saddlebrown", "salmon",
            "sandybrown", "seagreen", "seashell", "sienna",
            "silver", "skyblue", "slateblue", "slategray",
            "slategrey", "snow", "springgreen", "steelblue",
            "tan", "teal", "thistle", "tomato",
            "turquoise", "violet", "wheat", "white",
            "whitesmoke", "yellow", "yellowgreen", "transparent"
    };

    private IsColorConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = IsColorConverter.getInstance();
    }

    @Override
    public void testColorHex() {
        String input = "iscolor(#ff0)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertTrue("One-digit hex color", result);

        input = "iscolor(#ffff00)";
        value = new ParsedValueImpl<>(input, converter);
        result = converter.convert(value, null);

        assertTrue("Two-digit hex color", result);
    }

    @Override
    public void testColorKeyword() {
        for (String s : predefined) {
            String input = "iscolor("+s+")";
            ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
            boolean result = converter.convert(value, null);

            assertTrue("Keyword color: "+s, result);
        }
    }

    @Override
    public void testString() {
        String input = "iscolor(\"String!\")";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("String", result);
    }

    @Override
    public void testInteger() {
        String input = "iscolor(1)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Integerr", result);
    }

    @Override
    public void testPixel() {
        String input = "iscolor(1px)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Px", result);
    }

    @Override
    public void testEm() {
        String input = "iscolor(1em)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Em", result);
    }

    @Override
    public void testPercentage() {
        String input = "iscolor(1%)";
        ParsedValue<String, Boolean> value = new ParsedValueImpl<>(input, converter);
        boolean result = converter.convert(value, null);

        assertFalse("Percentage", result);
    }
}