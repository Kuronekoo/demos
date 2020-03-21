package cn.demo.controllor;

import cn.demo.cn.demo.properties.MySqlProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class HelloWorldControllor {

    @Value("${hello}")
    private String hello;
    @Resource
    private MySqlProperties mySqlProperties;

    @ResponseBody
    @RequestMapping("/helloWorld")
    public String helloWorld(){

        System.out.println("helloworld");
        return mySqlProperties.getJdbcUrl();
    }

    @ResponseBody
    @RequestMapping("/ajax")
    public String testAjax(){
        return mySqlProperties.getJdbcUrl();
    }
}
