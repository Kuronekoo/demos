package com.crv.test;

import com.crv.entity.TestSgCustomer;
import com.crv.repository.TestSgCustomerRepository;
import com.crv.service.TestService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: shenchao
 * @Date: created in 20:08 2018/3/19
 * @Description:
 */
public class TestConfig {

    private  ApplicationContext ctx;

    private TestService testSgCustomerRepository;

    {
       ctx= new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
       testSgCustomerRepository = ctx.getBean(TestService.class);
    }

    /** 测试spring容器是否实例化了数据源 。如果实例化了，说明Spring容器整合没问题 */
    @Test
    public void testDataSouce() throws SQLException {
        int update = testSgCustomerRepository.update("123", "89999999120424");
        System.out.println(update);


    }

}
