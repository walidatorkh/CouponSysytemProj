package beans.dao;

import java.util.Date;

public class Coupon {
	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;
	
	
	public Coupon() {		
	}


	public Coupon(String title, java.sql.Date past, java.sql.Date future, int amount, CouponType type, String message,
			double price, String image) {
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public java.sql.Date getStartDate() {
		return (java.sql.Date) startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public java.sql.Date getEndDate() {
		return (java.sql.Date) endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public CouponType getType() {
		return type;
	}


	public void setType(CouponType type) {
		this.type = type;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}


	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean leftCoupon() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
