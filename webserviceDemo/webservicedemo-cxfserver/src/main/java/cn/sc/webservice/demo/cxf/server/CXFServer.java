package cn.sc.webservice.demo.cxf.server;


import cn.sc.webservice.demo.cxf.api.HelloCXF;
import cn.sc.webservice.demo.cxf.api.HelloCXFImpl;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.message.Message;

import javax.xml.ws.Endpoint;
import java.util.List;

public class CXFServer {
    public static void main(String[] args) {
        String adress = "http://localhost:8080/cxf_server";
        HelloCXF helloCXF = new HelloCXFImpl();

        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();

        factoryBean.setAddress(adress);
        factoryBean.setServiceClass(HelloCXF.class);
        factoryBean.setServiceBean(helloCXF);

        List<Interceptor<? extends Message>> outInterceptors = factoryBean.getOutInterceptors();
        outInterceptors.add(new LoggingOutInterceptor());

        List<Interceptor<? extends Message>> inInterceptors = factoryBean.getInInterceptors();
        inInterceptors.add(new LoggingInInterceptor());
        factoryBean.create();
//        Endpoint publish = Endpoint.publish(adress, helloCXF);
//
//        EndpointImpl endpoint = (EndpointImpl) publish;
//
//        List<Interceptor<? extends Message>> inInterceptors = endpoint.getInInterceptors();


        System.out.println("cxf server started");
    }
}
