package com.fda.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderItem implements Serializable {

	private static final long serialVersionUID = -8473788002197900400L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Integer productCode;

	@NotEmpty(message = "Product Name cannot be null or blank")
	private String productName;

	private int quantity;

	private Double price;

}
