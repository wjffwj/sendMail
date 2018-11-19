package com.wjf.common.http;


import com.ning.http.client.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * /**
 * 网络调用工具类
 *
 * @author caojinbao
 * @create 2017-09-26 13:58
 **/
public class HttpClient {
    private AsyncHttpClient client;
    private final static int connectTimeout = 5000;
    private final static int readTimeout = 20000;
    private final static int requestTimeout = 20000;

    private static class HttpClientHolder {//懒加载
        private static HttpClient httpClient = new HttpClient();
    }

    private HttpClient() {
        AsyncHttpClientConfig.Builder configBuilder = new AsyncHttpClientConfig.Builder();
        configBuilder.setConnectTimeout(connectTimeout);
        configBuilder.setReadTimeout(readTimeout);
        configBuilder.setRequestTimeout(requestTimeout);
        this.client = new AsyncHttpClient(configBuilder.build());
    }

    public static HttpClient getAsyncHttpClient() {
        return HttpClientHolder.httpClient;
    }

    public String get(String url) throws SocketTimeoutException {
        try {
            ListenableFuture<Response> futrue = this.client.prepareGet(url).execute();
            Response res = futrue.get();
            String responseMsg = res.getResponseBody();
            return responseMsg;
        } catch (Exception e) {
            System.out.println("请求失败");
          e.printStackTrace();

        }
        return null;
    }

    public ListenableFuture<Response> get(String url, AsyncHandler resHandler) {
        return this.client.prepareGet(url).execute(resHandler);
    }

    private Request buildRequest(String url, Map<String, String> paramsMap) {
        RequestBuilder requestBuilder = new RequestBuilder();
        if (paramsMap != null && paramsMap.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (entry.getKey() != null) {
                    requestBuilder.addFormParam(entry.getKey(), entry.getValue());
                }
            }
        }
        // 添加RequestHeader，key
        requestBuilder.addHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        requestBuilder.addHeader("Accept", "application/json");
        requestBuilder.addHeader("connection", "Keep-Alive");
        requestBuilder.addHeader("Content-Length", "0");
        requestBuilder.setMethod("POST");
        requestBuilder.setUrl(url);

        return requestBuilder.build();
    }

    public ListenableFuture<Response> post(String url, Map<String, String> paramsMap) {
        Request req = this.buildRequest(url, paramsMap);
        return this.client.executeRequest(req);
    }

    public void post(String url, Map<String, String> paramsMap, AsyncHandler resHandler) {
        Request req = this.buildRequest(url, paramsMap);
        this.client.executeRequest(req, resHandler);
    }





    public static String analysisInputStream(Response input) throws IOException {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            char[] szBuffer = new char[1024];
            int result = inputStreamReader.read(szBuffer);
            StringBuffer buffer = new StringBuffer();
            while (result != -1) {
                buffer.append(new String(szBuffer, 0, result));
                result = inputStreamReader.read(szBuffer);
            }
            return buffer.toString();
        } catch (Exception e) {
            return input.getResponseBody("UTF-8");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
        }
    }
}