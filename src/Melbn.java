import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Melbn{
	private ArrayList<Property> melbnList;
	private HashMap<String, ArrayList<Property>> locationList;
	private HashMap<String, ArrayList<Property>> typeOfPlaceList;
	private TreeMap<Double, ArrayList<Property>> ratingList;
	private Scanner input;
	private ArrayList<Property> matchingList;
	private Property userChoice; //store the user's chosen information 
	private ReadFromFile csv;
	
	//find the matching list by string
	public ArrayList<Property> findMatchingList(int listName, String Information){
		ArrayList<Property> matchinglist = new ArrayList<Property>();
		for(Property p: matchinglist) {
			if()
		}
		
		return matchinglist;
	}
	
	//find the matching list by double
	public ArrayList<Property> findMatchingList(double Information){
		
		return null;
	}
	
	//display all the matching data
	public void matchingDataDisplay(ArrayList<Property> matchingList, int menuName) {
		int i = 0;
		switch(menuName) {
			case 1: //locationMenu
				for(i = 0; i < matchingList.size(); i++) {
					System.out.println("\t" + (i + 1) + ") " + matchingList.get(i).getLocation());
				}
				break;
			case 2: //typeOfPlaceMenu
				for(i = 0; i < matchingList.size(); i++) {
					System.out.println("\t" + (i + 1) + ") " + matchingList.get(i).getTypeOfPlace());
				}
				break;
			case 3: //ratingMenu
				for(i = 0; i < matchingList.size(); i++) {
					System.out.println("\t" + (i + 1) + ") " + matchingList.get(i).getProperty());
				}
				break;		
		}
		System.out.println("\t" + (i + 1) + ") Go to main menu");
	}
	
	//display the matching menu
	public int matchingMenuDisplay(int menuName) throws MelbnException {
		String titleDisplay = "";
		String userInput = "";
		while(true) {
			switch(menuName) {
				case 1: //locationMenu
					System.out.print("Please provide a location: ");
					titleDisplay = "> Select from matching list";
					break;
				case 2: //typeOfPlaceMenu
					titleDisplay = "> Browse by type of place";
					break;
				case 3: //ratingMenu
					System.out.print("Please provide the minimum rating: ");
					titleDisplay = "> Select from matching list";
					break;
				default:
					throw new MelbnException("You need to input a number in the range from 1 to 4. Please select again.");
			}
			try {
				userInput = input.nextLine();
				switch(menuName) {
					case 1: //locationMenu
						matchingList = findMatchingList(1, userInput);
						break;
					case 2: //typeOfPlaceMenu
						matchingList = findMatchingList(2, userInput);
						break;
					case 3: //ratingMenu
						matchingList = findMatchingList(Double.parseDouble(userInput));
						break;
				}
				
				System.out.println("-------------------------------------------------------------------------------\n"
						+ titleDisplay + "\n"
						+ "-------------------------------------------------------------------------------");
				
				//display all the matching data
				matchingDataDisplay(matchingList, menuName);
				
				//get the user's further choice
				System.out.print("Please select: ");
				String fChoice = input.nextLine();
				if(fChoice == "") throw new MelbnException("You need to input something. Please select again.");
				int choice = Integer.parseInt(fChoice);
				if(choice < 1 || choice > matchingList.size()) throw new MelbnException("You need to input a number in the range of the displayed list. Please select again.");
				
				if(choice == matchingList.size()) {
					return 0;
				}
				//get all information of user's choice
				userChoice = matchingList.get(choice);
				break;
			}catch(NumberFormatException ex) {
				System.err.println("You need to input a digit of number. Please select again.");
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}//while
		return -1;
	}
	
	//start the system
	public void begin() throws Exception{
		System.out.println("Welcome to Melbnb!");
		//	0 - main menu; 
		//	1 - search by location
		//	2 - search by type of place
		//	3 - search by rating
		menuDisplay(0); // display the main menu
		
		
//		exit();
	}
	
	//display the menu
	//	0 - main menu; 
	//	1 - search by location
	//	2 - search by type of place
	//	3 - search by rating
	//  4 - exit
	// -1 - complete the selection and ask for user's personal information
	public void menuDisplay(int menuName) throws Exception{
		boolean flag = true;
		while(flag) {
			try {
				switch(menuName) {
					case 0:
						menuName = mainMenuDisplay();
						break;
					case 1:
						menuName = matchingMenuDisplay(1);
						flag = false;
						break;
					case 2:
						menuName = matchingMenuDisplay(2);
						flag = false;
						break;
					case 3:
						menuName = matchingMenuDisplay(3);
						flag = false;
						break;
					case 4: 
						System.exit(0);
						break;
					case -1:
						
						break;
					default:
						throw new MelbnException("You need to input a number in the range from 1 to 4. Please select again.");
				}
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	//exit the system
	public void exit(){
		System.out.println("-------------------------------------------------------------------------------\n"
				+ "> Congratulations! Your trip is booked. A receipt has been sent to your email. \n"
				+ "  Details of your trip are shown below.\n"
				+ "  Your host will contact you before your trip. Enjoy your stay!\n"
				+ "--------------------------------------------------------------------------------");
		input.close();
		System.exit(0);
	}

	//initial all the properties and data structure
	public Melbn() throws FileNotFoundException, IOException{
		//initial the data structure
		melbnList = new ArrayList<Property>();
		locationList = new HashMap<>();
		typeOfPlaceList = new HashMap<>();
		ratingList = new TreeMap<>();
		matchingList = new ArrayList<>();
		//initial the user input stream
		input = new Scanner(System.in);
		userChoice = new Property();
		csv = new ReadFromFile("Melbnb.csv");
	}

	//display the main menu
	public int mainMenuDisplay() throws Exception{
		String mainChoice = "";
		int mainMenuChoice = -1;
		while(true) {
			try {
				System.out.println("-------------------------------------------------------------------------------\n"
						+ "> Select from main menu\n"
						+ "-------------------------------------------------------------------------------");
				System.out.println("\t1) Search by location");
				System.out.println("\t2) Browse by type of place");
				System.out.println("\t3) Filter by rating");
				System.out.println("\t4) Exit");
				System.out.print("Please select: ");
				mainChoice = input.nextLine();
				//Justify the input is not null
				if(mainChoice == "") throw new MelbnException("You need to input something. Please select again.");
				//Justify the input is whether in the range
				mainMenuChoice = Integer.parseInt(mainChoice);
				if(mainMenuChoice < 1 || mainMenuChoice > 4) throw new MelbnException("You need to input a number in the range from 1 to 4. Please select again.");
				break;
			}catch(NumberFormatException ex) {
				System.err.println("You need to input a digit of number. Please select again.");
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}
		return mainMenuChoice;
	}

	public ArrayList<Property> getMelbnList() {
		return melbnList;
	}

	public void addMelbnList(Property item) {
		melbnList.add(item);
	}

	public HashMap<String, ArrayList<Property>> getLocationList() {
		return locationList;
	}

	public boolean addLocationList(String location, Property item) {
		if(locationList.containsKey(location) && locationList.get(location).contains(item)) return false;
		if(!locationList.containsKey(location)) locationList.put(location, new ArrayList<Property>());
		locationList.get(location).add(item);
		return true;
	}

	public HashMap<String, ArrayList<Property>> getTypeOfPlaceList() {
		return typeOfPlaceList;
	}

	//add data to the location matching list
	public boolean addTypeOfPlaceList(String typeOfPlace, Property item) {
		if(typeOfPlaceList.containsKey(typeOfPlace) && typeOfPlaceList.get(typeOfPlace).contains(item)) return false;
		if(!typeOfPlaceList.containsKey(typeOfPlace)) typeOfPlaceList.put(typeOfPlace, new ArrayList<Property>());
		typeOfPlaceList.get(typeOfPlace).add(item);
		return true;
	}

	//add data to the type of place matching list
	public TreeMap<Double, ArrayList<Property>> getRatingList() {
		return ratingList;
	}
 
	//add data to the rating matching list
	public boolean addRatingList(double rating, Property item) {
		if(ratingList.containsKey(rating) && ratingList.get(rating).contains(item)) return false;
		if(!ratingList.containsKey(rating)) ratingList.put(rating, new ArrayList<Property>());
		ratingList.get(rating).add(item);
		return true;
	}
}
