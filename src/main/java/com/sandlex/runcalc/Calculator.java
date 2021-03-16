package com.sandlex.runcalc;

import com.sandlex.runcalc.model.PaceBlock;
import com.sandlex.runcalc.model.Schema;

/**
 * Calculator which calculates workout schema given as a set of input parameters:
 * - pace block: WU=5:00,T10=3:40,E=4:30,T5=3:30,Rest=10:00,M=4:00
 * - workout schema: 15:00WU + 3T10 + 1.5E + 5 * (0.4T5 + 00:30Rest) + 1.5E + 1:30:00M
 */
public class Calculator {

    /**
     * Calculates workout schema and return workout estimation.
     *
     * @param pacesInput string representation of a pace block, for example: "WU=5:00,T10=3:40,E=4:30,T5=3:30,Rest=10:00,M=4:00"
     * @param schemaInput string representation of a workout schema, for example: "15:00WU + 3T10 + 1.5E + 5 * (0.4T5 + 00:30Rest) + 1.5E + 1:30:00M"
     *
     * @return object of type Estimation containing calculated distance and duration
     * @throws InvalidPaceBlockException thrown in case of any validation errors in a pace block
     * @throws InvalidSchemaException thrown in case of any validation errors in a workout schema
     */
    public static Estimation getEstimation(String pacesInput, String schemaInput) throws InvalidPaceBlockException, InvalidSchemaException {
        PaceBlock paceBlock;
        try {
            paceBlock = new PaceBlock(pacesInput);
        } catch (IllegalArgumentException e) {
            throw new InvalidPaceBlockException("Incorrect pace block: " + e.getMessage());
        }

        Schema schema;
        try {
            schema = new Schema(schemaInput);
        } catch (IllegalArgumentException e) {
            throw new InvalidSchemaException("Incorrect schema: " + e.getMessage());
        }

        return Solver.solve(paceBlock, schema);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Expected input parameters:");
            System.out.println("1st - pace constants: E=4:45,L=4:45,M=4:14,T=4:00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20");
            System.out.println("2nd - session schema: 2E + 3 x 1T w/2 min rest + 3 x 3 min H w/2 min jg + 4 x 200 R w/200 jg + 1E");
            System.exit(1);
        }

        try {
            System.out.println(Calculator.getEstimation(args[0], args[1]));
        } catch (InvalidPaceBlockException | InvalidSchemaException e) {
            System.out.println(e.getMessage());
        }
    }

}
