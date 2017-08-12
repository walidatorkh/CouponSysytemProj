package d.dao;

import beans.dao.Customer;
import e.Exeptions.CouponSystemsException;
import beans.dao.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface CustomerDAO {
	public void createCustomer(Customer customer) throws CouponSystemsException, SQLException;

	public void removeCustomer(Customer customer) throws CouponSystemsException, SQLException;

	public void updateCustomer(Customer customer) throws CouponSystemsException, SQLException;

	public Customer getCustomer(long id);

	public ArrayList<Customer> getAllCustomers() throws CouponSystemsException, SQLException;

	public Collection<Coupon> getCoupons();

	public boolean login(String custName, String password);

	public void removeCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemsException;

	public Boolean nameExist(Customer customer) throws CouponSystemsException, SQLException;

	public Customer read(Customer customer) throws CouponSystemsException, SQLException;

	public ArrayList<Coupon> getCoupnsByCustomer(Customer customer) throws CouponSystemsException;

	public ArrayList<Customer> getAllCoupons() throws CouponSystemsException;

	public void unLinkCouponFromAllCustomers(Coupon coupon) throws CouponSystemsException, SQLException;

	ArrayList<Coupon> getCouponsByCustomer(Customer customer) throws CouponSystemsException, SQLException;

	public void linkCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemsException, SQLException;

	void unLinkCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemsException, SQLException;
	
	

}
