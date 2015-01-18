package com.guigarage.lessfx.converters.mathematics;

import com.guigarage.lessfx.converters.LessStyleConverter;
import com.sun.javafx.css.Size;
import javafx.css.ParsedValue;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

/**
 * @author Robin Küster
 * @since 2015-01-15
 */
public class SqrtConverter extends LessStyleConverter<String, Size> {
    private static class Holder {
        static final SqrtConverter INSTANCE = new SqrtConverter();
    }

    public static LessStyleConverter<String, Size> getInstance() {
        return Holder.INSTANCE;
    }

    private SqrtConverter() {
        super();
    }

    @Override
    public Size convert(ParsedValue<String, Size> value, Font font) {
        Matcher matcher = this.getMatcher(value.getValue(), "^sqrt\\(([0-9]+\\.?[0-9]*)(%|in|cm|mm|em|ex|pt|pc|px|grad|deg|rad|turn)?\\)$");

        // nonsensical input
        if (matcher == null) {
            return null;
        }

        // first parameter: value. Second parameter: unit
        return new Size(Math.sqrt(Double.valueOf(matcher.group(1))), this.getUnit(matcher.group(2)));
    }
}
