package cn.sc.webservice.demo.cxf.api;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Map;

@WebService
public interface HelloCXF {
    @WebMethod
    public String hello(String name);

    @WebMethod
    public Map<Integer,String> getMap();
}
