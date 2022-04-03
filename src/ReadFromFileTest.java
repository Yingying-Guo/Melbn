import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadFromFileTest {

	private ReadFromFile file;

	@Before
	public void setUp() throws Exception {
		file = new ReadFromFile("Melbnb.csv");
	}

	@After
	public void tearDown() throws Exception {
		file.finishLoading();
	}
	
	@Test(expected=IOException.class)
	public void testAddingInvalidFileAddress() throws IOException {
		file = new ReadFromFile("Melbnb1.csv");
	}
	
	@Test
	public void testAddingValidFileAddress() throws IOException {
		file = new ReadFromFile("Melbnb.csv");
	}
}
