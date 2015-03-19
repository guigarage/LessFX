package com.guigarage.lessfx.converters.misc;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @version 1.0-SNAPSHOT
 * @since 2015-03-06
 */
public class ImageSizeConverter extends LessStyleConverter<String, Size[]> {
    /**
     * Regular Expression to parse the function call.
     */
    private final static String REGEX = "^image-size\\(\\\"(.*)\"\\)$";

    /**
     * Initialization-on-demand holder
     */
    private static class Holder {
        static final ImageSizeConverter INSTANCE = new ImageSizeConverter();
    }

    /**
     * Instance of the converter that got initialize in the Holder.
     *
     * @return Instance of the converter.
     */
    public static ImageSizeConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts the given CSS function call to a Java object.
     *
     * @param value ParsedValue containing the function call
     * @param font Font used for functions that depend on the font size
     * @return The result of the function call as a Java object or null if function call failed
     */
    @Override
    public Size[] convert(ParsedValue<String, Size[]> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), REGEX);

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        BufferedImage reader;
        try {
            File file = new File(matcher.group(1));
            reader = ImageIO.read(file);
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }

        Size sizes[] = new Size[2];

        //width
        sizes[0] = new Size(reader.getWidth(), SizeUnits.PX);

        //height
        sizes[1] = new Size(reader.getHeight(), SizeUnits.PX);

        return sizes;
    }
}
