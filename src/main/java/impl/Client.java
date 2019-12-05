package impl;

import algorithms.Euclid;
import java.math.BigInteger;

import keygeneration.FindPrime;
import rsa.RivestShamirAdlemanService;

public class Client {
    private RivestShamirAdlemanService customerService;

    public Client() {
        customerService = CustomerServiceImpl.construct();
        customerService.generateKeys();
    }

    public BigInteger encrypt(BigInteger msg, Server.Entity entity) {
        return customerService.encrypt(msg, entity.publicE , entity.publicMod);
    }

    public String encrypt(String msg, int base, Server.Entity entity) {
        return encrypt(new BigInteger(msg, base), entity).toString(base);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return customerService.decrypt(encryptedMessage);
    }

    public String decrypt(String encryptedMessage, int base) {
        return decrypt(new BigInteger(encryptedMessage, base)).toString(base);
    }

    public BigInteger signMessage(String message) {
        return signMessage(customerService.transformMessage(message));
    }

    public BigInteger signMessage(BigInteger message) {
        return customerService.signMessage(message);
    }

    public String signMessage(String message, int base) {
        return signMessage(new BigInteger(message, base)).toString(base);
    }

    public boolean verifySignMessage(String message, String signature, int base, Server.Entity entity) {
        return verifySignMessage(new BigInteger(message, base),
                new BigInteger(signature, base), entity);
    }

    private boolean verifySignMessage(BigInteger message, BigInteger signature, Server.Entity entity) {
        return customerService.verifyMessageSignature(message,
                signature,
                entity.publicE,
                entity.publicMod);
    }

    public String getPublicE(int base) {
        return getPublicE().toString(base);
    }

    public BigInteger getPublicE() {
        return customerService.getPublicKey();
    }

    public String getMod(int base) {
        return getMod().toString(base);
    }

    public BigInteger getMod() {
        return customerService.getMod();
    }

    private static class CustomerServiceImpl extends RivestShamirAdlemanService {
        private BigInteger mod;
        private BigInteger publicE;
        private BigInteger privateD;

        @Override
        public BigInteger encrypt(BigInteger message, BigInteger publicKey, BigInteger mod) {
            return message.modPow(publicKey, mod);
        }

        @Override
        public BigInteger decrypt(BigInteger encryptedMessage) {
            return encryptedMessage.modPow(privateD, mod);
        }

        @Override
        public BigInteger signMessage(BigInteger message) {
            return message.modPow(privateD, mod);
        }

        @Override
        public boolean verifyMessageSignature(BigInteger message,
                                              BigInteger signature,
                                              BigInteger publicKey,
                                              BigInteger mod) {
            return signature.modPow(publicKey, mod).equals(message);
        }

        @Override
        public void generateKeys() {
            BigInteger primeP = FindPrime.findPrime(256);
            BigInteger primeQ;
            do {
                primeQ = FindPrime.findPrime(256);
            } while (primeP.equals(primeQ));
            BigInteger eulerFunc = primeP.subtract(BigInteger.ONE)
                    .multiply(primeQ.subtract(BigInteger.ONE));
            this.mod = primeP.multiply(primeQ);
            int numberOfBits = 10;
            do {
                this.publicE = FindPrime.findPrime(10);
                numberOfBits++;
            } while (!this.publicE.gcd(eulerFunc).equals(BigInteger.ONE));
            this.privateD = Euclid.modInverse(this.publicE, eulerFunc);
        }

        @Override
        public BigInteger transformMessage(byte[] bytes) {
            BigInteger returnValue = BigInteger.valueOf(bytes[0]);
            for (int i = 1; i < bytes.length; i++) {
                returnValue = returnValue.shiftLeft(8);
                returnValue = returnValue.or(BigInteger.valueOf(bytes[i]));
            }
            return returnValue;
        }

        @Override
        public BigInteger transformMessage(String msg) {
            return transformMessage(msg.getBytes());
        }

        @Override
        public String transformMessage(String msg, int base) {
            return transformMessage(msg.getBytes()).toString(base);
        }

        @Override
        public String transformMessage(BigInteger msg) {
            return new String(msg.toByteArray());
        }

        @Override
        public BigInteger getMod() {
            return mod;
        }

        @Override
        public BigInteger getPublicKey() {
            return publicE;
        }

        static RivestShamirAdlemanService construct() {
            return new CustomerServiceImpl();
        }
    }
}
