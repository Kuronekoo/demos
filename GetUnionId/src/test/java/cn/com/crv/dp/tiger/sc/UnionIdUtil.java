package cn.com.crv.dp.tiger.sc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class UnionIdUtil {
	
	public String getToken(String channel) {
		String access_toke_url = "http://crvweixin.crv.com.cn/activities/actUrlManager/actUrlManager_getToken.action?appidType=" + channel;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(access_toke_url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String access_token = null;

        if (entity != null) {
            try {
                access_token = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("access_token = " + access_token);

        }
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_token;
	}
	
    /**
     * 根据渠道号获取accessToken
     *
     * @param appType 渠道号
     * @return
     */
    public static String getAccessToken(String appType) {
        String access_toke_url = "http://crvweixin.crv.com.cn/activities/actUrlManager/actUrlManager_getToken.action?appidType=" + appType;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(access_toke_url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String access_token = null;

        if (entity != null) {
            try {
                access_token = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("access_token = " + access_token);

        }
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_token;
    }
    
    public JSONArray getUserArrayJson(String token, String usersJson) {
    	String userInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + token;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(userInfo_url);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String userInfo = null;
        httpPost.addHeader("Content-type", "application/json;charset=utf-8");
        httpPost.setEntity(new StringEntity(usersJson, "UTF-8"));
        try {
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            if (entity != null) {
                userInfo = EntityUtils.toString(entity, "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(userInfo);
//                System.out.println("jsonObject = " + jsonObject);
                JSONArray user_info_list = jsonObject.getJSONArray("user_info_list");
                return user_info_list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 向微信接口批量获取用户信息，一次100个
     *
     * @param accessToken
     * @param userList
     * @return
     */
    public static JSONArray getUserJsonArray(String accessToken, String userList) {
        String userInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(userInfo_url);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String userInfo = null;
        httpPost.addHeader("Content-type", "application/json;charset=utf-8");
        httpPost.setHeader("Content-type", " application/json");
        httpPost.setHeader("charset", " utf-8");
        httpPost.setHeader("Accept", "application/json");

        httpPost.setEntity(new StringEntity(userList, "UTF-8"));
        try {
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            if (entity != null) {
                userInfo = EntityUtils.toString(entity, "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(userInfo);
                System.out.println("jsonObject = " + jsonObject);
                JSONArray user_info_list = jsonObject.getJSONArray("user_info_list");
                return user_info_list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的数据中的openid和unionid拼接成逗号分隔字符串返回
     *
     * @param userList
     * @param appTpye
     * @return
     */
    public static String getReturnOpenIdAndUnionIdString(List<OldUser> userList, String appTpye) {
        StringBuilder sb = new StringBuilder();
        OldUserList oldUserList = new OldUserList(userList);
        System.out.println(userList.size());
        String oldUserListString = JSONObject.toJSONString(oldUserList);
        System.out.println(oldUserListString);
        String accessToken = UnionIdUtil.getAccessToken(appTpye);
        JSONArray userJsonArray = UnionIdUtil.getUserJsonArray(accessToken, oldUserListString);
        System.out.println("userJsonArray size = " + userJsonArray.size());
        if (userJsonArray != null && userJsonArray.size() > 0) {
            Integer count = 0;
            for (int i = 0; i < userJsonArray.size(); i++) {
                JSONObject o = (JSONObject) userJsonArray.get(i);
                sb.append(o.getString("openid")).
                        append(",").
                        append(o.getString("unionid")).
                        append("\n");
                count++;
            }
            System.out.println("sb count = " + count);
            return sb.toString();
        }

        return null;

    }

    public static String readCsvAndWriteCsv4UpgradeUnionId(String inFilePath, String outFilePath, String appTpye) {
        File csv = new File(inFilePath);  // CSV文件路径
        BufferedReader br = null;
        File out = new File(outFilePath);

        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line = "";//每行数据存储器
        String everyLine = "";//每行数据
        String[] everyLineArr;//拆分后数组
        List<OldUser> userList = new ArrayList<OldUser>();//存储用户数组
        Integer count = 0;//计数器
        Integer total = 0;//计数器
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                everyLineArr = everyLine.split(",");
                String openid = everyLineArr[0];
                String channel = everyLineArr.length == 2 ? everyLineArr[1] : "";

                if (!openid.equals("openid") || !channel.equals("channel")) {
                    //只把渠道是appType且openid是28位的存到数组里去
                    if (channel.equals(appTpye) && openid.length() == 28) {
                        OldUser oldUser = new OldUser(openid, "zh_CN");
                        userList.add(oldUser);
                        count++;
                        total++;
                    }
                }
                //每100个发一次请求一次
                if (count == 100) {
                    String returnUserListString = UnionIdUtil.getReturnOpenIdAndUnionIdString(userList, appTpye);
                    //写到文件里
                    if (returnUserListString != null && returnUserListString.length() > 0) {
                        sb.append(returnUserListString);
                        System.out.println("sb append work complete..");
                    }
                    System.out.println("count = " + count);
                    System.out.println("-------------------------");
                    count = 0;
                    userList.clear();
                }
            }
            //将尾巴也写进去
            if (total % 100 != 0) {
                String returnUserListString = UnionIdUtil.getReturnOpenIdAndUnionIdString(userList, appTpye);
                if (returnUserListString != null && returnUserListString.length() > 0) {
                    sb.append(returnUserListString);
                    System.out.println("tail sb append work complete..");
                }
            }
            System.out.println("count = " + count);
            System.out.println("-------------------------");
            FileUtils.writeStringToFile(out, sb.toString(), "ISO-8859-1");
            System.out.println("************ write work complete ************");
            System.out.println("total = " + total);
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "not ok";
        }
    }
}
