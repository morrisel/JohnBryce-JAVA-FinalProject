package dbcreate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

	public static void main(String[] args) {

		String driverName = "org.apache.derby.jdbc.ClientDriver";

		// Creating all tables for database DDL for TABLES
		try {
			Class.forName(driverName);

			System.out.println("driver loaded");

			String url = "jdbc:derby://localhost:1527/CouponDB;create=true";
			// String url = "jdbc:derby://localhost:1527/CouponDB";

			try (Connection con = DriverManager.getConnection(url);) {

				// get a connection to a specific database (inside ARM)
				System.out.println("connected to: " + con);
				Statement stmt = con.createStatement();

				// create a Statement for all tables
				String couponTable = "CREATE TABLE Coupon"
						+ "("
						+ "COUPON_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
						+ "TITLE VARCHAR(30),"
						+ "START_DATE DATE,"
						+ "END_DATE DATE,"
						+ "AMOUNT INTEGER,"
						+ "TYPE VARCHAR(20),"
						+ "MESSAGE VARCHAR(300),"
						+ "PRICE DOUBLE,"
						+ "IMAGE VARCHAR(500),"
						+ "CONSTRAINT PRIME_COUPON_ID PRIMARY KEY(COUPON_ID)"
						+ ")";
				stmt.executeUpdate(couponTable);
				System.out.println("success: " + couponTable);

				String companyTable = "CREATE TABLE Company"
						+ "("
						+ "COMPANY_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
						+ "COMPANY_NAME VARCHAR(20),"
						+ "COMPANY_PASSWORD VARCHAR(16),"
						+ "EMAIL VARCHAR(30),"
						+ "CONSTRAINT PRIME_COMPANY_ID PRIMARY KEY (COMPANY_ID)"
						+ ")";

				stmt.executeUpdate(companyTable);
				System.out.println("success: " + companyTable);

				String customerTable = "CREATE TABLE Customer"
						+ "("
						+ "CUSTOMER_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
						+ "CUSTOMER_NAME VARCHAR(25),"
						+ "CUSTOMER_PASSWORD VARCHAR(16),"
						+ "CONSTRAINT PRIME_CUSTOMER_ID PRIMARY KEY (CUSTOMER_ID)"
						+ ")";
				stmt.executeUpdate(customerTable);
				System.out.println("success: " + customerTable);

				//////////////////////////////////////////////////////////////////////////////////////////////////
				// JOIN TABLES
				// + "COMPANY_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				String company_CouponTable = "CREATE TABLE Company_Coupon"
						+ "("
						+ "COMPANY_ID BIGINT NOT NULL,"
						+ "COUPON_ID INTEGER,"
						+ "CONSTRAINT PRIME_COMPANY_COUPON_ID PRIMARY KEY (COMPANY_ID, COUPON_ID)"
						+ ")";
				
				stmt.executeUpdate(company_CouponTable);
				System.out.println("success: " + company_CouponTable);

				// + "CUSTOMER_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				String customer_Coupon = "CREATE TABLE Customer_Coupon"
						+ "("
						+ "CUSTOMER_ID BIGINT NOT NULL,"
						+ "COUPON_ID INTEGER,"
						+ "CONSTRAINT PRIME_CUSTOMER_COUPON_ID PRIMARY KEY (CUSTOMER_ID, COUPON_ID)"
						+ ")";
				stmt.executeUpdate(customer_Coupon);
				System.out.println("success: " + customer_Coupon);

				System.out.println("The database CouponDB it created");

			}

			System.out.println("connection closed");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
