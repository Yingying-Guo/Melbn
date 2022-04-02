import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFromFile {
	private ObjectInputStream input;
	
	public ReadFromFile(String fileName) throws FileNotFoundException, IOException {
		input = new ObjectInputStream(new FileInputStream(fileName));
	}
	
	//read the data from csv file and load into the data structure
	public boolean uploadData(Melbn store) {
		return locationLoad(store) && typeOfPlaceLoad(store) && ratingLoad(store);
	}
	
	//load data of the location matching list
	public boolean locationLoad(Melbn store) {
		return false;
	}
	
	//load data of the type of place matching list
	public boolean typeOfPlaceLoad(Melbn store) {
		return false;
	}
	
	//load data of the rating matching list
	public boolean ratingLoad(Melbn store) {
		return false;
	}
	
	public void finishLoading() throws IOException {
		input.close();
	}
}
