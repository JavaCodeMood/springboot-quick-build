
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
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	public void deleteAll(){
		orderMapper.deleteAll();
	}
	public List<Order> selectAll(){
		return orderMapper.selectAll();
	}
	
	public List<Order> select(Order order){
		return orderMapper.select(order);
	}
	
	public List<Order> selectByExample(String orderId){
		Example example = new Example(Order.class);
		example.createCriteria().andEqualTo("orderId", orderId);
		return orderMapper.selectByExample(example);
	}
	
	public int selectCount(Order order){
		return orderMapper.selectCount(order);
	}
	
	public Order selectOne(Order order){
		return orderMapper.selectOne(order);
	}
	
	public int insertSelective(Order order){
		return orderMapper.insert(order);
	}
	
	public void saveOrder(Order order){
		orderMapper.insertUseGeneratedKeys(order);
	}
	
	
}
