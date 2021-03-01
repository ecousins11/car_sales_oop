package login.privacy;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LoggedIn {

	private static Scanner scan;
	private static Scanner scan2;
	private static Scanner scan3;
	private static Scanner scan4;

	public static void main(String[] args) throws ParseException, IOException {
        //actionSell();
	}
    
	//once logged in as admin they can add a user, perform staff functionalities or log out
	public static void adminBegin(String input) throws ParseException, IOException  {
		scan = new Scanner(System.in);
		String newAction = "R";
		while (newAction.equals("R")) {	
			if (input.equals("8")) {
					actionAddUser();
				}else {
					newAction = staffBegin("admin", input);
				}
			if (newAction.contentEquals("B")) { 
					System.out.println("Options:\n 1 - Add multiple cars\n 2 - Add one car\n 3 - Sell a car\n 4 - Print cars\n 5 - Search\n 6 - Calculate Revenue\n 7 - Logout\n 8 - Add a user");
					String action = scan.nextLine();	
				
				LoginPageConsole.loggedIn("admin", action, false);
			}
			}
	}
	//once logged in as customer they have access to search and logging out
	public static void customerBegin() throws NumberFormatException, IOException, ParseException {
		String newAction = "Y";
		while (newAction.equals("Y")) {
			actionSearch("customer");
			System.out.println("\nMake another search? (N for no -log out and Y for new search)");
			scan = new Scanner(System.in);
			newAction = scan.nextLine();
			if (newAction.contentEquals("N")) {
				LoginPageConsole.enterCred();
			} else if (newAction.equals("Y")==false) {
				while (true) {
					System.out.println("Error please input either Y or N");
					newAction =scan.nextLine();
					if (newAction.contentEquals("Y")) { 
						break;
					} else if (newAction.contentEquals("N") ) {
						LoginPageConsole.enterCred();
						break;
					}
				}
			}
		}
	}
	// once logged in as staff (dependency on LoginPageConsole, they can perform staff methods or log out. 
	public static String staffBegin(String role, String input) throws ParseException, IOException  {
		scan = new Scanner(System.in);
		String newAction = "R";
		while (newAction.equals("R")) {
			if (input.equals("2")) {
				newAction = actionAddCar();
			} else if (input.equals("6")) {
				actionRev();
				System.out.println("Input B-back to menu or R - repeat print");
				newAction = scan.nextLine();
			} else if (input.equals("5")) {
				actionSearch(role);
				System.out.println("Input B-back to menu or R - repeat print");
				newAction = scan.nextLine();
			} else if (input.equals("4")) {
				System.out.println("Cars printed in cars-output.txt");
				StaffClass.printCars();
				System.out.println("Input B-back to menu or R - repeat print");
				newAction = scan.nextLine();
				//break;
			} else if (input.equals("3")) {
				actionSell();
				newAction = returnBack();
			} else if (input.contentEquals("7")) {
				System.out.println("Logging out.");
				LoginPageConsole.enterCred();
			} else if (input.equals("1")) {
				System.out.println("Add Cars chosen");
				newAction =actionAddCars();
			}
			if (role.equals("staff")) {
				if (newAction.contentEquals("B")) {
				System.out.println("Options:\n 1 - Add multiple cars\n 2 - Add one car\n 3 - Sell a car\n 4 - Print cars\n 5 - Search\n 6 - Calculate Revenue\n 7 - Logout\n 8 - Add a user");
				String action = scan.nextLine();
				LoginPageConsole.loggedIn("admin", action, false);
				} else if (newAction.equals("R")==false) {
					while (true) {
						System.out.println("Error please input either B or R");
						newAction =scan.nextLine();
						if (newAction.contentEquals("B")) { 
							break;
						} else if (newAction.contentEquals("R") ) {
							LoginPageConsole.loggedIn(role, input, false);
							break;
						}
					}
				}
			} else {
				break;
			}
		} 
		return newAction;
		
	}
	//admin and staff have access to add multiple cars from a file
	private static String actionAddCars() throws NumberFormatException, IOException, ParseException {
		scan = new Scanner(System.in);
		System.out.println("Please enter a file location");
		String location = scan.nextLine();
		boolean repeatLoop = true;
		while (repeatLoop) {
			try {
				StaffClass.addCars(location);
				StaffClass.printCars();
				System.out.println("Cars added, check cars-output.txt to check");
				break;
			} catch (Exception e) {
				System.out.println("Sorry couldnt find the file you were looking for.\n 'T' - Try again or 'B' - go back to menu?");
				String choice = scan.nextLine();
				while (true) {
					if (choice.equals("T")) {
						System.out.println("Enter file location here (e.g. cars-import.txt)");
						location = scan.nextLine();
						break;
					} else if (choice.equals("B")==false) {
						System.out.println("Error please input B or R.");
						 choice = scan.nextLine();
					} else {
						repeatLoop = false;
						break;
					}
				}
			}
		}
		return "B";
		
	}
	//function for returning to the menu screen.
	private static String returnBack() {
		scan = new Scanner(System.in);
		System.out.println("Back to menu or repeat action? (B or R)");
		String newAction = scan.nextLine();
		return newAction;
	}
	//validation check for model
	private static void checkModel(String modelInput) {
		while (true) {
			if (modelInput.length()<=3 ||modelInput.length() >100) {
				System.out.println("Length too long or short to be model type");
				System.out.println("Model: ");
				modelInput = scan.nextLine();
			} else {
				if (checkWord(modelInput)) {
					break;
				} else {
					System.out.println("Incorrect input, please use only letters");
					System.out.println("Model: ");
					modelInput = scan.nextLine();
				}
			}
		}
	}
	//validation check for only containing letters
	private static boolean checkWord(String word) {
		boolean check = false;
		for (int i = 0; i<word.length(); i++) {
			if ((word.charAt(i)>='a' && word.charAt(i)<='z') ||(word.charAt(i)>='A' && word.charAt(i)<='Z') ) {
				check = true;
			} 
		}
		return check;
	}
	////validation check for numeber plate
	private static boolean checkPlate(String plateInput) {
		scan = new Scanner(System.in);
		while (true) {
			if (plateInput.length()<=3 ||plateInput.length() >9) {
				System.out.println("Length too long or short to be model type");
				System.out.println("Plate: ");
				plateInput = scan.nextLine();
			} else {
				for (int i = 0; i<plateInput.length(); i++) {
					if ((plateInput.charAt(i)>='a' && plateInput.charAt(i)<='z') ||(plateInput.charAt(i)>='A' && plateInput.charAt(i)<='Z') || (plateInput.charAt(i)>=0 && plateInput.charAt(i)<=9)) {
						//System.out.println("Here");
						return true;
					} else {
						System.out.println("Incorrect input, please use only letters");
						System.out.println("Plate: ");
						plateInput = scan.nextLine();
					}
					
				} 
			}
		}
	}
	//admin and staff are able to sell a car using a number plate input (which is validated)
	private static void actionSell() throws ParseException, IOException {
		
		scan = new Scanner(System.in);		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		boolean loopNumberPlate = true;
		boolean checkIfSold = false;
		System.out.println("Please input number plate: ");
		String plateInput = scan.nextLine();
		if (checkPlate(plateInput)) {
			while (loopNumberPlate==true) {
				StaffClass example  = new StaffClass();
				ArrayList<Car> carTypes = example.getCars();
				StaffClass example2  = new StaffClass();
				ArrayList<Van> vanTypes = example2.getVans();
				for (int i = 0; i< carTypes.size(); i++) {
					Car car = carTypes.get(i);
					if (car.sold != null && car.numberPlate.equals(plateInput)) {
						System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival) + ", " + dateFormat.format(car.sold));
						System.out.println("Error car already sold, please input another number plate.");
						checkIfSold = true;
						plateInput = scan.nextLine();
						break;
					} else if (car.numberPlate.equals(plateInput)){
						System.out.println("Is your car: ");
						System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
						System.out.println("Y - Yes or N - No");
						String choice = scan.nextLine();
						while (true) {
							if (choice.equals("Y") && checkIfSold==false) {
								loopNumberPlate = false;
								break;
							} else if (choice.equals("N") && checkIfSold==false) {
								System.out.println("Input new plate: ");
								plateInput = scan.nextLine();
								break;
							} else if (checkIfSold==false) {
								System.out.println("Error, please input 'Y' or 'N'");
								choice = scan.nextLine();
							}
						}
						break;
					} 
				}
				for (int j = 0; j< vanTypes.size(); j++) {
					Van van = vanTypes.get(j);
					if (van.sold != null && van.numberPlate.equals(plateInput)) {
						System.out.println(van.numberPlate + ", " + van.model + ", " + van.carType +  ", " + van.size + ", " + van.colour + ", " + van.mileage + ", " + van.accidentHistory + ", " + van.transmissionType + ", " +van.price + ", " + dateFormat.format(van.arrival) + ", " + dateFormat.format(van.sold));
						System.out.println("Error car already sold, please input another number plate.");
						checkIfSold = true;
						System.out.println("Input new plate: ");
						plateInput = scan.nextLine();
						break;
					} else if (van.numberPlate.equals(plateInput)){
						System.out.println("Is your car: ");
						System.out.println(van.numberPlate + ", " + van.model + ", " + van.carType + ", " + van.size + ", "+  van.colour + ", " + van.mileage + ", " + van.accidentHistory + ", " + van.transmissionType + ", " +van.price + ", " + dateFormat.format(van.arrival));
						System.out.println("Y - Yes or N - No");
						String choice = scan.nextLine();
						while (true) {
							if (choice.equals("Y")) {
								loopNumberPlate = false;
								break;
							} else if (choice.equals("N") && checkIfSold==false) {
								System.out.println("Input new plate: ");
								plateInput = scan.nextLine();
								break;
							} else if (checkIfSold==false) {
								System.out.println("Error, please input 'Y' or 'N'");
							}
						}
						break;
					} 
				}
				
			}
		}

		StaffClass.sellCar(plateInput);
		
		StaffClass.printCars();
		System.out.println(plateInput + " has now been sold, please check cars-output.txt to check updated record.");
	}
	
	//all roles have access to search but will have different access levels
	private static void actionSearch(String role) throws IOException, NumberFormatException, ParseException {
		scan2 = new Scanner(System.in);
		System.out.println("How would you like to search? \n 1 - model and transmission\n 2 - colour \n 3 - seat number (min and max) \n 4 - A size of van");
		while (true) {
			String userInput = scan2.nextLine();
			if (userInput.equals("1")) {
				System.out.println("Input model name: ");
				String model = scan2.nextLine();
				checkModel(model);
				System.out.println("Input transmission type: ");
				String transmission = scan2.nextLine();
				while (true) {
					if (checkWord(transmission)) {
						break;
					} else {
						System.out.println("Error - use only letters; new colour: ");
						transmission = scan2.nextLine();
					}
				}
				StaffClass.search(role, model, transmission);
				break;
			} else if (userInput.equals("2")) {
				System.out.println("Input vehicle colour: ");
				String colour = scan2.nextLine();
				while (true) {
					if (checkWord(colour)) {
						break;
					} else {
						System.out.println("Error - use only letters; new colour: ");
						colour = scan2.nextLine();
					}
				}
				StaffClass.search(role, colour);
				break;
			}if (userInput.contentEquals("3")) {
				System.out.println("Input minimum seat number: ");
				String minimum = scan2.nextLine();
				while (true) {
					if (checkInt(minimum)) {
						break;
					} else {
						System.out.println("Error please input an integer.");
						minimum = scan2.nextLine();
					}
				}
				System.out.println("Input maximum seat number: ");
				String maximum = scan2.nextLine();
				while (true) {
					if (checkInt(maximum)) {
						break;
					} else {
						System.out.println("Error please input an integer.");
						maximum = scan2.nextLine();
					}
				}
				System.out.println("For minimum size: " +minimum + "\nTo maximum size " +maximum);
				StaffClass.search(role, Integer.parseInt(minimum), Integer.parseInt(maximum));
				break;
			} else if (userInput.contentEquals("4")) {
				System.out.println("Input van size (small or large)");
				String size = scan2.nextLine();
				StaffClass.search(role, size);
				break;
			} else {
				System.out.println("Error - please input option 1, 2 or 3.");
			}
		}
	}
	//validation check for username and password(in add user method)
	private static void checkUser(String word) {
		scan = new Scanner(System.in);
		while(true) {
			if (word.length()<5||word.length()>15) {
				System.out.println("Too long or short, please try again.");
				System.out.println("Try again: ");
				word = scan.nextLine();
			} else {
				if (checkWord(word)) {
					break;
				} else {
					System.out.println("Incorrect input, please use only letters");
					System.out.println("Try again: ");
					word = scan.nextLine();
				}
			}
		}
	}
	
	//validation check for role (in add user method)
	private static void checkRole(String role) {
		while (true) {
			if (role.equals("admin") || role.equals("customer") ||role.equals("staff") ) {
	        	break;
	        }  else {
	        	System.out.println("Invalid role; must be 'staff', 'customer' or 'admin'.");
	        	System.out.print("Type new role: ");
				role = scan.nextLine();
	        }
		}
	}
	
	//admin have access to add a user of type Login
	private static String actionAddUser() throws ParseException, IOException {
		String action = "R";
		while (action.equals("R")) {
			scan4 = new Scanner(System.in);
			System.out.println("New username: ");
			String userInput = scan4.nextLine();
			checkUser(userInput);
			System.out.println("New password: ");
			String passInput = scan4.nextLine();
			checkUser(passInput);
			System.out.println("New role (customer, admin, staff): ");
			String roleInput = scan4.nextLine();
			checkRole(roleInput);
			AdminClass.addUsers(userInput, passInput, roleInput);
			System.out.println("Please input B - back to menu or R - repeat to make another user");
			action = scan4.nextLine();
			while (true) {
				if (action.contentEquals("B")) { 
					break;
				} else if (action.contentEquals("R") ) {
					break;
				} else {
					System.out.println("Error please input either B or R");
					action =scan4.nextLine();
				}
			}
		}
		System.out.println("Options:\n 1 - Add multiple cars\n 2 - Add one car\n 3 - Sell a car\n 4 - Print cars\n 5 - Search\n 6 - Calculate Revenue\n 7 - Logout\n 8 - Add a user"); 
		String actionAdmin = scan.nextLine();	
		while (true) {
			try {
				int integer = Integer.parseInt(actionAdmin);
				if (integer <0 || integer  >8) {
					System.out.println("Wrong input, please select from menu (1-8)");
					actionAdmin = scan.nextLine();
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("Wrong input, please select from menu (1-8)");
				actionAdmin = scan.nextLine();
			}
		}
			LoggedIn.adminBegin(actionAdmin);
		scan.close();
		
		return actionAdmin;
		
		
	}
	
	//admin and staff can calculate revenue with different options
	private static void actionRev() throws IOException, NumberFormatException, ParseException {
		scan3 = new Scanner(System.in);
		System.out.print("Please input either: 1 - day; 2 - month; 3 - from one date to another");			//options to chose from
		while (true) {
			String dateOption = scan3.nextLine();
			if (dateOption.equals("1")) {
				System.out.println("Calculating revenue for a specific day. \nPlease input date in format 'YYYY-MM-DD'");
				String date = scan3.nextLine();
				date = dateCheck(date);
				StaffClass.calRevenueDate(date);
				
				break;
			} else if (dateOption.equals("2")) {
				System.out.println("Calculating revenue for a specific month. \nPlease input date in format 'YYYY-MM'");
				String date = scan3.nextLine();
				DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
				while (true) {
					try {
						Date monthDate = dateFormat2.parse(date);
						break;
					} catch (Exception e) {
						System.out.println("Error dates incorrectly input. Put in form 'YYYY-MM'");
						date = scan3.nextLine();
					}
				}
				StaffClass.calRevenueMonth(date);
				break;
			} else if (dateOption.equals("3")) {
				System.out.println("Calculating revenue for a specific time period. \nPlease input first date in format 'YYYY-MM-DD'");
				String date1 = scan3.nextLine();
				//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
				date1 = dateCheck(date1);
				System.out.println("Please input second date in format 'YYYY-MM-DD'");
				String date2 = scan3.nextLine();
				date2 = dateCheck(date2);
				StaffClass.calRevenue(date1, date2);
				break;
			} else {
				System.out.println("Error please input either 1, 2 or 3");
			}
		}
	}
	
	//a validation check for date
	private static String dateCheck(String date) {
		scan3 = new Scanner(System.in);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
		while (true) {
			try {
				Date dayDate = dateFormat.parse(date);
				System.out.println(dayDate);
				break;
			} catch (Exception e) {
				System.out.println("Error dates incorrectly input. Put in form 'YYYY-MM-DD'");
				date = scan3.nextLine();
			}
		}
		return date;
	}
	//validation check for integers like price
	private static boolean checkInt(String integer) {
		boolean check =false;
		try {
			int checkingInt = Integer.parseInt(integer);
			if (checkingInt>=0) {
				check = true;
			}
		} catch (Exception e) {
			check = false;
		}
		return check;
	}
	
	//action to add a car for admin and staff
	private static String actionAddCar() throws ParseException {
		Scanner scan = new Scanner(System.in);
		System.out.println("New numberplate: ");
		String plateInput = scan.nextLine();
		boolean repeat = true;
		while (repeat) {
			repeat =false;
			checkPlate(plateInput);
			StaffClass example  = new StaffClass();
			ArrayList<Car> carTypes = example.getCars();
			StaffClass example2  = new StaffClass();
			ArrayList<Van> vanTypes = example2.getVans();
			for (int i = 0; i< carTypes.size(); i++) {
				Car car = carTypes.get(i);
				if (car.numberPlate.equals(plateInput)) {
					System.out.println("This number plate is already used - please input a unique one.");
					plateInput = scan.nextLine();
					repeat = true;
					break;
				}
			}
			for (int j = 0; j< vanTypes.size(); j++) {
				Van van = vanTypes.get(j);
				if (van.numberPlate.equals(plateInput)) {
					System.out.println("This number plate is already used - please input a unique one.");
					plateInput = scan.nextLine();
					repeat = true;
					break;
				}
			}
		}
		System.out.println("New model: ");
		String modelInput = scan.nextLine();
		checkModel(modelInput);
		System.out.println("New car type: ");
		String typeInput = scan.nextLine();
		String vanSize ="none";
		if (typeInput.equals("Van" )) {
			System.out.println("Van size (small or large): ");
			vanSize = scan.nextLine();
			while (true) {
				if (vanSize.equals("small") ||vanSize.equals("large")) {
					break;
				} else {
					System.out.println("Error please input 'small' or 'large': ");
					vanSize = scan.nextLine();
				}
			}
		}
		
		System.out.println("New colour: ");
		String colourInput = scan.nextLine();
		while (true) {
			if (checkWord(colourInput)) {
				break;
			} else {
				System.out.println("Error - use only letters; new colour: ");
				colourInput = scan.nextLine();
			}
		}
		System.out.println("New mileage: ");
		String mileageInputString = scan.nextLine();
		int mileageInput = -1;
		while (true) {													//validation
			if (checkInt(mileageInputString)) {
				mileageInput = Integer.parseInt(mileageInputString);
				break;
			} else {
				System.out.println("Error please input an integer.");
				mileageInputString = scan.nextLine();
			}
		}
		System.out.println("Any accident history: ");
		String acciInput = scan.nextLine();
		System.out.println("New transmission type: ");
		String transmInput = scan.nextLine();
		System.out.println("The price: ");
		String priceInputString = scan.nextLine();
		int priceInput = -1;
		while (true) {
			if (checkInt(priceInputString)) {
				priceInput = Integer.parseInt(priceInputString);
				break;
			} else {
				System.out.println("Error please input an integer.");
				priceInputString = scan.nextLine();
			}
		}
		while (true) {
			System.out.println("Arrival date known? ('Y' or 'N') ");
			String arrivalKnown = scan.nextLine();
			
			if (arrivalKnown.equals("N") && typeInput.equals("Van")) {
				StaffClass.addCar(plateInput, modelInput, typeInput, vanSize, colourInput, mileageInput, acciInput, transmInput, priceInput);
				break;
			} else if (arrivalKnown.equals("N")) {
				StaffClass.addCar(plateInput, modelInput, typeInput, colourInput, mileageInput, acciInput, transmInput, priceInput);
				break;
			} 
			else if (arrivalKnown.equals("Y") &&  typeInput.equals("Van")) {
				System.out.println("Arrival date: ");
			    String arrivalDate = scan.nextLine();
			    arrivalDate = dateCheck(arrivalDate);
			    System.out.println("Sold date known? ('Y' or 'N') ");
				String soldKnown = scan.nextLine();
				if (soldKnown.equals("Y")) {
					System.out.println("Sold date: ");
				    String soldDate = scan.nextLine();
				    soldDate = dateCheck(soldDate);
				    StaffClass.addCar(plateInput, modelInput, typeInput, vanSize, colourInput, mileageInput, acciInput, transmInput, priceInput, arrivalDate, soldDate);
				    break;
				} else {
					StaffClass.addCar(plateInput, modelInput, typeInput, vanSize, colourInput, mileageInput, acciInput, transmInput, priceInput, arrivalDate);
					break;
				}
			}
			else if (arrivalKnown.equals("Y")) {
				System.out.println("Arrival date: ");
			    String arrivalDate = scan.nextLine();
			    arrivalDate = dateCheck(arrivalDate);
			    System.out.println("Sold date known? ('Y' or 'N') ");
				String soldKnown = scan.nextLine();
				if (soldKnown.equals("Y")) {
					System.out.println("Sold date: ");
				    String soldDate = scan.nextLine();
				    soldDate = dateCheck(soldDate);
				    AdminClass.addCar(plateInput, modelInput, typeInput, colourInput, mileageInput, acciInput, transmInput, priceInput, arrivalDate, soldDate);
				    break;
				} else {
					AdminClass.addCar(plateInput, modelInput, typeInput, colourInput, mileageInput, acciInput, transmInput, priceInput, arrivalDate);
					break;
				}
			}else {
				System.out.println("Error please type Y or N.");
			}
		}
		System.out.println("New car added.");
		//scan = new Scanner(System.in);
		System.out.println("R = repeat or B = back to menu:");
		String newAction = scan.nextLine();
		scan.close();
		return newAction;
	}

}
