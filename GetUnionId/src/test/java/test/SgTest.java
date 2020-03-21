package test;

import com.sun.javaws.Globals;
import org.apache.commons.httpclient.NameValuePair;
import org.junit.Test;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * @Author: shenchao
 * @Date: created in 11:47 2018/3/23
 * @Description:
 */
public class SgTest {
    public static String qryMemberUrl = "http://10.29.171.222:10005/CRM-web/weixin/getMemberInfo.do";//查询资料



    public static void main(String[] args) {
        String CardNo = "9701693032";
        NameValuePair[] data = {new NameValuePair( "cardCode" ,  CardNo)};
        String url = qryMemberUrl;
        String reqData = getSgImpl(url,data);
        System.out.println(reqData);
    }


    public static String getSgImpl(String url,NameValuePair[]  param) {
        String body = "";
        HttpUtils2 client;
        // 读配置文件 获取 超时 时长
        String connectTimeOut ="10000";

        if (connectTimeOut != null && !connectTimeOut.equals("")) {
            client = new HttpUtils2(Integer.valueOf(connectTimeOut));
        } else {
            client = new HttpUtils2();
        }

        try {
            //body = client.post(url.toString());
            body = client.sgHttpPost(url.toString(),param);
        }catch (SocketTimeoutException e) {
            System.out.println("请求连接超时异常>>>>>>>");
            body="0";
            e.printStackTrace();
        }
        catch (ConnectException e)
        {
            System.out.println("请求连接失败异常>>>>>>>");
            body="0";
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("请求sg接口异常！");
            // TODO Auto-generated catch block
            body="0";
            e.printStackTrace();
        }
        return body;
    }
}
