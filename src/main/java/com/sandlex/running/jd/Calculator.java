package com.sandlex.running.jd;

public class Calculator {

    public static String getEstimation(String paces, String schema) {
        Activity activity = new Activity(paces, schema);
        return activity.calculate().toString();
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Expected input parameters:");
            System.out.println("1st - pace constants: E=4:45,L=4:45,M=4:14,T=4:00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20");
            System.out.println("2nd - session schema: 2E + 3 x 1T w/2 min rest + 3 x 3 min H w/2 min jg + 4 x 200 R w/200 jg + 1E");
            System.exit(1);
        }

        System.out.println(Calculator.getEstimation(args[0], args[1]));
    }
}
