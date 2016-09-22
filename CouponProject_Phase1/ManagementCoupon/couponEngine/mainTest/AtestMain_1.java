package mainTest;

import java.sql.Date;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import beans.CouponType;
import beans.Customer;
import couponSystemSingelton.CouponSystem;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

public class AtestMain_1 {

	public static void main(String[] args) {

		CouponSystem cs = CouponSystem.getInstace();

		try {

			// ================================================================
			// ======================Test Admin
			// Facade==============================
			// ================================================================

			AdminFacade af = (AdminFacade) cs.login("admin", "1234", "Admin");
			Company company1 = new Company();
			Company company2 = new Company();
			Customer customer1 = new Customer();
			Customer customer2 = new Customer();

			/*
			 * My Db Doesn't work with auto increment ID's
			 */

			company1.setId(1);
			company1.setCompName("Zol");
			company1.setPassword("aaaa");
			company1.setEmail("a@a.com");
			company2.setId(2);
			company2.setCompName("Yakar");
			company2.setPassword("bbbb");
			company2.setEmail("b@b.com");

			// try {
			// af.createCompany(company1);
			// af.createCompany(company2);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }

			// ================================================================

			company1.setId(1);
			company1.setCompName("ZOL");
			company1.setPassword("AAAA");
			company1.setEmail("A@A.COM");

			company2.setId(2);
			company2.setCompName("YAKAR");
			company2.setPassword("BBBB");
			company2.setEmail("B@B.COM");

			// try {
			// af.updateCompany(company1);
			// af.updateCompany(company2);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }

			// ================================================================
			customer1.setId(1);
			customer1.setCustName("avi");
			customer1.setPassword("1234");
			customer2.setId(2);
			customer2.setCustName("benny");
			customer2.setPassword("2345");

			// try {
			// af.createCustomer(customer1);
			// af.createCustomer(customer2);
			// } catch (CouponSystemException e) {
			// e.printStackTrace();
			// }
			// ================================================================
			customer1.setId(1);
			customer1.setCustName("AVI");
			customer1.setPassword("1111");

			customer2.setId(2);
			customer2.setCustName("BENNY");
			customer2.setPassword("2222");

			// try {
			// af.updateCustomer(customer1);
			// af.updateCustomer(customer2);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }

			// ================================================================
			//
			// System.out.println(af.getCompany(company1.getId()));
			//
			// ================================================================
			// System.out.println(af.getAllCompanies());
			// ================================================================
			// System.out.println(af.getCustomer(customer1.getId()));
			// ================================================================
			// System.out.println(af.getAllCustomers());

			// ================================================================
			// ======================Test
			// CompanyFacade============================
			// ================================================================

			CompanyFacade cf = (CompanyFacade) cs.login("Zol", "AAAA", "Company");

			Coupon coupon1 = new Coupon();
			Coupon coupon2 = new Coupon();
			Coupon coupon3 = new Coupon();
			Coupon coupon4 = new Coupon();
			Coupon coupon5 = new Coupon();

			coupon1.setId(1);
			coupon1.setTitle("FirstCoupon");
			coupon1.setStartDate(Date.valueOf("2016-01-07"));
			coupon1.setEndDate(Date.valueOf("2016-07-29"));
			coupon1.setAmount(3);
			coupon1.setType(CouponType.CAMPING);
			coupon1.setMessage("FirstMessage");
			coupon1.setPrice(19.99);
			coupon1.setImage("FirstImage");

			coupon2.setId(2);
			coupon2.setTitle("SecondCoupon");
			coupon2.setStartDate(Date.valueOf("2016-01-07"));
			coupon2.setEndDate(Date.valueOf("2016-07-29"));
			coupon2.setAmount(3);
			coupon2.setType(CouponType.SPORTS);
			coupon2.setMessage("SecondMessage");
			coupon2.setPrice(29.99);
			coupon2.setImage("SecondImage");

			coupon3.setId(3);
			coupon3.setTitle("ThirdCoupon");
			coupon3.setStartDate(Date.valueOf("2016-01-07"));
			coupon3.setEndDate(Date.valueOf("2016-07-28"));
			coupon3.setAmount(3);
			coupon3.setType(CouponType.FOOD);
			coupon3.setMessage("ThirdMessage");
			coupon3.setPrice(39.99);
			coupon3.setImage("ThirdImage");

			coupon4.setId(4);
			coupon4.setTitle("FourthCoupon");
			coupon4.setStartDate(Date.valueOf("2016-01-07"));
			coupon4.setEndDate(Date.valueOf("2016-09-02"));
			coupon4.setAmount(3);
			coupon4.setType(CouponType.ELECTRICITY);
			coupon4.setMessage("FourthMessage");
			coupon4.setPrice(49.99);
			coupon4.setImage("FourthImage");

			coupon5.setId(5);
			coupon5.setTitle("FifthCoupon");
			coupon5.setStartDate(Date.valueOf("2016-01-07"));
			coupon5.setEndDate(Date.valueOf("2016-09-03"));
			coupon5.setAmount(3);
			coupon5.setType(CouponType.FOOD);
			coupon5.setMessage("FifthMessage");
			coupon5.setPrice(59.99);
			coupon5.setImage("FifthImage");

			// cf.createCoupon(coupon1);
			// cf.createCoupon(coupon2);
			// cf.createCoupon(coupon3);
			// cf.createCoupon(coupon4);
			// cf.createCoupon(coupon5);

			// ================================================================
			// coupon1.setId(1);
			// coupon1.setTitle("TryToChangeThisFirstCoupon");
			// coupon1.setStartDate(Date.valueOf("1999-01-01"));
			// coupon1.setEndDate(Date.valueOf("2016-08-01"));
			// coupon1.setAmount(3);
			// coupon1.setType(CouponType.RESTURANT);
			// coupon1.setMessage("TryToChangeThisFirstMessage");
			// coupon1.setPrice(19.50);
			// coupon1.setImage("TryToChangeThisFirstImage");
			//
			// coupon2.setId(2);
			// coupon2.setTitle("TryToChangeThisSecondCoupon");
			// coupon2.setStartDate(Date.valueOf("1999-01-01"));
			coupon2.setEndDate(Date.valueOf("2016-07-29"));
			// coupon2.setAmount(3);
			// coupon2.setType(CouponType.RESTURANT);
			// coupon2.setMessage("SecondMessage");
			// coupon2.setPrice(29.50);
			// coupon2.setImage("TryToChangeThisSecondImage");
			//
			// coupon3.setId(3);
			// coupon3.setTitle("TryToChangeThisThirdCoupon");
			// coupon3.setStartDate(Date.valueOf("1999-01-01"));
			coupon3.setEndDate(Date.valueOf("2016-08-03"));
			// coupon3.setAmount(3);
			// coupon3.setType(CouponType.RESTURANT);
			// coupon3.setMessage("TryToChangeThisThirdMessage");
			// coupon3.setPrice(39.50);
			// coupon3.setImage("TryToChangeThisThirdImage");
			//
			// coupon4.setId(4);
			// coupon4.setTitle("TryToChangeThisFourthCoupon");
			// coupon4.setStartDate(Date.valueOf("1999-01-01"));
			// coupon4.setEndDate(Date.valueOf("2016-08-04"));
			// coupon4.setAmount(3);
			// coupon4.setType(CouponType.RESTURANT);
			// coupon4.setMessage("TryToChangeThisFourthMessage");
			// coupon4.setPrice(49.50);
			// coupon4.setImage("FourthImage");
			//
			// coupon5.setId(5);
			// coupon5.setTitle("TryToChangeThisFifthCoupon");
			// coupon5.setStartDate(Date.valueOf("1999-01-01"));
			// coupon5.setEndDate(Date.valueOf("2016-08-05"));
			// coupon5.setAmount(3);
			// coupon5.setType(CouponType.RESTURANT);
			// coupon5.setMessage("TryToChangeThisFifthMessage");
			// coupon5.setPrice(59.50);
			// coupon5.setImage("TryToChangeThisFifthImage");
			//
			// cf.updateCoupon(coupon1);
			// cf.updateCoupon(coupon2);
			// cf.updateCoupon(coupon3);
			// cf.updateCoupon(coupon4);
			// cf.updateCoupon(coupon5);

			// ================================================================
			// System.out.println(cf.getCoupon(1));
			// ================================================================
			// System.out.println(cf.getAllCoupons());
			// ================================================================
			// System.out.println(cf.getCouponByType(CouponType.FOOD));
			// System.out.println("=====================================================");
			// System.out.println(cf.getCouponByType(CouponType.CAMPING));
			// System.out.println("=====================================================");
			// System.out.println(cf.getCouponByType(CouponType.RESTURANT));
			// System.out.println("=====================================================");
			// System.out.println(cf.getCouponByType(CouponType.SPORT));
			// System.out.println("=====================================================");
			// ================================================================
			// System.out.println(cf.getCouponUpToPrice(35.60));
			// ================================================================
			// System.out.println(cf.getCouponUpToDate(Date.valueOf("2016-08-03")));
			// ================================================================
			// ======================Test Customer Facade
			// ============================
			// ================================================================
			//
			CustomerFacade custFacade = (CustomerFacade) cs.login("avi", "1111", "Customer");

			// ================================================================

			// custFacade.purchaseCoupon(coupon1);
			custFacade.purchaseCoupon(coupon2);
			custFacade.purchaseCoupon(coupon3);
			// custFacade.purchaseCoupon(coupon4);
			// custFacade.purchaseCoupon(coupon5);
			// //
			// ================================================================
			//
			// System.out.println(custFacade.getAllPurchasedCoupons());
			//
			// ================================================================
			//
			// System.out.println(custFacade.getAllPurchasedCouponsByPrice(59.99));
			// ================================================================
			//
			// System.out.println(custFacade.getAllPurchasedCouponsByType(CouponType.SPORT));
			// ================================================================
			// cf.removeCoupon(coupon1);
			// cf.removeCoupon(coupon4);
			// cf.removeCoupon(coupon5);

			// af.removeCustomer(customer2);

			// af.removeCompany(company1);
			// af.removeCompany(company2);

			// af.removeCustomer(customer1);

		} catch (CouponSystemException e) {
			System.err.println(e);
			// ================================================================
		}
	}
}
