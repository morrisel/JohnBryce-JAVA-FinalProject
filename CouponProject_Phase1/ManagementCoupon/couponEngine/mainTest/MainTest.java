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

public class MainTest {

	public static void main(String[] args) throws CouponSystemException {

		CouponSystem cs = CouponSystem.getInstace();
		////////////////////////////////////////////////////////////
		// TEST ADMIN FACADE
		////////////////////////////////////////////////////////////

		// AdminFacade af = new AdminFacade();
		//
		// Company company1 = new Company();
		// Company company2 = new Company();
		// Customer customer1 = new Customer();
		// Customer customer2 = new Customer();
		//
		// // create company =========================================
		// // company1.setId(1);
		// company1.setCompName("Zol");
		// company1.setPassword("aaaa");
		// company1.setEmail("a@a.com");
		//
		// // company2.setId(2);
		// company2.setCompName("Yakar");
		// company2.setPassword("bbbb");
		// company2.setEmail("b@b.com");
		//
		// // try {
		// // af.createCompany(company1);
		// // af.createCompany(company2);
		// // } catch (Exception e) {
		// // // TODO Auto-generated catch block
		// // e.printStackTrace();
		// // }
		//
		// // update company =========================================
		// company1.setId(6);
		// company1.setCompName("ZOL");
		// company1.setPassword("AAAA");
		// company1.setEmail("A@A.COM");
		//
		// company2.setId(7);
		// company2.setCompName("YAKAR");
		// company2.setPassword("BBBB");
		// company2.setEmail("B@B.COM");
		//
		// // try {
		// // af.updateCompany(company1);
		// // af.updateCompany(company2);
		// // } catch (Exception e) {
		// // e.printStackTrace();
		// // }
		//
		// // create customer ========================================
		// customer1.setId(1);
		// customer1.setCustName("avi");
		// customer1.setPassword("1234");
		//
		// customer2.setId(2);
		// customer2.setCustName("benny");
		// customer2.setPassword("2345");
		//
		// // try {
		// // af.createCustomer(customer1);
		// // af.createCustomer(customer2);
		// // } catch (CouponSystemException e) {
		// // e.printStackTrace();
		// // }
		//
		// // update customer ========================================
		// customer1.setId(3);
		// customer1.setCustName("AVI");
		// customer1.setPassword("1111");
		//
		// customer2.setId(4);
		// customer2.setCustName("BENNY");
		// customer2.setPassword("2222");
		//
		// // try {
		// // af.updateCustomer(customer1);
		// // af.updateCustomer(customer2);
		// // } catch (Exception e) {
		// // e.printStackTrace();
		// // }
		//
		// // print company & customer =============================
		// // try {
		// // System.out.println(af.getCompany(company1.getId()));
		// //
		// // System.out.println(af.getAllCompanies());
		// //
		// // System.out.println(af.getCustomer(customer1.getId()));
		// //
		// // System.out.println(af.getAllCustomers());
		// // } catch (CouponSystemException e) {
		// // // TODO Auto-generated catch block
		// // e.printStackTrace();
		// // }
		//
		// // try {
		// // System.out.println(af.getAllCompanies());
		// // } catch (CouponSystemException e) {
		// // // TODO Auto-generated catch block
		// // e.printStackTrace();
		// // }

		////////////////////////////////////////////////////////////
		// TEST COMPANY FACADE
		////////////////////////////////////////////////////////////
		// CompanyFacade compFacade = new CompanyFacade();
		CompanyFacade compFacade = (CompanyFacade) cs.login("Zol", "AAAA", "Company");

		// create coupon ------------------------------------------
		Coupon coupon1 = new Coupon();
		Coupon coupon2 = new Coupon();
		Coupon coupon3 = new Coupon();

		//coupon1.setId(1);
		coupon1.setTitle("FirstCoupon");
		coupon1.setStartDate(Date.valueOf("2016-01-07"));
		coupon1.setEndDate(Date.valueOf("2016-08-27"));
		coupon1.setAmount(3);
		coupon1.setType(CouponType.CAMPING);
		coupon1.setMessage("FirstMessage");
		coupon1.setPrice(19.99);
		coupon1.setImage("FirstImage");

		//coupon2.setId(2);
		coupon2.setTitle("SecondCoupon");
		coupon2.setStartDate(Date.valueOf("2016-01-07"));
		coupon2.setEndDate(Date.valueOf("2016-09-27"));
		coupon2.setAmount(3);
		coupon2.setType(CouponType.SPORTS);
		coupon2.setMessage("SecondMessage");
		coupon2.setPrice(29.99);
		coupon2.setImage("SecondImage");

		//coupon3.setId(3);
		coupon3.setTitle("ThirdCoupon");
		coupon3.setStartDate(Date.valueOf("2016-01-07"));
		coupon3.setEndDate(Date.valueOf("2016-10-28"));
		coupon3.setAmount(3);
		coupon3.setType(CouponType.FOOD);
		coupon3.setMessage("ThirdMessage");
		coupon3.setPrice(39.99);
		coupon3.setImage("ThirdImage");

		compFacade.createCoupon(coupon1);
		compFacade.createCoupon(coupon2);
		compFacade.createCoupon(coupon3);

		// update coupon ----------------------------------
		coupon1.setId(1);
		coupon1.setTitle("TryToChangeThisFirstCoupon");
		coupon1.setStartDate(Date.valueOf("1999-01-01"));
		coupon1.setEndDate(Date.valueOf("2016-08-29"));
		coupon1.setAmount(3);
		coupon1.setType(CouponType.RESTAURANT);
		coupon1.setMessage("TryToChangeThisFirstMessage");
		coupon1.setPrice(19.50);
		coupon1.setImage("TryToChangeThisFirstImage");

		coupon2.setId(2);
		coupon2.setTitle("TryToChangeThisSecondCoupon");
		coupon2.setStartDate(Date.valueOf("1999-01-01"));
		coupon2.setEndDate(Date.valueOf("2016-09-29"));
		coupon2.setAmount(3);
		coupon2.setType(CouponType.RESTAURANT);
		coupon2.setMessage("SecondMessage");
		coupon2.setPrice(29.50);
		coupon2.setImage("TryToChangeThisSecondImage");

		coupon3.setId(3);
		coupon3.setTitle("TryToChangeThisThirdCoupon");
		coupon3.setStartDate(Date.valueOf("1999-01-01"));
		coupon3.setEndDate(Date.valueOf("2016-08-03"));
		coupon3.setAmount(3);
		coupon3.setType(CouponType.RESTAURANT);
		coupon3.setMessage("TryToChangeThisThirdMessage");
		coupon3.setPrice(39.50);
		coupon3.setImage("TryToChangeThisThirdImage");

		//
		// try {
		// compFacade.updateCoupon(coupon1);
		// compFacade.updateCoupon(coupon2);
		// compFacade.updateCoupon(coupon3);
		// compFacade.updateCoupon(coupon4);
		// compFacade.updateCoupon(coupon5);
		// } catch (CouponSystemException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// remove coupon ----------------------------------
		// coupon2.setId(2);
		// coupon3.setId(3);
		// try {
		// compFacade.removeCoupon(coupon2);
		// compFacade.removeCoupon(coupon3);
		// } catch (CouponSystemException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// get methods coupon ----------------------------------
		// try {
		// System.out.println(compFacade.getCoupon(1));
		// System.out.println(compFacade.getAllCoupons());
		// } catch (CouponSystemException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		////////////////////////////////////////////////////////////
		// TEST CUSTOMER FACADE
		////////////////////////////////////////////////////////////

		CustomerFacade custFacade = (CustomerFacade) cs.login("avi", "1111", "Customer");
		custFacade.purchaseCoupon(coupon1);

	}
}