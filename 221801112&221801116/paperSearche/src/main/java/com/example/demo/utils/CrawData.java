package com.example.demo.utils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CrawData {
    static HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };
    public static String getJson(String path,String params)
    {
        try
        {
            trustAllHttpsCertificates();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        HttpURLConnection httpURLConnection = null;
        String data = "";
        try {
            httpURLConnection=show(path);

            httpURLConnection.connect();
            String p = params;
            // String params = "{\"api_name\":\"stock_basic\",\"token\":\"7d7b****cfa\",\"params\":{\"list_status\":\"L\"},\"fields\":\"\"}";
            OutputStream out = httpURLConnection.getOutputStream();
            out.write(p.getBytes());
            out.flush();
            //关闭
            out.close();
            int code = httpURLConnection.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    data += line + "\n";
                }
                //关闭
                reader.close();
            }
            System.out.println("@@@@@@@@"+data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5. 断开连接
            if (null != httpURLConnection) {
                try {
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public  static HttpURLConnection  show(String path) throws IOException
    {
        HttpURLConnection httpURLConnection = null;
        URL url = new URL(path);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(6000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json,text/plain,*/*");
        httpURLConnection.setRequestProperty("Accept-Language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        httpURLConnection.setRequestProperty("Connection", "keep-alive");
        httpURLConnection.setRequestProperty("Content-Length", "122");
        httpURLConnection.setRequestProperty("Referer", "https://ieeexplore.ieee.org/search/searchresult.jsp?newsearch=true&queryText=mechanical");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate,br");
        return httpURLConnection;
    }

    public static String drawData(String words)
    {
//        words="123";
        String path = "https://ieeexplore.ieee.org/rest/search";
        String params = "{\"newsearch\":true,\"queryText\":\""+words+"\",\"highlight\":true,\"returnFacets\":[\"ALL\"],\"returnType\":\"SEARCH\",\"matchPubs\":true}";
        return  getJson(path,params);
    }

    /**
    * @Description: 设置信任所有证书，解决PKIX path building failed:SunCertPathBuilderException: v 参考：https://blog.csdn.net/flower1024/article/details/72729723?utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control
    * @Param: []
    * @return: void
    * @Date: 2021/3/31
    */
    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
    public static void main(String[] args) {

        String path = "https://ieeexplore.ieee.org/rest/search";
        String params = "{\"newsearch\":true,\"queryText\":\"123\",\"highlight\":true,\"returnFacets\":[\"ALL\"],\"returnType\":\"SEARCH\",\"matchPubs\":true}";
        getJson(path,params);
    }
}
