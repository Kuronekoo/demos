package com.yunji.oa.test.start;

import java.io.IOException;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class StartDubboServer {

//	public static void main(String[] args) {
//		ThinkjoyBootstrap.main(null);
//	}

//	private static final Logger LOG = LoggerFactory
//			.getLogger(StartServer.class);

    private static final String DEFAULT_SPRING_CONFIG = "applicationContext.xml";

    // Hide constructor
    private StartDubboServer() {
    }

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {DEFAULT_SPRING_CONFIG});
        context.start();

        System.out.println("Press any key to exit.");
        System.in.read();
    }


}

