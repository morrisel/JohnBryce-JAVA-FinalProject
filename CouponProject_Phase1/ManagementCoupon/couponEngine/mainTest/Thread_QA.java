package mainTest;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import beans.Customer;
import dao.DailyCouponExpirationTask;
import dao.db.CompanyDBDAO;
import dao.db.CouponDBDAO;
import dao.db.CustomerDBDAO;
import dao.interfaces.CompanyDAO;
import dao.interfaces.CouponDAO;
import dao.interfaces.CustomerDAO;
import facade.CompanyFacade;
import facade.CustomerFacade;

public class Thread_QA {

	public static void main(String[] args) throws CouponSystemException, InterruptedException {

		CouponDAO couponDao = new CouponDBDAO();
		CompanyDAO companyDao = new CompanyDBDAO();
		CustomerDAO customerDao = new CustomerDBDAO();
		Coupon coupon = new Coupon();
		Company company = new Company();
		Customer cusomter = new Customer();

		company = companyDao.getCompany(1);

		CompanyFacade companyfacade = new CompanyFacade(companyDao.getCompany(1));
		CustomerFacade customerfacade = new CustomerFacade(customerDao.getCustomer(1));
		// coupon=couponDao.getCoupon(1);
		// coupon.setEndDate(Date.valueOf("2016-07-26"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(2);
		// coupon.setEndDate(Date.valueOf("2016-07-25"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(3);
		// coupon.setEndDate(Date.valueOf("2016-07-25"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(4);
		// coupon.setEndDate(Date.valueOf("2016-07-26"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(5);
		// coupon.setEndDate(Date.valueOf("2016-07-25"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(6);
		// coupon.setEndDate(Date.valueOf("2016-07-24"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(7);
		// coupon.setEndDate(Date.valueOf("2016-07-24"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(8);
		// coupon.setEndDate(Date.valueOf("2016-07-24"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(9);
		// coupon.setEndDate(Date.valueOf("2016-07-23"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);
		// coupon=couponDao.getCoupon(10);
		// coupon.setEndDate(Date.valueOf("2016-07-22"));
		// coupon.setAmount(10);
		// couponDao.updateCoupon(coupon);
		// companyfacade.createCoupon(coupon);
		// customerfacade.purchaseCoupon(coupon);

		////////////////////////////////////////////////////////////////////////////////
		//////////////////////////// TEST FOR DailyCouponExpirationTask
		////////////////////////////////////////////////////////////////////////////////

		DailyCouponExpirationTask task = new DailyCouponExpirationTask();
		task.run();
		task.stopTask();
	}
}