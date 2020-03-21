package cn.sc.xmldemo;

import org.w3c.dom.*;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlMul {
    public static void getNodeAttr(Node node){
        NamedNodeMap attributes = node.getAttributes();//获取节点的属性节点map
        for(int i=0;i<attributes.getLength();i++){
            Node attrNode=attributes.item(i);//获取第i个属性节点
            //获取第i个属性节点的名字和值
            System.out.println(attrNode.getNodeName()+":"+attrNode.getFirstChild().getNodeValue());
        }

    }
    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();//获得documentBuilderFactory对象
            Document doc = documentBuilder.parse("E:\\work\\testProject\\demo\\XmlDemo\\resources\\students.xml");//读取xml文件

            NodeList studentsList = doc.getElementsByTagName("students");//获取students节点列表
            Element element=(Element)studentsList.item(0);//获取第一个students节点
            NodeList students=element.getElementsByTagName("student");//从第一个students节点中获取sutdent节点列表
            for(int i=0;i<students.getLength();i++){
                Element e=(Element)students.item(i);//获取第i个sutudent节点
                //打印student节点的第一个name节点，获取文本节点，获取值
                System.out.println("姓名："+e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());

                getNodeAttr(e.getElementsByTagName("name").item(0));
                System.out.println("性别："+e.getElementsByTagName("sex").item(0).getFirstChild().getNodeValue());
                System.out.println("================");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
