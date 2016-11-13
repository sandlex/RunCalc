package com.sandlex.running.jd;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * author: Alexey Peskov
 */
public class CalculatorTest {

    public static final String SCHEMA1 = "2E + 3 x 1T w/2 min rests + 3 x 3 min H w/2 min jg + 4 x 200 R w/200 jg + 1E";
    public static final String SCHEMA2 = "L = lesser of 15 miles (24 km) & 100 min";
    public static final String SCHEMA3 = "30 min E + 9M";
    public static final String SCHEMA4 = "2E + 5M + 1E + 4M + 2E";
    public static final String SCHEMA5 = "2E + 3T + 3 min rest + 2T + 2 min rest + 2 x 400R w/400jg + 2 x 1T w/1 min rest + 1E + 4 x 200R w/200jg + 1E";

    @Test
    public void testRebuildSchema1() {
        Activity activity = new Activity(SCHEMA1);
        Collection<String> parts = activity.rebuild();
        Assert.assertEquals(22, parts.size());
    }

    public void testRebuildSchema2() {
        Activity activity = new Activity(SCHEMA2);
        Collection<String> parts = activity.rebuild();
        Assert.assertEquals(22, parts.size());
    }

    @Test
    public void testRebuildSchema3() {
        Activity activity = new Activity(SCHEMA3);
        Collection<String> parts = activity.rebuild();
        Assert.assertEquals(2, parts.size());
    }

    @Test
    public void testRebuildSchema4() {
        Activity activity = new Activity(SCHEMA4);
        Collection<String> parts = activity.rebuild();
        Assert.assertEquals(5, parts.size());
    }

    @Test
    public void testRebuildSchema5() {
        Activity activity = new Activity(SCHEMA5);
        Collection<String> parts = activity.rebuild();
        Assert.assertEquals(23, parts.size());
    }
}
