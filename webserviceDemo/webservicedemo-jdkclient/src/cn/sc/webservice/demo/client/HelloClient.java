package cn.sc.webservice.demo.client;

import cn.sc.webservice.demo.api.HelloWSImpl;
import cn.sc.webservice.demo.api.HelloWSImplService;

public class HelloClient {
    public static void main(String[] args) {
        HelloWSImplService factory = new HelloWSImplService();//获取一个工厂对象
        HelloWSImpl helloWS = factory.getHelloWSImplPort();//生成服务端的一个代理对象
        String result = helloWS.hello("Tom");//调用方法
        System.out.println("client Hello : " + result);

    }
}
