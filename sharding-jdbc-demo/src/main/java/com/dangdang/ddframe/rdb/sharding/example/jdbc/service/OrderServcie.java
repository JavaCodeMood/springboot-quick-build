
package com.dangdang.ddframe.rdb.sharding.example.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdang.ddframe.rdb.sharding.example.jdbc.entity.Order;
import com.dangdang.ddframe.rdb.sharding.example.jdbc.mapper.OrderMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * ProjectName: sharding-jdbc-demo <br/>
 * ClassName: OrderServcie <br/>
 * Date: 2017年1月1日 下午10:09:50 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public class OrderServcie {
	@Autowired
	OrderMapper orderMapper;
	
	public List<Order> selectAll(){
		return orderMapper.selectAll();
	}
	
	public List<Order> selectOrderByUserId(Integer userId){
		Example example = new Example(Order.class);
		example.createCriteria().andEqualTo("userId", userId);
		return orderMapper.selectByExample(example);
	}
	
	public void saveOrder(Order order){
		orderMapper.insertUseGeneratedKeys(order);
	}
	
	
}
