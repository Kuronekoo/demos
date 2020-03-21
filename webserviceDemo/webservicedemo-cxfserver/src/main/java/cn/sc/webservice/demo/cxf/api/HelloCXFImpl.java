package cn.sc.webservice.demo.cxf.api;

import com.ctc.wstx.util.InternCache;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService
public class HelloCXFImpl implements HelloCXF {
    @Override
    public String hello(String name) {
        System.out.println("server hello : " + name);
        return "hello : " + name;
    }

    @Override
    public Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        map.put(1,"tom");
        map.put(2,"jerry");
        return map;
    }
}
