package beans;

/**
 * This Class Extends Exception class . Use For All Throwables Withto Coupon
 * System
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class CouponSystemException extends Exception {

	/**
	 * The serial UID of exceptions is: [serialVersionUID = 1111L] |
	 * {@link #addSuppressed(Throwable)}
	 * 
	 * @serial: serialVersionUID = 1111L
	 * 
	 *          {@value beans.CouponSystemException#serialVersionUID}
	 */
	private static final long serialVersionUID = 1111L;

	/**
	 * The Constructor refers to {@code super} without sending any parameters:
	 * 
	 */
	public CouponSystemException() {
		super();
	}

	/**
	 * The constructor transfer parameter to {@code super}:
	 * 
	 * @param message
	 *            the <i>{@code message}</i> parameter transfered to
	 *            <i>{@code super}</i>
	 */
	public CouponSystemException(String message) {
		super(message);
	}

	/**
	 * The constructor transfer parameter to {@code super}:
	 * 
	 * @param cause
	 *            the <i>{@code cause}</i> parameter transfered to
	 *            <i>{@code super}</i>
	 */
	public CouponSystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * The constructor transfers parameters to {@code super}:
	 * 
	 * @param message
	 *            the <i>{@code message}</i> parameter transfered to
	 *            <i>{@code super}</i>
	 * @param cause
	 *            the <i>{@code cause}</i> parameter transfered to
	 *            <i>{@code super}</i>
	 */
	public CouponSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * The constructor transfers parameters to {@code super}:
	 * 
	 * @param message
	 *            the <i>{@code message}</i> parameter transfered to
	 *            <i>{@code super}</i>
	 * @param cause
	 *            the <i>{@code cause}</i> parameter transfered to
	 *            <i>{@code super}</i>
	 * @param enableSuppression
	 *            the <i>{@code enableSuppression}</i> parameter transfered to
	 *            the <i>{@code super}</i>
	 * @param writableStackTrace
	 *            the <i>{@code writableStackTrace}</i> parameter transfered to
	 *            the <i>{@code super}</i>
	 */
	public CouponSystemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}