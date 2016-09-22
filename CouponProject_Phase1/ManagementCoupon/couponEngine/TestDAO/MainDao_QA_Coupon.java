package TestDAO;

import java.sql.Date;
import java.time.LocalDate;

import beans.Coupon;
import beans.CouponSystemException;
import beans.CouponType;
import dao.db.CouponDBDAO;

public class MainDao_QA_Coupon {

	public static void main(String[] args) throws CouponSystemException {

		////////////////////////////////////////////////////////////////////////////////
		//////////////////////////// TEST FOR COUPONS
		////////////////////////////////////////////////////////////////////////////////
		System.out.println("test for coupons is running...");

		// createCoupon - OK ==================================================
		 CouponDBDAO dao1 = new CouponDBDAO();
		 Coupon coupon = new Coupon();
		  coupon.setId(1);
		 coupon.setTitle("ACE");
		 coupon.setStartDate(Date.valueOf(LocalDate.now()));
		 coupon.setEndDate(Date.valueOf(LocalDate.now()));
		 coupon.setAmount(11111);
		 coupon.setType(CouponType.CAMPING);
		 coupon.setMessage("hello message from coupon");
		 coupon.setPrice(2341.23);
		 coupon.setImage("myImage");
		 dao1.createCoupon(coupon);
		// DBTablePrinter.printResultSet(dao1.printCoupons());
		// DBTablePrinter.printResultSet(dao1.printCouponsByType(CouponType.CAMPING));

		// removeCoupon - OK ==================================================
		// CouponDBDAO couponDbDao = new CouponDBDAO();
		// Coupon coupon = new Coupon();
		// coupon.setId(22);
		// couponDbDao.removeCoupon(coupon);
		// DBTablePrinter.printResultSet(couponDbDao.printCoupons());

		// updateCoupon - OK ==================================================
		// CouponDBDAO couponDbDao = new CouponDBDAO();
		// Coupon coupon = couponDbDao.getCoupon(24);
		// coupon.setAmount(234444);
		// couponDbDao.updateCoupon(coupon);
		// System.out.println(couponDbDao.getCoupon(24));
		// DBTablePrinter.printResultSet(couponDbDao.printCouponById(24));

		// getCoupon - OK =====================================================
		// CouponDBDAO couponDbDao = new CouponDBDAO();
		// Coupon coupon = couponDbDao.getCoupon(24);
		// System.out.println(coupon);
		// DBTablePrinter.printResultSet(couponDbDao.printCouponById(24));

		// getAllCoupons - OK =================================================
		// CouponDBDAO couponDbDao = new CouponDBDAO();
		// Collection<Coupon> coupons = couponDbDao.getAllCoupons();
		// System.out.println(coupons);
		// DBTablePrinter.printResultSet(couponDbDao.printCoupons());

		// getCouponByType - OK ===============================================
		// CouponDBDAO couponDbDao = new CouponDBDAO();
		// DBTablePrinter.printResultSet(couponDbDao.printCoupons());
		// System.out.println(couponDbDao.getCouponByType(CouponType.CAMPING));
		// DBTablePrinter.printResultSet(couponDbDao.printCouponsByType(CouponType.CAMPING));

		System.out.println("\ntest coupon is OK.");
	}
}