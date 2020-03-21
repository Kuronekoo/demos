package cn.com.crv.dp.tiger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.com.crv.dp.tiger.sc.OldUser;

public class FileRunner implements Runnable {
	
	private String openIdFileName;
	private String unionIdFileName;

	private UnionList zeroChannelList = new UnionList();
	private UnionList oneChannelList = new UnionList();
	private UnionList twoChannelList = new UnionList();
	private UnionList threeChannelList = new UnionList();
	private UnionList fourChannelList = new UnionList();
	private UnionList fiveChannelList = new UnionList();
	private UnionList sixChannelList = new UnionList();
	private UnionList sevenChannelList = new UnionList();
	private UnionList hundredChannelList = new UnionList();
	
	public FileRunner() {
		zeroChannelList.setChannel("0");
		oneChannelList.setChannel("1");
		twoChannelList.setChannel("2");
		threeChannelList.setChannel("3");
		fourChannelList.setChannel("4");
		fiveChannelList.setChannel("5");
		sixChannelList.setChannel("6");
		sevenChannelList.setChannel("7");
		hundredChannelList.setChannel("100");
	}
	
	public void initFileName() {
		zeroChannelList.setUnionIdFileName(unionIdFileName);
		oneChannelList.setUnionIdFileName(unionIdFileName);
		twoChannelList.setUnionIdFileName(unionIdFileName);
		threeChannelList.setUnionIdFileName(unionIdFileName);
		fourChannelList.setUnionIdFileName(unionIdFileName);
		fiveChannelList.setUnionIdFileName(unionIdFileName);
		sixChannelList.setUnionIdFileName(unionIdFileName);
		sevenChannelList.setUnionIdFileName(unionIdFileName);
		hundredChannelList.setUnionIdFileName(unionIdFileName);
	}
	
	private void addOpenId(String openId, String channel) {
		 OldUser ou = new OldUser(openId, "zh_CN");
		if ("0".equals(channel)) {
			zeroChannelList.add(ou);
		} else if ("100".equals(channel)) {
			hundredChannelList.add(ou);
		} else if ("2".equals(channel)) {
			twoChannelList.add(ou);
		} else if ("3".equals(channel)) {
			threeChannelList.add(ou);
		} else if ("4".equals(channel)) {
			fourChannelList.add(ou);
		} else if ("5".equals(channel)) {
			fiveChannelList.add(ou);
		} else if ("6".equals(channel)) {
			sixChannelList.add(ou);
		} else if ("7".equals(channel)) {
			sevenChannelList.add(ou);
		} else {
			oneChannelList.add(ou);
		}
	}
	
	@Override
	public void run() {
		initFileName();
		String[] str = null;
		try (LineIterator it = FileUtils.lineIterator(new File(openIdFileName));) {
			while (it.hasNext()) {
				str = it.nextLine().split(",");
				if (str == null || str.length != 2) {
					continue;
				}
				if (str[0].length() != 28 || str[1] == null) {
					continue;
				}
				addOpenId(str[0], str[1]);
			}
			finish();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	private void finish() {
		zeroChannelList.requestUniodId();
		oneChannelList.requestUniodId();
		twoChannelList.requestUniodId();
		threeChannelList.requestUniodId();
		fourChannelList.requestUniodId();
		fiveChannelList.requestUniodId();
		sixChannelList.requestUniodId();
		sevenChannelList.requestUniodId();
		hundredChannelList.requestUniodId();
	}

	// 文件总行数
	private int getLineTotal() {
		int lineTotal = 0;
		try (LineIterator it = FileUtils.lineIterator(new File(openIdFileName));) {
			while (it.hasNext()) {
				lineTotal++;
				it.nextLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return lineTotal;
	}

	public String getOpenIdFileName() {
		return openIdFileName;
	}

	public void setOpenIdFileName(String openIdFileName) {
		this.openIdFileName = openIdFileName;
	}

	public String getUnionIdFileName() {
		return unionIdFileName;
	}

	public void setUnionIdFileName(String unionIdFileName) {
		this.unionIdFileName = unionIdFileName;
	}
	

}
