package cn.sc.dubbo.demo.consumer;

import cn.sc.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class Consumer {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("=====start=====");
        DemoService demoService = (DemoService) context.getBean("demoService");
        System.out.println(demoService.sayHello("Tom"));
        List<String> list = demoService.getAll();
        if (list != null && list.size() > 0) {
            for(String name:list) {
                System.out.println(name);
            }
        }
        System.in.read();

    }
}
