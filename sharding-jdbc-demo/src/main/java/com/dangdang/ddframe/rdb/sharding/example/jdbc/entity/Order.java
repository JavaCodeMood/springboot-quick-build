
package com.dangdang.ddframe.rdb.sharding.example.jdbc.entity;

import javax.persistence.Table;

/**
 * ProjectName: sharding-jdbc-demo <br/>
 * ClassName: Order <br/>
 * Date: 2017年1月1日 下午10:12:07 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
@Table(name="t_order")
public final class Order {

	private long orderId;

	private int userId;

	private String status;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(final long orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("order_id: %s, user_id: %s, status: %s", orderId, userId, status);
	}
}
