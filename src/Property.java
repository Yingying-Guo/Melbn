
public class Property {
	private String property;
	private String location;
	private String description;
	private String typeOfPlace;
	private String host;
	private int maximumOfGuests;
	private double rating;
	private int price;
	private double serviceFee;
	private int cleaningFee;
	private int weeklyDiscount;
	
	public Property(String property, String location, String description, String typeOfPlace, String host,
			int maximumOfGuests, double rating, int price, double serviceFee, int cleaningFee, int weeklyDiscount) {
		this.property = property;
		this.location = location;
		this.description = description;
		this.typeOfPlace = typeOfPlace;
		this.host = host;
		this.maximumOfGuests = maximumOfGuests;
		this.rating = rating;
		this.price = price;
		this.serviceFee = serviceFee;
		this.cleaningFee = cleaningFee;
		this.weeklyDiscount = weeklyDiscount;
	}
	public Property() {
		
	}
	public String getProperty() {
		return property;
	}
	public String getLocation() {
		return location;
	}
	public String getDescription() {
		return description;
	}
	public String getTypeOfPlace() {
		return typeOfPlace;
	}
	public String getHost() {
		return host;
	}
	public int getMaximumOfGuests() {
		return maximumOfGuests;
	}
	public double getRating() {
		return rating;
	}
	public int getPrice() {
		return price;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public int getCleaningFee() {
		return cleaningFee;
	}
	public int getWeeklyDiscount() {
		return weeklyDiscount;
	}
}
