package generators;

import java.math.BigInteger;

public class BlumBlumShub extends BitGenerator {
    private final BigInteger mod;
    private BigInteger state;

    public BlumBlumShub(final BigInteger p, final BigInteger q, final BigInteger state) {
        mod = p.multiply(q);
        this.state = state;
    }

    public BlumBlumShub(String hexP, String hexQ, String state) {
        BigInteger p = new BigInteger(hexP, 16);
        BigInteger q = new BigInteger(hexQ, 16);
        mod = p.multiply(q);
        this.state = new BigInteger(state, 16);
    }

    @Override
    protected int getNext() {
        int returnValue = state.testBit(0) ? 1 : 0;
        state = state.pow(2).mod(mod);
        return returnValue;
    }
}
