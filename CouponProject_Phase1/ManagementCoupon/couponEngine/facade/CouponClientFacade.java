package facade;

import beans.CouponSystemException;

/**
 * This Class Extends Exception class . Use For All Throwables Within The Coupon
 * System
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public interface CouponClientFacade {

	public CouponClientFacade login(String userName, String password, String clientType) throws CouponSystemException;
}
