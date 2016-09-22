
package beans;

/**
 * @author Morris
 */
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the Company table in the CouponSystem database: id,
 * compName, password, and email. The attributes represents a column in this
 * table.
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class Company {

	private long id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons;

	/**
	 * The constructor initialize the list of {@code coupons}
	 * <br>
	 * {@code this.coupons = new ArrayList<>()}
	 * 
	 * @see <a href="http://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>,
	 *      <a href="http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html">Collection</a>
	 */
	public Company() {
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
				throw new CouponSystemException("Id value must be positive");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the {@link #compName}
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * @param compName
	 *            the {@link #compName} to set
	 */
	public void setCompName(String compName) {
		this.compName = compName;
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
	 * @return the {@link #email}
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the {@link #email} to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
		return "\nCompany [id=" + id + ",  compName=" + compName + ",  password=" + password + ",  email=" + email
				+ ",  coupons=" + coupons + "]";
	}
}