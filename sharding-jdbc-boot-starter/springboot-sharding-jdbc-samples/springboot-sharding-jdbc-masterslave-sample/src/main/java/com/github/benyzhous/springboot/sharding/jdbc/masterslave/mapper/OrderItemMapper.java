
package com.github.benyzhous.springboot.sharding.jdbc.masterslave.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.benyzhous.springboot.sharding.jdbc.masterslave.model.OrderItem;
import com.github.benyzhous.springboot.sharding.jdbc.masterslave.util.MyMapper;

/**
 * 订单明细Mapper
 * 
 * @author benyzhous@gmail.com
 */
@Mapper
public interface OrderItemMapper extends MyMapper<OrderItem>{

}
