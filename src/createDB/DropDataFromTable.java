package createDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropDataFromTable {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1527/db5";
		String sqlCompany = "DROP TABLE COMPANY ";
		String sqlCustomer = "DROP TABLE CUSTOMER";
		String sqlCoupon = "DROP TABLE COUPON";
		String sqlCustomerCoupon = "DROP TABLE CustomerCoupon";
		String sqlCompanyCoupon = "DROP TABLE CompanyCoupon";
		try (Connection con = DriverManager.getConnection(url); Statement stmt = con.createStatement();) {
				// Drop tables for maintenance purpose
				stmt.executeUpdate(sqlCompany);
				System.out.println("Company table has been dropped successfully!");
				stmt.executeUpdate(sqlCustomer);
				System.out.println("Customer table has been dropped successfully!");
				stmt.executeUpdate(sqlCoupon);
				System.out.println("Coupon table has been dropped successfully!");
				stmt.executeUpdate(sqlCustomerCoupon);
				System.out.println("CustomerCoupon table has been dropped successfully!");
				stmt.executeUpdate(sqlCompanyCoupon);
				System.out.println("CompanyCoupon table has been dropped successfully!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

