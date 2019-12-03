package algorithms;

import java.math.BigInteger;

public class PrimarilyTest {

    private static boolean executeTest(BigInteger randomXPowered,
                                       BigInteger number,
                                       BigInteger numberSubtractOne,
                                       int powerTwo) {
        for (int i = 0; i < powerTwo - 1; i++) {
            randomXPowered = randomXPowered.modPow(BigInteger.TWO, number);
            if (randomXPowered.compareTo(BigInteger.ONE) == 0) {
                return false;
            }
            if (randomXPowered.compareTo(numberSubtractOne) == 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean testMillerRabin(BigInteger number,
                                           BigInteger numberSubtractOne,
                                           BigInteger[] factorization) {
        BigInteger randomX = GenerateRandom.generate(BigInteger.TWO, number);
        if (randomX.gcd(number).compareTo(BigInteger.ONE) != 0) {
            return false;
        }
        BigInteger randomXPowered = randomX.modPow(factorization[0], number);
        if (randomXPowered.compareTo(numberSubtractOne) == 0) {
            return true;
        }
        if (randomXPowered.compareTo(BigInteger.ONE) == 0) {
            return true;
        }
        return executeTest(randomXPowered, number, numberSubtractOne, factorization[1].intValue());
    }

    public static boolean test(BigInteger number, int certainty) {
        if (!number.testBit(0)) {
            return false;
        }
        BigInteger numberSubtractOne = number.subtract(BigInteger.ONE);
        BigInteger[] factorization = Decomposition.decomposeNumber(numberSubtractOne);
        boolean returnValue;
        while (certainty > 0) {
            returnValue = testMillerRabin(number, numberSubtractOne, factorization);
            certainty--;
            if (!returnValue) {
                return false;
            }
        }
        return true;
    }

}
