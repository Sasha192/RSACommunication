package keygeneration;

import algorithms.GenerateRandom;
import algorithms.PrimarilyTest;
import java.math.BigInteger;
import java.security.SecureRandom;

public class FindPrime {
    private static final int CERTAINTY = 5;

    private static BigInteger run(BigInteger start, BigInteger end) {
        BigInteger randomX = GenerateRandom.generate(start, end);
        while (!PrimarilyTest.test(randomX, CERTAINTY)) {
            randomX = GenerateRandom.generate(start, end);
        }
        return randomX;
    }

    public static BigInteger findPrime(int numberOfBits) {
        BigInteger start  = BigInteger.ZERO;
        while (start.bitLength() < numberOfBits) {
            start = new BigInteger(numberOfBits, new SecureRandom());
        }
        return run(start, start.shiftLeft(2));
    }
}
