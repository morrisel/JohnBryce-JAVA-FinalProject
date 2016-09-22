package TestFACADE;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import dao.db.CompanyDBDAO;
import dao.db.CouponDBDAO;
import dao.db.CustomerDBDAO;
import facade.CompanyFacade;

public class Main_CompanyFacade_QA {

	public static void main(String[] args) throws CouponSystemException {

		////////////////////////////////////////////////////////////////////////////////
		//////////////////////////// TEST FOR COMPANY FACADE
		////////////////////////////////////////////////////////////////////////////////

		System.out.println("Test for CompanyFacade is running...");

		CompanyDBDAO companyDbDao = new CompanyDBDAO();
		CouponDBDAO couponDbDao = new CouponDBDAO();
		CustomerDBDAO customerDbDao = new CustomerDBDAO();
		Company company = new Company();
		Coupon coupon = new Coupon();
		CompanyFacade cf = new CompanyFacade(company);

		// CompanyFacade companyFacade2 = null;

		// 1
		// company_coupon(Company) - OK =====================================
		// coupon = couponDbDao.getCoupon(3);
		// System.out.println(company);
		// System.out.println(coupon);
		// couponDbDao.checkCouponDuplicateTitle(coupon);
		// System.out.println(couponDbDao.checkCouponDuplicateTitle(coupon));
		//
		// cf.createCoupon(coupon);
		// ====================================================================

		// 2
		// create_company_coupon(Company) - OK ================================
		// company = companyDbDao.getCompany(2);
		// cf.setCompany(companyDbDao.getCompany(4));
		// Collection<Coupon> coupons = cf.getAllCoupons();
		// System.out.println(coupons);
		// ====================================================================

		// 3
		// get coupons by type - OK ===========================================
		// cf.setCompany(companyDbDao.getCompany(1));
		// Collection<Coupon> coupons =
		// cf.getCouponByType(CouponType.ELECTRICITY);
		// ====================================================================

		// 4
		// get coupons up to price - OK =======================================
		// cf.setCompany(companyDbDao.getCompany(1));
		// Collection<Coupon> coupons = cf.getCouponUpToPrice(23.00);
		// System.out.println(coupons);
		// ====================================================================

		// 5
		// get coupons up to date - OK ========================================
		// cf.setCompany(companyDbDao.getCompany(1));
		// Collection<Coupon> coupons =
		// cf.getCouponUpToDate(Date.valueOf("2013-02-07"));
		// System.out.println(coupons);
		
		
		

		// *******************************************************************/

		// updateCoupon - OK ==================================================
		// CompanyFacade companyFacade = new CompanyFacade();
		// CouponDBDAO couponDbDao = new CouponDBDAO();
		//
		// Coupon coupon = couponDbDao.getCoupon(1);
		//
		// companyFacade.createCoupon(coupon);
		//
		// coupon.setTitle("Take-A-Way");
		// coupon.setStartDate(Date.valueOf("2015-05-01"));
		// coupon.setEndDate(Date.valueOf("2015-06-01"));
		// coupon.setAmount(4);
		// coupon.setType(CouponType.FOOD);
		// coupon.setMessage("Karlibach 19, Tel-Aviv Jaffa");
		// coupon.setPrice(140);
		// coupon.setImage("BLOB");
		//
		// couponDbDao.updateCoupon(coupon);
		//
		// System.out.println(couponDbDao.getAllCoupons());
		///////////////// ------------------------------------------------------------------------------------------------
		// CouponDBDAO couponDbDao = new CouponDBDAO();
		// Coupon coupon = null;
		// coupon = couponDbDao.getCoupon(24);
		// coupon.setAmount(234444);
		// couponDbDao.updateCoupon(coupon);
		// System.out.println(couponDbDao.getCoupon(24));

		// updateCompany - OK =================================================
		// Company company = compFacade.getCompany(-1);
		// compFacade.updateCompany(company);

		// getCompany(Company) - OK ===========================================
		// Company company = null;
		// // company = compFacade.getCompany(-2);
		// // company = compFacade.getCompany(0);
		// // company = compFacade.getCompany(2);
		// company = compFacade.getCompany(3);
		// System.out.println(company);

		// coupdb.CompanyJoinCoupon(comp, coupon);

		// Company comp = compFacade.getCompany(1);
		//
		// System.out.println(company.getCoupons());
		//
		// Collection<Coupon> list = comp.getCoupons();
		// list.add(coupon);

		// System.out.println(list);
		//
		// Collection<Company> companies = companyDbDao.getAllCompanies();
		// System.out.println(companies);

		System.out.println("\nTest for CompanyFacade is OK.");

		// System.out.println("\n-------------------------------------------------------------------------------------\n");
		//
		// System.out.println("Test for Customer is running...\n");

		// createCoupon - OK =================================================

		// 1 -
		// 1 - create references for coupons
		// Coupon coupon1 = couponDbDao.getCoupon(21);
		// Coupon coupon2 = couponDbDao.getCoupon(23);
		// Coupon coupon3 = couponDbDao.getCoupon(118);
		// Coupon coupon4 = couponDbDao.getCoupon(119);
		// Coupon coupon5 = couponDbDao.getCoupon(120);
		// Coupon coupon6 = couponDbDao.getCoupon(24);
		//
		// // 2 - create reference for specific company
		// company = companyDbDao.getCompany(1);
		//
		// // 3 - send company with reference to constructor that get parameter
		// companyFacade1 = new CompanyFacade(company);

		// System.out.println(companyFacade1.getAllCoupons());

		// Customer cust = customerDbDao.getCustomer(4);
		// couponDbDao.createCustomerJoinCoupon(cust, coupon6);
		// companyFacade1 = new CompanyFacade(company);
		//
		// companyFacade1.createCoupon(coupon6);

		// cf.createCoupon(coupon1);
		// cf.createCoupon(coupon2);
		// cf.createCoupon(coupon3);
		// cf.createCoupon(coupon4);
		// cf.createCoupon(coupon5);

		// Collection<Coupon> coupons = companyFacade.getAllCoupons();
		// System.out.println(coupons);

		// 2 - remove coupons
		// cf = new CompanyFacade();
		// Coupon coupon = coupdb.getCoupon(14);
		// cf.removeCoupon(coupon);

		// cf.updateCoupon(coupon);
		// Collection<Coupon> coupons = coupdb.getAllCoupons();
		// System.out.println(coupons);

		// create Company_Coupon - OK =========================================
		// company = companyDbDao.getCompany(3);
		// cf.setCompany(company);
		//
		//
		// System.out.println("\nTest for Customer is OK.");
	}
}