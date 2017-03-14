package createDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.dao.Company;
import p.ConnectionPool.*;

//	import classes.Person;

public class TestInsert {

	public static void main(String[] args) {

		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();

			// get connection from pool
			Connection con = connectionPool.getConnection();
			String sql = "insert into company(COMP_NAME, password, email) values(?,?,?)";

			List<Company> companies = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				Company company = new Company();
				company.setCompName("comp" + i);
				company.setEmail("email" + i);
				company.setPassword("pass" + i);
				companies.add(company);
			}

			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				for (Company c : companies) {
					// set the pstmt ? with actual arguments
					pstmt.setString(1, c.getCompName());
					pstmt.setString(2, c.getEmail());
					pstmt.setString(3, c.getPassword());
					// execute the sql
					pstmt.executeUpdate();
					System.out.println(c + " inserted to database");
				}

			} finally {
				connectionPool.returnToPool(con);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionPool.closeAllConnections();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
