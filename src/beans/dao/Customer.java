package beans.dao;

import java.util.Collection;

public class Customer {
	private long id;
	private String custName;
	private String password;
	private Collection<Coupon> coupons;
	public String getEmail;
	public Customer() {
	}
	public Customer(String name, String passwwor, String email) {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
	}
	public void setEmail(String email) { 
		// TODO Auto-generated method stub
		
	}
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
