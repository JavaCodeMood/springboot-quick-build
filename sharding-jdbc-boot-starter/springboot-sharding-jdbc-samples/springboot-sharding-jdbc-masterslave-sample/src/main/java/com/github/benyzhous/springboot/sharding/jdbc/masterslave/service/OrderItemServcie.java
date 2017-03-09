
package com.github.benyzhous.springboot.sharding.jdbc.masterslave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.benyzhous.springboot.sharding.jdbc.masterslave.mapper.OrderItemMapper;

/** 
 * 订单明细Service
 * @author benyzhous@gmail.com
 */
@Service
public class OrderItemServcie {
	@Autowired
	OrderItemMapper orderItemMapper;
	
	
}
 