package com.guigarage.lessfx.converters;

import javafx.css.StyleConverter;

/**
 * Created by Robin Küster on 11.01.2015.
 */
public abstract class MathematicsConverter extends StyleConverter<String, Number> {
    protected String getRegex(String function) {
        return "^"+function+"\\((-?[0-9]*\\.?[0-9]*)(deg|grad)?\\)$";
    }
}
