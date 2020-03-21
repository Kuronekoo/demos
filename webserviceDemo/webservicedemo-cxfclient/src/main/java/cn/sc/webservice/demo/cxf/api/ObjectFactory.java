
package cn.sc.webservice.demo.cxf.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.sc.webservice.demo.cxf.api package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetMapResponse_QNAME = new QName("http://api.cxf.demo.webservice.sc.cn/", "getMapResponse");
    private final static QName _Hello_QNAME = new QName("http://api.cxf.demo.webservice.sc.cn/", "hello");
    private final static QName _GetMap_QNAME = new QName("http://api.cxf.demo.webservice.sc.cn/", "getMap");
    private final static QName _HelloResponse_QNAME = new QName("http://api.cxf.demo.webservice.sc.cn/", "helloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.sc.webservice.demo.cxf.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMapResponse }
     * 
     */
    public GetMapResponse createGetMapResponse() {
        return new GetMapResponse();
    }

    /**
     * Create an instance of {@link GetMapResponse.Return }
     * 
     */
    public GetMapResponse.Return createGetMapResponseReturn() {
        return new GetMapResponse.Return();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link GetMap }
     * 
     */
    public GetMap createGetMap() {
        return new GetMap();
    }

    /**
     * Create an instance of {@link GetMapResponse.Return.Entry }
     * 
     */
    public GetMapResponse.Return.Entry createGetMapResponseReturnEntry() {
        return new GetMapResponse.Return.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMapResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.cxf.demo.webservice.sc.cn/", name = "getMapResponse")
    public JAXBElement<GetMapResponse> createGetMapResponse(GetMapResponse value) {
        return new JAXBElement<GetMapResponse>(_GetMapResponse_QNAME, GetMapResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.cxf.demo.webservice.sc.cn/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.cxf.demo.webservice.sc.cn/", name = "getMap")
    public JAXBElement<GetMap> createGetMap(GetMap value) {
        return new JAXBElement<GetMap>(_GetMap_QNAME, GetMap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.cxf.demo.webservice.sc.cn/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

}
