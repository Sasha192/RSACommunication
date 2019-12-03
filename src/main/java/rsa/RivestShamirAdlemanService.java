package rsa;

import java.math.BigInteger;

public abstract class RivestShamirAdlemanService implements CustomerService {
    public abstract BigInteger getPublicKey();

    public abstract BigInteger getMod();
}
