package TestDAO;

import java.sql.SQLException;

import beans.Company;
import beans.CouponSystemException;
import dao.db.CompanyDBDAO;

public class MainDao_QA_Company {

	public static void main(String[] args) throws CouponSystemException, SQLException {

		////////////////////////////////////////////////////////////////////////////////
		//////////////////////////// TEST FOR COMPANY
		////////////////////////////////////////////////////////////////////////////////

		System.out.println("Test for company is running...");

		// createCompany - OK =================================================
		 CompanyDBDAO companyDbDao = new CompanyDBDAO();
		 Company company = new Company();
		 company.setId(1);
		 company.setCompName("company name");
		 company.setPassword("my password");
		 company.setEmail("company@email.com");
		 companyDbDao.createCompany(company);
		// DBTablePrinter.printResultSet(companyDbDao.printCompanies());

		// removeCompany - OK =================================================
		// CompanyDBDAO companyDbDao = new CompanyDBDAO();
		// Company company = new Company();
		// company.setId(16);
		// companyDbDao.removeCompany(company);
		// DBTablePrinter.printResultSet(companyDbDao.printCompanies());

		// updateCompany - OK =================================================
		// CompanyDBDAO companyDbDao = new CompanyDBDAO();
		// Company company = new Company();
		// company.setId(15);
		// company.setCompName("updated_name");
		// company.setPassword("updated_password");
		// company.setEmail("updated@email.com");
		// companyDbDao.updateCompany(company);
		// DBTablePrinter.printResultSet(companyDbDao.printCompanies());

		// getCompany - OK ====================================================
//		CompanyDBDAO companyDbDao = new CompanyDBDAO();
//		Company company = new Company();
		//company = companyDbDao.getCompany(15);
		//System.out.println(company);

		// getAllCompanies - OK ===============================================
		// CompanyDBDAO companyDbDao = new CompanyDBDAO();
		// Collection<Company> companies = new ArrayList<>();
		// companies = companyDbDao.getAllCompanies();
		// System.out.println(companies);
		// DBTablePrinter.printResultSet(companyDbDao.printCompanies());

		System.out.println("\nTest for company is OK.");
	}
}