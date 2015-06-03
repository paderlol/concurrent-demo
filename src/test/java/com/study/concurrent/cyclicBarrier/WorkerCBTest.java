/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */
package com.study.concurrent.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

import com.study.concurrent.cyclicBarrier.WorkerCB;

/** 
 * Description: TODO {cyclicBarrier运行}<br/>
 *
 * @author zhanglong
 * @date: 2015年6月3日 下午12:35:12
 * @version 1.0
 * @since JDK 1.7
 */
public class WorkerCBTest {
    @Test
    public void test(){
        final int[] arrs = new int[3];
        //设置栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            
            @Override
            public void run() {
                System.out.println("testCyclicBarrier run");
                arrs[2]=arrs[0]+arrs[1];
                System.out.println("run result:"+arrs[2]);
                
            }
        });
        new Thread(new WorkerCB( 0, arrs, cyclicBarrier)).start();
        new Thread(new WorkerCB( 1, arrs, cyclicBarrier)).start();
    }

}
