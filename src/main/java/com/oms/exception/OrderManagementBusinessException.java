package  com.oms.exception;

public class OrderManagementBusinessException extends RuntimeException {

	private static final long serialVersionUID = -145942914795505609L;

	public OrderManagementBusinessException() {
		super();
	}

	public OrderManagementBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderManagementBusinessException(String message) {
		super(message);
	}

	public OrderManagementBusinessException(Throwable cause) {
		super(cause);
	}
}
