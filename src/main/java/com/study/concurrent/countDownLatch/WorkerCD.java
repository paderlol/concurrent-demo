/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */
package com.study.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

/** 
 * Description: TODO {计数器使用}<br/>
 *
 * @author zhanglong
 * @date: 2015年6月3日 下午2:16:55
 * @version 1.0
 * @since JDK 1.7
 */
public class WorkerCD implements Runnable {

    private  CountDownLatch doneSignal;
    private  int i;
    
    
    public WorkerCD(CountDownLatch doneSignal, int i) {
        super();
        this.doneSignal = doneSignal;
        this.i = i;
    }


    /** 
     * TODO(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author zhanglong
     * @date: 2015年6月3日 下午2:16:55
     * @version 1.0
     *
     */
    @Override
    public void run() {
       
        doWork();//做某些事情
        doneSignal.countDown();//计数释放一些
    }
    
    private void doWork() {
        System.out.println("doWork with:"+i);
    }

}
