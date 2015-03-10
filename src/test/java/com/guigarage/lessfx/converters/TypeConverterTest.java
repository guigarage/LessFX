package com.guigarage.lessfx.converters;

import org.junit.Test;

/**
 * @author Robin Küster
 * @since 2015-03-02
 */
public abstract class TypeConverterTest {
    @Test
    public abstract void testColorHex();

    @Test
    public abstract void testColorKeyword();

    @Test
    public abstract void testString();

    @Test
    public abstract void testInteger();

    @Test
    public abstract void testPixel();

    @Test
    public abstract void testEm();

    @Test
    public abstract void testPercentage();
}
