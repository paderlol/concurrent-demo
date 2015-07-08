/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */
package com.study.concurrent.forkjoin;

import java.io.Serializable;

/** 
 * Description: TODO {同步更新产品}<br/>
 *
 * @author zhanglong
 * @date: 2015年7月7日 下午2:52:05
 * @version 1.0
 * @since JDK 1.7
 */
public class Product implements Serializable {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     */  
    private static final long serialVersionUID = 1L;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 价格
     */
    private double price;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + "]";
    }
    
    

}
