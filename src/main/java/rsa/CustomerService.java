package rsa;

import java.math.BigInteger;

public interface CustomerService {
    BigInteger encrypt(BigInteger message, BigInteger publicKey, BigInteger mod);

    BigInteger decrypt(BigInteger encryptedMessage);

    BigInteger signMessage(BigInteger message);

    boolean verifyMessageSignature(BigInteger message,
                                      BigInteger signature,
                                      BigInteger publicKey,
                                   BigInteger mod);

    void generateKeys();

    BigInteger transformMessage(byte[] bytes);

    BigInteger transformMessage(String msg);

    String transformMessage(String msg, int base);

    String transformMessage(BigInteger msg);

}
