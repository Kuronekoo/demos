package cn.sc.dubbo.demo.impl;

import cn.sc.dubbo.demo.DemoService;

import java.util.ArrayList;
import java.util.List;

public class DemoServiceImpl  implements DemoService{
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

    @Override
    public List<String> getAll() {

        List<String> list = new ArrayList<>();

        list.add("Tom");
        list.add("Jerry");
        return list;
    }
}
