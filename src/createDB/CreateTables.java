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
		String sqlCompany = "CREATE TABLE COMPANY (id bigint primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), COMP_NAME varchar(25) unique, password varchar(10), email varchar(30))";
		String sqlCustomer = "CREATE TABLE CUSTOMER (id bigint primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), CUST_NAME varchar(25) unique, password varchar(10),email varchar(30))";
		String sqlCoupon = "CREATE TABLE COUPON (id bigint primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),TITLE varchar(25), startdate date, enddate date, type varchar(25), AMOUNT int, MESSAGE varchar(25), PRICE double, IMAGE varchar(25))";
		String sqlCustomerCoupon = "CREATE TABLE CustomerCoupon(CUST_ID bigint, COUPON_ID bigint, PRIMARY KEY (CUST_ID, COUPON_ID))";
		String sqlCompanyCoupon = "CREATE TABLE CompanyCoupon(COMPANY_ID bigint, COUPON_ID bigint, PRIMARY KEY (COMP_ID, COUPON_ID))";

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
		System.out.println("Tables: COMPANY created");
		System.out.println("Tables: CUSTOMER created");
		System.out.println("Tables: COUPON created");
		System.out.println("Tables: CUSTOMERCOUPON created");
		System.out.println("Tables: COMPANYCOUPON created");

	}

}
