package cn.sc.webservice.demo.api;

import javax.jws.WebService;
/**
 * SEI's implement
 */
//标识这是一个SEI
@WebService
public class HelloWSImpl implements HelloWS {
    @Override
    public String hello(String name) {
        System.out.println("server hello() : " + name);
        return "hello! " + name;
    }
}
