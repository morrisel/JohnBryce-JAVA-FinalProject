package mainTest;

import beans.Company;
import beans.CouponSystemException;
import beans.Customer;
import couponSystemSingelton.CouponSystem;
import facade.AdminFacade;

public class Main_QA {

	public static void main(String[] args) {

		CouponSystem cs = CouponSystem.getInstace();
		try {
			AdminFacade af = (AdminFacade) cs.login("admin", "1234", "admin");
			Company company1 = new Company();
			Company company2 = new Company();
			Customer customer1 = new Customer();
			Customer customer2 = new Customer();

			company1.setCompName("Zol");
			company1.setPassword("aaaa");
			company1.setEmail("a@a.com");
			company2.setCompName("Yakar");
			company2.setPassword("bbbb");
			company2.setEmail("b@b.com");

			 af.createCompany(company1);
			 af.createCompany(company2);

			af.removeCompany(company1);
			af.removeCompany(company2);

			// ================================================================
			customer1.setCustName("avi");
			customer1.setPassword("1234");
			customer2.setCustName("benny");
			customer2.setPassword("2345");

			// try {
			// af.createCustomer(customer1);
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// try {
			// af.createCustomer(customer2);
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			// try {
			// af.removeCustomer(customer1);
			// af.removeCustomer(customer2);
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			
			
			// ================================================================
			//
			// // Change Unique EMAIL in DB - MAOR
			//
			// company1.setId(3);
			// company1.setCompName("ZOL");
			// company1.setPassword("AAAA");
			// company1.setEmail("A@A.COM");
			//
			// company2.setId(4);
			// company2.setCompName("YAKAR");
			// company2.setPassword("BBBB");
			// company2.setEmail("B@B.COM");
			//
			// af.updateCompany(company1);
			// af.updateCompany(company2);
			//
			// //
			// ================================================================
			// customer1.setId(7);
			// customer1.setCustName("AVI");
			// customer1.setPassword("1234");
			//
			// customer2.setId(8);
			// customer2.setCustName("BENNY");
			// customer2.setPassword("2345");
			//
			// af.updateCustomer(customer1);
			// af.updateCustomer(customer2);
			// //
			// ================================================================
			//
			// company1.setId(3);
			// company2.setId(4);
			// System.out.println(af.getCompany(company1.getId()));
			// System.out.println(af.getCompany(company2.getId()));
			//
			// System.out.println(af.getAllCompanies());
			//
			// //
			// ================================================================
			// customer1.setId(7);
			// customer2.setId(8);
			// System.out.println(af.getCustomer(customer1.getId()));
			// System.out.println(af.getCustomer(customer2.getId()));
			//
			// System.out.println(af.getAllCustomers());
			//
			// //
			// ================================================================
			//
			// af.removeCompany(company1);
			// af.removeCompany(company2);
			//
			// af.removeCustomer(customer1);
			// af.removeCustomer(customer2);

			// ================================================================

		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
}
