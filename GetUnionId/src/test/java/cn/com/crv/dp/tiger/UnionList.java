package cn.com.crv.dp.tiger;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.alibaba.fastjson.JSONObject;

import cn.com.crv.dp.tiger.sc.OldUser;
import cn.com.crv.dp.tiger.sc.OldUserList;

public class UnionList extends ArrayList<OldUser> {

	private static final long serialVersionUID = 7562285687294846999L;
	private static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
	private String channel = "-1";
	private String unionIdFileName;
	
	@Override
	public boolean add(OldUser ou) {
		boolean ret = super.add(ou);
		if (size() >= 100) {
			// 每100次获取一次uniodId
			requestUniodId();
		}
		return ret;
	}
	 
	public void requestUniodId() {
		if (channel.equals("-1")) {
			System.err.println("channel == -1"); 
			return;
		}
		request();
	}
	
	private void request() {
		if (0 < size()) {
			OldUserList oul = new OldUserList(this);
			RequestUnionRunner runner = new RequestUnionRunner();
			String oldUserListString = JSONObject.toJSONString(oul);
			runner.setOldUserListString(oldUserListString);
			runner.setChannel(channel);
			runner.setUnionIdFileName(unionIdFileName);
			scheduledThreadPool.execute(runner);
			clear();
		}
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
	
}
