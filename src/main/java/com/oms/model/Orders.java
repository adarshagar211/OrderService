package com.oms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "ORDERS")
public class Orders implements Serializable {

	private static final long serialVersionUID = 320545199034L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
	@SequenceGenerator(name = "seq_order", initialValue = 1, allocationSize = 1)
	@Column(name = "ORDER_ID")
	private Integer orderId;

	@NotEmpty(message = "Customer Name cannot be null or blank")
	private String customerName;

	@NotEmpty(message = "Shipping Address cannot be null or blank")
	private String shippingAddress;

	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private List<OrderItem> orderItems = new ArrayList<>();

	private Double total;

	public Orders(String customerName, String shippingAddress, List<OrderItem> orderItems, double total) {
		super();
		this.customerName = customerName;
		this.shippingAddress = shippingAddress;
		this.orderItems = orderItems;
		this.total = total;
		this.orderDate = new Date();
	}

	public Orders() {
		super();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
