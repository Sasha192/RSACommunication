package impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Connection {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static Optional<JSONObject> doGet(String request)
            throws IOException {
        HttpGet httpGet = new HttpGet(request);
        return getJsonObject(httpClient.execute(httpGet));
    }

    private static Optional<JSONObject> getJsonObject(CloseableHttpResponse execute)
            throws IOException {
        try (CloseableHttpResponse response = execute) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return Optional.ofNullable((JSONObject) JSONValue.parse(result));
            }
        }
        return Optional.empty();
    }

    public static Optional<JSONObject> doPost(String request,
                                              List<NameValuePair> params) throws Exception {
        HttpPost httpPost = new HttpPost(request);
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        return getJsonObject(httpClient.execute(httpPost));
    }
}
