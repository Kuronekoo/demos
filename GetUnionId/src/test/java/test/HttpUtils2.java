package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author 胡剑波  Bruce.Hu
 * @email BruceHu0802@163.com
 * @createTime 2014-6-23 下午04:25:51
 */
public class HttpUtils2 {

    private static int CONNECTION_TIMEOUT = 30000;//默认30s
//    private static Log log = LogFactory.getLog(HttpUtils2.class);
    public HttpUtils2() {

    }

    public HttpUtils2(int connectTimeOut) {
        this.CONNECTION_TIMEOUT = connectTimeOut;
    }

    /**
     * Using GET method.
     *
     * @param url
     *            The remote URL.
     * @param queryString
     *            The query string containing parameters
     * @return Response string.
     * @throws Exception
     */
    public String httpGet(String url, String queryString) throws Exception {
        String responseData = null;

        if (queryString != null && !queryString.equals("")) {
            url += "?" + queryString;
        }

        //log.info("httpGet [1]. url = "+url);

        HttpClient httpClient = new HttpClient();
        GetMethod httpGet = new GetMethod(url);
        httpGet.getParams().setParameter("http.socket.timeout",
                new Integer(CONNECTION_TIMEOUT));

        try {
            int statusCode = httpClient.executeMethod(httpGet);
            if (statusCode != HttpStatus.SC_OK) {
                //log.info("HttpGet [2] Method failed: "
                //+ httpGet.getStatusLine());
            }
            // Read the response body.
            responseData = httpGet.getResponseBodyAsString();

            //log.info(" httpGet [3] getResponseBodyAsString() = "
            //+ httpGet.getResponseBodyAsString());

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            httpGet.releaseConnection();
            httpClient = null;
        }

        return responseData;
    }

    /**
     * Using POST method.
     *
     * @param url
     *            The remote URL.
     * @param queryString
     *            The query string containing parameters
     * @return Response string.
     * @throws Exception
     */
    public String httpPost(String url, String queryString) throws Exception {
        String responseData = null;
        HttpClient httpClient = new HttpClient();

        //log.info("QHttpClient httpPost [1] url = "+url);
        PostMethod httpPost = new PostMethod(url);
        httpPost.addParameter("Content-Type",
                "application/x-www-form-urlencoded");
        httpPost.getParams().setParameter("http.socket.timeout",
                new Integer(CONNECTION_TIMEOUT));
        if (queryString != null && !queryString.equals("")) {
            httpPost.setRequestEntity(new ByteArrayRequestEntity(queryString
                    .getBytes()));
        }

        try {
            int statusCode = httpClient.executeMethod(httpPost);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("HttpPost Method failed: "
                        + httpPost.getStatusLine());
            }
            responseData = httpPost.getResponseBodyAsString();

            //log.info("QHttpClient httpPost [2] responseData = "+responseData);
        } catch (Exception e) {
            throw e;
        } finally {
            httpPost.releaseConnection();
            httpClient = null;
        }

        return responseData;
    }
    /**
     * Using POST method.
     *
     * @param url
     *            The remote URL.
     * @param queryString
     *            The query string containing parameters
     * @return Response string.
     * @throws Exception
     */
    public String sgHttpPost(String url, NameValuePair[] queryString) throws Exception {
        String responseData = null;
        HttpClient httpClient = new HttpClient();

        //log.info("QHttpClient httpPost [1] url = "+url);
        PostMethod httpPost = new PostMethod(url);
        httpPost.addParameter("Content-Type",
                "application/x-www-form-urlencoded");
        httpPost.addParameter("charset","UTF-8");
        httpPost.getParams().setParameter("http.socket.timeout",
                new Integer(CONNECTION_TIMEOUT));
        if (queryString != null && !queryString.equals("")) {
            httpPost.setRequestBody(queryString);
        }

        try {
            int statusCode = httpClient.executeMethod(httpPost);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("HttpPost Method failed: "
                        + httpPost.getStatusLine());
            }
            responseData = httpPost.getResponseBodyAsString();

            //log.info("QHttpClient httpPost [2] responseData = "+responseData);
        } catch (Exception e) {
            throw e;
        } finally {
            httpPost.releaseConnection();
            httpClient = null;
        }

        return responseData;
    }


    /**
     * post请求方法 (存url地址请求、参数也在url中)
     *
     * @author 胡剑波
     * @CreateTime 2014-4-8  上午11:10:19
     * @param url
     * @return
     * @throws Exception
     */
    public String post(String url) throws Exception {
        // 测试地址
        // url="http://127.0.0.1:8121/imcc_robot2/nav/test.action";
        // url="http://197.3.176.26:8000/CMBC_MBServer_MB/svt";
        String locationsJSONString = "";
        HttpClient httpClient = new HttpClient();
        // 设置链接超时 时间
//		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
//				CONNECTION_TIMEOUT);
        PostMethod httpPost = new PostMethod(url);
        httpPost.getParams().setParameter("http.socket.timeout",
                new Integer(CONNECTION_TIMEOUT));
        try {
            int statusCode = httpClient.executeMethod(httpPost);
            //System.out.println("statusCode:"+statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                throw new Exception("链接不成功！状态码为：" + statusCode);
            }
//			locationsJSONString = httpPost.getResponseBodyAsString();
            InputStream inputStream = httpPost.getResponseBodyAsStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String str = "";
            if (bufferedReader!=null) {

                while ((str = bufferedReader.readLine())!=null) {
                    buffer.append(str);
                }
                locationsJSONString = buffer.toString();
            }
            //log.warn("请求连接返回的数据是："+locationsJSONString);
        }catch (SocketTimeoutException e) {
            System.out.println("请求连接超时异常>>>>>>>");
            throw e;
        }
        catch (ConnectException e)
        {
            System.out.println("请求连接失败异常>>>>>>>");
            throw e;
        }
        catch (Exception e) {
            throw e;
        } finally {
            httpPost.releaseConnection();
            httpClient = null;
        }
        return locationsJSONString;
    }





}
