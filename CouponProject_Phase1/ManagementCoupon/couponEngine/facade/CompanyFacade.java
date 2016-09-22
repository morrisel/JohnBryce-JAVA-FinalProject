package facade;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import beans.CouponType;
import dao.db.CompanyDBDAO;
import dao.db.CouponDBDAO;
//import dao.db.CustomerDBDAO;
import dao.interfaces.CompanyDAO;
import dao.interfaces.CouponDAO;
//import dao.interfaces.CustomerDAO;

/**
 * This Class Extends Exception class . Use For All Throwables Within The Coupon
 * System
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class CompanyFacade implements CouponClientFacade {

	private CompanyDAO companyDao = new CompanyDBDAO();
	private CouponDAO couponDao = new CouponDBDAO();
	// private CustomerDAO customerDao = new CustomerDBDAO();
	private Company company = new Company(); // when someone login the system
												// get company facade with the
												// right company
	// private Customer customer = new Customer();

	/**
	 * This is an initialization of an explicitly default constructor
	 * 
	 * @see <a href="{@docRoot}/facade/CouponClientFacade.html">CouponClientFacade</a>
	 */
	public CompanyFacade() {
	}

	/**
	 * The constructor get a company as a parameter and set to local {@code company} attribute
	 * <br>
	 * {@code this.coupons = new ArrayList<>()}
	 * 
	 * @param company the company to set for local attribute<br>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/facade/CouponClientFacade.html">CouponClientFacade</a>
	 */
	public CompanyFacade(Company company) {
		this.company = company;
	}

	// /**
	// * @return the {@link #compName}
	// */
	// GETTERS-SETTERS
	/**
	 * 
	 * @return the {@link #company}
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * 
	 * @param company the {@link #company} to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameter.
	 * The method insert data to <a href="{@docRoot}/beans/Coupon.html">{@code Coupon}</a> table.<br>
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
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public void createCoupon(Coupon coupon) throws CouponSystemException {

		boolean flag = couponDao.checkCouponDuplicateTitle(coupon);

		if (!flag) {
			couponDao.createCoupon(coupon);
			couponDao.createCompanyCoupon(company, coupon);
		} else {
			couponDao.createCompanyCoupon(company, coupon);
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameter.
	 * The method remove data from <a href="{@docRoot}/beans/Coupon.html">{@code Coupon}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param coupon the coupon to remove
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public void removeCoupon(Coupon coupon) throws CouponSystemException {

		// TODO remove coupon from company, remove customer coupons as well.
		if ((couponDao.getCoupon(coupon.getId()).getTitle()) != null) {
			// COMPANY_COUPON join table removal by COUPON ID
			couponDao.removeCompanyCoupon(coupon);

			// CUSTOMER_COUPON join table removal by COUPON ID
			couponDao.removeCustomerCoupon(coupon);

			couponDao.removeCoupon(coupon);
		} else {
			throw new CouponSystemException(
					"Unable To Remove Coupon From The DataBase. The Coupon May Exist, Please Check the Values.");
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameter.
	 * The method update data in the <a href="{@docRoot}/beans/Coupon.html">{@code Coupon}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param coupon the coupon to update
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public void updateCoupon(Coupon coupon) throws CouponSystemException {

		Coupon temp = couponDao.getCoupon(coupon.getId());

		temp.setEndDate(coupon.getEndDate());
		temp.setPrice(coupon.getPrice());

		couponDao.updateCoupon(temp);
	}

	/**
	 * This method get {@code id} as a parameter.
	 * The method return a specific coupon by {@code id} from {@code Coupon} table.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param id the id to select
	 * 
	 * @return coupon to get coupon by id 
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public Coupon getCoupon(long id) throws CouponSystemException {
		Coupon coupon = couponDao.getCoupon(id);

		return coupon;
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
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public Collection<Coupon> getAllCoupons() throws CouponSystemException {

		Collection<Coupon> temp = companyDao.getCoupons(company);
		Collection<Coupon> coupons = new ArrayList<>();

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
	 * @param type to select a type of coupon
	 * 
	 * @return couponsByType to get all coupons by selected type
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public Collection<Coupon> getCouponByType(CouponType type) throws CouponSystemException {

		// for get information data
		Collection<Coupon> companyCoupons = getAllCoupons();
		Collection<Coupon> couponsByType = new ArrayList<>();

		int count = 0;

		for (Coupon curr : companyCoupons) {
			if (curr.getType() == type) {
				couponsByType.add(curr);
				count++;
			}
		}

		if (count == 0) {
			System.out.println("Coupon of specified type " + type + " was not found");
		}

		return couponsByType;
	}

	///////////////////////////////////////////////////////////////////////////
	/** Additional methods */
	/**
	 * This method get {@code price} as a parameter.
	 * The method check if existing price of coupons is up to price that sended as a parameter and return these coupons.
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param price the up to price
	 * 
	 * @return coupons to get coupons by up to price
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public Collection<Coupon> getCouponUpToPrice(double price) throws CouponSystemException {

		Collection<Coupon> companyCoupons = getAllCoupons();
		Collection<Coupon> couponsUpToPrice = new ArrayList<>();
		int count = 0;

		for (Coupon curr : companyCoupons) {
			if (curr.getPrice() <= price) {
				couponsUpToPrice.add(curr);
				count++;
			}
		}

		if (count == 0) {
			System.out.println("Coupon up to specified price:  " + price + " was not found");
		}

		return couponsUpToPrice;

	}

	/**
	 * This method get <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Date.html">{@code date}</a> as a parameter.
	 * The method compares the current date of coupons with the date that was transferred as a parameter,
	 * and returns the updated coupons.
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param date the date to select
	 * 
	 * @return coupons to get coupons with updated date
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public Collection<Coupon> getCouponUpToDate(Date date) throws CouponSystemException {

		Collection<Coupon> companyCoupons = couponDao.getCouponUpToDate(date);

		return companyCoupons;

	}

	/**
	 * This method get <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>compName</code></a>,
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
	 * @see <a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>,
	 * 		<a href="{@docRoot}/facade/CouponClientFacade.html">CouponClientFacade</a>,
	 * 		<a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html">String</a>
	 */
	@Override
	public CouponClientFacade login(String userName, String password, String clientType) throws CouponSystemException {
		boolean flag = companyDao.login(userName, password);
		if (flag) {
			CouponClientFacade client = new CompanyFacade();
			return client;
		}
		return null;
	}
}