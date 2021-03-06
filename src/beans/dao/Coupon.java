package beans.dao;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
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

	public Coupon(String title, java.sql.Date startDate, java.sql.Date endDate, int amount, CouponType type,
			String message, double price, String image) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;

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

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public java.sql.Date getStartDate() {
		return (java.sql.Date) startDate;
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
		long ts = System.currentTimeMillis();
		Date cd = new Date(ts);
		if (cd.after(startDate) && cd.before(endDate)) {
			return true;
		}
		return false;
	}

	public Boolean isExpired() {
		long ts = System.currentTimeMillis();
		Date cd = new Date(ts);
		if (cd.after(endDate)) {
			return true;
		}
		return false;
	}

	public boolean leftCoupon() {
		if (amount > 0) {
			return true;
		}
		return false;
	}

}
