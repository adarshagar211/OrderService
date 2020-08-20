package com.oms;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oms.model.Orders;

@FeignClient(contextId = "OrderServiceClient", value = "orderServiceClient", url = "http://localhost:8081/")
public interface OrderServiceClient {
 	
	@PostMapping("/oms/orders")
	public ResponseEntity<Orders> createOrder(@RequestBody Orders order) ;
	
	@GetMapping("/oms/orders/{id}")
	public ResponseEntity<Orders> retriveOrder(@PathVariable int id) ;

}
