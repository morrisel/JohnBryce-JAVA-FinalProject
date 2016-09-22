package beans;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the Customer table in the CouponSystem database: id,
 * custName and password. The attributes represents a column in this table.
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class Customer {

	private long id;
	private String custName;
	private String password;
	private Collection<Coupon> coupons;

	/**
	 * The constructor initialize the list of {@code coupons}
	 * <br>
	 * {@code this.coupons = new ArrayList<>()}
	 * 
	 * @see <a href="http://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html">Collection</a>
	 */
	public Customer() {
		this.coupons = new ArrayList<>();
	}

	/**
	 * @return the {@link #id}
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the {@link #id} to set
	 */
	public void setId(long id) {
		try {
			if (id > 0) {
				this.id = id;
			} else {
				throw new CouponSystemException("Id Value Must Be Positive");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the {@link #custName}
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName
	 *            the {@link #custName} to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the {@link #password}
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the {@link #password} to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the {@link #coupons}
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * @param coupons
	 *            the {@link #coupons} to set
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "\nCustomer [id=" + id + ", custName=" + custName + ", password=" + password + ", coupons=" + coupons
				+ "]";
	}

}
