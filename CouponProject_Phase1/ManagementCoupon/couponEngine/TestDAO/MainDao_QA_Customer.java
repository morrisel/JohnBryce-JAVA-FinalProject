package TestDAO;

import java.sql.SQLException;

import beans.CouponSystemException;
import beans.Customer;
import dao.db.CustomerDBDAO;

public class MainDao_QA_Customer {

	public static void main(String[] args) throws CouponSystemException, SQLException {

		////////////////////////////////////////////////////////////////////////////////
		//////////////////////////// TEST FOR CUSTOMER
		////////////////////////////////////////////////////////////////////////////////

		System.out.println("Tests for Customer is running...");

		// createCustomer - OK ============================================
//		 CustomerDBDAO dao = new CustomerDBDAO();
//		 Customer customer = new Customer();
//		 customer.setId(1);
//		 customer.setCustName("Your name");
//		 customer.setPassword("paaass");
//		 dao.createCustomer(customer);

		// DBTablePrinter.printResultSet(dao.printCustomers());
		//// DBTablePrinter.printResultSet(dao.printCoupons());

		// removeCustomer - OK ============================================
		// Customer customer = dao.getCustomer(3);
		// dao.removeCustomer(customer);

		// updateCustomer - OK ============================================
		// Customer customer = dao.getCustomer(2);
		// customer.setCustName("My_NEW_Name");
		// customer.setPassword("FBI_password");
		// dao.updateCustomer(customer);

		// getCustomer - OK ===============================================
		// System.out.println(dao.getCustomer(2));

		// getAllCustomers - OK ===========================================
		// System.out.println(dao.getAllCustomers());

		// getAllCustomers - OK ===========================================
		// System.out.println(dao.getAllCustomers());

		System.out.println("\nTest Customer is OK.");

	}
}