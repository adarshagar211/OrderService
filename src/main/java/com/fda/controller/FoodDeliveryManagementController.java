package com.fda.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fda.model.Orders;
import com.fda.service.OrderService;

@RestController
public class FoodDeliveryManagementController {

	@Autowired
	OrderService orderService;

	@GetMapping(path = "/oms/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orders>> retriveOrders() {
		List<Orders> orders = orderService.retriveOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping(path = "/oms/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> retriveOrder(@PathVariable int id) {
		Orders order = orderService.retriveOrder(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PostMapping(path = "/oms/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> addOrder(@RequestBody @Valid Orders order) {
		Orders orderResponse = orderService.addOrder(order);
		return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
	}

	@PatchMapping(path = "/oms/orders/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> updateOrderStatus(@PathVariable int id, @RequestBody Orders order) {
		Orders orderResponse = orderService.updateOrder(id, order);
		return new ResponseEntity<>(orderResponse, HttpStatus.ACCEPTED);
	}

}
