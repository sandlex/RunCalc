package com.sandlex.running.jd;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;
import java.util.List;

/**
 * author: Alexey Peskov
 */
public class CalculatorTest {

    public static final String SCHEMA1 = "2E + 3 x 1T w/2 min rest + 3 x 3 min H w/2 min jg + 4 x 200 R w/200 jg + 1E";
    public static final String SCHEMA2 = "L = lesser of 15 miles (24 km) & 100 min";
    public static final String SCHEMA3 = "30 min E + 9M";
    public static final String SCHEMA4 = "2E + 5M + 1E + 4M + 2E";
    public static final String SCHEMA5 = "2E + 3T + 3 min rest + 2T + 2 min rest + 2 x 400R w/400jg + 2 x 1T w/1 min rest + 1E + 4 x 200R w/200jg + 1E";

    public static final String PACES = "E=4:45,L=4:45,M=4:14,T=4:00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20";
    public static final String PACES_ERR1 = "E=4:45,L=4:45,M=4:14,T-4:00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20";
    public static final String PACES_ERR2 = "E=4:45,L=4:45,M=4:14,T=4.00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20";
    public static final String PACES_ERR3 = "E=4:4d5,L=4:45,M=4:14,T=4.00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testRebuildPacesErr1() {
        thrown.expect(IllegalArgumentException.class);
        Activity activity = new Activity(PACES_ERR1, SCHEMA1);
        thrown.expectMessage("Cannot parse pace: T-4:00");
        activity.rebuildPaces();
    }

    @Test
    public void testRebuildPacesErr2() {
        thrown.expect(IllegalArgumentException.class);
        Activity activity = new Activity(PACES_ERR2, SCHEMA1);
        thrown.expectMessage("Cannot parse pace value: 4.00");
        activity.rebuildPaces();
    }

    @Test(expected = NumberFormatException.class)
    public void testRebuildPacesErr3() {
        Activity activity = new Activity(PACES_ERR3, SCHEMA1);
        activity.rebuildPaces();
    }

    @Test
    public void testRebuildPacesSize() {
        Activity activity = new Activity(PACES, SCHEMA1);
        List<Pace> paces = activity.rebuildPaces();
        Assert.assertEquals(9, paces.size());
    }

    @Test
    public void testRebuildPacesOrder() {
        Activity activity = new Activity(PACES, SCHEMA1);
        List<Pace> paces = activity.rebuildPaces();
        Assert.assertEquals("rest", paces.get(0).getName());
    }

    @Test
    public void testRebuildPacesValueInSeconds() {
        Activity activity = new Activity(PACES, SCHEMA1);
        List<Pace> paces = activity.rebuildPaces();
        Assert.assertEquals(320, paces.get(0).getTime());
    }

    @Test
    public void testRebuildSchema1() {
        Activity activity = new Activity(PACES, SCHEMA1);
        Collection<String> parts = activity.rebuildSchema();
        Assert.assertEquals(22, parts.size());
    }

    public void testRebuildSchema2() {
        Activity activity = new Activity(PACES, SCHEMA2);
        Collection<String> parts = activity.rebuildSchema();
        Assert.assertEquals(22, parts.size());
    }

    @Test
    public void testRebuildSchema3() {
        Activity activity = new Activity(PACES, SCHEMA3);
        Collection<String> parts = activity.rebuildSchema();
        Assert.assertEquals(2, parts.size());
    }

    @Test
    public void testRebuildSchema4() {
        Activity activity = new Activity(PACES, SCHEMA4);
        Collection<String> parts = activity.rebuildSchema();
        Assert.assertEquals(5, parts.size());
    }

    @Test
    public void testRebuildSchema5() {
        Activity activity = new Activity(PACES, SCHEMA5);
        Collection<String> parts = activity.rebuildSchema();
        Assert.assertEquals(23, parts.size());
    }

    public void testCalculate1() {
        Activity activity = new Activity(PACES, SCHEMA1);
        Target target = activity.calculate();
        Assert.assertEquals("01:38:57", target.getTime());
        Assert.assertEquals(22.4, target.getDistance(), 2);
    }

    public void testCalculate2() {
        Activity activity = new Activity(PACES, SCHEMA2);
        Target target = activity.calculate();
        Assert.assertEquals("01:38:57", target.getTime());
        Assert.assertEquals(22.4, target.getDistance(), 2);
    }

    public void testCalculate3() {
        Activity activity = new Activity(PACES, SCHEMA3);
        Target target = activity.calculate();
        Assert.assertEquals("01:38:57", target.getTime());
        Assert.assertEquals(22.4, target.getDistance(), 2);
    }

    @Test
    public void testCalculate4() {
        Activity activity = new Activity(PACES, SCHEMA4);
        Target target = activity.calculate();
        Assert.assertEquals("01:38:57", target.getTime());
        Assert.assertEquals(22.4, target.getDistance(), 2);
    }

    public void testCalculate5() {
        Activity activity = new Activity(PACES, SCHEMA4);
        Target target = activity.calculate();
        Assert.assertEquals("01:38:57", target.getTime());
        Assert.assertEquals(22.4, target.getDistance(), 2);
    }

//    "Cannot parse time value: " + parts[0].trim()
//    "Cannot parse distance value: " + distanceStr
//    "Cannot determine pace of: " + phase
}
