package algorithms;

import java.math.BigInteger;

public class Decomposition {
    private static void run(BigInteger number, BigInteger[] factorization) {
        int twoPower = 0;
        while (!number.testBit(0)) {
            twoPower++;
            number = number.shiftRight(1);
        }
        factorization[0] = number;
        factorization[1] = BigInteger.valueOf(twoPower);
    }

    public static BigInteger[] decomposeNumber(BigInteger number) {
        BigInteger[] factorization = new BigInteger[2];
        if (number.testBit(0)) {
            factorization[0] = number;
            factorization[1] = BigInteger.ZERO;
        } else {
            run(number, factorization);
        }
        return factorization;
    }
}
