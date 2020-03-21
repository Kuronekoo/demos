package test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @XmlAccessorType(XmlAccessType.FIELD)指定映射本类的所有字段
 * @XmlRootElement 用在class类的注解，常与@XmlRootElement，@XmlAccessorType一起使用.也可以单独使用,如果单独使用,需要在get方法上加@XmlElement等注解.
 * @XmlType,在使用@XmlType的propOrder 属性时，必须列出JavaBean对象中的所有XmlElement，否则会报错。
 * @author ZhaoQian
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Customer")
@XmlType(name = "Customer", propOrder = { "id","age", "name" })
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    // 其实@XmlType已经默认会读取下面的name和age.@XmlElement在@XmlType存在的情况下,只会起到一个标识作用.
    @XmlAttribute(name = "test")
    int id;
    @XmlElement(name = "test2",required = true  )
    String name;
    @XmlElement
    int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}