package com.crv.unionid.get.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public class FooTest2 {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FooTest2.class);
	private static final int unit = 500;
	private static final String srcFileName = "E:\\work\\weixinProject\\新需求\\批量刷入unionid\\test\\openId\\";
	private static final String destSubFileDir = "E:\\work\\weixinProject\\新需求\\批量刷入unionid\\test\\test\\";
	private static final String validateFileDir = "E:\\work\\weixinProject\\新需求\\批量刷入unionid\\test\\test\\";

	@Test
	// @Transactional
	// @Rollback(false)
	public void test3() throws IOException {
		int writeFileCount = 0;
		for (int j =1;j<=19;j++){
		File srcFile = new File(srcFileName + j);
		StringBuilder sb = new StringBuilder();
		int count = 0;
		int total = 0;
		String line = null;
		String[] split = null;
		
		int lineTotal = 0;
		LineIterator it = FileUtils.lineIterator(srcFile);
		while (it.hasNext()) {
			lineTotal++;
			it.nextLine();
		}
		it.close();
		it = FileUtils.lineIterator(srcFile);
		
		File writeFile = null;
		
		for (int i = 1; it.hasNext(); i++) {

			if (i % unit == 0 || i == lineTotal) {
				writeFileCount++;
				writeFile = new File(destSubFileDir + writeFileCount);
				FileUtils.write(writeFile, sb.toString(), "ISO-8859-1");
				break;
//				if (i == lineTotal) {
//					break;
//				}
//				sb.setLength(0);
			}
			line = it.nextLine().trim().replaceAll("\"", "");
			split = line.split(",");
			if (split.length == 2 && split[1].length() > 0) {
				sb.append(line).append("\n");
				count++;
			}
			total++;
		}
		System.out.println("finish -- " + count);
	    System.out.println("total  -- " + total);
		}
		}


	@Test
	// @Transactional
	// @Rollback(false)
	public void test4() throws IOException {
		int trueCount = 0;
		int falseCount = 0;
		for (int j =1;j<2;j++){
			File openIdFile = new File(validateFileDir + j);
			File unionIdFile = new File(validateFileDir + j + ".txt");
			String accessToken = null;
			int count = 0;
			int total = 0;
			String line = null;
			String[] openIdSplit = null;
			String[] unionIdSplit = null;

			LineIterator openIdIt = FileUtils.lineIterator(openIdFile);


			String openId = null;
			String appType = null;
			String uionId = null;
			for (; openIdIt.hasNext();) {
				LineIterator unionIdIt = FileUtils.lineIterator(unionIdFile);
				openIdSplit =  openIdIt.nextLine().split(",");

				if (openIdSplit.length == 2 && openIdSplit[1].length() > 0) {
					openId = openIdSplit[0].trim();
					appType = openIdSplit[1].trim();
					if (null == accessToken || "".equals(accessToken)){
						accessToken = UnionIdUtil.getAccessToken(appType);
					}
					uionId = UnionIdUtil.getOneUserJson(accessToken, openId);

					if("fail".equals(uionId)){
						logger.info("enter fail");
						accessToken = UnionIdUtil.getAccessToken(appType);
						uionId = UnionIdUtil.getOneUserJson(accessToken, openId);
					}
					for(;unionIdIt.hasNext();){
						unionIdSplit = unionIdIt.nextLine().split(",");

						if (unionIdSplit.length == 2 && unionIdSplit[1].length() > 0) {
							if((unionIdSplit[0].trim()).equals(openId)){
								logger.info("openId: " + openId + " uionId : " +uionId);
								logger.info("openId: " + unionIdSplit[0].trim() + " uionId : " +unionIdSplit[1].trim());
								if((unionIdSplit[1].trim()).equals(uionId)){
									trueCount++;
									logger.info("trueCount:" +trueCount );
								}else {
									falseCount++;
									logger.info("falseCount:" +falseCount );
								}
							}
						}
						}
				}


			}
			logger.info("trueCount -- " + trueCount);
			logger.info("falseCount  -- " + falseCount);
		}
	}
}
