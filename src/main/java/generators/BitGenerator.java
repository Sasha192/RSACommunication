package generators;

import java.math.BigInteger;

public abstract class BitGenerator implements Generator {
    @Override
    public BigInteger getRandom(BigInteger start, BigInteger end) {
        BigInteger random = BigInteger.ZERO;
        do {
            random = random.shiftLeft(1);
            if (getNext() == 1) {
                random = random.setBit(0);
            }
        } while (random.compareTo(start) < 0);
        end = end.shiftRight(1);
        int numberOfBits = (int) (Math.random() * (end.bitLength() - start.bitLength()));
        if (numberOfBits < 1) {
            numberOfBits = 1;
        }
        while (random.compareTo(end) < 0 && numberOfBits > 0) {
            random = random.shiftLeft(1);
            if (getNext() == 1) {
                random = random.setBit(0);
            }
            numberOfBits--;
        }
        return random;
    }

    protected abstract int getNext();
}
