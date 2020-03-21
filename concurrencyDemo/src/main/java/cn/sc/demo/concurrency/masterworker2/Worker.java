package cn.sc.demo.concurrency.masterworker2;
import java.util.Queue;
public abstract class Worker implements Runnable{
    /**
     * id
     */
    private int id;

    /**
     * 任务队列
     */
    protected Queue<Object> jobs;

    /**
     * 子任务结果集
     */
    protected Queue<Object> results;

    public abstract Object handle(Object input);

    @Override
    public void run() {
        System.out.println(String.format("Worker:[%d] start working...", this.id));
        while(true){
            Object input = jobs.poll(); //贪婪式获取任务

            if(null == input) break;
            //处理子任务，并传递给结果集
            putResult(handle(input));
        }
        System.out.println(String.format("Worker:[%d] finish the jobs...", this.id));
    }

    public void setJobQueue(Queue<Object> jobQueue) {
        this.jobs = jobQueue;
    }

    public void setResults(Queue<Object> results) {
        this.results = results;
    }

    private void putResult(Object result) {
        results.add(result);
    }

    public void setId(int id) {
        this.id = id;
    }
}
