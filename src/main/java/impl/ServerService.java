package impl;

import org.json.simple.JSONObject;

import java.io.IOException;

public class ServerService {
    public static JSONObject getServerKey(int keySize) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/"
                + "serverKey?keySize="
                + keySize;
        try {
            return Connection.doGet(request).orElseThrow(NullPointerException::new);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject encrypt(String hexMessage, String hexPublicExponent, String hexModulus) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/encrypt?".concat(
                "modulus=8092F9103640E910594AA0F4E2148B79")
                .concat("&publicExponent=10001&message=Test_Message&type=BYTES");
        return null;
    }

    public static JSONObject decrypt(String hexCipherText) {
        String request = null;
        return null;
    }

    public static JSONObject sign(String hexMessage) {
        String request = null;
        return null;
    }

    public static JSONObject verify(String hexMessage,
                                    String hexSignature,
                                    String hexPublicExponent,
                                    String hexModulus) {
        String request = null;
        return null;
    }

    public static JSONObject sendKey(String hexPublicExponent, String modulus) {
        String request = null;
        return null;
    }

    public static JSONObject receiveKey(String hexKey,
                                        String hexSignature,
                                        String hexModulus,
                                        String hexPublicExponent) {
        String request = null;
        return null;
    }
}
