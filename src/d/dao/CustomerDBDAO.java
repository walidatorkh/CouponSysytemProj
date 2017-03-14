package d.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import beans.dao.Coupon;
import beans.dao.Customer;
import e.Exeptions.CouponSystemsException;
import p.ConnectionPool.ConnectionPool;

//implementation of link CustomerDAO which using ConnectionPool to work with derby DB
public class CustomerDBDAO implements CustomerDAO {
	private CouponDAO couponDAO = new CouponDBDAO();

	@Override
	public void createCustomer(Customer customer) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			// get con from pool
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO customer (cust_name , password, email) values (?,?,?)");

			pstmt.setString(1, customer.getCustName());
			pstmt.setString(2, customer.getPassword());
			pstmt.setString(3, (String) customer.getEmail());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("create customer failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public Customer read(Customer customer) throws CouponSystemsException, SQLException {
		Connection con = null;
		try {
			// get con from pool
			PreparedStatement pstmt = null;

			String query = null;
			ResultSet rs = null;
			if (customer.getEmail() == null) {

				query = "select id,cust_name,password,email from CUSTOMER where ID = ?";
				con = ConnectionPool.getInstance().getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setLong(1, customer.getId());
				rs = pstmt.executeQuery();
			} else {
				query = "select id,cust_name,password,email from customer where email = ?";
				con = ConnectionPool.getInstance().getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, customer.getEmail());
				rs = pstmt.executeQuery();
			}

			while (rs.next()) {
				customer.setId(rs.getLong("ID"));
				customer.setCustName(rs.getString("CUST_NAME"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setEmail(rs.getString("EMAIL"));

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("read customer failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			// Updates

			String query = "update customer set cust_name= ?, password = ?  WHERE email = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, customer.getCustName());
			pstmt.setString(2, customer.getPassword());
			pstmt.setString(3, (String) customer.getEmail());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("Update customer failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public void removeCustomer(Customer customer) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			// get con from pool

			String query = "DELETE FROM customer WHERE email = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, (String) customer.getEmail());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("delete customer failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			// get con from pool

			String query = "select id,cust_name,password,email from customer";
			con = ConnectionPool.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Customer customer = new Customer();

				customer.setId(rs.getLong("ID"));
				customer.setCustName(rs.getString("CUST_NAME"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setEmail(rs.getString("EMAIL"));
				if (customer.getEmail() != null) {
					customers.add(customer);
				}

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("getAllCompanies failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return customers;
	}

	@Override
	public ArrayList<Coupon> getCouponsByCustomer(Customer customer) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		ArrayList<Coupon> coupons = new ArrayList<>();
		try {
			// get coupons by customer

			customer = read(customer);

			String query = "select COUPON_ID from Customer_Coupon where Cust_ID = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setLong(1, customer.getId());
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
	public void linkCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			customer = read(customer);
			coupon = couponDAO.read(coupon);

			// get con from pool
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO Customer_Coupon (CUST_ID , COUPON_ID) values (?,?)");

			pstmt.setLong(1, customer.getId());
			pstmt.setLong(2, coupon.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("linkCustomerCoupon failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public void unLinkCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			// get con from pool

			String query = "DELETE FROM Customer_Coupon WHERE CUSTOMER_ID = ? and COUPON_ID = ? ";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setLong(1, customer.getId());
			pstmt.setLong(2, coupon.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("unLinkCustomerCoupon failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}
	}

	@Override
	public Boolean nameExist(Customer customer) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		int numOfNames = 0;

		try {
			// get con from pool

			String query = "select count (cust_name) AS numOfNames from CUSTOMER where cust_name = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, customer.getCustName());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				numOfNames = rs.getInt("NUMOFNAMES");

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("nameExist cust failed", e);
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
	public void unLinkCouponFromAllCustomers(Coupon coupon) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			// get con from pool

			String query = "DELETE FROM Customer_Coupon WHERE  COUPON_ID = ? ";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setLong(1, coupon.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("unLinkCouponFromAllCustomers failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}
	}

	@Override
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Coupon> getCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String custName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeCustomerCoupon(Customer customer, Coupon coupon) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Coupon> getCoupnsByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

}
