package com.crv.unionid.get.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crv.unionid.get.beans.OldUser;
import com.crv.unionid.get.beans.OldUserList;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UnionIdUtil {

    private static Logger logger = Logger.getLogger(UnionIdUtil.class);

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
                logger.info("access_token = " + access_token);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            httpClient.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return access_token;
    }

    public static String getOneUserJson(String accessToken, String openId) {


        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" +
                accessToken +
                "&openid=" +
                openId +
                "&lang=zh_CN";
//        System.out.println(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String userInfo = null;
        try {
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if (entity != null) {
                userInfo = EntityUtils.toString(entity, "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(userInfo);
                String unionid = jsonObject.getString("unionid");
                if (unionid != null&&!"".equals(unionid)) {
                    return unionid;
                }
                return "fail";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }


        return "fail";
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
                JSONArray user_info_list = jsonObject.getJSONArray("user_info_list");
                return user_info_list;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 将返回的数据中的openid和unionid拼接成逗号分隔字符串返回,一次100个
     *
     * @param userList
     * @param appTpye
     * @return
     */
    public static String getReturnOpenIdAndUnionIdString(List<OldUser> userList, String appTpye) {
        StringBuilder sb = new StringBuilder();
        OldUserList oldUserList = new OldUserList(userList);
        String oldUserListString = JSONObject.toJSONString(oldUserList);
        String accessToken = UnionIdUtil.getAccessToken(appTpye);
        JSONArray userJsonArray = UnionIdUtil.getUserJsonArray(accessToken, oldUserListString);
        logger.info("back userJsonArray size = " + userJsonArray.size());
        if (userJsonArray != null && userJsonArray.size() > 0) {
            for (int i = 0; i < userJsonArray.size(); i++) {
                JSONObject o = (JSONObject) userJsonArray.get(i);
                sb.append(o.getString("openid")).
                        append(",").
                        append(o.getString("unionid")).
                        append("\n");
            }
            return sb.toString();
        }
        return null;
    }

    public static String readCsvAndWriteCsv4UpgradeUnionId(String inFilePath, String outFilePath) {
        File csv = new File(inFilePath);  // CSV文件路径
        BufferedReader br = null;
        File out = new File(outFilePath);

        try {
            br = new BufferedReader(new FileReader(csv));
//            fw = new FileWriter(outFilePath);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        String line = "";//每行数据存储器
        String everyLine = "";//每行数据
        String[] everyLineArr;//拆分后数组
        List<OldUser> userList1 = new ArrayList<OldUser>();//华润万家超市
        List<OldUser> userList2 = new ArrayList<OldUser>();//华润苏果超市
        List<OldUser> userList3 = new ArrayList<OldUser>();//V+超市
        List<OldUser> userList4 = new ArrayList<OldUser>();//V>nGo便利店
        List<OldUser> userList5 = new ArrayList<OldUser>();//乐购express
        List<OldUser> userList6 = new ArrayList<OldUser>();//乐购乐活派
        List<OldUser> userList7 = new ArrayList<OldUser>();//苏果标超便利店
        List<OldUser> userList100 = new ArrayList<OldUser>();//VAGO
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                everyLineArr = everyLine.split(",");
                String openid = everyLineArr[0];
                String channel = everyLineArr.length == 2 ? everyLineArr[1] : "";
                if (!openid.equals("openid") || !channel.equals("channel")) {
                    if ("1".equals(channel) && openid.length() == 28) {
                        userList1.add(new OldUser(openid, "zh_CN"));//华润万家超市
                    } else if ("2".equals(channel) && openid.length() == 28) {
                        userList2.add(new OldUser(openid, "zh_CN"));//华润苏果超市
                    } else if ("3".equals(channel) && openid.length() == 28) {
                        userList3.add(new OldUser(openid, "zh_CN"));//V+超市
                    } else if ("4".equals(channel) && openid.length() == 28) {
                        userList4.add(new OldUser(openid, "zh_CN"));//V>nGo便利店
                    } else if ("5".equals(channel) && openid.length() == 28) {
                        userList5.add(new OldUser(openid, "zh_CN"));//乐购express
                    } else if ("6".equals(channel) && openid.length() == 28) {
                        userList6.add(new OldUser(openid, "zh_CN"));//乐购乐活派
                    } else if ("7".equals(channel) && openid.length() == 28) {
                        userList7.add(new OldUser(openid, "zh_CN"));//苏果标超便利店
                    } else if ("100".equals(channel) && openid.length() == 28) {
                        userList100.add(new OldUser(openid, "zh_CN"));//VAGO
                    }
                }
            }

            String userListString1 = getOutputString(userList1, "1");//华润万家超市
            String userListString2 = getOutputString(userList2, "2");//华润苏果超市
            String userListString3 = getOutputString(userList3, "3");//V+超市
            String userListString4 = getOutputString(userList4, "4");//V>nGo便利店
            String userListString5 = getOutputString(userList5, "5");//乐购express
            String userListString6 = getOutputString(userList6, "6");//乐购乐活派
            String userListString7 = getOutputString(userList7, "7");//苏果标超便利店
            String userListString100 = getOutputString(userList100, "8");//VAGO

            sb.append(userListString1).
                    append(userListString2).
                    append(userListString3).
                    append(userListString4).
                    append(userListString5).
                    append(userListString6).
                    append(userListString7).
                    append(userListString100);
            FileUtils.writeStringToFile(out, sb.toString(), "UTF-8");
            logger.info("************ write work complete ************");
            return "ok";
        } catch (IOException e) {
            logger.error(e.getMessage());
            return "not ok";
        }

    }

    public static String getOutputString(List<OldUser> userList, String appType) {
        Integer count = 0;
        Integer total = 0;
        List<OldUser> size100UserList = new ArrayList<OldUser>();
        StringBuilder sb = new StringBuilder();
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                size100UserList.add(userList.get(i));
                count++;
                total++;
                //每100个发一次请求一次
                if (count == 100) {
                    String returnUserListString = UnionIdUtil.getReturnOpenIdAndUnionIdString(size100UserList, appType);
                    //拼接成字符串
                    if (returnUserListString != null && returnUserListString.length() > 0) {
                        sb.append(returnUserListString);
                    }
                    count = 0;
                    size100UserList.clear();
                }

            }
            //将尾巴也写进去
            if (total % 100 != 0) {
                String returnUserListString = UnionIdUtil.getReturnOpenIdAndUnionIdString(size100UserList, appType);
                if (returnUserListString != null && returnUserListString.length() > 0) {
                    sb.append(returnUserListString);
                }

            }

            logger.info("appType : " + appType + " 's result apend work is done..");
            logger.info("appType : " + appType + " 's total = " + total);
            logger.info("--------------------------------------------.");
        }
        return sb.toString();

    }

    public static void validateUnionId() {

    }
}


