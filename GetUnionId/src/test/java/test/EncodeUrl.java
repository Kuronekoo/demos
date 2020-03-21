package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeUrl {

    public static void getUrl(String backUrl,String state) throws UnsupportedEncodingException {
        String appid = "wx806d86f13f02b7c1";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + appid+
                "&redirect_uri=" + URLEncoder.encode(backUrl,"UTF-8") +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=" +state+
                "#wechat_redirect";
        System.out.println(url);
    }


}
