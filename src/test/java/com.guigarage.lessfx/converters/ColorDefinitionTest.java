package com.guigarage.lessfx.converters;

import org.junit.Test;

/**
 * @author Robin Küster
 * @since 2015-03-03
 */
public abstract class ColorDefinitionTest {
    @Test
    public abstract void testInteger();

    @Test
    public abstract void testPercentage();

    @Test
    public abstract void testMissingParameters();

    @Test
    public abstract void testTooManyParameters();

    @Test
    public abstract void testEmptyParameters();

    @Test
    public abstract void testOutOfRange();
}
