package dao.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import beans.CouponSystemException;

/**
 * This Class Extends Exception class . Use For All Throwables Within The Coupon
 * System
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 * 
 */
public class ConnectionPool {

	static final int MAX_CONS = 10;
	private Set<Connection> connections = new HashSet<Connection>();
	private static ConnectionPool instance = new ConnectionPool();
	String url = "jdbc:derby://localhost:1527/CouponDB";

	/**
	 * This method add new connection
	 */
	private ConnectionPool() {
		for (int i = 0; i < MAX_CONS; i++) {
			try {
				connections.add(DriverManager.getConnection(url));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This is a static method, return instance of connection
	 * 
	 * @return {@link ConnectionPool #instance} instance
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}

	/**
	 * This is a synchronized connection method
	 * {@code public synchronized Connection getConnection()}
	 * 
	 * @return {@link Connection} connection
	 */
	public synchronized Connection getConnection() {
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Iterator<Connection> it = connections.iterator();
		Connection conn = it.next();
		it.remove();
		
		return conn;
	}

	/**
	 * This method get connection and add the connection to the <a href=
	 * "https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html">{@code HashSet}</a>
	 * connections
	 * 
	 * @param conn
	 *            {@link Connection} to add
	 */
	public synchronized void returnConnection(Connection conn) {
		connections.add(conn);
		notify();
	}

	/**
	 * @throws CouponSystemException
	 *             <a href=
	 *             "{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>
	 *             If an exception occurred
	 */
	public void closeAllConnections() throws CouponSystemException {

		for (Connection connection : connections) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new CouponSystemException("failed to close connection", e);
			}

		}
	}
}