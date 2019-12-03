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
        } catch (IOException e) {
            e.printStackTrace();
            return new
    }
}
