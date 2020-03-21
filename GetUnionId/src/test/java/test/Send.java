package test;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: shenchao
 * @Date: created in 9:19 2018/3/2
 * @Description:
 */
public class Send {

    @Test
    public void testSMS(){

        try {
//            String postData  = "sname=dlwangjf&spwd=lexinerbu123&scorpid=&sprdid=1012888&sdst=18682117217&smsg="+ URLEncoder.encode("【华润万家】短信测试","utf-8");
//            String postUrl = "http://cf.51welink.com/submitdata/service.asmx/g_Submit";
//            String result = SMS(postData,postUrl);
            String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                    "<CSubmitState xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://tempuri.org/\">" +
                    "<State>0</State>" +
                    "<MsgID>1803020927129628363</MsgID>" +
                    "<MsgState>提交成功</MsgState>" +
                    "<Reserve>1</Reserve>" +
                    "</CSubmitState>";
//            System.out.println(result);
            if(result==null || "".equals(result)){

            }

            Document document = DocumentHelper.parseText(result);

            Element root = document.getRootElement();
            Element state = root.element("State");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
}
