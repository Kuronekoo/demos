package com.crv.unionid.get.validate;

import com.crv.unionid.get.utils.UnionIdUtil;
import org.junit.Test;

public class ValidateUnionId {

    @Test
    public void validateTest(){
        System.out.println("***********start************");
        String infileName = "E:\\work\\weixinProject\\新需求\\批量刷入unionid\\test\\test\\test1";
        String infileName50 = "E:\\work\\weixinProject\\新需求\\批量刷入unionid\\test\\test\\1";
        String result = UnionIdUtil.readCsvAndWriteCsv4UpgradeUnionId(infileName,infileName);
        System.out.println(result);
        System.out.println("***********end************");
    }

}
