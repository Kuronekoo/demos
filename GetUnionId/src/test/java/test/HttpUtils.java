package test;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;


public class HttpUtils
{
    /**
     * get方法请求的超时
     */
    private static final int GETMETHOD_REQUEST_TIMEOUT = 10000;

    /**
     * http连接的超时
     */
    private static final String HTTP_CONNECTION_TIMEOUT = "http_client_timeout";

    public HttpUtils(Integer integer) {
    }

    /**
     * 发送HTTP消息
     * @param body String类型。表示需要发送出去的XML报文体。
     * @param sendUrl String类型。目的URL。
     * @return String 表示对方返回的报文。如果发送成功，则返回对方返回的报文；如果发送失败，则返回null
     * @throws IOException ioException
     */
    public String sendHttpReq(String body, String sendUrl) throws IOException
    {
        URL urlcon = null;
        HttpURLConnection con = null;
        PrintWriter out = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        InputStreamReader in = null;
        try
        {
            urlcon = new URL(sendUrl);
            con = (HttpURLConnection) urlcon.openConnection();
            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestProperty("Accept", "text/xml");
            con.setRequestProperty("version", "100");
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.connect();

            //设置超时时间
            setClientTimeout();

            bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            out = new PrintWriter(bw);

            out.print(body);
            out.flush();
            out.close();

            StringBuffer sb = new StringBuffer();

            in = new InputStreamReader(con.getInputStream());

            br = new BufferedReader(in);
            String line = br.readLine();

            //modify by LiJinfeng.
            createSB(br, sb, line);

            return sb.toString();
        }
        catch (IOException e)
        {

            //modify by LiJinfeng
            closeObject(con, br, bw, in);
            closeOut(out);
            return null;
        }
        catch (Exception ex)
        {

            in.close();
            br.close();
            out.close();
            bw.close();
            con.disconnect();
            return null;
        }
        finally
        {
            closeObject(con, br, bw, in);
            closeOut(out);
        }
    }

    /**
     * 发送HTTP消息
     * @param body String类型。表示需要发送出去的XML报文体。
     * @param sendUrl String类型。目的URL。
     * @return String 表示对方返回的报文。如果发送成功，则返回对方返回的报文；如果发送失败，则返回null
     * @throws IOException ioException
     */
    public String sendHttpReq(String body, String sendUrl, String encoding) throws IOException
    {
        URL urlcon = null;
        HttpURLConnection con = null;
        PrintWriter out = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        InputStreamReader in = null;
        try
        {
            urlcon = new URL(sendUrl);
            con = (HttpURLConnection) urlcon.openConnection();
            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestProperty("Accept", "text/xml");
            con.setRequestProperty("version", "100");
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.connect();

            //设置超时时间
            setClientTimeout();

            bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), encoding));
            out = new PrintWriter(bw);

            out.print(body);
            out.flush();
            out.close();

            StringBuffer sb = new StringBuffer();

            in = new InputStreamReader(con.getInputStream());

            br = new BufferedReader(in);
            String line = br.readLine();

            //modify by LiJinfeng.
            createSB(br, sb, line);

            return sb.toString();
        }
        catch (IOException e)
        {

            //modify by LiJinfeng
            closeObject(con, br, bw, in);
            closeOut(out);
            return null;
        }
        catch (Exception ex)
        {

            in.close();
            br.close();
            out.close();
            bw.close();
            con.disconnect();
            return null;
        }
        finally
        {
            closeObject(con, br, bw, in);
            closeOut(out);
        }
    }

    public String postMethod(String content, String addr, String encoding) throws IOException
    {
        StringBuilder rtn = new StringBuilder();
        OutputStream os = null;
        PrintWriter pw = null;
        BufferedReader in = null;
        System.out.println("addr:" + addr);
        System.out.println("content:" + content);
        try {
            URL url = new URL(addr);
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();

            uc.setConnectTimeout(2000);		//连接超时设置为2s
            uc.setReadTimeout(20000);			//读超时设置为20s
            uc.setDoOutput(true);				//因为这个是post请求，参数要放在http正文内，因此需要设为true，默认情况下是false
            uc.setUseCaches(false);				//post请求不能使用缓存
            // 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            uc.setRequestProperty("Content-type", "text/xml;charset=utf-8");
            uc.setRequestMethod("POST");	// 设定请求的方法为"POST"，默认是GET
            uc.connect();

            os = uc.getOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(os, "utf-8");
            pw = new PrintWriter(out);
            pw.print(content);
            pw.flush();

			 /*获取服务器端返回信息*/
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(),encoding));
            String inputLine;
            while((inputLine = in.readLine()) != null){
                rtn.append(inputLine).append("\r\n");
            }

        } catch(SocketTimeoutException e) {
            e.printStackTrace();
            return "time_out";
        }
        catch (Exception ex) {
            System.out.println("调用微信发送模板消息接口异常" + ex);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                    pw = null;
                }

                if (os != null) {
                    os.close();
                    os = null;
                }

                if (in != null) {
                    in.close();
                    in = null;
                }
            }
            catch (Exception ex) {
                System.out.println("释放资源异常" + ex);
            }
        }
        return rtn.toString();
    }

    /**
     * post请求方法
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String postMethod(String url, NameValuePair[] param)
            throws IOException
    {
        HttpClient httpClient = new HttpClient();

        // 设置httpClient的字符集
        //         httpClient.getParams().setParameter(
        //                 HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        // 设置httpClient的链接超时
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(
                Integer.parseInt("25000"));

        PostMethod postMethod = new PostMethod(url);

        // 设置请求的字符集
        postMethod.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");

        //         postMethod.setRequestHeader("Accept-Language", "zh-cn");
        //
        //         postMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");

        // 设置post方法请求失败，自动重试三次
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        postMethod.setRequestHeader("Connection", "close");

        // 设置post方法的请求超时
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 25000);

        try
        {

            postMethod.setRequestBody(param);

            int statusCode = httpClient.executeMethod(postMethod);

            // post方法执行失败
            if (HttpStatus.SC_OK != statusCode)
            {

                return null;
            }

            // post方法执行成功,获取并返回返回值
            String responseBody = postMethod.getResponseBodyAsString();

            return responseBody;
        }
        // 发生HTTP异常
        catch (HttpException e)
        {

            return null;
        }
        // 发生IO异常
        catch (IOException e)
        {

            return null;
        }
        // 发生未知异常
        catch (Exception e)
        {

            return null;
        }
        finally
        {
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0);
        }
    }


    private void createSB(BufferedReader br, StringBuffer sb, String line)
            throws IOException
    {
        while (null != line)
        {
            sb.append(line);
            line = br.readLine();
            if (null != line)
            {
                sb.append(line);
            }
        }
    }

    private void closeOut(PrintWriter out)
    {
        if (null != out)
        {
            out.close();
        }
    }

    private void closeObject(HttpURLConnection con, BufferedReader br,
                             BufferedWriter bw, InputStreamReader in) throws IOException
    {
        if (null != con)
        {
            con.disconnect();
        }
        if (null != br)
        {
            br.close();
        }
        if (null != bw)
        {
            bw.close();
        }
        if (null != in)
        {
            in.close();
        }
    }

    /**
     * 设置client超时时间
     */
    public static void setClientTimeout()
    {
        System.setProperty("sun.net.client.defaultConnectTimeout",
                Long.toString(GETMETHOD_REQUEST_TIMEOUT));
        System.setProperty("sun.net.client.defaultReadTimeout",
                Long.toString(GETMETHOD_REQUEST_TIMEOUT));
    }


}

