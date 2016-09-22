package facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import beans.Customer;
import dao.db.CompanyDBDAO;
import dao.db.CouponDBDAO;
import dao.db.CustomerDBDAO;
import dao.interfaces.CompanyDAO;
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
public class AdminFacade implements CouponClientFacade {

	private CompanyDAO companyDao = new CompanyDBDAO();
	private CustomerDAO customerDao = new CustomerDBDAO();
	private CouponDAO couponDao = new CouponDBDAO();

	/**
	 * This is an initialization of an explicitly default constructor.
	 * 
	 * @see <a href="{@docRoot}/facade/CouponClientFacade.html">CouponClientFacade</a>
	 */
	public AdminFacade() {
	}

	///////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// COMPANY
	///////////////////////////////////////////////////////////////////////////

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html">{@link Company company}</a> as a parameter.
	 * The method insert data to <a href="{@docRoot}/beans/Company.html">{@link Company}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param company the company to insert
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public void createCompany(Company company) throws CouponSystemException {

		boolean flag = companyDao.isFindDuplicateName(company);
		long currId = companyDao.getNextId(company);

		if (!flag && currId > 0) {
			if (company.getId() != 0) {
				System.err.println("The system does not allow to create an ID #" + company.getId()
						+ ", the ID generates automatically");
			}
			else {
				companyDao.createCompany(company);
				System.out.println("The Company '" + company.getCompName() + "' is created");
			}
		} else {
			throw new CouponSystemException("Check Values - Company May Exists In The DataBase ");
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html">{@link Company company}</a> as a parameter.
	 * The method remove data from <a href="{@docRoot}/beans/Company.html">{@link Company}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param company the company to remove
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 *             
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>
	 */
	public void removeCompany(Company company) throws CouponSystemException {

		//if ((companyDao.getCompany(company.getId())).getCompName() != null)
		if (company.getId() > 0) {
			List<Coupon> companyCouponList = (ArrayList<Coupon>) companyDao.getCoupons(company);
			for (Coupon coupon : companyCouponList) {
				couponDao.removeCoupon(coupon);
				couponDao.removeCustomerCoupon(coupon);
				couponDao.removeCompanyCoupon(coupon);
			}
			companyDao.removeCompany(company);
			System.out.println("Company " + company.getCompName() + " removed successfully");
		} else {
			throw new CouponSystemException(
					"Unable To Remove Company From The DataBase. The Company May Exist, Please Check the Values.");
		}

	}

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html">{@link Company company}</a> as a parameter.
	 * The method update data in the <a href="{@docRoot}/beans/Company.html">{@link Company}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param company the company to update
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public void updateCompany(Company company) throws CouponSystemException {
		
		System.out.println("Update: " + company.getCompName());
		Company temp = companyDao.getCompany(company.getId());
		temp.setPassword(company.getPassword());
		temp.setEmail(company.getEmail());
		
		companyDao.updateCompany(temp);
		System.out.println("The company updated successfully");
	}

	/**
	 * This method get {@code id} as a parameter.
	 * The method return a specific company by {@code id} from {@code Company} table.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param id the id to select
	 * 
	 * @return company to get company by id 
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public Company getCompany(long id) throws CouponSystemException {
		Company company = companyDao.getCompany(id);
		return company;
	}

	/**
	 * This method return all companies from the <a href="{@docRoot}/beans/Company.html">{@code Company}</a> table.
	 * 
	 * @return companies to get all companies
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>
	 */
	public Collection<Company> getAllCompanies() throws CouponSystemException {
		Collection<Company> companies = companyDao.getAllCompanies();
		return companies;
	}

	///////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// CUSTOMER
	///////////////////////////////////////////////////////////////////////////

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html">{@link Customer customer}</a> as a parameter.
	 * The method insert data to <a href="{@docRoot}/beans/Customer.html">{@link Customer}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to insert
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>
	 */
	public void createCustomer(Customer customer) throws CouponSystemException {

		boolean flag = customerDao.isFindDuplicateName(customer);
		long currId = customerDao.getNextId(customer);

		if (!flag && currId > 0) {
			if (customer.getId() != 0) {
				System.err.println("The system does not allow to create an ID #" + customer.getId()
						+ ", the ID generates automatically");
			} else {
				customerDao.createCustomer(customer);
				System.out.println("The Customer '" + customer.getCustName() + "' is created");
			}
		} else {
			throw new CouponSystemException("Check Values - Customer May Exists In The DataBase");
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html">{@link Customer customer}</a> as a parameter.
	 * The method remove data from <a href="{@docRoot}/beans/Customer.html">{@link Customer}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to remove
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>
	 */
	public void removeCustomer(Customer customer) throws CouponSystemException {

		// if ((customerDao.getCustomer(customer.getId()).getCustName() != null))
		if (customer.getId() > 0) {
			List<Coupon> customerCouponList = (ArrayList<Coupon>) customerDao.getCoupons(customer);
			for (Coupon coupon : customerCouponList) {
				couponDao.removeCustomerCoupon(coupon);
				// couponDao.removeCoupon(coupon);
			}
			customerDao.removeCustomer(customer);
			System.out.println("Customer " + customer.getCustName() + " removed successfull");
		} else {
			throw new CouponSystemException(
					"Unable To Remove Customer From The DataBase. The Customer May Exist, Please Check the Values.");
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html">{@link Customer customer}</a> as a parameter.
	 * The method update data in the <a href="{@docRoot}/beans/Customer.html">{@link Customer}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to update
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>
	 */
	public void updateCustomer(Customer customer) throws CouponSystemException {

		System.out.println("Update: " + customer.getCustName());
		Customer temp = customerDao.getCustomer(customer.getId());
		temp.setCustName(customer.getCustName());
		temp.setPassword(customer.getPassword());

		customerDao.updateCustomer(temp);
		System.out.println("The customer updated successfull");
	}

	/**
	 * This method get {@code id} as a parameter.
	 * The method return a specific customer by {@code id} from {@code Customer} table.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param id the id to select
	 * 
	 * @return customer to get customer by id 
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>
	 */
	public Customer getCustomer(long id) throws CouponSystemException {
		Customer customer = customerDao.getCustomer(id);
		return customer;
	}

	/**
	 * This method return all customers from the <a href="{@docRoot}/beans/Customer.html">{@code Customer}</a> table.
	 * 
	 * @return customers to get all customers
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>
	 */
	public Collection<Customer> getAllCustomers() throws CouponSystemException {
		Collection<Customer> customers = customerDao.getAllCustomers();
		return customers;
	}

	// Not In Use

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

		// CouponClientFacade client = null;
		//
		// if (userName == "admin" && password == "1234") {
		// client = new AdminFacade();
		// return client;
		// }

		return null;
	}

}