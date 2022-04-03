import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;
	private Property property;
	
	@Before
	public void setUp() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		property = new Property("Private room in the heart of Southbank", "Southbank", 
				"The apartment is situated in the heart of Southbank with an easy access to shops and cafes. It has a warm and spacious living room with an amazing view of the gardens.", 
				"Private room", "Shelley", 
				5, 4.2, 52, 12.0, 18, 10);
		user = new User(format.parse("2022-04-22"), format.parse("2022-04-30"), property);
	}
	
	@Test
	public void testGetPrice() {
		assertEquals("price expected is not the same as the returned price", 416.0, user.getPrice(), 0);
	}
	
	@Test
	public void testGetDiscountedPrice() {
		assertEquals("discountedPrice expected is not the same as the returned discountedPrice", 374.4, user.getDiscountedPrice(), 0);
	}
	
	@Test
	public void testGetDiscount() {
		assertEquals("discount expected is not the same as the returned discount", 46.8, user.getDiscount(), 0);
	}
	
	@Test
	public void testGetServiceFee() {
		assertEquals("serviceFee expected is not the same as the returned serviceFee", 96.0, user.getServiceFee(), 0);
	}
	
	@Test
	public void testGetCleaningFee() {
		assertEquals("cleaningFee expected is not the same as the returned cleaningFee", 18.0, user.getCleaningFee(), 0);
	}
	
	@Test
	public void testGetTotal() {
		assertEquals("total expected is not the same as the returned total", 488.4, user.getTotal(), 0);
	}
	
	@Test
	public void testGetCheckinDate() {
		assertEquals("checkinDate expected is not the same as the returned checkinDate", "2022-04-22", user.getCheckinDate());
	}
	
	@Test
	public void testGetCheckoutDate() {
		assertEquals("checkoutDate expected is not the same as the returned checkinDate", "2022-04-30", user.getCheckoutDate());
	}
	
	@Test
	public void testGetStay() {
		assertEquals("stay expected is not the same as the returned stay", "Private room in the heart of Southbank hosted by Shelley", user.getStay());
	}
	
	@Test
	public void testGetDates() {
		assertEquals("dates expected is not the same as the returned dates", 8, user.getDates());
	}
	
	@Test
    public void testSetGivenName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User testUser = new User();

        //when
        testUser.setGivenName("Yingying");

        //then
        final Field field = testUser.getClass().getDeclaredField("givenName");
        field.setAccessible(true);
        assertEquals("givenName didn't match", field.get(testUser), "Yingying");
    }
	
	@Test
    public void testSetSurname() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User testUser = new User();

        //when
        testUser.setSurname("Guo");

        //then
        final Field field = testUser.getClass().getDeclaredField("surname");
        field.setAccessible(true);
        assertEquals("surname didn't match", field.get(testUser), "Guo");
    }
	
	@Test
    public void testSetEmail() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User testUser = new User();

        //when
        testUser.setEmail("s3860867@student.rmit.edu.au");

        //then
        final Field field = testUser.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals("email didn't match", field.get(testUser), "s3860867@student.rmit.edu.au");
    }
	
	@Test
    public void testSetNumberOfGuests() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User testUser = new User();

        //when
        testUser.setNumberOfGuests(5);

        //then
        final Field field = testUser.getClass().getDeclaredField("numberOfGuests");
        field.setAccessible(true);
        assertEquals("numberOfGuests didn't match", field.get(testUser), 5);
    }
	
	@Test
	public void testGetName() {
		user.setGivenName("Yingying");
		user.setSurname("Guo");
		assertEquals("name expected is not the same as the returned name", "Yingying Guo", user.getName());
	}
	
	@Test
	public void testGetEmail() {
		user.setEmail("s3860867@student.rmit.edu.au");
		assertEquals("email expected is not the same as the returned email", "s3860867@student.rmit.edu.au", user.getEmail());
	}
	
	@Test
	public void testGetNumberOfGuests() {
		user.setNumberOfGuests(2);
		assertEquals("numberOfGuests expected is not the same as the returned numberOfGuests", 2, user.getNumberOfGuests());
	}
	
	
}
