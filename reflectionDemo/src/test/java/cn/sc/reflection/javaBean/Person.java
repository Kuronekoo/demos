package cn.sc.reflection.javaBean;

import cn.sc.reflection.annotation.AgeValidator;

public class Person {
    private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    @AgeValidator(min = 18, max = 40)
    public void setAge(Integer age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
        System.out.println("name = " + name);
    }

    private void pMethod() {
        System.out.println("pMethod");
    }

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
