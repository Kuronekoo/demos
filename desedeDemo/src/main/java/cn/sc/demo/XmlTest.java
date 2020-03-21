package cn.sc.demo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Iterator;

public class XmlTest {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {

        XmlTest xmlTest = new XmlTest();
//        xmlTest.XmlToString("E:\\work\\testProject\\demo\\desedeDemo\\src\\main\\resources\\test.xml");
        XStream xStream = new XStream();
        Person person = new Person("tom",123);
        xstream.alias("person",Person.class);

        String xml = xstream.toXML(person);
        System.out.println(xml);


    }

    public String XmlToString(String path) throws FileNotFoundException, DocumentException {
        String result = null;
        File xml = new File(path);
        InputStream inputStream = new FileInputStream(xml);
        SAXReader reader = new SAXReader();
        Document doc = reader.read(inputStream);

        Element root = doc.getRootElement();
        Node node = doc.selectSingleNode("//MsgId");


        System.out.println(node.getText());
        return  result;
    }

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
