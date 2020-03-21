package cn.sc.webservice.demo.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * SEI：WebService EndPoint Interface 终端接口
 */

//标识这是一个SEI
@WebService
public interface HelloWS {
    //标识这是一个Webservice方法
    @WebMethod
    String hello(String name);
}
