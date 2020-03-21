package com.yunji.oa.service.start;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;

public class StartDubboServer {

//	public static void main(String[] args) {
//		ThinkjoyBootstrap.main(null);
//	}

//	private static final Logger LOG = LoggerFactory
//			.getLogger(StartServer.class);

    private static final Log log = LogFactory.getLog(StartDubboServer.class);
    private static final String DEFAULT_SPRING_CONFIG = "applicationContext.xml";

    // Hide constructor
    private StartDubboServer() {
    }

    public static void main(String[] args) throws IOException {
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {DEFAULT_SPRING_CONFIG});
        context.start();

        System.out.println("Press any key to exit.");
        System.in.read();*/
        //read使用报错时使用底下这种方式
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {DEFAULT_SPRING_CONFIG});
            context.start();
            log.info("Press any key to exit.");
            //System.out.println("Press any key to exit.");
        } catch (Exception e) {
            log.error("== StartDubboServer context start error:", e);
        }
        synchronized (StartDubboServer.class) {
            while (true) {
                try {
                    StartDubboServer.class.wait();
                } catch (InterruptedException e) {
                    log.error("== synchronized error:", e);
                    log.info(e);
                }
            }
        }
    }

}

