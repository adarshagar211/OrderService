package com.oms;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.oms.model.OrderItem;
import com.oms.model.Orders;

@SpringBootTest
class OrderServiceApplicationTests {
	
	@Autowired
	OrderServiceClient orderServiceClient;
	
	@BeforeEach
	public void contextLoads() {
		
		// Prepare Order 
		List<OrderItem> orderItems = new ArrayList<>();
		OrderItem orderItem1 = new OrderItem("Biscuit",2);
		orderItems.add(orderItem1);
		
		Orders order = new Orders("ROhan","43, Wall Street",orderItems ,400.00);
	    ResponseEntity<Orders> r1 = orderServiceClient.createOrder(order);
		
	}

	@Test
	public void test1() {
		ResponseEntity<Orders> re = orderServiceClient.retriveOrder(1);
		Assert.notNull(re.getBody());
	}

}
