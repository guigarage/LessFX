package com.guigarage.lessfx.converters;

import org.junit.Test;

/**
 * @author Robin Küster
 * @since 2015-02-18
 */
public abstract class MathematicsTest {
    @Test
    public abstract void testInteger();

    @Test
    public abstract void testDouble();

    @Test
    public abstract void testNegInteger();

    @Test
    public abstract void testNegDouble();

    @Test
    public abstract void testMultipleParameters();

    @Test
    public abstract void testOneParameter();

    @Test
    public abstract void testUnits();

    @Test
    public abstract void testNaN();

    @Test
    public abstract void testEmpty();
}
