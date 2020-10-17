package com.fda.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fda.exception.OrderNotFoundException;
import com.fda.model.OrderStatus;
import com.fda.model.Orders;
import com.fda.repository.OrderRepository;
import com.fda.util.FdaUtils;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRespository;

	public List<Orders> retriveOrders() {
		return orderRespository.findAll().stream().filter(x -> x.getOrderStatus() != OrderStatus.COMPLETED)
				.collect(Collectors.toList());
	}

	public Orders retriveOrder(int id) {
		return orderRespository.findById(id).orElseThrow(OrderNotFoundException::new);
	}

	public Orders updateOrder(int id, Orders order) {
		Orders orderDB = orderRespository.findById(id).orElseThrow(OrderNotFoundException::new);
		Orders mergeOrder = FdaUtils.mergeObjects(order, orderDB);
		return orderRespository.save(mergeOrder);
	}

	public Orders addOrder(Orders orders) {
		orders.setOrderStatus(OrderStatus.RECIEVED);
		return orderRespository.save(orders);
	}

}
