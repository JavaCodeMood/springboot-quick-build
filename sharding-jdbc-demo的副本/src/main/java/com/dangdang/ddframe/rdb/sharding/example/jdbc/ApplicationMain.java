
package com.dangdang.ddframe.rdb.sharding.example.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dangdang.ddframe.rdb.sharding.example.jdbc.entity.Order;
import com.dangdang.ddframe.rdb.sharding.example.jdbc.service.OrderServcie;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;

/**
 * ProjectName: sharding-jdbc-demo <br/>
 * ClassName: ApplicationMain <br/>
 * Date: 2016年12月31日 下午11:46:36 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

	@Autowired
	OrderServcie orderService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int userId = 11;
		Order orders = new Order();
        final CommonSelfIdGenerator idGenerator = new CommonSelfIdGenerator();
		orders.setOrderId(idGenerator.generateId().longValue());
		orders.setUserId(11);
		orderService.saveOrder(orders);
		
		List<Order> orderList = null;
		orderList = orderService.selectOrderByUserId(userId);
		for(Order order : orderList){
			System.err.println(order.toString());
		}
		System.err.println("=========================");
		orderList = orderService.selectAll();
		for(Order order : orderList){
			System.err.println(order.toString());
		}
	}
}
