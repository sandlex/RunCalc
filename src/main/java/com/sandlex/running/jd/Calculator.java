package com.sandlex.running.jd;

/**
 * author: Alexey Peskov
 */
public class Calculator {

    public static Target getEstimation(String schema) {
        Activity activity = new Activity(schema);
        return activity.calculate();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("input parameter is missing");
            System.exit(1);
        }

        System.out.println(Calculator.getEstimation(args[0]));
    }
}
