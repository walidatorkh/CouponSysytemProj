package beans.dao;

import java.util.Collection;

/**
 * id bigint primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
 * COMP_NAME varchar(25) unique, 
 * password varchar(10), 
 * email varchar(30)
 * @author igorh
 *
 */
public class Company {
	private long id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons;
	
	
	public Company() {
	}

	public Company(String name, String password, String email) {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + "]";
	}


	
	
	
}
