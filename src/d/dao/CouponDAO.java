package d.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import e.Exeptions.CouponSystemsException;
import beans.dao.Company;
import beans.dao.Coupon;
import beans.dao.CouponType;

public interface CouponDAO {
	public void createCoupon(Coupon coupon) throws CouponSystemsException;

	public void removeCoupon(Coupon coupon) throws CouponSystemsException;

	public void updateCoupon(Coupon coupon) throws CouponSystemsException;

	public Coupon getCoupon(long id) throws CouponSystemsException;

	public ArrayList<Coupon> getAllCoupons() throws CouponSystemsException, SQLException; 

	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemsException, SQLException;

	public void delete(Company company) throws CouponSystemsException;

	public void delete(Coupon coupon) throws CouponSystemsException, SQLException;

	public boolean titleExist(Coupon coupon) throws CouponSystemsException, SQLException;

	public void create(Coupon coupon) throws CouponSystemsException, SQLException  ;

	public Coupon read(Coupon coupon) throws CouponSystemsException, SQLException;

	public void update(Coupon coupon) throws CouponSystemsException, SQLException;

	public ArrayList<Coupon> getCouponByType(CouponType couponType);

	Collection<Coupon> getCouponByType();
	
	
	

}
