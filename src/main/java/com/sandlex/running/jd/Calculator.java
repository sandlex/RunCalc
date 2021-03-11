package com.sandlex.running.jd;

import com.sandlex.running.jd.model.Distance;
import com.sandlex.running.jd.model.PaceBlock;
import com.sandlex.running.jd.model.Schema;

public class Calculator {

    public static Estimation getEstimation(String paces, String schema, String metricSystem) {
        return Solver.solve(new PaceBlock(paces), new Schema(schema), Distance.System.METRIC);
    }

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Expected input parameters:");
            System.out.println("1st - pace constants: E=4:45,L=4:45,M=4:14,T=4:00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20");
            System.out.println("2nd - session schema: 2E + 3 x 1T w/2 min rest + 3 x 3 min H w/2 min jg + 4 x 200 R w/200 jg + 1E");
            System.exit(1);
        }

        System.out.println(Calculator.getEstimation(args[0], args[1], ""));
    }

}
