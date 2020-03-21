package cn.sc.demo.concurrency.masterworker2;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
public class Master {
    /**
     * 任务列表(这边作为Master成员变量，可以拆分)
     */
    private Queue<Object> jobQueue = new ConcurrentLinkedQueue<Object>();

    /**
     * Worker组
     */
    private Map<String, Thread> workers = new HashMap<>();

    /**
     * 结果集(这边作为Master成员变量，可以拆分)
     */
    private Queue<Object> results = new ConcurrentLinkedQueue<Object>();

    /**
     * 原始任务数
     */
    private AtomicLong oriJobNum = new AtomicLong(0);

    /**
     * @param workerNum，worker数量上限
     * @param workerClass, worker实现类
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Master(int workerNum, Class<? extends Worker> workerClass)
            throws InstantiationException, IllegalAccessException {
        for (int i = 0; i < workerNum; i ++) {
            Worker worker = workerClass.newInstance();
            worker.setJobQueue(jobQueue);
            worker.setResults(results);
            worker.setId(i);
            workers.put(Integer.toString(i),
                    new Thread(worker, Integer.toString(i)));
        }
    }

    /**
     * 任务是否已经完成
     * @return
     */
    public boolean isComplete() {
        for (Map.Entry<String, Thread> worker : workers.entrySet()) {
            if (Thread.State.TERMINATED != worker.getValue().getState()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 由Client提交一个子任务
     * @param job
     */
    public void submit(Object job) {
        oriJobNum.incrementAndGet(); //允许动态增加任务
        jobQueue.add(job);
    }

    /**
     * 返回子任务结果集
     * @return
     */
    public Queue<Object> getResults() {
        return results;
    }

    /**
     * 将运行结果放到结果集
     * @param result
     */
    public void putResult(Object result) {
        results.add(result);
    }

    /**
     * 启动worker，进行任务处理
     */
    public void execute() {
        for(Map.Entry<String, Thread> entry : workers.entrySet()) {
            entry.getValue().start();
        }
    }

    /**
     * 获取完成任务占比
     * @return
     */
    public float getFinishRatio() {
        return 100.0f-100.0f * ((float)jobQueue.size()) / oriJobNum.get();
    }
}
