package com.yunji.oa.start;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.yunji.oa.domain.User;
import com.yunji.oa.service.IUserService;

public class StartClient {

    private static IUserService iUserSevice;


    public static void main(String[] args) throws InterruptedException {
    	start();
    }

    private static void start() throws InterruptedException
    {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

        ctx.load("applicationContext.xml");
        ctx.refresh();

        System.out.println("start=====");
        long start = System.currentTimeMillis();
        iUserSevice = (IUserService)ctx.getBean("iUserSevice");
        System.out.println("query=====");
        List<User> users = iUserSevice.findAll();
        
        for(User user:users)
        {
        	System.out.println("username:"+user.getUserName());
        }
        long end = System.currentTimeMillis();
        System.out.println("end!took:"+((end-start)/1000)+"s");
        
        Thread.sleep(5000);
    }

}
