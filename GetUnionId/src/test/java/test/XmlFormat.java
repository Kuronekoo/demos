package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlFormat {
    private Document document;		//代表整个xml文档
    private Element root;			//代表根节点
    private Element current;		//代表当前指向的节点

    public XmlFormat(String rootStr) {
        root = new Element(rootStr);
        document = new Document(root);
        current = root;
    }


    public void addAttribute(String attributeName, String attributeValue) {
        current.setAttribute(attributeName, attributeValue);
    }

    /**
     * 会将指向新添加的这个节点
     * @param nodeName
     */
    public void addInnerNode(String nodeName) {
        Element node = new Element(nodeName);
        current.addContent(node);
        current = node;
    }

    /**
     * 添加之后，不会指新添加的节点
     * @param nodeName
     */
    public void addNodeInner(String nodeName) {
        Element node = new Element(nodeName);
        current.addContent(node);
    }

    /**
     * 添加邻居节点，会执行邻居节点
     * @param nodeName
     */
    public void addSiblingNode(String nodeName) {
        Element node = new Element(nodeName);
        current.getParentElement().addContent(node);
        current = node;
    }

    public void setText(String msg) {
        current.setText(msg);
    }

    public void parent() {
        current =  current.getParentElement();
    }

    public void parent(String msg) {
        Element element = current.getParentElement();
        while (!msg.equals(element.getName())) {
            element = element.getParentElement();
        }
        current = element;
    }
    /**
     * 将document对象装成字符串
     * @return
     */
    public String getXmlString() {
        Properties sysProp = new Properties(System.getProperties());
        String encoding = sysProp.getProperty("file.encoding");
        Format format = Format.getCompactFormat();
        format.setEncoding(encoding);
        format.setIndent("    ");
        XMLOutputter writer = new XMLOutputter(format);
        ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
        try {
            writer.output(document, byteRep);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteRep.toString();
    }

    /**
     * 将document对象装成字符串,可以指定编码
     * @return
     */
    public String getXmlString(String encoding) {
        Format format = Format.getCompactFormat();
        format.setEncoding(encoding);
        format.setIndent("    ");
        XMLOutputter writer = new XMLOutputter(format);
        ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
        try {
            writer.output(document, byteRep);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteRep.toString();
    }

    /**
     * 将document对象装成字符串，不包括声明头
     * @param
     */
    public String getXmlStringOmitDeclaration() {
        Properties sysProp = new Properties(System.getProperties());
        String encoding = sysProp.getProperty("file.encoding");
        Format format = Format.getCompactFormat();
        format.setEncoding(encoding);
        format.setOmitDeclaration(true);
        XMLOutputter writer = new XMLOutputter(format);
        ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
        try {
            writer.output(document, byteRep);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteRep.toString();
    }

    public void setContent(CDATA cdata) {
        current.setContent(cdata);
    }

    /**
     * 指向root节点
     */
    public void root() {
        current = root;
    }
}
