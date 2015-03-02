package com.guigarage.lessfx.converters.type;

import com.guigarage.lessfx.converters.LessStyleConverter;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-03-02
 */
public class IsColorConverter extends LessStyleConverter<String, Boolean> {
    private final static ArrayList<String> predefined = new ArrayList<>(Arrays.asList(new String[] {
            "aliceblue",        "antiquewhite",         "aqua",                 "aquamarine",
            "azure",            "beige",                "bisque",               "black",
            "blanchedalmond",   "blue",                 "blueviolet",           "brown",
            "burlywood",        "cadetblue",            "chartreuse",           "chocolate",
            "coral",            "cornflowerblue",       "cornsilk",             "crimson",
            "cyan",             "darkblue",             "darkcyan",             "darkgoldenrod",
            "darkgray",         "darkgreen",            "darkgrey",             "darkkhaki",
            "darkmagenta",      "darkolivegreen",       "darkorange",           "darkorchid",
            "darkred",          "darksalmon",           "darkseagreen",         "darkslateblue",
            "darkslategray",    "darkslategrey",        "darkturquoise",        "darkviolet",
            "deeppink",         "deepskyblue",          "dimgray",              "dimgrey",
            "dodgerblue",       "firebrick",            "floralwhite",          "forestgreen",
            "fuchsia",          "gainsboro",            "ghostwhite",           "gold",
            "goldenrod",        "gray",                 "green",                "greenyellow",
            "grey",             "honeydew",             "hotpink",              "indianred",
            "indigo",           "ivory",                "khaki",                "lavender",
            "lavenderblush",    "lawngreen",            "lemonchiffon",         "lightblue",
            "lightcoral",       "lightcyan",            "lightgoldenrodyellow", "lightgray",
            "lightgreen",       "lightgrey",            "lightpink",            "lightsalmon",
            "lightseagreen",    "lightskyblue",         "lightslategray",       "lightslategrey",
            "lightsteelblue",   "lightyellow",          "lime",                 "limegreen",
            "linen",            "magenta",              "maroon",               "mediumaquamarine",
            "mediumblue",       "mediumorchid",         "mediumpurple",         "mediumseagreen",
            "mediumslateblue",  "mediumspringgreen",    "mediumturquoise",      "mediumvioletred",
            "midnightblue",     "mintcream",            "mistyrose",            "moccasin",
            "navajowhite",      "navy",                 "oldlace",              "olive",
            "olivedrab",        "orange",               "orangered",            "orchid",
            "palegoldenrod",    "palegreen",            "paleturquoise",        "palevioletred",
            "papayawhip",       "peachpuff",            "peru",                 "pink",
            "plum",             "powderblue",           "purple",               "red",
            "rosybrown",        "royalblue",            "saddlebrown",          "salmon",
            "sandybrown",       "seagreen",             "seashell",             "sienna",
            "silver",           "skyblue",              "slateblue",            "slategray",
            "slategrey",        "snow",                 "springgreen",          "steelblue",
            "tan",              "teal",                 "thistle",              "tomato",
            "turquoise",        "violet",               "wheat",                "white",
            "whitesmoke",       "yellow",               "yellowgreen",          "transparent"
    }));

    private final static String REGEX1 = "^iscolor\\((.*)\\)$";
    private final static String REGEX2 = "#([0-9a-zA-Z]{3}|[0-9a-zA-Z]{6})";

    private static class Holder {
        static final IsColorConverter INSTANCE = new IsColorConverter();
    }

    public static IsColorConverter getInstance() {
        return Holder.INSTANCE;
    }

    public IsColorConverter() {

    }

    @Override
    public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
        Matcher matcher = getMatcher(value.getValue(), REGEX1);

        if (matcher == null) {
            return false;
        }

        if (predefined.contains(matcher.group(1))) {
            return true;
        } else {
            matcher = getMatcher(matcher.group(1), REGEX2);
            return matcher != null;
        }
    }
}
