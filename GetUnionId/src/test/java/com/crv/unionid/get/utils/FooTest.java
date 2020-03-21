package com.crv.unionid.get.utils;

import java.io.File;
import java.io.IOException;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;


public class FooTest {
	private static final int unit = 1000;
	private static final String srcFileName = "E:\\work\\weixinProject\\新需求\\批量刷入unionid\\test\\openId\\1";
	private static final String destSubFileDir = "E:\\work\\weixinProject\\新需求\\批量刷入unionid\\test\\test\\";
	
	@Test
	// @Transactional
	// @Rollback(false)
	public void test3() throws IOException {
		File srcFile = new File(srcFileName);
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
		
		int writeFileCount = 0;
		File writeFile = null;
		
		for (int i = 2; it.hasNext(); i++) {
			if (i == 2) {
				it.nextLine();
			}
			if (i % unit == 0 || i == lineTotal) {
				writeFileCount++;
				writeFile = new File( destSubFileDir + writeFileCount);
				FileUtils.write(writeFile, sb.toString(), "ISO-8859-1");
				if (i == lineTotal) {
					break;
				}
				sb.setLength(0);
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
