package com.fda;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fda.model.OrderItem;
import com.fda.model.OrderStatus;
import com.fda.model.Orders;
import com.fda.repository.OrderRepository;
import com.fda.util.FdaUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FoodDeliveryApplication.class)
@AutoConfigureMockMvc
class FoodDeliveryApplicationTests {

	@MockBean
	private OrderRepository orderRespository;

	@Autowired
	private MockMvc mockmvc;

	private static final String FOOD_DELIVERY_APP_URL = "/oms/orders";
	private static final String FOOD_DELIVERY_APP_URL_ID = "/oms/orders/{id}";

	@BeforeEach
	public void prepareData() {
		// Sending mock response from OrderRepository

		// Order 1
		Orders order1 = createOrder1();

		// Order 2
		Orders order2 = createOrder2();

		// Order 3
		Orders order3 = createOrder3();
		
		// Order 6
		Orders order4 = createSaveOrder();

		// List of orders
		List<Orders> listOfOrders = new ArrayList<>();
		listOfOrders.add(order1);
		listOfOrders.add(order2);
		listOfOrders.add(order3);

		when(orderRespository.findById(1)).thenReturn(Optional.of(order1));
		when(orderRespository.findById(2)).thenReturn(Optional.of(order2));
		when(orderRespository.findById(3)).thenReturn(Optional.of(order3));

		when(orderRespository.findAll()).thenReturn(listOfOrders);
		
		when(orderRespository.save(Mockito.any(Orders.class))).thenReturn(order4);	
	}


	// Testing Endpoint : @GetMapping "/oms/orders"
	@Test
	public void testEndpoint1() throws Exception {
		MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.get(FOOD_DELIVERY_APP_URL))
				 .andExpect(MockMvcResultMatchers.status().isOk())
    			 .andExpect(MockMvcResultMatchers.jsonPath("$[0]['orderId']").value(1))
    			 .andReturn();
		log.info("result of test 1 " + mvcResult.getResponse().getContentAsString());
	}

	// Testing Endpoint : @GetMapping "/oms/orders/{id}"
	@Test
	public void testEndpoint2() throws Exception {
		MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.get(FOOD_DELIVERY_APP_URL_ID, 2))
				.andExpect(MockMvcResultMatchers.status().isOk())
    			 .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(2))	
				.andReturn();
		log.info("result of test 2 " + mvcResult.getResponse().getContentAsString());
	}

	// Testing Endpoint : @GetMapping "/oms/orders/{id}"
	@Test
	public void testEndpoint3() throws Exception {
		MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.get(FOOD_DELIVERY_APP_URL_ID, 3))
				                      .andExpect(MockMvcResultMatchers.status().isOk())
				          			  .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(3))
				          			  .andReturn();
		log.info("result of test 3 " + mvcResult.getResponse().getContentAsString());
	}

	// Testing Endpoint : @GetMapping "/oms/orders/{id}"
	@Test
	public void testEndpoint4() throws Exception {
		MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.get(FOOD_DELIVERY_APP_URL_ID, 5))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Order Not found in database"))
				.andReturn();
		log.info("result of test 4 " + mvcResult.getResponse().getContentAsString());
	}

	// Testing Endpoint : @PostMapping "/oms/orders"

	@Test
	public void testEndpoint5() throws Exception {

		// Order 6
		OrderItem orderItem6 = OrderItem.builder().productCode(6).productName("FoodItem6").quantity(5).price(50.00)
				.build();

		Orders order4 = Orders.builder().orderId(4).orderDate(new Date()).customerName("Person4")
				.shippingAddress("Address3").orderItems(Collections.singletonList(orderItem6)).build();
			
		MvcResult mvcResult = mockmvc
				.perform(MockMvcRequestBuilders.post(FOOD_DELIVERY_APP_URL)
				.content(FdaUtils.asJsonString(order4))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
    			.andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(5))
    			.andReturn();
		log.info("result of test 5 " + mvcResult.getResponse().getContentAsString());
	}

	// Testing Endpoint : @PutMapping "/oms/orders/{id}"

	@Test
	public void testEndpoint6() throws Exception {

		// Order 5
		Orders order5 = Orders.builder().orderStatus(OrderStatus.OUTFORDELIVERY).build();
	
		MvcResult mvcResult = mockmvc
				.perform(MockMvcRequestBuilders.patch(FOOD_DELIVERY_APP_URL_ID, 2)
						.content(FdaUtils.asJsonString(order5))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				        .andExpect(MockMvcResultMatchers.status().isAccepted())
	          			 .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(5))
	          			.andReturn();
		log.info("result of test 6 " + mvcResult.getResponse().getContentAsString());
	}


	// Create Order 1
	private Orders createOrder1() {
		OrderItem orderItem1 = OrderItem.builder().productCode(1).productName("FoodItem1").quantity(1).price(10.00)
				.build();
		OrderItem orderItem2 = OrderItem.builder().productCode(2).productName("FoodItem2").quantity(2).price(20.00)
				.build();
		List<OrderItem> list1 = new ArrayList<OrderItem>();
		list1.add(orderItem1);
		list1.add(orderItem2);
		
		return Orders.builder().orderId(1).orderDate(new Date()).customerName("Person1")
				.orderStatus(OrderStatus.RECIEVED).shippingAddress("Address1").orderItems(list1).build();
	}
	
	// Create Order 2
	private Orders createOrder2() {
		OrderItem orderItem3 = OrderItem.builder().productCode(3).productName("FoodItem3").quantity(3).price(10.00)
				.build();
		OrderItem orderItem4 = OrderItem.builder().productCode(4).productName("FoodItem4").quantity(2).price(20.00)
				.build();
		List<OrderItem> list2 = new ArrayList<OrderItem>();
		list2.add(orderItem3);
		list2.add(orderItem4);

		Orders order2 = Orders.builder().orderId(2).orderDate(new Date()).customerName("Person2")
				.orderStatus(OrderStatus.RECIEVED).shippingAddress("Address2").orderItems(list2).build();
		return order2;
	}
	
	// Create Order 3
	private Orders createOrder3() {
		OrderItem orderItem5 = OrderItem.builder().productCode(5).productName("FoodItem5").quantity(5).price(50.00)
				.build();

		Orders order3 = Orders.builder().orderId(3).orderDate(new Date()).customerName("Person3")
				.orderStatus(OrderStatus.COMPLETED).shippingAddress("Address3")
				.orderItems(Collections.singletonList(orderItem5)).build();
		return order3;
	}
	
	// Create Save Order 	
	private Orders createSaveOrder() {
		OrderItem orderItem6 = OrderItem.builder().productCode(7).productName("FoodItem7").quantity(5).price(50.00)
				.build();

		Orders order5 = Orders.builder().orderId(5).orderDate(new Date()).customerName("Person4")
				.shippingAddress("Address3").orderItems(Collections.singletonList(orderItem6)).build();
		return order5;
	}
	
}
