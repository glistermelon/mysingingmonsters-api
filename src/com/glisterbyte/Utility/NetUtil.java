package com.glisterbyte.Utility;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetUtil {

    public final static Logger NetUtilErrorLogger = Logger.getLogger(NetUtil.class.getName());
    public static void logError(Exception ex) {
        NetUtilErrorLogger.log(Level.SEVERE, "Network Utility Error", ex);
    }

    public static String JsonToHttpQueryStr(ObjectNode json, String[] order) {
        List<String> parts = new ArrayList<>();
        if (order != null) {
            for (String key : order) {
                parts.add(key + "=" + json.get(key).asText());
            }
        }
        else {
            json.fields().forEachRemaining(
                    field -> parts.add(
                            field.getKey() + "=" + field.getValue().asText()
                    )
            );
        }
        return String.join("&", parts);
    }

    private static String basicHttpsRequest(HttpUriRequestBase request, String hostname,
                                            ObjectNode headers, String[] order) throws Exception {

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient;
        if (order == null) httpClient = clientBuilder.build();
        else httpClient = clientBuilder.addRequestInterceptorLast(
                (request1, _, _) -> {
                    ArrayList<Header> headers1 = new ArrayList<>(Arrays.asList(request1.getHeaders()));
                    for (Header header : headers1) request1.removeHeader(header);

                    for (String name : order) {
                        int length = headers1.size();
                        for (int i = 0; i < length; i++)
                            if (headers1.get(i).getName().equals(name)) {
                                request1.addHeader(headers1.remove(i));
                                break;
                            }
                    }
                }
        ).build();

        ClassicHttpResponse response = httpClient.executeOpen(
                new HttpHost("https", hostname, 443),
                request, null);

        final HttpEntity entity = response.getEntity();
        String output = EntityUtils.toString(entity);
        EntityUtils.consume(entity);
        httpClient.close();

        return output;
    }

    public static String basicHttpsPost(String uri, String hostname, String postData,
                                        ObjectNode headers, String[] order) throws Exception {

        HttpPost httpPost = new HttpPost(uri);
        
        headers.fields().forEachRemaining(
                entry -> httpPost.addHeader(entry.getKey(), entry.getValue().asText())
        );

        final StringEntity strEntity = new StringEntity(postData);
        httpPost.setEntity(strEntity);

        return basicHttpsRequest(httpPost, hostname, headers, order);
    }
    public static String basicHttpsGet(String uri, String hostname,
                                       ObjectNode headers, String[] order) throws Exception {

        HttpGet httpGet = new HttpGet(uri);

        headers.fields().forEachRemaining(
                entry -> httpGet.addHeader(entry.getKey(), entry.getValue().asText())
        );

        return basicHttpsRequest(httpGet, hostname, headers, order);
    }
}
