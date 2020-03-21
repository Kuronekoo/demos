package cn.sc.dubbo.demo;

import java.util.List;

public interface DemoService {
    String sayHello(String name);

    List<String> getAll();
}
