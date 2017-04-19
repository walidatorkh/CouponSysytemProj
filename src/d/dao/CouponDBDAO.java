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
import beans.dao.CouponType;
import e.Exeptions.CouponSystemsException;
import p.ConnectionPool.ConnectionPool;

public class CouponDBDAO implements CouponDAO {

	@Override
	public void create(Coupon coupon) throws CouponSystemsException, SQLException {

		Connection con = null;
		try {
			// get con from pool
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO Coupon (TITLE,STARTDATE, ENDDATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) values (?,?,?,?,?,?,?,?)");

			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, coupon.getStartDate());
			pstmt.setDate(3, coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().toString());
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("create Coupon failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public Coupon read(Coupon coupon) throws CouponSystemsException, SQLException {
		
		Connection con = null;
		try {
			// get con from pool
			con = ConnectionPool.getInstance().getConnection();
			String query = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			if (coupon.getTitle()==null) {
				query = "select ID,TITLE,STARTDATE, ENDDATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from COUPON where ID=?";
				//con = ConnectionPool.getInstance().getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setLong(1, coupon.getId());
				rs = pstmt.executeQuery();
			} else {
				query = "select ID,TITLE,STARTDATE, ENDDATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from COUPON where TITLE=?";
				//con = ConnectionPool.getInstance().getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, coupon.getTitle());
				rs = pstmt.executeQuery();
			}
			
			

			while (rs.next()) {
				coupon.setId(rs.getLong("ID"));
				coupon.setTitle(rs.getString("TITLE"));
				coupon.setStartDate(rs.getDate("STARTDATE"));
				coupon.setEndDate(rs.getDate("ENDDATE"));
				coupon.setAmount(rs.getInt("AMOUNT"));
				coupon.setType(CouponType.valueOf(rs.getString("TYPE")));
				coupon.setMessage(rs.getString("MESSAGE"));
				coupon.setPrice(rs.getDouble("PRICE"));
				coupon.setImage(rs.getString("IMAGE"));

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("read failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return coupon;
	}

	@Override
	public void update(Coupon coupon) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			// Updates
			
			
			String query = " update coupon set STARTDATE = ?, ENDDATE = ?, AMOUNT = ?, TYPE = ?, MESSAGE = ?, PRICE = ?, IMAGE = ? where TITLE = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			
			pstmt.setDate(1, coupon.getStartDate());
			pstmt.setDate(2, coupon.getEndDate());
			pstmt.setInt(3, coupon.getAmount());
			pstmt.setString(4, coupon.getType().toString());
			pstmt.setString(5, coupon.getMessage());
			pstmt.setDouble(6, coupon.getPrice());
			pstmt.setString(7, coupon.getImage());
			pstmt.setString(8, coupon.getTitle());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("update coupon failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public void delete(Coupon coupon) throws CouponSystemsException, SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			// get con from pool

			String query = "DELETE FROM coupon WHERE id = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setLong(1, coupon.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemsException("delete failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

	}

	@Override
	public ArrayList<Coupon> getAllCoupons() throws CouponSystemsException, SQLException {
		
		Connection con = null;
		ArrayList<Coupon> coupons = new ArrayList<>();
		try {
			// get con from pool

			String query = "select ID, TITLE,STARTDATE, ENDDATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from COUPON";
			con = ConnectionPool.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getLong("ID"));
				coupon.setTitle(rs.getString("TITLE"));
				coupon.setStartDate(rs.getDate("STARTDATE"));
				coupon.setEndDate(rs.getDate("ENDDATE"));
				coupon.setAmount(rs.getInt("AMOUNT"));
				coupon.setType(CouponType.valueOf(rs.getString("TYPE")));
				coupon.setMessage(rs.getString("MESSAGE"));
				coupon.setPrice(rs.getDouble("PRICE"));
				coupon.setImage(rs.getString("IMAGE"));
				if (coupon.getTitle()!=null) {
					coupons.add(coupon);
				}
				

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("getAllCoupons failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemsException, SQLException {
		

		Connection con = null;
		ArrayList<Coupon> coupons = new ArrayList<>();
		try {
			// get coupons by company

			String query = "select ID from COUPON where Type=?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, couponType.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Coupon coupon = new Coupon();

				coupon.setId(rs.getLong("ID"));
				if (coupon.getId()!=0) {
					coupons.add(this.read(coupon));
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
	public boolean titleExist(Coupon coupon) throws CouponSystemsException, SQLException {
	
		Connection con = null;
		int numOfTitles = 0;
		
		try {
			// get con from pool
			
			String query = "select count (TITLE) AS NUMOFTITLES from COUPON where TITLE = ?";
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, coupon.getTitle());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				numOfTitles = rs.getInt("NUMOFTITLES");

			}

		} catch (SQLException e) {
			throw new CouponSystemsException("titleExist coupon failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().returnToPool(con);
			}
		}
		
		if (numOfTitles>0) {
			return true;
		}

		return false;
	}

	@Override
	public void createCoupon(Coupon coupon) throws CouponSystemsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCoupon(Coupon coupon) throws CouponSystemsException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Coupon getCoupon(long id) throws CouponSystemsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company company) throws CouponSystemsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Coupon> getCouponByType(CouponType couponType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Coupon> getCouponByType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemsException {
		// TODO Auto-generated method stub
		
	}

	
}
