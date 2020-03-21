package com.crv.unionid.get.main;

import com.crv.unionid.get.utils.UnionIdUtil;
import org.junit.Test;
public class unionIdTest {
    @Test
    public void TestIOUtil(){
        System.out.println("***********start************");
        String infileName = "E:\\work\\weixinProject\\新需求\\批量刷入unionid\\im_wj_retail_store_customer20171107\\test.csv";
        String outfileName = "E:\\test2.csv";
        String result = UnionIdUtil.readCsvAndWriteCsv4UpgradeUnionId(infileName,outfileName);
        System.out.println(result);
        System.out.println("***********end************");
    }
}
