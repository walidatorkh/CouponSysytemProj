package createDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoTables {

	public static void main(String[] args) {

		// Defining DB url
		String url = "jdbc:derby://localhost:1527/sample;create=true;user=user;password=123";
		String sqlInsertCompany = "INSERT INTO Company (COMP_NAME, password, email) VALUES(?,?,?)";
		String sqlInsertCustomer = "INSERT INTO Customer (CUST_NAME, password, email) VALUES(?,?,?)";
		String sqlInsertCoupon = "INSERT INTO Coupon (TITLE, STARTDATE, ENDDATE, AMOUNT, MESSAGE, PRICE, IMAGE) VALUES(?,?,?,?,?,?,?)";

		try (Connection con = DriverManager.getConnection(url);
				Statement stmt = con.createStatement();

				PreparedStatement pstmt = con.prepareStatement(sqlInsertCompany);
				PreparedStatement pstmt1 = con.prepareStatement(sqlInsertCustomer);
				PreparedStatement pstmt2 = con.prepareStatement(sqlInsertCoupon);) {

			pstmt.setString(1, "TestInsertCOMP_NAME"); // set the COMP_NAME
			pstmt.setString(2, "123"); // set the password
			pstmt.setString(3, "TestInsertCOMP_NAME@email.com"); // set the email
			pstmt.executeUpdate();
			
			pstmt.setString(1, "TestInsertCOMP_NAME2"); // set the COMP_NAME
			pstmt.setString(2, "234"); // set the password
			pstmt.setString(3, "TestInsertCOMP_NAME2@email.com"); // set the email
			pstmt.executeUpdate();

			java.sql.Timestamp startdate = new java.sql.Timestamp(new java.util.Date().getTime());
			java.sql.Timestamp enddate = new java.sql.Timestamp(new java.util.Date().getTime());

			pstmt1.setString(1, "TestInsertCustomer");
			pstmt1.setString(2, "1234");
			pstmt1.setString(3, "TestInsertCustomer@email.com"); // set the email
			pstmt1.executeUpdate();
			
			pstmt1.setString(1, "TestInsertCustomer2");
			pstmt1.setString(2, "2345");
			pstmt1.setString(3, "TestInsertCustomer2@email.com"); // set the email
			pstmt1.executeUpdate();
			//
			pstmt2.setString(1, "SPORT");
			pstmt2.setTimestamp(2, startdate);
			pstmt2.setTimestamp(3, enddate);
			pstmt2.setInt(4, 10);
			pstmt2.setString(5, "MESSAGE");
			pstmt2.setDouble(6, 11.22);
			pstmt2.setString(7, "Image@image.com");
			pstmt2.executeUpdate();

			pstmt2.setString(1, "ELECTRICITY");
			pstmt2.setTimestamp(2, startdate);
			pstmt2.setTimestamp(3, enddate);
			pstmt2.setInt(4, 10);
			pstmt2.setString(5, "MESSAGE");
			pstmt2.setDouble(6, 11.22);
			pstmt2.setString(7, "Image2.com");
			pstmt2.executeUpdate();

			System.out.println(sqlInsertCompany);
			System.out.println(sqlInsertCustomer);
			System.out.println(sqlInsertCoupon);

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}
}
