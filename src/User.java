import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private Date checkinDate;
	private Date checkoutDate;
	private String givenName;
	private String surname;
	private String email;
	private Property book;
	private int numberOfGuests;
	private int dates;
	
	public User(Date checkinDate, Date checkoutDate, Property book) {
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.book = book;
		dates = (int)((checkoutDate.getTime() - checkinDate.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public double getPrice() {
		return dates * book.getPrice();
	}
	
	public double getDiscountedPrice() {
		if(dates >= 7) return getPrice() * (100 - book.getWeeklyDiscount()) / 100.0;
		return getPrice();
	}
	
	public double getServiceFee() {
		return dates * book.getServiceFee();
	}
	
	public double getCleaningFee() {
		return book.getCleaningFee();
	}
	
	public double getTotal() {
		return this.getDiscountedPrice() + this.getServiceFee() + this.getCleaningFee();
	}
	
	public String getCheckinDate() {
		// format to date object
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // Convert the date into a
        // string using format() method
        String dateToString = df.format(checkinDate);
		return dateToString;
	}
	
	public String getCheckoutDate() {
		// format to date object
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // Convert the date into a
        // string using format() method
        String dateToString = df.format(checkinDate);
		return dateToString;
	}
	
	public String getName() {
		return givenName + " " + surname;
	}
	public String getStay() {
		return book.getProperty();
	}
	public String getEmail() {
		return email;
	}
	public int getNumberOfGuests() {
		return numberOfGuests;
	}
	public int getDates() {
		return dates;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	public double getDiscount() {
		if(dates >= 7) return book.getPrice() * (100 - book.getWeeklyDiscount()) / 100.0;
		return book.getPrice();
	}
}
