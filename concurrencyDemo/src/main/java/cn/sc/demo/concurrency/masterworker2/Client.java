package cn.sc.demo.concurrency.masterworker2;

import java.util.Queue;

/**
 * Client
 * @author linjx
 * @Date 2016年9月22日
 */
public class Client {

    private static int jobNum = 500000;

    public static void main(String args[]) throws InterruptedException {
        testMasterWorker();
        System.out.println("-----------------");
        testSington();
    }

    static void testMasterWorker() throws InterruptedException {
        //固定使用4个Workder
        Master master = null;
        try {
            master = new Master(4, PlusWorker.class);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for(long i = 1; i <= jobNum; i ++) //提交100个子任务
            master.submit(i);
        master.execute(); //开始计算

        long re = 0;
        while(true) {
            if(master.isComplete()) {//等待计算结束
                Queue<Object> results = master.getResults();
                for (Object result : results) {
                    re = re + (long) result;
                }
                System.out.println(master.getFinishRatio() + "%");
                break;
            } else {
                System.out.println(master.getFinishRatio() + "%");
                Thread.sleep(1);
            }
        }

        System.out.println(re);
    }

    static void testSington() {
        long result = 0;
        for (long i = 1; i <= jobNum; i ++) {
            result = result + (i*i*i);
        }
        System.out.println(result);
    }
}
