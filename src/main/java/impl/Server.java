package impl;

import org.json.simple.JSONObject;

import java.math.BigInteger;

public class Server {
    private Entity entity;

    public Entity getEntity() {
        return entity;
    }

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

    public String[] sendKey(String hexPublicExponent, String hexModulus) {
        JSONObject jsonObject = ServerService.sendKey(hexPublicExponent, hexModulus);
        return new String[] {(String) jsonObject.get("key"), (String) jsonObject.get("signature")};
    }

    public String[] receiveKey(String hexKey,
                               String hexSignature,
                               String hexModulus,
                               String hexPublicExponent) {
        JSONObject jsonObject = ServerService.receiveKey(hexKey, hexSignature, hexModulus, hexPublicExponent);
        return new String[] {(String) jsonObject.get("key"), (String) jsonObject.get("verified")};
    }

    static class Entity {
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

}
