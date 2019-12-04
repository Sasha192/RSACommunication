package impl;

import org.json.simple.JSONObject;

import java.io.IOException;

public class ServerService {

    private static JSONObject doGet(String request) {
        try {
            return Connection.doGet(request).orElseThrow(NullPointerException::new);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getServerKey(int keySize) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/"
                + "serverKey?keySize="
                + keySize;
        return doGet(request);
    }

    public static JSONObject encrypt(String hexMessage, String hexPublicExponent, String hexModulus) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/encrypt?"
                .concat("modulus=" + hexModulus)
                .concat("&publicExponent=" + hexPublicExponent)
                .concat("&message=" + hexMessage +"&type=BYTES");
        return doGet(request);
    }

    public static JSONObject decrypt(String hexCipherText) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/decrypt?"
                .concat("message=" + hexCipherText)
                .concat("&expectedType=BYTES");
        return doGet(request);
    }

    public static JSONObject sign(String hexMessage) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/sign"
                .concat("?message=" + hexMessage)
                .concat("&type=BYTES");
        return doGet(request);
    }

    public static JSONObject verify(String hexMessage,
                                    String hexSignature,
                                    String hexPublicExponent,
                                    String hexModulus) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/verify?"
                .concat("message=" + hexMessage)
                .concat("&type=BYTES")
                .concat("&signature=" + hexSignature)
                .concat("&modulus=" + hexModulus)
                .concat("&publicExponent=" + hexPublicExponent);
        return doGet(request);
    }

    public static JSONObject sendKey(String hexPublicExponent, String hexModulus) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/sendKey?"
                .concat("modulus=" + hexModulus)
                .concat("&publicExponent=" + hexPublicExponent);
        return doGet(request);
    }

    public static JSONObject receiveKey(String hexKey,
                                        String hexSignature,
                                        String hexModulus,
                                        String hexPublicExponent) {
        String request = "http://asymcryptwebservice.appspot.com/rsa/receiveKey?"
                .concat("key=" + hexKey)
                .concat("&signature=" + hexSignature)
                .concat("&modulus=" + hexModulus)
                .concat("&publicExponent=" + hexPublicExponent);
        return doGet(request);
    }
}
