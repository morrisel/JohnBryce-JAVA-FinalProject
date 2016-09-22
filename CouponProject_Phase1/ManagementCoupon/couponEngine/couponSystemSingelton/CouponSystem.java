package couponSystemSingelton;

import beans.Company;
import beans.CouponSystemException;
import beans.Customer;
import dao.DailyCouponExpirationTask;
import dao.connectionPool.ConnectionPool;
import dao.db.CompanyDBDAO;
import dao.db.CustomerDBDAO;
import dao.interfaces.CompanyDAO;
import dao.interfaces.CustomerDAO;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CouponClientFacade;
import facade.CustomerFacade;

/**
 * This class serves as a base for connection of the Coupon system components
 * Allows the entrance to subscribers and to carry out actions for the
 * identified users.
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class CouponSystem {

	private CompanyDAO companyDao = null;
	private CustomerDAO customerDao = null;
	private DailyCouponExpirationTask dailyCouponExpirationTask = null;
	private CouponClientFacade couponClientFacade = null;

	/**
	 * This is a <i>single instance</i> of CouponSystem [private instance]
	 */
	private static CouponSystem instance = null;

	/**
	 * The private constructor create an instance for {@link CompanyDBDAO} and
	 * {@link CustomerDBDAO}. Create and initialize for starting run a new
	 * thread of {@link DailyCouponExpirationTask}
	 */
	private CouponSystem() {
		companyDao = new CompanyDBDAO();
		customerDao = new CustomerDBDAO();

		// creates or reset the DailyCouponExpirationTask object
		this.dailyCouponExpirationTask = new DailyCouponExpirationTask();
		Thread t = new Thread(dailyCouponExpirationTask);
		t.start();
	}

	// 3 getInstance Method
	/**
	 * @return the {@link #instance} of {@link CouponSystem}
	 */
	public static CouponSystem getInstace() {
		if (instance == null) {
			instance = new CouponSystem();
		}
		return instance;
	}

	/**
	 * This method allows to log in - get following parameters userName,
	 * password and clientType. By the clientType the method knows what type it
	 * is necessary to return - CustomerFacade, CompanyFacade or AdminFacade.
	 * 
	 * @param userName
	 *            giving the userName from another method
	 * @param password
	 *            giving the password from another method
	 * @param clientType
	 *            giving the clientType from another method
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred
	 * 
	 * @return couponClientFacade the {@link #couponClientFacade}
	 */
	public CouponClientFacade login(String userName, String password, String clientType) throws CouponSystemException {

		switch (clientType) {
		case "Customer":
			try {
				if (customerDao.login(userName, password)) {
					Customer customer = customerDao.getCustomerByName(userName, password);
					couponClientFacade = new CustomerFacade(customer);
				} else {
					throw new CouponSystemException("Invalid Customer Login");
				}
			} catch (CouponSystemException e) {
				throw new CouponSystemException("Invalid Customer Login", e);
			}
			break;

		case "Company":
			try {
				if (companyDao.login(userName, password)) {
					Company company = companyDao.getCompanyByName(userName, password);
					couponClientFacade = new CompanyFacade(company);
				} else {
					throw new CouponSystemException("Invalid Company Login");
				}
			} catch (CouponSystemException e) {
				throw new CouponSystemException("Invalid Company Login", e);
			}

			break;

		default:
			try {
				if (userName.equals("admin") && password.equals("1234")) {

					couponClientFacade = new AdminFacade();
				} else {
					throw new CouponSystemException("Invalid Administrator Login");
				}
			} catch (CouponSystemException e) {
				System.err.println(e);
			}
			break;
		}
		return couponClientFacade;
	}

	/**
	 * This method stop the task and closing the opened connection
	 * 
	 * @throws CouponSystemException
	 *             <a href=
	 *             "{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 *             If an exception occurred
	 * 
	 * @see <a href=
	 *      "{@docRoot}/dao/DailyCouponExpirationTask.html">DailyCouponExpirationTask</a>
	 *      class.
	 * 
	 *      <br>
	 *      <br>
	 *      <code style="font-style: italic">
	 *      <a href="{@docRoot}/dao/DailyCouponExpirationTask.html">DailyCouponExpirationTask</a>.<a href="{@docRoot}/dao/DailyCouponExpirationTask.html#stopTask--">stopTask()</a>
	 *      <br>
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>.<a href="{@docRoot}/dao/connectionPool/ConnectionPool.html#getInstance--">getInstance()</a>.<a href="{@docRoot}/dao/connectionPool/ConnectionPool.html#closeAllConnections--">closeAllConnections()</a>
	 *      </code>
	 */
	public void shutdown() throws CouponSystemException {

		dailyCouponExpirationTask.stopTask();
		ConnectionPool.getInstance().closeAllConnections();

		System.out.println("System Was Shut Down");
	}

}
