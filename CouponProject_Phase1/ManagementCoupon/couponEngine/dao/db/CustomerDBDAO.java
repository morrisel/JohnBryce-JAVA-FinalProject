package dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import beans.Coupon;
import beans.CouponSystemException;
import beans.Customer;
import dao.connectionPool.ConnectionPool;
import dao.interfaces.CompanyDAO;
import dao.interfaces.CustomerDAO;

/**
 * This class implements <a href="{@docRoot}/dao/interfaces/CompanyDAO.html">
 * {@link CompanyDAO}</a> interface for basic DML functions of Company table in
 * database. The class using for all throwables within the
 * CouponManagementSystem
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class CustomerDBDAO implements CustomerDAO {
	
	private Connection conn;

	public CustomerDBDAO() {
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html"><code>customer</code></a> as a parameter.
	 * The method insert data to <a href="{@docRoot}/beans/Customer.html">{@code Customer}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to insert
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>
	 */
	@Override
	public void createCustomer(Customer customer) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "INSERT INTO Customer(CUSTOMER_NAME, CUSTOMER_PASSWORD) VALUES(?, ?)";
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, customer.getCustName());
			pstmt.setString(2, customer.getPassword());

			pstmt.executeUpdate();
			System.out.println("Customer " + customer.getCustName() + " Was Created In DB");

		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Create Customer , Check Values, Company May Exist", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html"><code>customer</code></a> as a parameter.
	 * The method remove data from <a href="{@docRoot}/beans/Customer.html">{@code Customer}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to remove
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>
	 */
	@Override
	public void removeCustomer(Customer customer) throws CouponSystemException {

		String sql = "DELETE FROM Customer WHERE CUSTOMER_ID = " + customer.getId();
		conn = ConnectionPool.getInstance().getConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			String msg = "Customer " + customer.getId() + " is removed";
			System.out.println(msg);

			System.out.println("Customer " + customer.getCustName() + " Was Deleted From DB");
		} catch (SQLException e) {
			throw new CouponSystemException("Invalid Values - Unable To Remove Company From DB", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html"><code>customer</code></a> as a parameter.
	 * The method update data in the <a href="{@docRoot}/beans/Customer.html">{@code Customer}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to update
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>
	 */
	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		Statement stmt;
		String sql = "UPDATE Customer SET CUSTOMER_NAME = '" + customer.getCustName() + "',"
				+ " CUSTOMER_PASSWORD = '" + customer.getPassword() + "'" + " WHERE CUSTOMER_ID = " + customer.getId();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Customer " + customer.getId() + " Was Updated In DB");
		} catch (SQLException e) {
			throw new CouponSystemException("Customer Update Failed! Please Check Values", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
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
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Customer getCustomer(long id) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM Customer WHERE CUSTOMER_ID = " + id;
		Customer customer = new Customer();
		Statement stmt;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				customer.setId(rs.getLong(1));
				customer.setCustName(rs.getString(2));
				customer.setPassword(rs.getString(3));
			} else {
				System.out.println("Customer not found");
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get Customer From The DataBase", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return customer;
	}

	/**
	 * This method return all customers from the <a href="{@docRoot}/beans/Customer.html">{@code Customer}</a> table.
	 * 
	 * @return customers to get all customers
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Collection<Customer> getAllCustomers() throws CouponSystemException {
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM Customer";
		List<Customer> customers = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getLong(1));
				customer.setCustName(rs.getString(2));
				customer.setPassword(rs.getString(3));
				customers.add(customer);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get All Customers From The DataBase", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

		return customers;
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html"><code>customer</code></a> as a parameter.
	 * The method return the coupons of the specific <a href="{@docRoot}/beans/Customer.html"><code>customer</code></a>.<br>
	 * 
	 * @param customer the customer to select
	 * 
	 * @return coupons to get coupons by customer
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Collection<Coupon> getCoupons(Customer customer) throws CouponSystemException {
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT COUPON_ID FROM CUSTOMER_COUPON WHERE CUSTOMER_ID = " + customer.getId();
		Collection<Coupon> coupons = new ArrayList<>();
		Coupon coupon = new Coupon();
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				coupon = new Coupon();
				coupon.setId(rs.getLong(1));

				coupons.add(coupon);
			}
			return coupons;
		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get Coupons From The DataBase", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}


	@Override
	public boolean login(String custName, String password) throws CouponSystemException {

		if (getCustomerByName(custName, password) instanceof Customer) {
			return true;
		}
		return false;
	}

	// ------------------------- ADITIONAL METHODS ------------------------- //
	
	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html"><code>customer</code></a> as a parameter.
	 * The method return true if the name of customer already exists, false otherwise.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to select by customer name
	 * 
	 * @return boolean to get <i>true</i> or <i>false</i>
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public boolean isFindDuplicateName(Customer customer) throws CouponSystemException {
		
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT CUSTOMER_NAME FROM Customer WHERE CUSTOMER_NAME = '" + customer.getCustName() + "'";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("Customer name already taken");
				return true;
			} else {
				System.out.println("Customer name is free to use");
				return false;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Failed To Check For Customer Duplications", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}
	
	/**
	 * This method get <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>custName</code></a> and
	 * <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>password</code></a> as a parameters.
	 * The method check if the data valid and return the customer.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param custName the custName to select
	 * 
	 * @param password the password to select
	 * 
	 * @return customer to get customer
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CustomerDAO.html">CustomerDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Customer getCustomerByName(String custName, String password) throws CouponSystemException {
		
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_NAME = '" + custName + "' AND CUSTOMER_PASSWORD = '" + password + "'";
		Customer customer = new Customer();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				customer.setId(rs.getLong(1));
				customer.setCustName(rs.getString(2));
				customer.setPassword(rs.getString(3));
			} else {
				System.out.println("Unable To Get Customer From The DataBase");
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get Customer From The DataBase", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return customer;
	}
	

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html"><code>company</code></a> as a parameter.
	 * The method find the last ID in database and return the next ID.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to select
	 * 
	 * @return lastId the next Id for customer
	 * 
	 */
	public long getNextId(Customer customer) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sqlRow = "SELECT MAX(CUSTOMER_ID) FROM CUSTOMER";
		PreparedStatement pstmtId;
		long lastId = 0;

		try {
			pstmtId = conn.prepareStatement(sqlRow);
			ResultSet rsId = pstmtId.executeQuery();

			if (rsId.next()) {
				lastId = rsId.getInt(1) + 1;
			}

			return lastId;

		} catch (SQLException e) {
			throw new CouponSystemException("Not Enough Spase In DataBase!", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

}
