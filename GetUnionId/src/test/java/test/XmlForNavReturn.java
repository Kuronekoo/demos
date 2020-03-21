/**
 * @(#)IMInfoServices.java 1.0 2011-2-23
 * @Copyright:  Copyright 2000 - 2011 soft Tech. Co. Ltd. All Rights Reserved.
 * @Description:
 *
 * Modification History:
 * Date:        2011-2-23
 * Author:      邢红
 * Version:     IM客服平台V1.0
 * Description: (Initialize)
 * Reviewer:
 * Review Date:
 */
package test;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * Copyright:   Copyright 2000 - 2021 soft Tech. Co. Ltd. All Rights Reserved.
 * Date:        2013-08-02
 * Author:      sung 创建返回给导航的xml
 * Version:     IM客服平台V1.0
 * Description: Initialize
 */
public class XmlForNavReturn
{
    Logger logger = Logger.getLogger("common");

    private Document doc;

    public Element root;

    private Element curElement;

    private String rootName = "response";

    public XmlForNavReturn()
    {
        doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("gbk");
        root = doc.addElement(rootName);
        curElement = root;
    }

    /**
     * 带参的构造方法
     * @param rootNameS 根节点名称
     * @param charset xml文档字符编码
     */
    public XmlForNavReturn(String rootNameS,String charset)
    {
        doc = DocumentHelper.createDocument();
        doc.setXMLEncoding(charset);
        root = doc.addElement(rootNameS);
        curElement = root;
    }

    public XmlForNavReturn(String rName)
    {
        doc = DocumentHelper.createDocument();
        root = doc.addElement(rName);
        curElement = root;
    }

    /**
     * 添加内部节点，并指向刚添加的节点。<br>
     *  内部节点：如果此节点下面还有一组子节点
     */
    public void addInnerNode(String nodeName)
    {
        try
        {
            if (nodeName == null || "".equals(nodeName.trim()))
            {
                logger.error("节点名称为空，无法添加。");
                return;
            }
            Element e = curElement.addElement(nodeName);
            setCurElement(e);
        }
        catch (RuntimeException e)
        {
            logger.error("添加内部节点失败。", e);
        }
    }

    /**
     * 加入一个属性
     */
    public void addAttribute(String nodeName, String nodeValue)
    {
        curElement.addAttribute(nodeName, nodeValue);
    }

    /**
     * 指定节点添加属性
     * @param nodeName 属性名
     */
    public void addAttribute(Element targetElement,String nodeName, String nodeValue)
    {
//    	curElement = curElement.element(targetRoot);
        curElement = targetElement;
        curElement.addAttribute(nodeName, nodeValue);
        this.returnToParent();
    }

    public Element addLeafNode(String nodeName, String nodeValue)
    {
        try
        {
            if (nodeName == null || "".equals(nodeName.trim()))
            {
                logger.error("节点名称为空，无法添加。");
                return null;
            }
            Element e = curElement.addElement(nodeName);
            if (nodeValue == null)
            {
                logger.info(nodeName + "的值为null，已添加为空串");
                nodeValue = "";
            }
            e.addText(nodeValue);
            return e;
        }
        catch (RuntimeException e)
        {
            logger.error("添加内部节点失败。", e);
            return null;
        }
    }

    public void addNodes(List<Node> nodes)
    {
        if (nodes == null || nodes.isEmpty())
            return;
        for (Node node : nodes)
        {
            node.getParent().remove(node);
            root.add(node);
        }
    }

    private void setCurElement(Element e)
    {
        curElement = e;
    }

    /**
     * 添加叶子节点。叶子节点：如果此节点没有子节点
     * @param attributes
     */
    public void addLeaves(Map<String, String> attributes)
    {
        for (Map.Entry<String, String> entry : attributes.entrySet())
        {
            addLeafNode(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 添加叶子节点，指定其父节点。
     * @param parent 父节点的xpath
     * @param attributes
     */
    public void addLeaves(String parent, Map<String, String> attributes)
    {
        Element p = (Element) doc.selectSingleNode(parent);
        setCurElement(p);
        addLeaves(attributes);
    }

    /**
     * 返回文档字符串形式
     *
     * @return
     */
    public String getXmlString()
    {
        return doc.asXML();
    }

    /**
     * 返回到根结点
     */
    public void returnToRoot()
    {
        curElement = root;
    }

    /**
     * 返回到上一级节点
     */
    public void returnToParent()
    {
        curElement = curElement.getParent();
    }

    /**
     * 切换一个节点的名称。
     * @param old
     * @param now
     */
    public void changeName(String old, String now)
    {
        Node n = doc.selectSingleNode("//" + old);
        n.setName(now);
    }

    /**
     * 切换一个节点的值
     * @param name
     * @param value
     */
    public void setValue(String name, String value)
    {
        Node n = doc.selectSingleNode("//" + name);
        n.setText(value);
    }

    public String getValue(String name)
    {
        Node n = doc.selectSingleNode("//" + name);
        return n.getText();
    }
}
