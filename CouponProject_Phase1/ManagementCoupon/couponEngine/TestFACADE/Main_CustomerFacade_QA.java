package TestFACADE;

import beans.Coupon;
import beans.CouponSystemException;
import dao.db.CompanyDBDAO;
import dao.db.CouponDBDAO;
import dao.db.CustomerDBDAO;
import facade.CustomerFacade;

public class Main_CustomerFacade_QA {

	public static void main(String[] args) throws CouponSystemException {

		////////////////////////////////////////////////////////////////////////////////
		//////////////////////////// TEST FOR COMPANY FACADE
		////////////////////////////////////////////////////////////////////////////////

		System.out.println("Test for CustomerFacade is running...");

		CompanyDBDAO companyDbDao = new CompanyDBDAO();
		CouponDBDAO couponDbDao = new CouponDBDAO();
		CustomerDBDAO customerDbDao = new CustomerDBDAO();
		// Customer customer = new Customer();
		Coupon coupon = new Coupon();
		CustomerFacade cf = new CustomerFacade();

		// create customer_coupon - OK ========================================
		// fill data to customer_coupon table...
		// customer = customerDbDao.getCustomer(1);
		// cf.setCustomer(customerDbDao.getCustomer(1));
		// coupon = couponDbDao.getCoupon(6);
		// couponDbDao.customer_coupon(cf.getCustomer(), coupon);

		// check for checkCustomerCouponDup - OK ==============================
		// customer = customerDbDao.getCustomer(1);
		// cf.setCustomer(customerDbDao.getCustomer(4));
		// coupon = couponDbDao.getCoupon(23);
		// System.out.println(
		// couponDbDao.checkCustomerCouponDup(cf.getCustomer(), coupon));

		// check for checkCustomerCouponDup - OK ==============================
		// customer = customerDbDao.getCustomer(1);
		// cf.setCustomer(customerDbDao.getCustomer(4));
		// coupon = couponDbDao.getCoupon(1);
		// System.out.println(couponDbDao.checkCustomerCouponDup(cf.getCustomer(),
		// coupon));

		// check for purchaseCoupon - OK ======================================
		// cf.setCustomer(customerDbDao.getCustomer(4));
		// coupon = couponDbDao.getCoupon(15);
		// coupon.setEndDate(Date.valueOf("2016-07-20")); //for inner testing
		// couponDbDao.updateCoupon(coupon);
		// cf.purchaseCoupon(coupon);

		// check for getAllPurchasedCoupons - OK ==============================
		// cf.setCustomer(customerDbDao.getCustomer(1));
		// System.out.println(cf.getAllPurchasedCoupons());

		// check for getAllPurchasedCouponsByType - OK ========================
		// cf.setCustomer(customerDbDao.getCustomer(1));
		// System.out.println(cf.getAllPurchasedCouponsByType(CouponType.HEALTH));
		// System.out.println(cf.getAllPurchasedCouponsByType(CouponType.CAMPING));

		// check for getAllPurchasedCouponsByPrice - OK =======================
		// cf.setCustomer(customerDbDao.getCustomer(1));
		// System.out.println(cf.getAllPurchasedCouponsByPrice(24));

		System.out.println("\nTest for CustomerFacade is OK.");

	}
}