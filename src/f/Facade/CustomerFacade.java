package f.Facade;

import d.dao.CustomerDBDAO;
import d.dao.CouponDBDAO;
import e.Exeptions.CouponSystemsException;

import java.util.ArrayList;
import java.util.Collection;

import beans.dao.Coupon;
import beans.dao.CouponType;
import beans.dao.Customer;
import d.dao.CouponDAO;
import d.dao.CustomerDAO;
public class CustomerFacade implements ClientFacade {
	private static CustomerDAO customerDAO = new CustomerDBDAO();
	private static CouponDAO couponDAO = new CouponDBDAO();
	private Customer customer;
	
	public CustomerFacade() {
		
	}
	
		private CustomerFacade(Customer customer) throws CouponSystemsException, Throwable {
			
				this.customer = customerDAO.read(customer);
			

		}
		



		public void purchaseCoupon(Coupon coupon) throws CouponSystemsException, Throwable {
			
				customer = customerDAO.read(customer);
				coupon = couponDAO.read(coupon);
				ArrayList<Coupon> customersCoupons = customerDAO.getCouponsByCustomer(customer);
				Boolean hasCoupon = false;

				if (!customersCoupons.isEmpty()) {
					for (Coupon customerCoupon : customersCoupons) {
						if (customerCoupon.getTitle().equals(coupon.getTitle())) {
							hasCoupon = true;
						}
					}
				}

				if (coupon.isActive() && hasCoupon == false && coupon.leftCoupon()) {
					customerDAO.linkCustomerCoupon(customer, coupon);
					coupon.setAmount(coupon.getAmount() - 1);
					couponDAO.update(coupon);
				} else {

					throw new CouponSystemsException("Coupon is not available for you");

				}
			
		}



		public Collection<Coupon> getAllPurchasedCoupons() throws CouponSystemsException, Throwable {
			ArrayList<Coupon> allPurchasedCoupons = new ArrayList<>();
			
				allPurchasedCoupons = customerDAO.getCouponsByCustomer(customer);
			

			return allPurchasedCoupons;

		}



		public Collection<Coupon> getAllPurchasedCouponsByType(CouponType couponType) throws CouponSystemsException, Throwable {
			ArrayList<Coupon> couponsByType = new ArrayList<>();
			ArrayList<Coupon> coupons = new ArrayList<>();

			
				coupons = customerDAO.getCouponsByCustomer(customer);
			

			for (Coupon coupon : coupons) {
				if (couponType.equals(coupon.getType())) {
					couponsByType.add(coupon);
				}
			}

			return couponsByType;
		}


		
		public Collection<Coupon> getAllPurchasedCouponsByPrice(Double price) throws CouponSystemsException, Throwable {
			ArrayList<Coupon> couponsByPrice = new ArrayList<>();
			ArrayList<Coupon> coupons = new ArrayList<>();

			
				coupons = customerDAO.getCouponsByCustomer(customer);
			

			for (Coupon coupon : coupons) {
				if (coupon.getPrice() == price) {
					couponsByPrice.add(coupon);
				}
			}

			return couponsByPrice;
		}

		
		public CustomerFacade login(String email, String password) throws Throwable {
			Customer customer = new Customer();
			customer.setEmail(email);
			CustomerFacade customerFacade = new CustomerFacade(customer);
			
			
				customer = customerDAO.read(customer);
			
			if (customer.getId() != 0 && password.equals(customer.getPassword())) {
				return customerFacade;
			}else {
				throw new CouponSystemsException("incorrect customer Login");
			}
			
		

	}


}
