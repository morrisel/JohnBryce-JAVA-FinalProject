package TestFACADE;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;
import dao.db.CompanyDBDAO;
import dao.db.CouponDBDAO;
import facade.AdminFacade;
import facade.CompanyFacade;

public class Main_AdminFacade_QA {

	public static void main(String[] args) throws CouponSystemException {

		////////////////////////////////////////////////////////////////////////////////
		//////////////////////////// TEST FOR ADMIN FACADE
		////////////////////////////////////////////////////////////////////////////////
		AdminFacade compFacade = new AdminFacade();
		AdminFacade custFacade = new AdminFacade();
		
		 CompanyDBDAO cdb = new CompanyDBDAO();
		 CouponDBDAO coupdb = new CouponDBDAO();
		 CompanyFacade cf = null;
		 Company comp = cdb.getCompany(1);
		 Coupon coupon = coupdb.getCoupon(14);
		
		 // Coupon coupon = new Coupon();
		 // coupon.setId(1311);
		 // coupon.setTitle("GEmVER");
		 // coupon.setStartDate(Date.valueOf(LocalDate.now()));
		 // coupon.setEndDate(Date.valueOf(LocalDate.now()));
		 // coupon.setAmount(11111);
		 // coupon.setType(CouponType.CAMPING);
		 // coupon.setMessage("hello message from coupon");
		 // coupon.setPrice(2341.23);
		 // coupon.setImage("myImage");
		 cf = new CompanyFacade(comp);
		 cf.createCoupon(coupon);
		
		 // coupdb.CompanyJoinCoupon(comp, coupon);
		

		System.out.println("Test for Company is running...");

//		Company comp = compFacade.getCompany(1);
//
//		System.out.println(comp.getCoupons());
//
//		Collection<Coupon> list = comp.getCoupons();
//		list.add(coupon);

//		System.out.println(list);

		// createCompany - OK =================================================
		// Company company = new Company();
		/// company.setId(2323); not relevant, because the id is autoincrement
		/// in db
		// company.setCompName("444sdfblabla");
		// company.setPassword("paassswor");
		// company.setEmail("myemail@myemail.com");
		// compFacade.createCompany(company);

		// removeCompany - OK =================================================
		// Company company = null;
		// company = compFacade.getCompany(111);
		// compFacade.removeCompany(company);

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

		// getAllCompanies - OK ===============================================
		Collection<Company> companies = compFacade.getAllCompanies();
//		System.out.println(companies);

		System.out.println("\nTest for company is OK.");

		System.out.println("\n-------------------------------------------------------------------------------------\n");

		System.out.println("Test for Customer is running...\n");

		// createCustomer - OK ================================================
		// Customer customer = new Customer();
		// customer.setCustName("Maor");
		// customer.setPassword("111112qwe");
		// try {
		// custFacade.createCustomer(customer);
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		// removeCustomer - OK ================================================
		// Customer customer = null;
		// try {
		// customer=custFacade.getCustomer(5);
		// custFacade.removeCustomer(customer);
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		// updateCustomer - OK ================================================
		// // in this method i attempt to use with Scanner writer - look the
		// // applying in Facade method
		// Customer customer = custFacade.getCustomer(4);
		// customer.setCustName("Bibi Netanyau");
		// customer.setCustName("Oren Hofman");
		// customer.setPassword("12344321");
		// custFacade.updateCustomer(customer);

		// getCustomer(Customer) - OK =========================================
		// Customer customer = null;
		// customer = custFacade.getCustomer(4);
		// System.out.println(customer);

		// getAllCustomers - OK ===============================================
		// Collection<Customer> customers = custFacade.getAllCustomers();
		// System.out.println(customers);

		System.out.println("\nTest for Customer is OK.");
	}
}