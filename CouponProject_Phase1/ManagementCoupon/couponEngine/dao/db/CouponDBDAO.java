package dao.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import beans.CouponType;
import beans.Customer;
import dao.connectionPool.ConnectionPool;
import dao.interfaces.CouponDAO;

/**
 * This class implements <a href="{@docRoot}/dao/interfaces/CouponDAO.html">
 * {@link CouponDAO}</a> interface for basic DML functions of Coupon table in
 * database. The class using for all throwables within the
 * CouponManagementSystem
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class CouponDBDAO implements CouponDAO {
	
	private Connection conn;

//	@param couponType the couponType to select
//	@return coupons to get coupons
	
	public CouponDBDAO() {
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
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>
	 */
	@Override
	public void createCoupon(Coupon coupon) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "INSERT INTO Coupon(TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, coupon.getStartDate());
			pstmt.setDate(3, coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, String.valueOf(coupon.getType()));
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());

			pstmt.executeUpdate();
			System.out.println("Coupon #" + coupon.getId() + " Was Created In DB");
		} catch (SQLException e) {
			throw new CouponSystemException("Coupon Creation Failed/Coupon Already Exists in DB", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
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
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>
	 */
	@Override
	public void removeCoupon(Coupon coupon) throws CouponSystemException {

		String sql = "DELETE FROM Coupon WHERE COUPON_ID = " + coupon.getId();
		conn = ConnectionPool.getInstance().getConnection();

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Invalid Values - Unable To Remove Coupon From DB", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
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
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "UPDATE Coupon SET TITLE = ?, START_DATE = ?, END_DATE = ?, AMOUNT = ?, TYPE = ?, MESSAGE = ?, PRICE = ?, IMAGE = ? WHERE COUPON_ID = "
				+ coupon.getId();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// pstmt.setLong(1, coup.getId());
			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, coupon.getStartDate());
			pstmt.setDate(3, coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().toString());
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());

			pstmt.executeUpdate();

			System.out.println("Coupon #" + coupon.getId() + " Was Updated ");

		} catch (SQLException e) {
			throw new CouponSystemException("Coupon Update Failed! Please Check Values", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
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
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Coupon getCoupon(long id) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM COUPON WHERE COUPON_ID = " + id;
		Coupon coupon = new Coupon();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next()) {
				coupon.setId(rs.getLong(1));
				coupon.setTitle(rs.getString(2));
				coupon.setStartDate(rs.getDate(3));
				coupon.setEndDate(rs.getDate(4));
				coupon.setAmount(rs.getInt(5));
				coupon.setType(CouponType.valueOf(rs.getString(6)));
				coupon.setMessage(rs.getString(7));
				coupon.setPrice(rs.getDouble(8));
				coupon.setImage(rs.getString(9));
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get coupon failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return coupon;
	}

	/**
	 * This method return all coupons from the <a href="{@docRoot}/beans/Coupon.html">{@code Coupon}</a> table.
	 * 
	 * @return coupons to get all coupons
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Collection<Coupon> getAllCoupons() throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM COUPON";
		Collection<Coupon> coupons = new ArrayList<>();
		Coupon coupon = null;
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// Coupon coupon = new Coupon();
				coupon = new Coupon();
				coupon.setId(rs.getLong(1));
				coupon.setTitle(rs.getString(2));
				coupon.setStartDate(rs.getDate(3));
				coupon.setEndDate(rs.getDate(4));
				coupon.setAmount(rs.getInt(5));
				coupon.setType(CouponType.valueOf(rs.getString(6)));
				coupon.setMessage(rs.getString(7));
				coupon.setPrice(rs.getDouble(8));
				coupon.setImage(rs.getString(9));

				coupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get All Coupons From The DataBase", e);
		}
		finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

		return coupons;
	}

	/**
	 * This method get <a href="{@docRoot}/beans/CouponType.html">{@code CouponType}</a> as a parameter.
	 * The method return the list of coupons by <a href="{@docRoot}/beans/CouponType.html">CouponType</a>.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this method
	 * will inform about the error.
	 * 
	 * @param couponType the couponType to select
	 * 
	 * @return coupons to get coupons by couponType
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/CouponType.html">CouponType</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Collection<Coupon> getCouponByType(CouponType couponType) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM Coupon WHERE TYPE = '" + couponType + "'";
		Collection<Coupon> coupons = new ArrayList<>();
		Coupon couponByType = null;
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				couponByType = new Coupon();

				couponByType.setId(rs.getLong(1));
				couponByType.setTitle(rs.getString(2));
				couponByType.setStartDate(rs.getDate(3));
				couponByType.setEndDate(rs.getDate(4));
				couponByType.setAmount(rs.getInt(5));
				couponByType.setType(CouponType.valueOf(rs.getString(6)));
				couponByType.setMessage(rs.getString(7));
				couponByType.setPrice(rs.getDouble(8));
				couponByType.setImage(rs.getString(9));

				coupons.add(couponByType);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get Coupons From Type " + couponType + " From The DataBase", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return coupons;
	}

	// ------------------------- ADITIONAL METHODS ------------------------- //

	/**
	 * This method get <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameter.
	 * The method return true if the name of coupon already exists, false otherwise.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @return boolean to get <i>true</i> or <i>false</i>
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public boolean checkCouponDuplicateTitle(Coupon coupon) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM COUPON WHERE TITLE = '" + coupon.getTitle() + "'";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("Coupon Title already taken");
				return true;
			} else {
				System.out.println("Coupon Title is free to use");
				return false;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Coupon May Exist in The DataBase", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}
	
	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html"><code>customer</code></a> and
	 * <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameters.
	 * The method check if the name of the customer and coupon already exists return true, false, otherwise.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @return boolean to get <i>true</i> or <i>false</i>
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public boolean checkCustomerCouponDuplicate(Customer customer, Coupon coupon) throws CouponSystemException {
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM CUSTOMER_COUPON WHERE CUSTOMER_ID = ? AND COUPON_ID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, customer.getId()); // PRIMARY_KEY
			pstmt.setLong(2, coupon.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("CUSTOMER_COUPON Exists");
				return true;
			} else {
				System.out.println("CUSTOMER_COUPON does not Exists");
				return false;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Error While Checking For Coupon Duplications", e);
			
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	// COMPANY_COUPON methods -------------------------------------------------

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html"><code>company</code></a> and
	 * <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameters.
	 * The method insert the {@code id}'s of <a href="{@docRoot}/beans/Company.html">{@code Company}</a> and
	 * <a href="{@docRoot}/beans/Coupon.html">{@code Coupon}</a> to COMPANY_COUPON table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param company the company to insert
	 * 
	 * @param coupon the coupon to insert
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/Coupon.html">Coupon</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	public void createCompanyCoupon(Company company, Coupon coupon) throws CouponSystemException {

		if (company.getId() > 0 && coupon.getId() > 0) {
			String sql = "INSERT INTO COMPANY_COUPON(Company_ID, Coupon_ID) VALUES (?, ?)";

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt;

			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setLong(1, company.getId()); // PRIMARY_KEY
				pstmt.setLong(2, coupon.getId());

				pstmt.executeUpdate();

			} catch (SQLException e) {
				throw new CouponSystemException("COMPANY_COUPON Creation Failed! - Please Check Values", e);
			} finally {
				ConnectionPool.getInstance().returnConnection(conn);
			}
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameter.
	 * The method delete the coupon from the COMPANY_COUPON table.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param coupon the coupon to delete
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	public void removeCompanyCoupon(Coupon coupon) throws CouponSystemException {
		
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "DELETE FROM COMPANY_COUPON WHERE COUPON_ID = " + coupon.getId();
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("Company Coupon Removal Failed! - Please Check Values", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html">{@code Company}</a> as a parameter.
	 * The method return all coupons of the company.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param company the company to select
	 * 
	 * @return coupons to get coupons of the company
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/Company_Coupon.html">Company_Coupon</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	public Collection<Coupon> getCompanyCoupon(Company company) throws CouponSystemException {
		
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT COUPON_ID FROM COMPANY_COUPON WHERE COMPANY_ID = " + company.getId();
		PreparedStatement pstmt;

		try {
			Collection<Coupon> coupons = company.getCoupons();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getLong(1));
				coupons.add(coupon);
			}
			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get COMPANY_COUPON From The DataBase! - Please Check Values", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	// CUSTOMER_COUPON methods -------------------------------------------------

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html"><code>customer</code></a> and
	 * <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameters.
	 * The method insert the {@code id}'s of <a href="{@docRoot}/beans/Customer.html">{@code Customer}</a> and
	 * <a href="{@docRoot}/beans/Coupon.html">{@code Coupon}</a> to join table - <i>Customer_Coupon</i>.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to insert
	 * 
	 * @param coupon the coupon to insert
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	public void createCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemException {

		if (customer.getId() > 0 && coupon.getId() > 0) {
			conn = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO CUSTOMER_COUPON(Customer_ID, Coupon_ID) VALUES (?, ?)";
			PreparedStatement pstmt;

			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setLong(1, customer.getId()); // PRIMARY_KEY
				pstmt.setLong(2, coupon.getId());

				pstmt.executeUpdate();

			} catch (SQLException e) {
				throw new CouponSystemException("CUSTOMER_COUPON Creation Failed! - Please Check Values", e);
			} finally {
				ConnectionPool.getInstance().returnConnection(conn);
			}
		}
	}
	
	/**
	 * This method get <a href="{@docRoot}/beans/Coupon.html"><code>coupon</code></a> as a parameter.
	 * The method delete the coupon from the table.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param coupon the coupon to delete
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	public void removeCustomerCoupon(Coupon coupon) throws CouponSystemException {

		conn = ConnectionPool.getInstance().getConnection();
		String sql = "DELETE FROM CUSTOMER_COUPON WHERE COUPON_ID = " + coupon.getId();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new CouponSystemException("CUSTOMER_COUPON Removal Failed! - Please Check Values", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Customer.html">{@code Customer}</a> as a parameter.
	 * The method return all coupons of the customer.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param customer the customer to select
	 * 
	 * @return coupons to get coupons of the customer
	 * 
	 * @throws CouponSystemException
	 *             If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Customer</a>,
	 * 		<a href="{@docRoot}/beans/getCustomerCoupon.html">getCustomerCoupon</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Collection<Coupon> getCustomerCoupon(Customer customer) throws CouponSystemException {
		
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT COUPON_ID FROM Customer_Coupon WHERE CUST_ID = " + customer.getId();
		PreparedStatement pstmt;
		
		try {
			Collection<Coupon> coupons = customer.getCoupons();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getLong(1));

				coupons.add(coupon);
			}
			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get Customer Coupon From The DataBase! - Please Check Values", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}
	
	// Customized Get Coupon Method -------------------------------------------
	
	/**
	 * This method get <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Date.html">{@code Date}</a> as a parameter.
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
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Date.html">Date</a>
	 */
	public Collection<Coupon> getCouponUpToDate(Date date) throws CouponSystemException {
		
		conn = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM COUPON WHERE END_DATE <= '" + date + "'";
		Collection<Coupon> coupons = new ArrayList<>();
		Coupon coupon = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				coupon = new Coupon();
				coupon.setId(rs.getLong(1));
				coupon.setTitle(rs.getString(2));
				coupon.setStartDate(rs.getDate(3));
				coupon.setEndDate(rs.getDate(4));
				coupon.setAmount(rs.getInt(5));
				coupon.setType(CouponType.valueOf(rs.getString(6)));
				coupon.setMessage(rs.getString(7));
				coupon.setPrice(rs.getDouble(8));
				coupon.setImage(rs.getString(9));
				coupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Unable To Get  Coupons Up To " + date + " From The DataBase", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		
		return coupons;
	}
	
}
