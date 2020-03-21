package cn.sc.webservice.demo.cxf.interceptors;

import com.sun.org.apache.xml.internal.utils.DOMHelper;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.omg.PortableInterceptor.Interceptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;


public class AddUserInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private String name;
    private String password;



    public AddUserInterceptor(String name, String password) {
        super(Phase.PRE_PROTOCOL);//准备协议化时拦截
        this.name = name;
        this.password = password;
        System.out.println("AddUserInterceptor()....");
    }

    /*
         <Envelope>
             <head>
                 <atguigu>
                     <name>xfzhang</name>
                     <password>123456</password>
                 </atguigu>
                 <atguigu2>
                     <name>xfzhang</name>
                     <password>123456</password>
                 </atguigu2>
             <head>
             <Body>
                 <sayHello>
                     <arg0>BOB</arg0>
                 <sayHello>
             </Body>
         </Envelope>
     */
    @SuppressWarnings("deprecation")
    @Override
    public void handleMessage(SoapMessage msg) throws Fault {
        List<Header> headers = msg.getHeaders();

		/*
		 <atguigu>
			<name>xfzhang</name>
			<password>123456</password>
		</atguigu>
		 */
        Document document = DOMHelper.createDocument();
        Element rootEle = document.createElement("atguigu");
        Element nameELe = document.createElement("name");
        nameELe.setTextContent(name);
        rootEle.appendChild(nameELe);
        Element passwordELe = document.createElement("password");
        passwordELe.setTextContent(password);
        rootEle.appendChild(passwordELe);

        headers.add(new Header(new QName("atguigu"), rootEle));
        System.out.println("client handleMessage()....");
    }

}