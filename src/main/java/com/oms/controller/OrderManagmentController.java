package com.oms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oms.model.Orders;
import com.oms.service.OrderService;

@RestController
public class OrderManagmentController {

	@Autowired
	OrderService orderService;

	@PostMapping("/oms/orders")
	public ResponseEntity<Orders> createOrder(@Valid @RequestBody Orders order) {
		orderService.createOrder(order);
		return new ResponseEntity<>(order,HttpStatus.CREATED) ;
	}

	@GetMapping("/oms/orders/{id}")
	public ResponseEntity<Orders> retriveOrder(@PathVariable int id) {
		Orders order = orderService.retriveOrder(id);
		return new ResponseEntity<>(order,HttpStatus.OK) ;
	}

}
