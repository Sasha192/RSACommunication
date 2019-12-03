package algorithms;

import java.math.BigInteger;

public class Euclid {
    private static BigInteger run(BigInteger numberA, BigInteger numberB) {
        if (numberB.compareTo(BigInteger.ZERO) == 0) {
            return numberA;
        }
        return run(numberB, numberA.mod(numberB));
    }

    public static BigInteger gcd(BigInteger numberA, BigInteger numberB) {
        int compare = numberA.compareTo(numberB);
        if (compare == 0) {
            return numberA;
        }
        if (compare < 0) {
            return run(numberB, numberA);
        }
        return run(numberA, numberB);
    }

    public static BigInteger modInverse(BigInteger number, BigInteger mod) {
        return number.modInverse(mod);
    }
}
