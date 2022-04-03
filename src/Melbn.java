import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	private Date checkin;
	private Date checkout;
	
	//justify the input from user
	public int isValidInput(int type, String input) throws MelbnException{
		if(input == "") throw new MelbnException("You need to input something. Please select again.");
		switch(type) {
			case 1: // N/Y
				if(!input.toUpperCase().equals("N") && !input.toUpperCase().equals("Y")) throw new MelbnException("You need to input Y/N. Please select again.");
				if(input.toUpperCase().equals("N")) return 0;
				return 1;
			case 2:  // Sting
				break;
			case 3:  // email
				String pattern = "^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\\.[a-z]{2,}$";
				Pattern p  = Pattern.compile(pattern);
				Matcher m;
				m = p.matcher(input);
				if(!m.find()) throw new MelbnException("You need to input a valid email address. Please input again.");
				break;
		}
		return 0;
		
	}
	
	//Show property details
	public int propertyDisplay() throws MelbnException{
		String choice = "";
		System.out.println("-------------------------------------------------------------------------------\n"
				+ "> Show property details\n"
				+ "-------------------------------------------------------------------------------");
			
			System.out.println("Property:\t\t" + userChoice.getProperty());
			System.out.println("Type of place:\t\t" + userChoice.getTypeOfPlace());
			System.out.println("Location:\t\t" + userChoice.getLocation());
			System.out.println("Rating:\t\t\t" + userChoice.getRating());
			System.out.println("Description:\t\t" + userChoice.getDescription());
			System.out.println("Number of guests:\t" + userChoice.getMaximumOfGuests());
			System.out.println("Price:\t\t\t$" + String.format("%.2f", user.getPrice()) + "\t($" + String.format("%.2f", userChoice.getPrice() * 1.0) + " * " + user.getDates() + " nights)");
			System.out.println("Discounted price:\t$" + String.format("%.2f", user.getDiscountedPrice()) + "\t($" + String.format("%.2f", user.getDiscount()) + " * " + user.getDates() + " nights)");
			System.out.println("Service fee:\t\t$" + String.format("%.2f", user.getServiceFee()) + "\t($" + String.format("%.2f", userChoice.getServiceFee()) + " * " + user.getDates() + " nights)");
			System.out.println("Cleaning fee:\t\t$" + String.format("%.2f", user.getCleaningFee() * 1.0));
			System.out.println("Total:\t\t\t$" + String.format("%.2f", user.getTotal()));
		while(true) {
			try {
				System.out.print("Would you like to reserve the property (Y/N)? ");
				choice = input.nextLine();
				return isValidInput(1, choice);
			}catch(MelbnException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	//Provide dates
	public void getDates() {
		System.out.println("-------------------------------------------------------------------------------\n"
				+ "> Provide dates\n"
				+ "-------------------------------------------------------------------------------");
		String pattern = "^(?:(?!0000)[0-9]{4}(-)(?:(?:0?[1-9]|1[0-2])(-)(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])(-)(?:29|30)|(?:0?[13578]|1[02])(-)31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)(-)0?2(-)29)$";
		Pattern p  = Pattern.compile(pattern);
		Matcher m;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
        Date todayDate = calendar.getTime();
        todayDate.setSeconds(0);
        todayDate.setHours(0);
        todayDate.setMinutes(0);
        String checkinDate, checkoutDate;
        String[] date;
		while(true) {
			try {
				System.out.print("Please provide check-in date (dd/mm/yyyy): ");
				checkinDate = input.nextLine();
				if(checkinDate == "") throw new MelbnException("You need to input something. Please select again.");
				date = checkinDate.split("/");
				checkinDate = date[2] + "-" + date[1] + "-" + date[0];
				m = p.matcher(checkinDate);
				if(!m.find()) throw new MelbnException("You need to input a valid date. Please input again.");
				checkin = format.parse(checkinDate);
				if(checkin.getTime() < todayDate.getTime()) throw new MelbnException("You need to input a date equals to or behind today. Please input again.");
				break;
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}catch (ParseException e) {
		        e.printStackTrace();
		    }
		}
		while(true) {
			try {
				System.out.print("Please provide checkout date (dd/mm/yyyy): ");
				checkoutDate = input.nextLine();
				if(checkoutDate == "") throw new MelbnException("You need to input something. Please select again.");
				date = checkoutDate.split("/");
				checkoutDate = date[2] + "-" + date[1] + "-" + date[0];
				m = p.matcher(checkoutDate);
				if(!m.find()) throw new MelbnException("You need to input a valid date. Please input again.");
				checkout = format.parse(checkoutDate);
				if(checkout.getTime() <= checkin.getTime()) throw new MelbnException("You need to input a date behind checkin date. Please input again.");
				break;
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}catch (ParseException e) {
		        e.printStackTrace();
		    }
		}
		user = new User(checkin, checkout, userChoice);
	}
	
	//Provide personal information
	public int getPersonalDetails() {
		String givenName, surname, email, choice;
		int numberOfGuests;
		System.out.println("-------------------------------------------------------------------------------\n"
				+ "> Provide personal information\n"
				+ "-------------------------------------------------------------------------------");
		while(true) {
			try {
				System.out.print("Please provide your given name: ");
				givenName = input.nextLine();
				isValidInput(2, givenName);
				break;
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}
		while(true) {
			try {
				System.out.print("Please provide your surname: ");
				surname = input.nextLine();
				isValidInput(2, surname);
				break;
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}
		while(true) {
			try {
				System.out.print("Please provide your email address: ");
				email = input.nextLine();
				isValidInput(3, email);
				break;
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}
		while(true) {
			try {
				System.out.print("Please provide number of guests: ");
				String num = input.nextLine();
				if(num == "") throw new MelbnException("You need to input something. Please select again.");
				numberOfGuests = Integer.parseInt(num);
				if(numberOfGuests < 1) throw new MelbnException("You need to input a positive number. Please select again.");
				if(numberOfGuests > userChoice.getMaximumOfGuests()) throw new MelbnException("You need to input the number under the maximum. Please select again.");
				break;
			}catch(NumberFormatException ex) {
				System.err.println("You need to input a digit of number. Please select again.");	
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}
		while(true) {
			try {
				System.out.print("Confirm and pay (Y/N): ");
				choice = input.nextLine();
				if(isValidInput(1, choice) == 0) return 0;
				user.setGivenName(givenName);
				user.setSurname(surname);
				user.setEmail(email);
				user.setNumberOfGuests(numberOfGuests);
				break;
			}catch(MelbnException ex) {
				System.err.println(ex.getMessage());
			}
		}
		return 1;
	}
	
	//display personal information
	public void personalDisplay() {
		System.out.println("Name:\t\t\t" + user.getName());
		System.out.println("Email:\t\t\t" + user.getEmail());
		System.out.println("Your stay:\t\t" + user.getStay());
		System.out.println("Who's coming:\t\t" + user.getNumberOfGuests() + (user.getNumberOfGuests() > 1 ? " guests" : " guest"));
		System.out.println("Check-in date:\t\t" + user.getCheckinDate());
		System.out.println("Checkout date:\t\t" + user.getCheckoutDate());
		System.out.println("\nTotal payment:\t\t$" + String.format("%.2f", user.getTotal()));
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
			exit();
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
						getDates();
						if(propertyDisplay() == 0 || getPersonalDetails() == 0) {
							menuName = 0;
							continue;
						}
						flag = false;
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
		try {
			csv = new ReadFromFile("Melbnb.csv");
		}catch(FileNotFoundException e) {
			System.err.println("Can not find the file.");
		}catch(IOException e) {
			System.err.println("Wrong of file reading.");
		}
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
					isValidInput(2, userInput);
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
						if(Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > typeOfPlaceList.keySet().size() + 1) throw new MelbnException("You need to a number in the range of the displayed list. Please select again.");
						matchingList = findMatchingList(2, typeChoice);
//						System.out.println(matchingList.size());
						break;
					case 3: //ratingMenu
						double rating = Double.parseDouble(userInput);
						if(rating < 0) throw new MelbnException("You need to a positive number. Please select again.");
						matchingList = findMatchingList(rating);
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
