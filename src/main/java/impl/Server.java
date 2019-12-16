package impl;

import java.math.BigInteger;
import org.json.simple.JSONObject;

public class Server {
    private static int BASE = 16;

    public Entity entity;

    public Server(int keySize) {
        getServerKeys(this, keySize);
    }

    public String encrypt(String hexPublicExponent, String hexModulus, String hexMessage) {
        return (String) ServerService.encrypt(hexMessage, hexPublicExponent, hexModulus)
                .get("cipherText");
    }

    public String decrypt(String hexCipherText) {
        return (String) ServerService.decrypt(hexCipherText)
                .get("message");
    }

    public String signMessage(String hexMessage) {
        return (String) ServerService.sign(hexMessage)
                .get("signature");
    }

    public Boolean verifySignMessage(String hexMessage,
                                     String hexSignature,
                                     String hexPublicExponent,
                                     String hexModulus) {
        return (Boolean) ServerService
                .verify(hexMessage, hexSignature, hexPublicExponent, hexModulus)
                .get("verified");
    }

    public KeyEntity sendKey(String hexPublicExponent, String hexModulus) {
        JSONObject jsonObject = ServerService.sendKey(hexPublicExponent, hexModulus);
        return new KeyEntity((String) jsonObject.get("key"),
                (String) jsonObject.get("signature"), BASE);
    }

    public boolean receiveKey(String hexKey,
                               String hexSignature,
                               String hexModulus,
                               String hexPublicExponent) {
        JSONObject jsonObject = ServerService.receiveKey(hexKey,
                hexSignature,
                hexModulus,
                hexPublicExponent);
        return (boolean) jsonObject.get("verified");
    }

    public static class Entity {
        public BigInteger publicE;
        public BigInteger publicMod;

        public Entity(BigInteger publicE, BigInteger publicMod) {
            this.publicE = publicE;
            this.publicMod = publicMod;
        }

        @Override
        public String toString() {
            return "publicExponent : " + publicE + "\n".concat("publicModulus : " + publicMod);
        }
    }

    public static Server getServerKeys(Server server, int keySize) {
        if (keySize < 256) {
            keySize = 256;
        }
        JSONObject jsonObject = ServerService.getServerKey(keySize);
        server.entity = new Entity(
                new BigInteger((String) jsonObject.get("publicExponent"), BASE),
                new BigInteger((String) jsonObject.get("modulus"), BASE)
        );
        return server;
    }
}
