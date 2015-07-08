/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */
package com.study.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;

/** 
 * Description: TODO {一句话描述类是干什么的}<br/>
 *
 * @author zhanglong
 * @date: 2015年7月7日 下午2:54:00
 * @version 1.0
 * @since JDK 1.7
 */
public class ProductListGenerator {

    
    public List<Product> generate (int size){
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < size; i++) {
            Product product=new Product();
            product.setName("Product"+i);
            product.setPrice(10);
            products.add(product);

        }
        return products;
    }
}
