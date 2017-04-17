package beans.dao;

public class Customer {
	private long id;
	private String custName;
	private String password;
	// private Collection<Coupon> coupons;
	public String email;
	


	public long getId() {
		return id;
	}

	public Customer() {

	}

	public Customer(String name, String password, String email) {
		this.custName = name;
		this.password = password;
		this.email = email;
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
	// public Collection<Coupon> getCoupons() {
	// return coupons;
	// }
	// public void setCoupons(Collection<Coupon> coupons) {
	// this.coupons = coupons;
	// }

	public void setEmail(String email) {
		this.email = email;

	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
	}

}
