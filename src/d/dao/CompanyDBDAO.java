package d.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import beans.dao.Company;
import beans.dao.Coupon;
import e.Exeptions.CouponSystemsException;
import p.ConnectionPool.ConnectionPool;

//implementation of CompanyDAO which uses ConnectionPool to work with Apache derby DB
public class CompanyDBDAO implements CompanyDAO {

	private CouponDAO couponDAO = new CouponDBDAO();
	// private CustomerDAO customerDAO = new CustomerDBDAO();

	@Override
	public void createCompany(Company company) throws CouponSystemsException, Throwable {

		Connection con = null;
		try {
			// get con from pool
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO Company (comp_name , password,email) values (?,?,?)");

			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("Failed to save the company, please check input", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public Company read(Company company) throws CouponSystemsException, SQLException {

		Connection con = null;
		try {
			// get con from pool

			String query = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			if (company.getEmail() == null) {
				query = "select id,comp_name,password,email from Company where ID = ?";
				con = ConnectionPool.getInstance().getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setLong(1, company.getId());
				System.out.println(company.getId());
				rs = pstmt.executeQuery();
			} else {
				query = "select id,comp_name,password,email from Company where email = ?";
				con = ConnectionPool.getInstance().getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, company.getEmail());
				System.out.println(company.getEmail());
				rs = pstmt.executeQuery();
			}

			while (rs.next()) {
				company.setId(rs.getLong("ID"));
				company.setCompName(rs.getString("COMP_NAME"));
				company.setPassword(rs.getString("PASSWORD"));
				company.setEmail(rs.getString("EMAIL"));

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("read failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return company;
	}

	@Override
	public void update(Company company) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			// Updates

			String query = "update Company set comp_name= ?, password = ? ,email = ? WHERE id = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.setLong(4, company.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("update company failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public void delete(Company company) throws CouponSystemsException, SQLException {

		Connection con = null;
		try {
			// get con from pool

			String query = "DELETE FROM Company WHERE email like ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, company.getEmail());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("delete failed", e);
		} finally {
		}

	}

	@Override
	public ArrayList<Company> getAllCompanies() throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		ArrayList<Company> companies = new ArrayList<>();
		try {
			// get con from pool

			String query = "select id,comp_name,password,email from Company";
			con = ConnectionPool.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Company company = new Company();

				company.setId(rs.getLong("ID"));
				company.setCompName(rs.getString("COMP_NAME"));
				company.setPassword(rs.getString("PASSWORD"));
				company.setEmail(rs.getString("EMAIL"));
				if (company.getEmail() != null) {
					companies.add(company);
				}

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("getAllCompanies failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return companies;
	}

	@Override
	public ArrayList<Coupon> getCouponsByCompany(Company company) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		ArrayList<Coupon> coupons = new ArrayList<>();
		try {
			// get coupons by company

			String query = "select COUPON_ID from Company_Coupon where Company_ID = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setLong(1, company.getId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Coupon coupon = new Coupon();

				coupon.setId(rs.getLong("COUPON_ID"));

				if (coupon.getId() != 0) {
					coupons.add(couponDAO.read(coupon));
				}

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("getCouponsByCompany failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return coupons;
	}

	@Override
	public void linkCompanyCoupon(Company company, Coupon coupon) throws CouponSystemsException, SQLException {

		Connection con = null;
		try {
			// get con from pool
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO Company_Coupon (Company_ID , COUPON_ID) values (?,?)");

			company = read(company);
			coupon = couponDAO.read(coupon);

			pstmt.setLong(1, company.getId());
			pstmt.setLong(2, coupon.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException(
					"linking Company to Coupon failed, check if company has this coupon already", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public void unLinkCompanyCoupon(Company company, Coupon coupon) throws CouponSystemsException, SQLException {

		Connection con = null;
		try {
			// get con from pool

			String query = "DELETE FROM Company_Coupon WHERE Company_ID = ? and COUPON_ID = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setLong(1, company.getId());
			pstmt.setLong(2, coupon.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("unLinkCompanyCoupon failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public boolean nameExist(Company company) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		int numOfNames = 0;

		try {
			// get con from pool

			String query = "select count (comp_name) AS numOfNames from Company where comp_name = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, company.getCompName());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				numOfNames = rs.getInt("numOfNames");

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("nameExist comp failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		if (numOfNames > 0) {
			return true;
		}

		return false;
	}

	@Override
	public void removeCompany(long companyId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Company getCompany(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Coupon> getCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String compName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeCompanyCoupon(Company company, Coupon coupon) {
		// TODO Auto-generated method stub

	}

	@Override
	public Company getCompanyById(long id) throws CouponSystemsException, SQLException {
		Connection con = null;
		Company company = new Company();
		try {
			// get con from pool

			String query = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			query = "select id,comp_name,password,email from Company where ID = ?";
			con = ConnectionPool.getInstance().getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				company.setId(rs.getLong("ID"));
				company.setCompName(rs.getString("COMP_NAME"));
				company.setPassword(rs.getString("PASSWORD"));
				company.setEmail(rs.getString("EMAIL"));
			}

		} catch (SQLException e) {
			throw new CouponSystemsException("read failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return company;
	}

	@Override
	public Company getCompanyByEmail(String email) throws CouponSystemsException, SQLException {
		Connection con = null;
		Company company = new Company();
		try {
			// get con from pool

			String query = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			query = "select id,comp_name,password,email from Company where email = ?";
			con = ConnectionPool.getInstance().getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				company.setId(rs.getLong("ID"));
				company.setCompName(rs.getString("COMP_NAME"));
				company.setPassword(rs.getString("PASSWORD"));
				company.setEmail(rs.getString("EMAIL"));
			}

		} catch (SQLException e) {
			throw new CouponSystemsException("read failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return company;
	}

	@Override
	public void updateCompany(Company company) throws CouponSystemsException {
		Connection con = null;
		try {
			// Updates

			String query = "update Company set comp_name= ?, password = ? ,email = ? WHERE id = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.setLong(4, company.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("update company failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public void removeCompany(Company company2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Company getCompanyById(Company company2) {
		// TODO Auto-generated method stub
		return null;
	}

}
