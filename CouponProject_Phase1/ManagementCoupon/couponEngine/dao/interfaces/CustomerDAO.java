package dao.interfaces;

import java.util.Collection;

import beans.Coupon;
import beans.CouponSystemException;
import beans.Customer;

/**
 * The {@code CustomerDAO} interface provides seven basics methods for DML functionality
 * and two additional methods.<br>
 * 
 * The {@code CustomerDAO} interface throws all exceptions to the throwable class
 * <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>.
 * 
 * <dl>
 * 		<dt>All Known Implementing Classes:</dt>
 * 		<dd><a href="{@docRoot}/dao/db/CustomerDBDAO.html">{@link dao.db.CustomerDBDAO}</a></dd>
 * </dl>
 * <hr>
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public interface CustomerDAO {

	void createCustomer(Customer customer) throws CouponSystemException;

	void removeCustomer(Customer customer) throws CouponSystemException;

	void updateCustomer(Customer customer) throws CouponSystemException;

	Customer getCustomer(long id) throws CouponSystemException;

	Collection<Customer> getAllCustomers() throws CouponSystemException;

	Collection<Coupon> getCoupons(Customer customer) throws CouponSystemException;

	boolean login(String custName, String password) throws CouponSystemException;

	// additional methods

	boolean isFindDuplicateName(Customer customer) throws CouponSystemException;

	Customer getCustomerByName(String custName, String password) throws CouponSystemException;

	public long getNextId(Customer customer) throws CouponSystemException;
}