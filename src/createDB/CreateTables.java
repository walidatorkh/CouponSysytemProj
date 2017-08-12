package createDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

	public static void main(String[] args) {

		// String url =
		// "jdbc:derby://localhost:1527/igordb;user=igork;password=igork;create=true";
		String url = "jdbc:derby://localhost:1527/sample;create=true;user=user;password=123";
		String sqlCompany = "Create table Company(id bigint primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), COMP_NAME varchar(25) unique, password varchar(10), email varchar(30))";
		String sqlCustomer = "Create table Customer(id bigint primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), CUST_NAME varchar(25) unique, password varchar(10),email varchar(30))";
		String sqlCoupon = "Create table Coupon(id bigint primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),TITLE varchar(25), startdate date, enddate date, type varchar(25), AMOUNT int, MESSAGE varchar(25), PRICE double, IMAGE varchar(25))";
		String sqlCustomerCoupon = "Create table Customer_Coupon(CUST_ID bigint, COUPON_ID bigint, PRIMARY KEY (CUST_ID, COUPON_ID))";
		String sqlCompanyCoupon = "Create table Company_Coupon(COMPANY_ID bigint, COUPON_ID bigint, PRIMARY KEY (COMPANY_ID, COUPON_ID))";

		try {
			Connection con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sqlCompany);
			stmt.executeUpdate(sqlCustomer);
			stmt.executeUpdate(sqlCoupon);
			stmt.executeUpdate(sqlCustomerCoupon);
			stmt.executeUpdate(sqlCompanyCoupon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Tables: Company created");
		System.out.println("Tables: Customer created");
		System.out.println("Tables: Coupon created");
		System.out.println("Tables: Customer_Coupon created");
		System.out.println("Tables: Company_Coupon created");

	}

}
