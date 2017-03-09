
package com.github.benyzhous.springboot.sharding.jdbc.masterslave.model;

import javax.persistence.Table;

@Table(name = "t_order_item")
public final class OrderItem {

	private long orderItemId;

	private int orderId;

	private int userId;

	private String status;

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("order_item_id：s%，order_id: %s, user_id: %s, status: %s", orderItemId, orderId, userId, status);
	}
}
