import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
	private File csv = null;
	private BufferedReader textFile = null;
	
	public ReadFromFile(String fileName) throws FileNotFoundException, IOException {
		try {
			csv = new File(fileName);
			textFile = new BufferedReader(new FileReader(csv));
		}catch(FileNotFoundException e) {
			System.err.println("Can not find the file.");
		}catch(IOException e) {
			System.err.println("Wrong of file reading.");
		}
	}
	
	//read the data from csv file and load into the data structure
	public boolean uploadData(Melbn store) throws IOException {
		String lineData = "";
		String[] data = null;
		int i = 0;
		try {
			while((lineData = textFile.readLine()) != null) {
				if(i == 0) continue;
				
				data = lineData.split(",");
				int maximumOfGuests = Integer.parseInt(data[5]);
				double rating = Double.parseDouble(data[6]);
				int price = Integer.parseInt(data[7]);
				double serviceFee = Double.parseDouble(data[8]);
				int cleaningFee = Integer.parseInt(data[9]);
				int weeklyDiscount = Integer.parseInt(data[10]);
				Property temp = new Property(
					data[0], data[1], data[2], data[3], data[4], 
					maximumOfGuests, rating, price, serviceFee, cleaningFee, weeklyDiscount
					);
				if(!store.addLocationList(data[1], temp) || !store.addTypeOfPlaceList(data[3], temp) || !store.addRatingList(rating, temp)) return false;
				i++;
			 }
			if(i <= 11) return true;
		}catch(IOException e) {
			System.err.println("Wrong of file reading.");
		}
		return false;
	}
	public void finishLoading() throws IOException {
		try {
			textFile.close();
		}catch(IOException e) {
			System.err.println("Wrong of file reading.");
		}
	}
}
