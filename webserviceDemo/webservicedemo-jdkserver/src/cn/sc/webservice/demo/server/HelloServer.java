package cn.sc.webservice.demo.server;

import cn.sc.webservice.demo.api.HelloWSImpl;

import javax.xml.ws.Endpoint;

public class HelloServer {
    public static void main(String[] args) {
        String adress = "http://127.0.0.1:8080/HelloWS";//发布地址
        HelloWSImpl implementor = new HelloWSImpl();//发布的服务对象
        Endpoint.publish(adress,implementor);//发布
    }
}
