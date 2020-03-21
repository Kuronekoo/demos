package cn.sc.demo.concurrency.masterworker2;

/**
 * 求立方和
 * @author linjx
 * @Date 2016年9月22日
 */
public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input) {
        long i = (Long) input;
        return i * i * i;
    }
}