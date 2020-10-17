package com.fda.exception;

public class FoodDeliveryServiceException extends RuntimeException {

	private static final long serialVersionUID = -145942914795505609L;

	public FoodDeliveryServiceException() {
		this("Internal Server Error for OrderItem Service, contact support team");
	}

	public FoodDeliveryServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public FoodDeliveryServiceException(String message) {
		super(message);
	}

	public FoodDeliveryServiceException(Throwable cause) {
		super(cause);
	}
}
