/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */
package com.study.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

/** 
 * Description: TODO {计数器用例运行}<br/>
 *
 * @author zhanglong
 * @date: 2015年6月3日 下午2:25:38
 * @version 1.0
 * @since JDK 1.7
 */
public class WorkerCDTest {
    private Executor executor;
    @Test
    public void test() throws InterruptedException{
        int size = 5;
        CountDownLatch doneSignal = new CountDownLatch(size);
        executor=Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            executor.execute(new WorkerCD(doneSignal, i));
        }
        doneSignal.await();
        System.out.println("完成任务后");
    }
}
