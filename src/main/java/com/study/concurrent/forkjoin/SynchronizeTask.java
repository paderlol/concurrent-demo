/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */
package com.study.concurrent.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO {无返回接口forktask}<br/>
 * 
 * @author zhanglong
 * @date: 2015年7月7日 下午2:57:43
 * @version 1.0
 * @since JDK 1.7
 */
public class SynchronizeTask extends RecursiveAction {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 1L;
    /**
     * 产品集合
     */
    private List<Product> products;
    /**
     * 头长
     */
    private int first;
    /**
     * 尾长
     */
    private int last;
    /**
     * 增长价格
     */
    private double increment;

    public SynchronizeTask(List<Product> products, int first, int last, double increment) {
        super();
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if ((last - first) < 10) {// 如果任务尾大于任务头超过10个间距,那么进行更新
            updatePrice();
        } else { // 否则进行任务切分
            int middle = (first + last) / 2;
            System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount()); // 此方法返回已经提交给池已经开始他们的执行的任务数
            SynchronizeTask t1 = new SynchronizeTask(products, first, middle + 1, increment);
            SynchronizeTask t2 = new SynchronizeTask(products, middle + 1, last, increment);
            invokeAll(t1, t2);// 同步执行切分任务

        }
    }

    /**
     * 
     * TODO(更新价格). <br/>
     * 
     * @author zhanglong
     * @date: 2015年7月7日 下午3:15:48
     * @version 1.0
     * 
     */
    private void updatePrice() {

        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }

    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);
        SynchronizeTask task = new SynchronizeTask(products, 0, products.size(), 0.2);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        do {
            System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());// 此方法返回当前执行任务的线程的数量。
            System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());// 此方法返回long值，worker线程已经从另一个线程偷取到的时间数
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());// 此方法返回池的并行的级别

            try {
                TimeUnit.MILLISECONDS.sleep(5);// 休眠五毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        pool.shutdown();
        if (task.isCompletedNormally()) { // 检查是否有异常
            System.out.printf("Main: The process has completed normally.\n");
        }
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getPrice() != 12) {
                System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
            }
        }
        System.out.println("Main: End of the program.\n");


    }
}
