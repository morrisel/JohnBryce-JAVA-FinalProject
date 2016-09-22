package dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import dao.connectionPool.ConnectionPool;
import dao.interfaces.CompanyDAO;

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
public class CompanyDBDAO implements CompanyDAO {
	
	private Connection connection;

	public CompanyDBDAO() {
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html"><code>company</code></a> as a parameter.
	 * The method insert data to <a href="{@docRoot}/beans/Company.html">{@code Company}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this
	 * method will inform about the error.
	 * 
	 * @param company the company to insert
	 * 
	 * @throws CouponSystemException If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>
	 */
	@Override
	public void createCompany(Company company) throws CouponSystemException {
		
		connection = ConnectionPool.getInstance().getConnection();
		String sql = "INSERT INTO Company(COMPANY_NAME, COMPANY_PASSWORD, EMAIL) VALUES (?, ?, ?)";
		PreparedStatement pstmt;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("Create company failed", e);
		}finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html"><code>company</code></a> as a parameter.
	 * The method remove data from <a href="{@docRoot}/beans/Company.html">{@code Company}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this
	 * method will inform about the error.
	 * 
	 * @param company the company to remove
	 * 
	 * @throws CouponSystemException If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>
	 */
	@Override
	public void removeCompany(Company company) throws CouponSystemException {

		connection = ConnectionPool.getInstance().getConnection();
		String sql = "DELETE FROM Company WHERE COMPANY_ID = " + company.getId();

		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Remove company failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html"><code>company</code></a> as a parameter.
	 * The method update data in the <a href="{@docRoot}/beans/Company.html">{@code Company}</a> table.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem then, this
	 * method will inform about the error.
	 * 
	 * @param company the company to update
	 * 
	 * @throws CouponSystemException If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CouponDAO.html">CouponDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>
	 */
	@Override
	public void updateCompany(Company company) throws CouponSystemException {

		connection = ConnectionPool.getInstance().getConnection();
		Statement stmt;

		String sql = "UPDATE Company" + " SET" + " COMPANY_NAME = '" + company.getCompName() + "',"
				+ " COMPANY_PASSWORD = '" + company.getPassword() + "'," + " EMAIL = '" + company.getEmail() + "'"
				+ " WHERE COMPANY_ID = " + company.getId();
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			String msg = "Update company failed";
			throw new CouponSystemException(msg);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	/**
	 * This method get {@code id} as a parameter.
	 * The method return a specific company by {@code id} from {@code Company} table.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem then, this
	 * method will inform about the error.
	 * 
	 * @param id the id to select
	 * 
	 * @return company to get company by id
	 * 
	 * @throws CouponSystemException If an exception occurred<br>
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
	public Company getCompany(long id) throws CouponSystemException {

		connection = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM Company WHERE COMPANY_ID = " + id;
		Company company = new Company();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				company.setId(rs.getLong(1));
				company.setCompName(rs.getString(2));
				company.setPassword(rs.getString(3));
				company.setEmail(rs.getString(4));
			} else {
				System.out.println("The Company not found");
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get company failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		return company;
	}

	/**
	 * This method return all companies from the
	 * <a href="{@docRoot}/beans/Company.html">{@code Company}</a> table.
	 * 
	 * @return companies to get all companies
	 * 
	 * @throws CouponSystemException If an exception occurred<br>
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
	public Collection<Company> getAllCompanies() throws CouponSystemException {

		connection = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM Company";
		Collection<Company> companies = new ArrayList<>();
		Statement stmt;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Company comp = new Company();

				comp.setId(rs.getLong(1));
				comp.setCompName(rs.getString(2));
				comp.setPassword(rs.getString(3));
				comp.setEmail(rs.getString(4));

				companies.add(comp);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get All companies failed", e);
		}finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		return companies;
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html"><code>company</code></a> as a parameter.
	 * The method return the coupons of the specific <a href="{@docRoot}/beans/Company.html"><code>company</code></a>.<br>
	 * 
	 * @param company the company to select
	 * 
	 * @return coupons to get coupons by company
	 * 
	 * @throws CouponSystemException If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Collection<Coupon> getCoupons(Company company) throws CouponSystemException {

		connection = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT COUPON_ID FROM Company_Coupon WHERE Company_ID = " + company.getId();
		PreparedStatement pstmt;
		Collection<Coupon> coupons = new ArrayList<>();

		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Coupon coupon = new Coupon();
				coupon.setId(rs.getLong(1));
				coupons.add(coupon);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("join relation for Company_Coupon is failed");
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return coupons;
	}

	/**
	 * This method get <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>compName</code></a>
	 * and <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>password</code></a>
	 * as a parameters.<br>
	 * 
	 * The method <i>{@code login}</i> return true if the compName and password will be match
	 * by {@link #getCompanyByName(String, String)} method, false otherwise.
	 * 
	 * @param compName send a company name
	 * 
	 * @param password send a password
	 * 
	 * @return boolean to get <i>true</i> or <i>false</i>
	 */
	@Override
	public boolean login(String compName, String password) throws CouponSystemException {

		if (getCompanyByName(compName, password) instanceof Company) {
			return true;
		}

		return false;
	}

	/** Additional methods */

	/**
	 * This method get <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>compName</code></a>
	 * and <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html"><code>password</code></a>
	 * as a parameters.
	 * The method check if the data valid and return the company.<br>
	 * 
	 * If the parameter/s doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param compName the compName to select
	 * 
	 * @param password the password to select
	 * 
	 * @return company to get company
	 * 
	 * @throws CouponSystemException If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html">Statement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	@Override
	public Company getCompanyByName(String compName, String password) throws CouponSystemException {
		
		connection = ConnectionPool.getInstance().getConnection();
		Company company = new Company();
		String sql = "SELECT * FROM COMPANY WHERE COMPANY_NAME = '" + compName + "' AND COMPANY_PASSWORD = '" + password
				+ "'";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				company.setId(rs.getLong(1));
				company.setCompName(rs.getString(2));
				company.setPassword(rs.getString(3));
				company.setEmail(rs.getString(4));

			} else {

				System.out.println("The Company not found");
			}

		} catch (SQLException e) {
			String msg = "Get company failed";
			throw new CouponSystemException(msg);
		}finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		return company;
	}

	/**
	 * This method get <a href="{@docRoot}/beans/Company.html"><code>company</code></a> as a parameter.
	 * The method return true if the name of company already exists, false otherwise.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param company the company to select by company name
	 * 
	 * @return boolean to get <i>true</i> or <i>false</i>
	 * 
	 * @throws CouponSystemException If an exception occurred<br>
	 *             <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/SQLException.html">{@link SQLException}</a> |
	 *             <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 * 
	 * @see <a href="{@docRoot}/beans/Company.html">Company</a>,
	 * 		<a href="{@docRoot}/beans/CouponSystemException.html">CouponSystemException</a>,
	 *      <a href="{@docRoot}/dao/connectionPool/ConnectionPool.html">ConnectionPool</a>,
	 *      <a href="{@docRoot}/dao/interfaces/CompanyDAO.html">CompanyDAO</a>,
	 *      <br>
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html">Connection</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html">PreparedStatement</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html">ResultSet</a>
	 */
	public boolean isFindDuplicateName(Company company) throws CouponSystemException {

		connection = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT COMPANY_NAME FROM Company WHERE COMPANY_NAME = '" + company.getCompName() + "'";
		PreparedStatement pstmt;

		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("Please insert another name for creating a Company");
				return true;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Company Does not exists", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return false;
	}
	
	/**
	 * This method get <a href="{@docRoot}/beans/Company.html"><code>company</code></a> as a parameter.
	 * The method find the last ID in database and return the next ID.<br>
	 * 
	 * If the parameters doesn't valid or if there is some problem, this method
	 * will inform about the error.
	 * 
	 * @param company the company to select
	 * 
	 * @return lastId the next Id for company
	 * 
	 */
	public long getNextId(Company company) throws CouponSystemException {

		connection = ConnectionPool.getInstance().getConnection();
		String sqlRow = "SELECT MAX(COMPANY_ID) FROM Company";
		PreparedStatement pstmtId;
		long lastId = 0;

		try {
			pstmtId = connection.prepareStatement(sqlRow);
			ResultSet rsId = pstmtId.executeQuery();

			if (rsId.next()) {
				lastId = rsId.getInt(1) + 1;
			}

			return lastId;

		} catch (SQLException e) {
			throw new CouponSystemException("Company Does not exists", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}
	
	// public long getIdByCompanyName(Company company) throws
	// CouponSystemException {
	//
	// connection = ConnectionPool.getInstance().getConnection();
	// String sqlRow = "SELECT COMPANY_ID FROM Company WHERE COMPANY_NAME = '" +
	// company.getCompName() + "'";
	// PreparedStatement pstmtId;
	// long companyId = 0;
	//
	// try {
	// pstmtId = connection.prepareStatement(sqlRow);
	// ResultSet rsId = pstmtId.executeQuery();
	//
	// if (rsId.next()) {
	// companyId = rsId.getLong(1);
	// }
	//
	// return companyId;
	//
	// } catch (SQLException e) {
	// throw new CouponSystemException("Company Does not exists", e);
	// } finally {
	// ConnectionPool.getInstance().returnConnection(connection);
	// }
	// }
}