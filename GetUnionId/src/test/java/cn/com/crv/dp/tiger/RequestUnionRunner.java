package cn.com.crv.dp.tiger;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.crv.dp.tiger.sc.OldUserList;
import cn.com.crv.dp.tiger.sc.UnionIdUtil;

public class RequestUnionRunner implements Runnable {

	private OldUserList oldUserList;
	private String channel;
	private String unionIdFileName;
	private String oldUserListString;

	@Override
	public void run() {
		UnionIdUtil util = new UnionIdUtil();
		String token = util.getToken(channel);
		JSONArray userArrayJson = util.getUserArrayJson(token, oldUserListString);
		String openUnionId = parseUsersJsonArray(userArrayJson);
		UnionIdWriter instance = UnionIdWriter.getInstance();
		instance.setUnionIdFileName(unionIdFileName);
		try {
			instance.writeUnionId(openUnionId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String parseUsersJsonArray(JSONArray userJsonArray) {
		StringBuilder sb = new StringBuilder();
		if (userJsonArray != null && userJsonArray.size() > 0) {
			Integer count = 0;
			for (int i = 0; i < userJsonArray.size(); i++) {
				JSONObject o = (JSONObject) userJsonArray.get(i);
				sb.append(o.getString("openid")).append(",").append(o.getString("unionid")).append("\n");
				count++;
			}
			// System.out.println("sb count = " + count);
		}
		return sb.toString();
	}

	public OldUserList getOldUserList() {
		return oldUserList;
	}

	public void setOldUserList(OldUserList oldUserList) {
		this.oldUserList = oldUserList;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUnionIdFileName() {
		return unionIdFileName;
	}

	public void setUnionIdFileName(String unionIdFileName) {
		this.unionIdFileName = unionIdFileName;
	}

	public void setOldUserListString(String oldUserListString) {
		this.oldUserListString = oldUserListString;
	}
	

}
