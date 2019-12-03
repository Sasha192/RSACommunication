package generators;

import java.math.BigInteger;

public interface Generator {
    BigInteger getRandom(BigInteger start, BigInteger end);
}
