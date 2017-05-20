package f.Facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import beans.dao.Company;
import beans.dao.Coupon;
import beans.dao.Customer;
import d.dao.CompanyDAO;
import d.dao.CompanyDBDAO;
import d.dao.CouponDAO;
import d.dao.CouponDBDAO;
import d.dao.CustomerDAO;
import d.dao.CustomerDBDAO;
import e.Exeptions.CouponSystemsException;

public class AdminFacade implements ClientFacade {
	private CompanyDAO compDAO = new CompanyDBDAO();
	private CouponDAO couponDAO = new CouponDBDAO();
	private CustomerDAO customerDAO = new CustomerDBDAO();
	private final String ADMIN = "Admin";

	// Create company
	public void createCompany(Company company) throws Throwable {
		if (!compDAO.nameExist(company)) {
			compDAO.createCompany(company);
		} else {
			throw new CouponSystemsException("Name exist allready");
		}
	}

	// remove company
	public void removeCompany(long companyId) throws CouponSystemsException, SQLException {
		Company company = compDAO.getCompanyById(companyId);
		ArrayList<Coupon> allThisCompanyCoupons;

		allThisCompanyCoupons = compDAO.getCouponsByCompany(company);
		for (Coupon coupon : allThisCompanyCoupons) {
			ArrayList<Customer> allCustomers = (ArrayList<Customer>) customerDAO.getAllCustomers();
			for (Customer customer : allCustomers) {
				customerDAO.removeCustomerCoupon(customer, coupon);
			}
			compDAO.removeCompanyCoupon(company, coupon);
			couponDAO.delete(company);
		}
		compDAO.removeCompany(companyId);
	}

	public void updateCompany(Company company) throws CouponSystemsException {
		compDAO.updateCompany(company);

	}

	public Company getCompany(long companyId) throws CouponSystemsException, Throwable {
		Company company = compDAO.getCompanyById(companyId);
		if (company.getId() != 0) {
			return company;
		} else {
			return null;
		}

	}

	public Collection<Company> getAllCompanies() throws CouponSystemsException, Throwable {
		ArrayList<Company> allCompanies = new ArrayList<>();
		allCompanies = (ArrayList<Company>) compDAO.getAllCompanies();
		return allCompanies;
	}

	// creating customer
	public void createCustomer(Customer customer) throws CouponSystemsException, Throwable {
		if (!customerDAO.nameExist(customer)) {
			customerDAO.createCustomer(customer);
		} else {
			throw new CouponSystemsException("Customer name already exist!");
		}

	}

	public void removeCustomer(Customer customer) throws CouponSystemsException, Throwable {
		customer = customerDAO.read(customer);
		ArrayList<Coupon> allCoupons = customerDAO.getCoupnsByCustomer(customer);
		for (Coupon coupon : allCoupons) {
			customerDAO.removeCustomerCoupon(customer, coupon);
			couponDAO.delete(coupon);
		}
		customerDAO.removeCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws CouponSystemsException, Throwable {
		customerDAO.updateCustomer(customer);
	}

	// get customer by .... email or ..
	public Customer getCustomer(Customer customer) throws CouponSystemsException, Throwable {
		customer = customerDAO.read(customer);
		return customer;
	}

	public Collection<Customer> getAllCustomers() throws CouponSystemsException {
		System.out.println("intogetAllCustomer");
		ArrayList<Customer> allCustomers = new ArrayList<>();
		System.out.println("ithul Array of getAllCustomer");
		allCustomers = customerDAO.getAllCoupons();
		System.out.println("AllCustomer  " + allCustomers );
		System.out.println("+++++++++++++++++++++++++");
		return allCustomers;
	}

	public Collection<Coupon> getAllCoupon() throws CouponSystemsException {
		ArrayList<Coupon> allCoupons = new ArrayList<>();
		return allCoupons;
	}

	// gets coupons by company
	public Collection<Coupon> getCouponByCompany(Company company) throws CouponSystemsException, Throwable {
		ArrayList<Coupon> couponByCompany = new ArrayList<>();
		couponByCompany = compDAO.getCouponsByCompany(company);
		return couponByCompany;
	}

	public AdminFacade login(String email, String password) throws CouponSystemsException {
		if (email.equals(ADMIN) && password.equals(ADMIN)) {
			return new AdminFacade();
		} else {
			throw new CouponSystemsException("Admin can't login");
		}
	}

	public Company getCompany(Company company) throws CouponSystemsException, Throwable {
		company = compDAO.read(company);
		if (company.getId() != 0) {
			return company;
		} else {
			return null;
		}
	}

	public void removeCompany(Company company) throws CouponSystemsException, Throwable {
		company = compDAO.read(company);
		ArrayList<Coupon> allThisCompanyCoupons;

		allThisCompanyCoupons = compDAO.getCouponsByCompany(company);

		for (Coupon coupon : allThisCompanyCoupons) {
			ArrayList<Customer> allCustomers = customerDAO.getAllCustomers();
			for (Customer customer : allCustomers) {
				customerDAO.unLinkCustomerCoupon(customer, coupon);
			}
			compDAO.unLinkCompanyCoupon(company, coupon);
			couponDAO.delete(coupon);
		}
		compDAO.delete(company);

	}

}
