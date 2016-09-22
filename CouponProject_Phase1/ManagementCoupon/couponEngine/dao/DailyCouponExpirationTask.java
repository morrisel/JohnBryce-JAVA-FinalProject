package dao;

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.Coupon;
import beans.CouponSystemException;
import dao.db.CouponDBDAO;
import dao.interfaces.CouponDAO;

/**
 * This class implements Runnable interface. DailyCouponExpirationTask class
 * removes coupons from the system that which expired of date.
 * <hr>
 * 
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public class DailyCouponExpirationTask implements Runnable {

	private CouponDAO couponDao = new CouponDBDAO();
	private List<Coupon> couponsToUpdate;
	private Coupon current;
	private long timeToSleep;
	// this will let us use interrupt of the thread to stop to daily task
	// operation
	private boolean quit;

	// * @see
	// com.liferay.portal.service.persistence.BasePersistence#clearCache()

	/**
	 * The constructor initialize and set following attributes:<br>
	 * initialize the {@code couponsToUpdate} with {@code new ArrayList<>()},<br>
	 * initialize the {@code current} with {@code new Coupon()},<br>
	 * set {@code quit} to {@code true},<br>
	 * set {@code timeToSleep} to zero
	 * 
	 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html">ArrayList</a>,
	 *      <a href="{@docRoot}/beans/Coupon.html">Coupon</a>
	 */
	public DailyCouponExpirationTask() {

		couponsToUpdate = new ArrayList<>();
		current = new Coupon();
		quit = true;
		timeToSleep = 0;
	}

	/**
	 * This method running a daily task for thread and remove the expired
	 * coupons from join tables of Customer and Company
	 * 
	 * @see <a href="{@docRoot}/beans/Customer.html">Coupon</a>,
	 *      <a href="{@docRoot}/beans/Company.html">Coupon</a>
	 */
	@Override
	public void run() {
		System.out.println("Daily Task Started...");

		while (quit) {
			try {
				// stopTask();
				Thread.sleep(timeToSleep); // calculate sleeping time

			} catch (InterruptedException ie) {
				quit = false;
				System.out.println("Daily Task Interrupted");
				break;
			}

			try {
				if (quit) {
					couponsToUpdate = (List<Coupon>) couponDao.getAllCoupons();

					for (Coupon curr : couponsToUpdate) {

						long currentTime = System.currentTimeMillis();
						long endDateMilis = curr.getEndDate().getTime();
						long expiredCouponDate = endDateMilis - currentTime;

						if (expiredCouponDate <= 0) {
							couponDao.removeCompanyCoupon(curr);
							couponDao.removeCustomerCoupon(curr);
							couponDao.removeCoupon(curr);
						}
					}
					System.out.println("Expired Coupons Removed From The System");

				} else {
					throw new CouponSystemException("Could not preform Daily Task");
				}
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}

			Calendar c = Calendar.getInstance();
			long now = c.getTimeInMillis();// current time - thread start time
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);

			long timeDiff = now - c.getTimeInMillis();// (00:00=current hour in
														// miliseconds)
			// sets the sleeping time for this thread
			timeToSleep = 86400000 - timeDiff;
		}
	}

	/**
	 * This void method set {@code false} for {@code quit} attribute
	 * 
	 * @see DailyCouponExpirationTask#DailyCouponExpirationTask
	 */
	public void stopTask() {
		// use outside this class - in singleton
		quit = false;
		// Thread.currentThread().interrupt();
	}
}