package impl;

import org.json.simple.JSONObject;

import java.math.BigInteger;

public class Server {
    private Entity entity;

    public Server(int keySize) {
        if(keySize < 256) {
            keySize = 256;
        }
        JSONObject jsonObject = ServerService.getServerKey(keySize);
        entity = new Entity(
                new BigInteger((String) jsonObject.get("publicExponent"), 16),
                new BigInteger((String) jsonObject.get("modulus"), 16)
        );
    }

    public String send(String hexPublicExponent, String hexModulus, String hexMessage) {
        return (String) ServerService.encrypt(hexMessage, hexPublicExponent, hexModulus)
                .get("cipherText");
    }

    public String getMessage(String hexCipherText) {
        return (String) ServerService.decrypt(hexCipherText)
                .get("message");
    }

    public String signMessage(String hexMessage) {
        return (String) ServerService.sign(hexMessage)
                .get("signature");
    }

    public String verifySignMessage(String hexMessage,
                                    String hexSignature,
                                    String hexPublicExponent,
                                    String hexModulus) {
        return (String) ServerService
                .verify(hexMessage, hexSignature, hexPublicExponent, hexModulus)
                .get("verified");
    }

    public Object sendKey() {
        return null;
    }

    public Object receiveKey() {
        return null;
    }

    static class Entity {
        public BigInteger publicE;
        public BigInteger publicMod;

        public Entity(BigInteger publicE, BigInteger publicMod) {
            this.publicE = publicE;
            this.publicMod = publicMod;
        }
    }

}
