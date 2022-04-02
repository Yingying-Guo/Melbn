import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Melbn{
	private ArrayList<Property> melbnList;
	private HashMap<String, ArrayList<Property>> locationList;
	private HashMap<String, ArrayList<Property>> typeOfPlaceList;
	private TreeMap<Double, ArrayList<Property>> ratingList;
	private Scanner input;
	private ArrayList<Property> matchingList;
	private Property userChoice; //store the user's chosen information 
	private ReadFromFile csv;
	private User user;
	
	//Show property details
	public int propertyDisplay() {
		String choice = "N";
		System.out.println("Property:\t\t" + userChoice.getProperty());
		System.out.println("Type of place:\t\t" + userChoice.getTypeOfPlace());
		System.out.println("Location:\t\t" + userChoice.getLocation());
		System.out.println("Rating:\t\t\t" + userChoice.getRating());
		System.out.println("Description:\t\t" + userChoice.getDescription());
		System.out.println("Number of guests:\t" + userChoice.getMaximumOfGuests());
		System.out.println("Price:\t\t\t$" + userChoice.getPrice() + "\t(" + ")");
		System.out.println("Discounted price:\t$" + userChoice.getPrice() + "\t(" + ")");
		System.out.println("Service fee:\t\t$" + userChoice.getPrice() + "\t(" + ")");
		System.out.println("Cleaning fee:\t\t" + userChoice.getPrice());
		System.out.println("Total:\t\t\t" + userChoice.getPrice());
		System.out.print("Would you like to reserve the property (Y/N)? ");
		choice = input.nextLine();
		if(choice.toUpperCase().equals("N")) return 0;
		return 1;
	}
	
	//Provide dates
	public void getDates() {
		
	}
	
	//Provide personal information
	public void getPersonalDetails() {
		
	}
	
	//display personal information
	public void personalDisplay() {
		
	}
	
	//start the system
	public void begin() throws Exception{
		//load the data from file
		if(csv.uploadData(this)) {
			
			System.out.println("Welcome to Melbnb!");
			//	0 - main menu; 
			//	1 - search by location
			//	2 - search by type of place
			//	3 - search by rating
			menuDisplay(0); // display the main menu
//			System.out.println("11111111111111111111111111111111111111");
//			Set<String> set = typeOfPlaceList.keySet();
//			for(String str: set) {
//				System.out.println("111111111111111111111" + str);
//				for(Property p: typeOfPlaceList.get(str)) {
//					System.out.println(p.getProperty());
//				}
//			}
			csv.finishLoading();
	//		exit();
		}
		System.err.println("System wrong.");
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
						if(menuName != 0 && menuName != -1) flag = false;
						break;
					case 2:
						menuName = matchingMenuDisplay(2);
						if(menuName != 0 && menuName != -1) flag = false;
						break;
					case 3:
						menuName = matchingMenuDisplay(3);
						if(menuName != 0 && menuName != -1) flag = false;
						break;
					case 4: 
						System.exit(0);
						break;
					case -1:
//						System.out.println(userChoice.getProperty());
						menuName = propertyDisplay();
						if(menuName != 0) flag = false;
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
		
		//display personal information
		personalDisplay();
		
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

	//add data to the melbn list 
	public boolean addMelbnList(Property item) {
		if(melbnList.contains(item)) return false;
		melbnList.add(item);
		return true;
	}

	//add data to the location matching list
	public boolean addLocationList(String location, Property item) {
		if(locationList.containsKey(location) && locationList.get(location).contains(item)) return false;
		if(!locationList.containsKey(location)) locationList.put(location, new ArrayList<Property>());
		locationList.get(location).add(item);
		return true;
	}

	//add data to the type of place matching list
	public boolean addTypeOfPlaceList(String typeOfPlace, Property item) {
		if(typeOfPlaceList.containsKey(typeOfPlace) && typeOfPlaceList.get(typeOfPlace).contains(item)) return false;
		if(!typeOfPlaceList.containsKey(typeOfPlace)) typeOfPlaceList.put(typeOfPlace, new ArrayList<Property>());
		typeOfPlaceList.get(typeOfPlace).add(item);
		return true;
	}

	//add data to the rating matching list
	public boolean addRatingList(double rating, Property item) {
		if(ratingList.containsKey(rating) && ratingList.get(rating).contains(item)) return false;
		if(!ratingList.containsKey(rating)) ratingList.put(rating, new ArrayList<Property>());
		ratingList.get(rating).add(item);
		return true;
	}


	//find the matching list by string
	public ArrayList<Property> findMatchingList(int listName, String information){
		ArrayList<Property> matchinglist = new ArrayList<>();
		switch(listName) {
			case 1: //search by location
				matchinglist = new ArrayList<>();
				Pattern pattern = Pattern.compile(information,Pattern.CASE_INSENSITIVE);
				Set<String> set = locationList.keySet();
				for (String str: set) {  
					Matcher matcher = pattern.matcher(str); 
					if(matcher.find()){
						for(Property p: locationList.get(str)) {
							matchinglist.add(p);
						}// for value
					}// if
				}// for key
				break;
			case 2: //search by type of place
				return typeOfPlaceList.get(information);
		}
		return matchinglist;
	}
	
	//find the matching list by double
	public ArrayList<Property> findMatchingList(double minimumRating){
		ArrayList<Property> matchinglist = new ArrayList<>();
		Set<Double> set = ratingList.keySet();
		for (double rating: set) {
			if(rating >= minimumRating) {
				for(Property p: ratingList.get(rating)) {
					matchinglist.add(p);
				}// for value
			}// if
		}// for key
		return matchinglist;
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
					System.out.println("\t" + (i + 1) + ") " + matchingList.get(i).getProperty());
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
		String typeChoice = "";
		matchingList = new ArrayList<>();
		boolean typeFlag = true;
		while(true) {
			switch(menuName) {
				case 1: //locationMenu
					System.out.print("Please provide a location: ");
					titleDisplay = "> Select from matching list";
					break;
				case 2: //typeOfPlaceMenu
					titleDisplay = "> Select from entire place list";
					if(typeFlag) {
						System.out.println("-------------------------------------------------------------------------------\n"
								+ "> Browse by type of place\n"
								+ "-------------------------------------------------------------------------------");
						int i = 0;
						for(String typeOfPlace: typeOfPlaceList.keySet()) {
							i++;
							System.out.println("\t" + i + ") " + typeOfPlace);
						}
						System.out.println("\t" + (i + 1) + ") Go to main menu");
						System.out.print("Please select: ");
					}
					break;
				case 3: //ratingMenu
					System.out.print("Please provide the minimum rating: ");
					titleDisplay = "> Select from matching list";
					break;
				default:
					throw new MelbnException("You need to input a number in the range from 1 to 4. Please select again.");
			}
			try {
				if(typeFlag) {
					userInput = input.nextLine();
					}
				
				switch(menuName) {
					case 1: //locationMenu
						matchingList = findMatchingList(1, userInput);
						break;
					case 2: //typeOfPlaceMenu
						if(Integer.parseInt(userInput) == typeOfPlaceList.keySet().size() + 1) return 0;
						int i = 0;
						for(String typeOfPlace: typeOfPlaceList.keySet()) {
							i++;
							if(i == Integer.parseInt(userInput)) {
								typeChoice = typeOfPlace;
								break;
							}
						}
						if(Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > typeOfPlaceList.keySet().size() + 1) throw new MelbnException("You need to input a number in the range of the displayed list. Please select again.");
						matchingList = findMatchingList(2, typeChoice);
//						System.out.println(matchingList.size());
						break;
					case 3: //ratingMenu
						matchingList = findMatchingList(Double.parseDouble(userInput));
						break;
				}
				
				if(matchingList.size() == 0) throw new MelbnException("Can not find the matching list in the system. Please input again.");
				
				System.out.println("-------------------------------------------------------------------------------\n"
						+ titleDisplay + "\n"
						+ "-------------------------------------------------------------------------------");
				typeFlag = false;
				
				//display all the matching data
				matchingDataDisplay(matchingList, menuName);
				
				//get the user's further choice
				System.out.print("Please select: ");
				String fChoice = input.nextLine();
				if(fChoice == "") throw new MelbnException("You need to input something. Please select again.");
				int choice = Integer.parseInt(fChoice);
				if(choice < 1 || choice > matchingList.size() + 1) throw new MelbnException("You need to input a number in the range of the displayed list. Please select again.");
				
				if(choice == matchingList.size() + 1) {
					return 0;
				}
				//get all information of user's choice
				userChoice = matchingList.get(choice - 1);
				break;
			}catch(NumberFormatException ex) {
				System.err.println("You need to input a digit of number. Please select again.");
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}//while
		return -1; //complete the selection
	}

}
