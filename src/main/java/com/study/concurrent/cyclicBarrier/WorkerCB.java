/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */
package com.study.concurrent.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description: TODO {执行重复的工作线程}<br/>
 * 
 * @author zhanglong
 * @date: 2015年6月3日 下午12:02:23
 * @version 1.0
 * @since JDK 1.7
 */
public class WorkerCB implements Runnable {

    private int num;
    private int[] arrs;
    /**
     * 栅栏
     */
    private CyclicBarrier barrier;

    public WorkerCB(int num, int[] arrs, CyclicBarrier barrier) {
        super();
        this.num = num;
        this.arrs = arrs;
        this.barrier = barrier;
    }

    /**
     * TODO(工作线程). <br/>
     * 
     * @author zhanglong
     * @date: 2015年6月3日 下午12:02:24
     * @version 1.0
     * 
     */
    @Override
    public void run() {
        arrs[num] = new Random().nextInt(100);
        System.out.println("Component " + num + " generates: " + arrs[num]);
        try {
            System.out.println("Component " + num + " sleep");
            barrier.await();
            System.out.println("Component " + num + " awaked");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupted();
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println("栅栏被破坏");
            e.printStackTrace();
        }

    }

}
