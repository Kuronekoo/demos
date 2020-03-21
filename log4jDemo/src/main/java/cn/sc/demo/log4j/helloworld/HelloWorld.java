package cn.sc.demo.log4j.helloworld;

import org.apache.log4j.Logger;

public class HelloWorld {

    private static Logger logger = Logger.getLogger(HelloWorld.class);//获取log实例

    public static void main(String[] args) {
        logger.info("info message");
        logger.debug("debug message");
        logger.error("error message");
        logger.warn("warn message");
        logger.fatal("fatal message");

        logger.error("debug message",new RuntimeException("HELLOWORD"));

    }

}
