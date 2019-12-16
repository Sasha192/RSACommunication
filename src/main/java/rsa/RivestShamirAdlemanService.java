package rsa;

import java.math.BigInteger;

public abstract class RivestShamirAdlemanService implements CustomerService {
    public abstract BigInteger getPublicKey();

    public abstract void generateKeys(int bits);

    public abstract BigInteger getMod();
}
