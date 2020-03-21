package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Kuroneko
 * @Date: created in 19:52 2018/4/2
 * @Description:
 */
public class DeleteMsg {

    @Test
    public void testDelete(){
        String accessToken ="8_a53qOk7xKsQtOM5eEef1Xvaj1EyTFP2kfHhLVNET5Q2lnYD8i2WwCX2aPdZStJEzcixgSsFO_dO9cVW1wvCVvOvKlRqwRsSE-dc_Ym150uUTuYYDpvzTQ60uGJpiKaQMQ-xpAeB_vbXfGIC5SJSfADADUD" ;

        for(int i = 308;i<=316;i++){
            String msg_id="3147546"+String.valueOf(i);
            Map<String,String> map =new HashMap<>();
            map.put("msg_id",msg_id);
            map.put("article_idx","");
            String msgJson = JSON.toJSONString(map);
            System.out.println(msgJson);
            delete(accessToken,msgJson);
        }

    }

    /**
     * 向微信接口批量获取用户信息，一次100个
     *
     * @param accessToken
     * @return
     */
    public static void delete(String accessToken, String msgJson) {
        String delete_url = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=" + accessToken;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(delete_url);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String result = null;
        httpPost.addHeader("Content-type", "application/json;charset=utf-8");
        httpPost.setHeader("Content-type", " application/json");
        httpPost.setHeader("charset", " utf-8");
        httpPost.setHeader("Accept", "application/json");

        httpPost.setEntity(new StringEntity(msgJson, "UTF-8"));
        try {
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(result);
                System.out.println(jsonObject);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
