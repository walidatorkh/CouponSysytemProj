package d.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import beans.dao.Company;
import beans.dao.Coupon;
import e.Exeptions.CouponSystemsException;

public interface CompanyDAO {
	public void createCompany(Company company) throws CouponSystemsException, SQLException, Throwable;

	public void removeCompany(Company company2);

	public void updateCompany(Company company) throws CouponSystemsException;

	public Company getCompany(long id);

	Collection<Company> getAllCompanies() throws CouponSystemsException, SQLException;

	public Collection<Coupon> getCoupons();

	public boolean login(String compName, String password);

	public boolean nameExist(Company company) throws CouponSystemsException, SQLException;

	public ArrayList<Coupon> getCouponsByCompany(Company company) throws CouponSystemsException, SQLException;

	//public Company read(Company company) throws CouponSystemsException, SQLException;
	public Company getCompanyById(long id) throws CouponSystemsException, SQLException;
	public Company getCompanyByEmail(String email) throws CouponSystemsException, SQLException;

	public void removeCompanyCoupon(Company company, Coupon coupon);

	public void linkCompanyCoupon(long companyId, Coupon coupon) throws CouponSystemsException, SQLException;

	public void unLinkCompanyCoupon(Company company, Coupon coupon) throws CouponSystemsException, SQLException;

	void update(Company company) throws CouponSystemsException, SQLException;

	void delete(Company company) throws CouponSystemsException, SQLException;

	Company read(Company company) throws CouponSystemsException, SQLException;

	public Company getCompanyById(Company company2);

	public void removeCompany(long companyId);
	
	

}

