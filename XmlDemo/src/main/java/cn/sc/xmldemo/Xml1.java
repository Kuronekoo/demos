package cn.sc.xmldemo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Xml1 {
    public static void getNodeAttr(Node node){

    }
    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse("E:\\work\\testProject\\demo\\XmlDemo\\resources\\student.xml");
            NodeList studentList = doc.getElementsByTagName("student");
            Element student = (Element) studentList.item(0);
            String name = student.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            System.out.println("name = " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
