package com.fda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders implements Serializable {

	private static final long serialVersionUID = 320545199034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private Integer orderId;

	@NotEmpty(message = "Customer Name cannot be null or blank")
	private String customerName;

	@NotEmpty(message = "Shipping Address cannot be null or blank")
	private String shippingAddress;

	@Temporal(TemporalType.DATE)
	private Date orderDate;

	private OrderStatus orderStatus;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private List<OrderItem> orderItems = new ArrayList<>();
	

}
