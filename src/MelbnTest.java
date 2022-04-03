import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MelbnTest {

	private Melbn melbn;
	
	@Before
	public void setUp() throws Exception {
		melbn = new Melbn();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAccessFile() throws FileNotFoundException, IOException {
		melbn = new Melbn();
	}
	
	@Test(expected=MelbnException.class)
	public void testNullInputInYesOrNoInput() throws MelbnException{
		melbn.isValidInput(1, "");
	}
	@Test(expected=MelbnException.class)
	public void testInvlidInputInYesOrNoInput() throws MelbnException{
		melbn.isValidInput(1, "a");
	}
	@Test
	public void testVlidInputInYesOrNoInput() throws MelbnException{
		melbn.isValidInput(1, "n");
	}
	
	@Test(expected=MelbnException.class)
	public void testNullInputInStringInput() throws MelbnException{
		melbn.isValidInput(2, "");
	}
	@Test
	public void testVlidInputInStringInput() throws MelbnException{
		melbn.isValidInput(2, "Yingying");
	}
	
	@Test(expected=MelbnException.class)
	public void testNullInputInEmailInput() throws MelbnException{
		melbn.isValidInput(3, "");
	}
	@Test(expected=MelbnException.class)
	public void testInvlidInputInEmailInput() throws MelbnException{
		melbn.isValidInput(3, "fdasdfasdf");
	}
	@Test
	public void testVlidInputInEmailInput() throws MelbnException{
		melbn.isValidInput(3, "s3860867@student.rmit.edu.au");
	}
	
	@Test(expected=MelbnException.class)
	public void testNullInputInNumOfGuestsInput() throws MelbnException{
		melbn.isValidInput(5, "");
	}
}
