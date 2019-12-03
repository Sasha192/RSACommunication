package algorithms;

import generators.BlumBlumShub;
import generators.Generator;

import java.math.BigInteger;

public class GenerateRandom {
    private static Generator generator;

    static {
        String hexP = "D5BBB96D30086EC484EBA3D7F9CAEB07";
        String hexQ = "425D2B9BFDB25B9CF6C416CC6E37B59C1F";
        generator = new BlumBlumShub(hexP, hexQ, "6D30086EC484E");
    }

    public static BigInteger generate(BigInteger start, BigInteger end) {
        return generator.getRandom(start, end);
    }
}
