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
 * @since 2015-03-06
 */
public class ImageSizeConverter extends LessStyleConverter<String, Size[]> {
    private final static String REGEX = "^image-size\\(\\\"(.*)\"\\)$";

    private static class Holder {
        static final ImageSizeConverter INSTANCE = new ImageSizeConverter();
    }

    public static ImageSizeConverter getInstance() {
        return Holder.INSTANCE;
    }

    public ImageSizeConverter() {

    }

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
            e.printStackTrace();
            return null;
        }

        Size sizes[] = new Size[2];
        sizes[0] = new Size(reader.getWidth(), SizeUnits.PX);
        sizes[1] = new Size(reader.getHeight(), SizeUnits.PX);

        return sizes;
    }
}
