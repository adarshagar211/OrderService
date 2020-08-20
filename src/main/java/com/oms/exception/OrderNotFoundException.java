package  com.oms.exception;

public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1459429147953505609L;

	public OrderNotFoundException() {
		this("Order Not found in database");
	}

	public OrderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException(Throwable cause) {
		super(cause);
	}
}
