package facade;

import java.util.ArrayList;
import java.util.Collection;

import beans.Coupon;
import beans.CouponSystemException;
import beans.CouponType;
import beans.Customer;
//import dao.db.CompanyDBDAO;
import dao.db.CouponDBDAO;
import dao.db.CustomerDBDAO;
//import dao.interfaces.CompanyDAO;
import dao.interfaces.CouponDAO;
import dao.interfaces.CustomerDAO;

/**
 * This Class Extends Exception class . Use For All Throwables Within The Coupon
 * System
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class CustomerFacade implements CouponClientFacade {

	// private CompanyDAO companyDao = new CompanyDBDAO();
	private CustomerDAO customerDao = new CustomerDBDAO();
	private CouponDAO couponDao = new CouponDBDAO();
	private Customer customer = new Customer();// when someone login the system
												// get customer facade with the
												// right company
	// private Company company = new Company();

	// CTORS
	/**
	 * This is an initialization of an explicitly default constructor.
	 * 
	 * @see <a href="{@docRoot}/facade/CouponClientFacade.html">CouponClientFacade</a>
	 */
	public CustomerFacade() {
	}

	/**
	 * The constructor get a customer as a parameter and set to local {@code customer} attribute
	 * <br>
	 * {@code this.customer = customer}
	 * 
	 * @param customer the customer to set for local attribute<br>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/facade/CouponClientFacade.html">CouponClientFacade</a>
	 */
	public CustomerFacade(Customer customer) {
		super();
		this.customer = customer;
	}

	// GETTERS-SETTERS
	/**
	 * 
	 * @return the {@link #customer}
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * 
	 * @param customer the {@link #customer} to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// Class Methods
	/**
	 * This method get <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameter.
	 * The method purchase coupon and insert data to
	 * <a href="{@docRoot}/dao/interfaces/CouponDAO.html">{@code Customer_Coupon}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param coupon the coupon to insert
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>
	 */
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {

		if ((couponDao.getCoupon(coupon.getId()).getTitle()) != null) {

			boolean flag = couponDao.checkCustomerCouponDuplicate(this.customer, coupon);
			long today = System.currentTimeMillis();
			long endDateMilis = coupon.getEndDate().getTime();
			long diffDate = endDateMilis - today;
			long hoursLimit = 43200000;

			// check that there's no customer coupon in join table, that the
			// amount
			// of this coupon in the is greater than 0 and the the
			// coupon is still valid(end date is greater than 12 hrs from the
			// purchase day)
			if (!flag && coupon.getAmount() > 0 && diffDate > hoursLimit) {
				int temp = coupon.getAmount();
				--temp;
				coupon.setAmount(temp);
				couponDao.updateCoupon(coupon);
				couponDao.createCustomerCoupon(this.customer, coupon);
			}
		}
	}

	/**
	 * This method return all coupons from the <a href="{@docRoot}/beans/Coupon.html">{@code Coupon}</a> table.
	 * 
	 * @return coupons to get all coupons
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>
	 */
	public Collection<Coupon> getAllPurchasedCoupons() throws CouponSystemException {

		Collection<Coupon> coupons = new ArrayList<>();

		// Get coupon id's by customer id.
		Collection<Coupon> temp = customerDao.getCoupons(customer);

		for (Coupon curr : temp) {
			Coupon coup2 = couponDao.getCoupon(curr.getId());

			coupons.add(coup2);
		}

		return coupons;
	}

	/**
	 * This method get <a href="{@docRoot}/beans/CouponType.html">{@code type}</a> as a parameter.
	 * The method return the list of coupons by <a href="{@docRoot}/beans/CouponType.html">CouponType</a>.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param coupontpe to select a type of coupon
	 * 
	 * @return couponsByType to get all coupons by selected type
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>
	 */
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType coupontpe) throws CouponSystemException {
		Collection<Coupon> customerCoupons = getAllPurchasedCoupons();
		Collection<Coupon> couponsByType = new ArrayList<>();
		int count = 0;

		for (Coupon curr : customerCoupons) {
			if (curr.getType() == coupontpe) {
				couponsByType.add(curr);
				count++;
			}
		}

		if (count == 0) {
			System.out.println("Coupon of specified type " + coupontpe + " was not found");
		}

		return couponsByType;
	}

	/**
	 * This method get {@code price} as a parameter.
	 * The method return the list of coupons up to {@code price} was transfered as a parameter.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param price to select a price of coupon
	 * 
	 * @return couponsUpToPrice to get all coupons up to price
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>
	 */
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price) throws CouponSystemException {

		Collection<Coupon> customerCoupons = getAllPurchasedCoupons();
		Collection<Coupon> couponsUpToPrice = new ArrayList<>();
		int count = 0;

		for (Coupon curr : customerCoupons) {
			if (curr.getPrice() <= price) {
				couponsUpToPrice.add(curr);
				count++;
			}
		}

		if (count == 0) {
			System.out.println("Coupn up to specified price: " + price + " was not found");
		}

		return couponsUpToPrice;
	}

	/**
	 * This method get <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>userName</code></a>,
	 * <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>password</code></a>
	 * and <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>clientType</code></a> as a parameters.<br>
	 * 
	 * The method {@code public CouponClientFacade login(String userName, String password, String clientType)}
	 * return <i><b>{@code client}</b></i> object if the userName and password match, <i><b>{@code null}</b></i> otherwise.
	 * 
	 * @param userName send a userName
	 * 
	 * @param password send a password
	 * 
	 * @param clientType send a clientType
	 * 
	 * @return object to get <i>true</i> or <i>false</i>
	 * 
	 * @see <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 * 		<a href="{@docRoot}/facade/CouponClientFacade.html">CouponClientFacade</a>,
	 * 		<a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html">String</a>
	 */
	@Override
	public CouponClientFacade login(String userName, String password, String clientType) throws CouponSystemException {
		boolean flag = customerDao.login(userName, password);
		if (flag) {
			CouponClientFacade client = new CustomerFacade();
			return client;
		}
		return null;
	}
}