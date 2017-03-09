
package com.github.benyzhous.springboot.sharding.jdbc.masterslave.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import com.github.benyzhous.springboot.sharding.jdbc.masterslave.mapper.OrderMapper;
import com.github.benyzhous.springboot.sharding.jdbc.masterslave.model.Order;

import tk.mybatis.mapper.entity.Example;

/**
 * 订单Service
 * 
 * @author benyzhous@gmail.com
 */
@Service
@Transactional//事务控制
public class OrderService {
	// id生成器
	@Autowired
	private CommonSelfIdGenerator commonSelfIdGenerator;
	@Autowired
	OrderMapper orderMapper;

	public void deleteAll() {
		orderMapper.deleteAll();
	}

	public void saveTransaction() {
		List<Order> orderList = new ArrayList<Order>();
		// 生成10条数据，看看分布情况
		for (int i = 0; i < 10; i++) {
			Order order = new Order();
			order.setOrderId(commonSelfIdGenerator.generateId().longValue());
			order.setUserId(2);
			if(i==3){
				throw new RuntimeException("test transaction");
			}
			order.setStatus("1");
			orderList.add(order);
			orderMapper.insert(order);
		}
	}

	// 使用Hint强制路由主库示例
	public List<Order> selectAllHint() {
		HintManager hintManager = HintManager.getInstance();
		hintManager.setMasterRouteOnly();
		return orderMapper.selectAll();
	}

	// 从Slave只读库查询
	public List<Order> selectAll() {
		return orderMapper.selectAll();
	}

	public List<Order> select(Order order) {
		return orderMapper.select(order);
	}

	public List<Order> selectByExample(String orderId) {
		Example example = new Example(Order.class);
		example.createCriteria().andEqualTo("orderId", orderId);
		return orderMapper.selectByExample(example);
	}

	public int selectCount(Order order) {
		return orderMapper.selectCount(order);
	}

	public Order selectOne(Order order) {
		return orderMapper.selectOne(order);
	}

	public int insertSelective(Order order) {
		return orderMapper.insert(order);
	}

	public void saveOrder(Order order) {
		orderMapper.insertUseGeneratedKeys(order);
	}

}
