package f.Facade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import beans.dao.Company;
import beans.dao.Coupon;
import beans.dao.CouponType;
import d.dao.CompanyDAO;
import d.dao.CompanyDBDAO;
import d.dao.CouponDAO;
import d.dao.CouponDBDAO;
import d.dao.CustomerDAO;
import d.dao.CustomerDBDAO;
import e.Exeptions.CouponSystemsException;

public class CompanyFacade implements ClientFacade {
	private static CompanyDAO compDAO = new CompanyDBDAO();
	private static CustomerDAO customerDAO = new CustomerDBDAO();
	private static CouponDAO couponDAO = new CouponDBDAO();
	private Company company;

	public CompanyFacade() {

	}

	private CompanyFacade(Company company) throws CouponSystemsException, Throwable {
		this.company = compDAO.read(company);

	}

	public void createCoupon(Coupon coupon) throws CouponSystemsException, Throwable {
		if (!couponDAO.titleExist(coupon)) {
			CouponDAO.create(coupon);
			compDAO.linkCompanyCoupon(company, coupon);
		} else {
			throw new CouponSystemsException("This title exist");
		}

	}

	public void removeCoupon(Coupon coupon) throws CouponSystemsException, Throwable {
		coupon = couponDAO.read(coupon);
		company = compDAO.read(company);
		compDAO.unLinkCompanyCoupon(company, coupon);
		customerDAO.unLinkCouponFromAllCustomers(coupon);
		couponDAO.delete(coupon);
	}

	public void updateCoupon(Coupon coupon) throws CouponSystemsException, Throwable {
		couponDAO.update(coupon);

	}

	public Coupon getCoupon(Coupon coupon) throws CouponSystemsException, Throwable {
		coupon = couponDAO.read(coupon);
		return coupon;
	}

	public Collection<Coupon> getAllMyCompanyCoupons() throws CouponSystemsException, Throwable {
		ArrayList<Coupon> coupons = new ArrayList<>();
		coupons = compDAO.getCouponsByCompany(company);
		return coupons;
	}

	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemsException {
		ArrayList<Coupon> couponsByType = new ArrayList<>();
		couponsByType = couponDAO.getCouponByType(couponType);
		return couponsByType;
	}

	public Collection<Coupon> getCouponsByPrice(Double price) throws CouponSystemsException, Throwable {
		ArrayList<Coupon> allCoupons = new ArrayList<>();
		ArrayList<Coupon> allCouposByPrice = new ArrayList<>();

		allCoupons = couponDAO.getAllCoupons();
		for (Coupon coupon : allCoupons) {
			if (coupon.getPrice() < price) {
				allCouposByPrice.add(coupon);
			}
		}
		return allCouposByPrice;
	}

	public Collection<Coupon> getCouponsByDate(Date date) throws CouponSystemsException, Throwable {
		ArrayList<Coupon> allCoupons = new ArrayList<>();
		ArrayList<Coupon> AllCouponsByDate = new ArrayList<>();
		allCoupons = couponDAO.getAllCoupons();

		for (Coupon coupon : allCoupons) {
			if (coupon.getEndDate().after(date)) {
				AllCouponsByDate.add(coupon);
			}
		}
		return AllCouponsByDate;
	}

	public CompanyFacade login(String email, String password) throws CouponSystemsException, Throwable {
		Company company = new Company();
		company.setEmail(email);
		CompanyFacade companyFacade = new CompanyFacade(company);
		company = compDAO.read(company);
		if (company.getId() != 0 && password.equals(company.getPassword())) {
			return companyFacade;
		} else {
			throw new CouponSystemsException("Company login not correct");
		}

	}

}
