package cn.sc.reflection.demo;

import cn.sc.reflection.javaBean.ArithmeticCaculator;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class TestProxy {

    @Test
    public void testProxy(){
        ArithmeticCaculator arithmeticCaculator =
                (ArithmeticCaculator) Proxy.newProxyInstance(null,null,null);
    }
}
