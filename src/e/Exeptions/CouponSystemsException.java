package e.Exeptions;

public class CouponSystemsException extends Exception {

	private static final long serialVersionUID = 1L;

	public CouponSystemsException() {

	}

	public CouponSystemsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponSystemsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponSystemsException(String message) {
		super(message);
	}

	public CouponSystemsException(Throwable cause) {
		super(cause);
	}

}



