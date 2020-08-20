package com.oms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.oms.client.OrderItemServiceClient;
import com.oms.exception.OrderNotFoundException;
import com.oms.model.OrderItem;
import com.oms.model.Orders;
import com.oms.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRespository;
	
	@Autowired
	OrderItemServiceClient orderItemServiceClient;
	

	public void createOrder(Orders order) {
		orderRespository.save(order);
		List<OrderItem> orderItems= order.getOrderItems();
		if (!CollectionUtils.isEmpty(orderItems)) {
			int orderId = orderRespository.getCurrValOrderSeq();
			orderItemServiceClient.createOrderItems(orderItems,orderId);
		}
	}

	public Orders retriveOrder(int id) {
		Orders orders = orderRespository.findById(id).orElseThrow(OrderNotFoundException::new);
		List<OrderItem> orderItems=orderItemServiceClient.retriveOrderItems(id).getBody();
		orders.setOrderItems(orderItems);
		return orders;
	}

}
