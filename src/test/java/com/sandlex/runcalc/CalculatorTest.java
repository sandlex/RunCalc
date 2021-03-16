package com.sandlex.runcalc;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CalculatorTest {

    @Test
    @SneakyThrows
    void shouldCalculate() {
        Estimation estimation = Calculator.getEstimation("WU=5:00,T10=3:40,E=4:30,T5=3:30,Rest=10:00,M=4:00", "15:00WU + 3T10 + 1.5E + 5 * (0.4T5 + 00:30Rest) + 1.5E + 1:30:00M");
        assertThat(estimation.getDistance()).isEqualTo(new BigDecimal("33.750"));
        assertThat(estimation.getSeconds()).isEqualTo(8340);
        assertThat(estimation.getFormattedTime()).isEqualTo("02:19:00");
        assertThat(estimation).hasToString(String.format("Estimated distance - %.3f, time - 02:19:00", 33.750));
    }

    @Test
    void shouldThrowInvalidPaceBlockException() {
        assertThatExceptionOfType(InvalidPaceBlockException.class)
                .isThrownBy(() -> Calculator.getEstimation("", ""))
                .withMessage("Incorrect pace block: Pace block can't be empty. Expected format: pace1=mm:ss,pace2=mm:ss");
    }

    @Test
    void shouldThrowInvalidSchemaException() {
        assertThatExceptionOfType(InvalidSchemaException.class)
                .isThrownBy(() -> Calculator.getEstimation("T10=3:35", ""))
                .withMessage("Incorrect schema: Schema can't be empty");
    }

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
