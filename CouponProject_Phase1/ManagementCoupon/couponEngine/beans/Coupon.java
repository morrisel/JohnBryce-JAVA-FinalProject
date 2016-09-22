package beans;

//https://docs.oracle.com/javase/8/docs/api/
//https://docs.oracle.com/javase/8/docs/api/java/package-summary.html
//https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html
//https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

import java.sql.Date;

/**
 * This class represents the Company table in the Coupon System Database id,
 * title, startDate, endDate, amount, type, message, price and image Attributes
 * Represents a Column in this table
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class Coupon {

	private long id;
	private String title;
	private Date startDate;
	private Date endDate; // ***FOR CUSTOMERS
	private int amount;// ***FOR CUSTOMERS
	private CouponType type; // ***FOR CUSTOMERS
	private String message;
	private double price;
	private String image;

	/**
	 * Explicit default constructor, without parameters and initializations
	 */
	public Coupon() {
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
	 * @return the {@link #title}
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the {@link #title} to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the {@link #startDate}
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the {@link #startDate} to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the {@link #endDate}
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the {@link #endDate} to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the {@link #amount}
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the {@link #amount} to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the {@link #type}
	 */
	public CouponType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the {@link #type} to set
	 */
	public void setType(CouponType type) {
		this.type = type;
	}

	/**
	 * @return the {@link #message}
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the {@link #message} to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the {@link #price}
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the {@link #price} to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the {@link #image}
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the {@link #image} to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "\nCoupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}

}