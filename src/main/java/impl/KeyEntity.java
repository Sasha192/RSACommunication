package impl;

import java.math.BigInteger;

public class KeyEntity {
    public BigInteger key;
    public BigInteger sign;
    public String hexKey;
    public String hexSign;

    public KeyEntity(BigInteger key, BigInteger sign, int base) {
        this.key = key;
        this.sign = sign;
        this.hexKey = key.toString(base);
        this.hexSign = sign.toString(base);
    }

    public KeyEntity(String key, String sign, int base) {
        this.key = new BigInteger(key, base);
        this.sign = new BigInteger(sign, base);
        this.hexKey = this.key.toString(base);
        this.hexSign = this.sign.toString(base);
    }

    public KeyEntity(BigInteger key, BigInteger sign) {
        this.key = key;
        this.sign = sign;
    }

    public KeyEntity toBase(int base) {
        if (hexSign == null || hexKey == null) {
            this.hexKey = key.toString(base);
            this.hexSign = sign.toString(base);
        }
        return this;
    }
}
