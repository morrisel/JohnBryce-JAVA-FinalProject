package dao.interfaces;

import java.sql.Date;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import beans.CouponType;
import beans.Customer;

/**
 * The {@code CouponDAO} interface provides six basics methods for DML functionality
 * and seven additional methods.<br>
 * 
 * The {@code CouponDAO} interface throws all exceptions to the throwable class
 * <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>.
 * 
 * <dl>
 * 		<dt>All Known Implementing Classes:</dt>
 * 		<dd><a href="{@docRoot}/dao/db/CouponDBDAO.html">{@link dao.db.CouponDBDAO}</a></dd>
 * </dl>
 * <hr>
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public interface CouponDAO {

	// required methods
	void createCoupon(Coupon coupon) throws CouponSystemException;

	void removeCoupon(Coupon coupon) throws CouponSystemException;

	void updateCoupon(Coupon coupon) throws CouponSystemException;

	Coupon getCoupon(long id) throws CouponSystemException;

	Collection<Coupon> getAllCoupons() throws CouponSystemException;

	Collection<Coupon> getCouponByType(CouponType couponType) throws CouponSystemException;
	
	/*
	 * Additional Methods
	 */
	// Checkup Methods
	boolean checkCouponDuplicateTitle(Coupon coupon) throws CouponSystemException;

	boolean checkCustomerCouponDuplicate(Customer customer, Coupon coupon) throws CouponSystemException;

	// Company_Coupon Methods
	public void createCompanyCoupon(Company company, Coupon coupon) throws CouponSystemException;

	public void removeCompanyCoupon(Coupon coupon) throws CouponSystemException;
	
	public Collection<Coupon> getCompanyCoupon(Company company) throws CouponSystemException;

	// Customer_Coupon Methods
	public void createCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemException;

	public void removeCustomerCoupon(Coupon coupon) throws CouponSystemException;
	
	public Collection<Coupon> getCustomerCoupon(Customer customer) throws CouponSystemException;

	// Customized Get Coupon Method
	public Collection<Coupon> getCouponUpToDate(Date date) throws CouponSystemException;

}