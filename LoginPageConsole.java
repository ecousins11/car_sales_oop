package login.privacy;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginPageConsole {
	public static String role;
	public static Scanner sc;
	
	public static void enterCred()  throws ParseException, IOException  { 
		System.out.println("Login Page");
		boolean check = false;
		while (check ==false) {
			System.out.print("Username:");
			Scanner sc = new Scanner(System.in);
			String username = sc.nextLine();		
			System.out.print("Password: ");
			String password = sc.nextLine();
			System.out.print("Role (customer, staff, admin): ");
			role = sc.nextLine();
			
			Login login = new Login(username, password, role);
			AdminClass example  = new AdminClass();
			AdminClass.addUsers();
			ArrayList<Login> loginExamples = example.getUsers();
			
			
			for (int i=0; i<loginExamples.size(); i++) {
				Login log = loginExamples.get(i);
				if (login.username.equals(log.username) && login.getPassword().equals(log.getPassword()) && login.role.equals(log.role)) {
					check = true;
					break;
				}
			}
			
			if (check == true) {
				System.out.println("Login successful, welcome, "+ login.username);
				StaffClass.addVehicle("carType.txt");
				StaffClass.addCars("cars-import.txt");
				
				loggedIn(login.role, "N", true);
				sc.close();
			} else {
				System.out.println("User login failed");
			}
		}
		
	}
	
	public static void loggedIn(String role, String action, boolean first) throws ParseException, IOException {
		sc = new Scanner(System.in);
		if (role.equals("admin")) {
			if (first) {
				System.out.println("Options:\n 1 - Add multiple cars\n 2 - Add one car\n 3 - Sell a car\n 4 - Print cars\n 5 - Search\n 6 - Calculate Revenue\n 7 - Logout\n 8 - Add a user");
				action = sc.nextLine();	
			}
				
			while (true) {
				try {
					int integer = Integer.parseInt(action);
					if (integer <0 || integer  >8) {
						System.out.println("Wrong input, please select from menu (1-8)");
						action = sc.nextLine();
					} else {
						break;
					}
				} catch (Exception e) {
					System.out.println("Wrong input, please select from menu (1-8)");
					action = sc.nextLine();
				}
			}
				LoggedIn.adminBegin(action);		
			
		} else if (role.contentEquals("staff")) {
			if (first) {
				System.out.println("Options:\n 1 - Add multiple cars\n 2 - Add one car\n 3 - Sell a car\n 4 - Print cars\n 5 - Search\n 6 - Calculate Revenue\n 7 - Logout\n 8 - Add a user");
				action = sc.nextLine();	
			}
			LoggedIn.staffBegin("staff", action);
		} else if (role.equals("customer")) {
			LoggedIn.customerBegin();
		}else {
			System.out.println("Incorrect login");
		}
	}
	
	public static void main(String[] args) throws ParseException, IOException {
		enterCred();
	}
}
