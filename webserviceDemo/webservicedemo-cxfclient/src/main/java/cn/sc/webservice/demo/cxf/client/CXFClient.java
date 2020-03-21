package cn.sc.webservice.demo.cxf.client;


import cn.sc.webservice.demo.cxf.api.GetMapResponse;
import cn.sc.webservice.demo.cxf.api.HelloCXF;
import cn.sc.webservice.demo.cxf.api.HelloCXFService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;

import java.util.List;

public class CXFClient {
    public static void main(String[] args) {
        HelloCXFService helloCXFService = new HelloCXFService();
        HelloCXF helloCXFPort = helloCXFService.getHelloCXFPort();

        Client client = ClientProxy.getClient(helloCXFService);
        List<Interceptor<? extends Message>> inInterceptors = client.getInInterceptors();


        GetMapResponse.Return map = helloCXFPort.getMap();
        List<GetMapResponse.Return.Entry> entrys = map.getEntry();

        for (GetMapResponse.Return.Entry entry : entrys) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key : " + key + ", value : " + value);
        }

    }
}
