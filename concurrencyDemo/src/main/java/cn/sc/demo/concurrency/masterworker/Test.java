package cn.sc.demo.concurrency.masterworker;

import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        //固定使用5个Worker，并指定Worker
        Master m = new Master(new PlusWorker(), 5);
        //提交100个子任务
        for(int i=0;i<100;i++){
            m.submit(i);
        }
        //开始计算
        m.execute();
        int re= 0;
        //保存最终结算结果
        Map<String ,Object> resultMap =m.getResultMap();

        //不需要等待所有Worker都执行完成，即可开始计算最终结果
        while(resultMap.size()>0 || !m.isComplete()){
            Set<String> keys = resultMap.keySet();
            String key =null;
            for(String k:keys){
                key=k;
                break;
            }
            Integer i =null;
            if(key!=null){
                i=(Integer)resultMap.get(key);
            }
            if(i!=null){
                //最终结果
                re+=i;
            }
            if(key!=null){
                //移除已经被计算过的项
                resultMap.remove(key);
            }

        }
    }
}
