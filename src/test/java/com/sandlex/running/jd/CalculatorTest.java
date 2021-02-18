package com.sandlex.running.jd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.within;

import java.util.Collection;
import java.util.List;

/**
 * author: Alexey Peskov
 */
public class CalculatorTest {
/*
    private static final String SCHEMA1 = "2E + 3 x 1T w/2 min rest + 3 x 3 min H w/2 min jg + 4 x 200 R w/200 jg + 1E";
    private static final String SCHEMA2 = "L = lesser of 15 miles (24 km) & 100 min";
    private static final String SCHEMA3 = "30 min E + 9M";
    private static final String SCHEMA4 = "2E + 5M + 1E + 4M + 2E";
    private static final String SCHEMA5 = "2E + 3T + 3 min rest + 2T + 2 min rest + 2 x 400R w/400 jg + 2 x 1T w/1 min rest + 1E + 4 x 200R w/200jg + 1E";
    private static final String SCHEMA6 = "T = lesser of 5 miles (8 km) & 90 min";
    private static final String SCHEMA_ERR1 = "3O min E + 9M";
    private static final String SCHEMA_ERR2 = "30 min E + 1OM";
    private static final String SCHEMA_ERR3 = "2E + 3T + 3 min rest + 2T + 2 min rest + 2 x 400R w/400 jog + 2 x 1T w/1 min rest + 1E + 4 x 200R w/200jg + 1E";
    private static final String SCHEMA_ERR4 = "L = lesser of 15 miles (2O km) & 100 min";
    private static final String SCHEMA_ERR5 = "L = lesser of 15 miles (24 km) & 1O0 min";

    private static final String PACES = "E=4:45,L=4:45,M=4:14,T=4:00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20";
    private static final String PACES_ERR1 = "E=4:45,L=4:45,M=4:14,T-4:00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20";
    private static final String PACES_ERR2 = "E=4:45,L=4:45,M=4:14,T=4.00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20";
    private static final String PACES_ERR3 = "E=4:4d5,L=4:45,M=4:14,T=4.00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20";

    @Test
    public void testRebuildPacesErr1() {
        Activity activity = new Activity(PACES_ERR1, SCHEMA1);
//        assertThatCode(activity::rebuildPaces).hasMessageContaining("Cannot parse pace: T-4:00");
    }

    @Test
    public void testRebuildPacesErr2() {
        Activity activity = new Activity(PACES_ERR2, SCHEMA1);
//        assertThatCode(activity::rebuildPaces).hasMessageContaining("Cannot parse pace value: 4.00");
    }

    @Test
    public void testRebuildPacesErr3() {
        Activity activity = new Activity(PACES_ERR3, SCHEMA1);
//        assertThatCode(activity::rebuildPaces).isInstanceOf(NumberFormatException.class);
    }

    @Test
    public void testRebuildPacesSize() {
        Activity activity = new Activity(PACES, SCHEMA1);
//        List<Pace> paces = activity.rebuildPaces();
//        assertThat(paces).hasSize(9);
    }

    @Test
    public void testRebuildPacesOrder() {
        Activity activity = new Activity(PACES, SCHEMA1);
//        List<Pace> paces = activity.rebuildPaces();
//        assertThat(paces.get(0).getName()).isEqualTo("rest");
    }

    @Test
    public void testRebuildPacesValueInSeconds() {
        Activity activity = new Activity(PACES, SCHEMA1);
//        List<Pace> paces = activity.rebuildPaces();
//        assertThat(paces.get(0).getTime()).isEqualTo(320);
    }

    @Test
    public void testRebuildSchema1() {
        Activity activity = new Activity(PACES, SCHEMA1);
        Collection<String> parts = activity.rebuildSchema();
        assertThat(parts).hasSize(22);
    }

    public void testRebuildSchema2() {
        Activity activity = new Activity(PACES, SCHEMA2);
        Collection<String> parts = activity.rebuildSchema();
        assertThat(parts).hasSize(22);
    }

    @Test
    public void testRebuildSchema3() {
        Activity activity = new Activity(PACES, SCHEMA3);
        Collection<String> parts = activity.rebuildSchema();
        assertThat(parts).hasSize(2);
    }

    @Test
    public void testRebuildSchema4() {
        Activity activity = new Activity(PACES, SCHEMA4);
        Collection<String> parts = activity.rebuildSchema();
        assertThat(parts).hasSize(5);
    }

    @Test
    public void testRebuildSchema5() {
        Activity activity = new Activity(PACES, SCHEMA5);
        Collection<String> parts = activity.rebuildSchema();
        assertThat(parts).hasSize(23);
    }

    @Test
    public void testCalculateErr1() {
        Activity activity = new Activity(PACES, SCHEMA_ERR1);
        assertThatCode(activity::calculate).hasMessageContaining("Cannot parse time value: 3O");
    }

    @Test
    public void testCalculateErr2() {
        Activity activity = new Activity(PACES, SCHEMA_ERR2);
        assertThatCode(activity::calculate).hasMessageContaining("Cannot parse distance value: 1O");
    }

    @Test
    public void testCalculateErr3() {
        Activity activity = new Activity(PACES, SCHEMA_ERR3);
        assertThatCode(activity::calculate).hasMessageContaining("Cannot determine pace of: 400 jog");
    }

    @Test
    public void testCalculate1() {
        Activity activity = new Activity(PACES, SCHEMA1);
        Target target = activity.calculate();
        assertThat(target.getTime()).isEqualTo("01:09:44");
        assertThat(target.getDistance()).isEqualTo(15.97f, within(0.1f));
    }

    public void testCalculate2() {
        Activity activity = new Activity(PACES, SCHEMA2);
        Target target = activity.calculate();
        assertThat(target.getTime()).isEqualTo("01:38:57");
        assertThat(target.getDistance()).isEqualTo(22.4f);
    }

    @Test
    public void testCalculate3() {
        Activity activity = new Activity(PACES, SCHEMA3);
        Target target = activity.calculate();
        assertThat(target.getTime()).isEqualTo("01:30:57");
        assertThat(target.getDistance()).isEqualTo(20.7f, within(0.1f));
    }

    @Test
    public void testCalculate4() {
        Activity activity = new Activity(PACES, SCHEMA4);
        Target target = activity.calculate();
        assertThat(target.getTime()).isEqualTo("01:38:57");
        assertThat(target.getDistance()).isEqualTo(22.4f);
    }

    @Test
    public void testCalculate5() {
        Activity activity = new Activity(PACES, SCHEMA5);
        Target target = activity.calculate();
        assertThat(target.getTime()).isEqualTo("01:35:40");
        assertThat(target.getDistance()).isEqualTo(22.11f, within(0.1f));
    }

    @Test
    public void testCalculateLesserErr1() {
        Activity activity = new Activity(PACES, SCHEMA_ERR4);
        assertThatCode(activity::calculate).hasMessageContaining("Cannot parse distance value: 2O");
    }

    @Test
    public void testCalculateLesserErr2() {
        Activity activity = new Activity(PACES, SCHEMA_ERR5);
        assertThatCode(activity::calculate).hasMessageContaining("Cannot parse time value: 1O0");
    }

    @Test
    public void testLesser1() {
        Activity activity = new Activity(PACES, SCHEMA2);
        Target target = activity.calculate();
        assertThat(target.getTime()).isEqualTo("01:39:59");
        assertThat(target.getDistance()).isEqualTo(21.1f, within(0.1f));
    }

    @Test
    public void testLesser2() {
        Activity activity = new Activity(PACES, SCHEMA6);
        Target target = activity.calculate();
        assertThat(target.getTime()).isEqualTo("00:32:00");
        assertThat(target.getDistance()).isEqualTo(8);
    }
*/
}
