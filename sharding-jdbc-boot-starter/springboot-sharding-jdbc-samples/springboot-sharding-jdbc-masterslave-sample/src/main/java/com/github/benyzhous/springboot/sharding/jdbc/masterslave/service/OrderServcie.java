
package com.github.benyzhous.springboot.sharding.jdbc.masterslave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.benyzhous.springboot.sharding.jdbc.masterslave.mapper.OrderMapper;
import com.github.benyzhous.springboot.sharding.jdbc.masterslave.model.Order;

import tk.mybatis.mapper.entity.Example;

/**
 * 订单Service
 * 
 * @author benyzhous@gmail.com
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
