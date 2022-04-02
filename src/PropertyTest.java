import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PropertyTest {

	private Property property;
	
	@Before
	public void setUp() throws Exception {
		property = new Property("Private room in the heart of Southbank", "Southbank", 
				"The apartment is situated in the heart of Southbank with an easy access to shops and cafes. It has a warm and spacious living room with an amazing view of the gardens.", 
				"Private room", "Shelley", 
				5, 4.2, 52, 12.0, 18, 10);
	}

	@Test
	public void testGetProperty(){
		assertEquals("property expected is not the same as the returned property", "Private room in the heart of Southbank", property.getProperty());
	}
	
	@Test
	public void testGetLocation(){
		assertEquals("location expected is not the same as the returned location", "Southbank", property.getLocation());
	}
	
	@Test
	public void testGetDescription(){
		assertEquals("description expected is not the same as the returned description", "The apartment is situated in the heart of Southbank with an easy access to shops and cafes. It has a warm and spacious living room with an amazing view of the gardens.", property.getDescription());
	}
	
	@Test
	public void testGetTypeOfPlace(){
		assertEquals("typeOfPlace expected is not the same as the returned typeOfPlace", "Private room", property.getTypeOfPlace());
	}
	
	@Test
	public void testGetHost(){
		assertEquals("host expected is not the same as the returned host", "Shelley", property.getHost());
	}
	
	@Test
	public void testMaximumOfGuests(){
		assertEquals("maximumOfGuests expected is not the same as the returned maximumOfGuests", 5, property.getMaximumOfGuests());
	}
	
	@Test
	public void testGetRating(){
//		Junit中没有assertEquals(double,double)的方法。因为double值是允许误差的。
//		所以要实现double的断言要用assertEquals(double,double,double)这个方法。
//		其中第三个参数是允许误差 。
		assertEquals("rating expected is not the same as the returned rating", 4.2, property.getRating(), 0);
	}
	
	@Test
	public void testGetPrice(){
		assertEquals("price expected is not the same as the returned price", 52, property.getPrice());
	}
	
	@Test
	public void testGetServiceFee(){
		assertEquals("serviceFee expected is not the same as the returned serviceFee", 12.0, property.getServiceFee(), 0);
	}
	
	@Test
	public void testGetCleaningFee(){
		assertEquals("cleaningFee expected is not the same as the returned cleaningFee", 18, property.getCleaningFee());
	}
	
	@Test
	public void testGetWeeklyDiscount(){
		assertEquals("weeklyDiscount expected is not the same as the returned weeklyDiscount", 10, property.getWeeklyDiscount());
	}
}
