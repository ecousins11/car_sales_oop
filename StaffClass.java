package login.privacy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class StaffClass {
    

    final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static ArrayList<Car> carTypes = new ArrayList<Car>();
    public static ArrayList<Van> vanTypes = new ArrayList<Van>();
    //vehicleTypes will include both car and van types
    public static ArrayList<Vehicle> vehicleTypes = new ArrayList<Vehicle>();
    Scanner scan = new Scanner(System.in);
    
    public ArrayList<Car> getCars() {
		return carTypes;
	} 
    public ArrayList<Van> getVans() {
		return vanTypes;
	} 
    //this adds the file carType and is used once the user has logged on successfully
	public static void addVehicle(String location) throws IOException {
		File inputFile = new File (location);
		BufferedReader br = new BufferedReader(new FileReader(inputFile)); 
		try {
			String line; 
			
			while((line = br.readLine()) != null) {
				String[] type = line.split(", ");
				Vehicle newVehicle = new Vehicle(type[0],Integer.parseInt(type[1]),  Integer.parseInt(type[2]));
				vehicleTypes.add(newVehicle);
			}
			br.close(); 
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	//this adds multiple cars, it is used once the user has logged on successfully and can then be used by staff and admin with an input file location
	public static void addCars(String location) throws IOException, NumberFormatException, ParseException {
		File inputFile = new File (location);
		BufferedReader br = new BufferedReader(new FileReader(inputFile)); 
		try {
			String line; 
			while((line = br.readLine()) != null) {
				String[] type = line.split(", ");
				if (type[2].equals("Van")) {
					if (type.length == 11) {
						Van newVan = new Van(type[0],type[1],type[2],type[3],type[4],Integer.parseInt(type[5]),type[6],type[7],Integer.parseInt(type[8]),type[9], type[10]);
						vanTypes.add(newVan);
					} else if (type.length == 10) {
						Van newVan = new Van(type[0],type[1],type[2],type[3],type[4],Integer.parseInt(type[5]),type[6],type[7],Integer.parseInt(type[8]),type[9]);
						vanTypes.add(newVan);
					} else {
						Van newVan = new Van(type[0],type[1],type[2],type[3],type[4],Integer.parseInt(type[5]),type[6],type[7],Integer.parseInt(type[8]));
						vanTypes.add(newVan);
					}
				} else {
					if (type.length == 10) {
						Car newCar = new Car(type[0],type[1],type[2],type[3],Integer.parseInt(type[4]),type[5],type[6],Integer.parseInt(type[7]),type[8], type[9]);
						carTypes.add(newCar);
					} else if (type.length ==9){
						Car newCar = new Car(type[0],type[1],type[2],type[3],Integer.parseInt(type[4]),type[5],type[6],Integer.parseInt(type[7]),type[8]);
						carTypes.add(newCar);
					} else {
						Car newCar = new Car(type[0],type[1],type[2],type[3],Integer.parseInt(type[4]),type[5],type[6],Integer.parseInt(type[7]));
						carTypes.add(newCar);
					}
				}
			}
			br.close(); 
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//these will add a car or van to the appropriate array
	public static void addCar(String numberPlate, String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, int price) throws ParseException {
		Car newCar = new Car(numberPlate, model, cartype, colour, mileage, accidentHistory, transmissionType, price);
		carTypes.add(newCar);
	}
	
	public static void addCar(String numberPlate, String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, int price, String arrival) throws ParseException {
		Car newCar = new Car(numberPlate, model, cartype, colour, mileage, accidentHistory, transmissionType, price, arrival);
		carTypes.add(newCar);
	}
	
	public static void addCar(String numberPlate, String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, int price, String arrival, String sold) throws ParseException {
		Car newCar = new Car(numberPlate, model, cartype, colour, mileage, accidentHistory, transmissionType, price, arrival, sold);
		carTypes.add(newCar);
	}
	
	public static void addCar(String numberPlate, String model,  String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, int price, String arrival, String sold) throws ParseException {
		Van newVan = new Van(numberPlate, model, size, cartype, colour, mileage, accidentHistory, transmissionType, price, arrival, sold);
		vanTypes.add(newVan);
	}
	public static void addCar(String numberPlate, String model,  String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, int price, String arrival) throws ParseException {
		Van newVan = new Van(numberPlate, model, size, cartype, colour, mileage, accidentHistory, transmissionType, price, arrival);
		vanTypes.add(newVan);
	}
	public static void addCar(String numberPlate, String model,  String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, int price) throws ParseException {
		Van newVan = new Van(numberPlate, model, size, cartype, colour, mileage, accidentHistory, transmissionType, price);
		vanTypes.add(newVan);
	}
	
	static void sellCar(String numberPlate) throws ParseException {
			for (int i = 0; i< carTypes.size(); i++) {
				Car car = carTypes.get(i);
				if (numberPlate.equals(car.numberPlate)) {
					if (car.sold != null)	{
						System.out.println("Error car already sold");
					} else {
						try {
							Date currentDate = new Date();
							String nowSold = dateFormat.format(currentDate);
							car.sold = new SimpleDateFormat("yyyy-MM-dd").parse(nowSold);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							break;
						}
					}
				}
			for (int j = 0; j< vanTypes.size(); j++) {
				Van van = vanTypes.get(j);
				if (numberPlate.equals(van.numberPlate) ) {
					if (van.sold != null)	{
						System.out.println("Error car already sold");
						break;
					} else {
						try {
							Date currentDate = new Date();
							String nowSold = dateFormat.format(currentDate);
							van.sold = new SimpleDateFormat("yyyy-MM-dd").parse(nowSold);
					    } catch (ParseException e){
					    	e.printStackTrace();
					    }
						
					}
					break;
				}
			}
	}
	//this will output a list of the current vehicles to a text file output.txt, ordered by sold/unsold then date; 
	public static void printCars() throws IOException, ParseException {
		File file = new File("cars-output.txt");
		FileWriter fileWriter = new FileWriter(file);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		ArrayList<Vehicle> vehicleSold = new ArrayList<Vehicle>();
		ArrayList<Vehicle> vehicleNotSold = new ArrayList<Vehicle>();
		
		for (int i = 0; i< carTypes.size(); i++) {
			Car car = carTypes.get(i);
			if (car.sold != null) {
				Vehicle vehicle = new Vehicle(car.numberPlate, car.model, car.carType, car.colour, car.mileage, car.accidentHistory, car.transmissionType, car.price, dateFormat.format(car.arrival), dateFormat.format(car.sold));
				vehicleSold.add(vehicle);
			} else {
				Vehicle vehicle = new Vehicle(car.numberPlate, car.model, car.carType, car.colour, car.mileage, car.accidentHistory, car.transmissionType, car.price, dateFormat.format(car.arrival));
				vehicleNotSold.add(vehicle);
			}
		}
		for (int i = 0; i< vanTypes.size(); i++) {
			Van van = vanTypes.get(i);
			if (van.sold != null) {
				Vehicle vehicle = new Vehicle(van.numberPlate, van.model, van.carType, van.size, van.colour, van.mileage, van.accidentHistory, van.transmissionType, van.price, dateFormat.format(van.arrival), dateFormat.format(van.sold));
				vehicleSold.add(vehicle);
			} else {
				Vehicle vehicle = new Vehicle(van.numberPlate, van.model, van.carType, van.size, van.colour, van.mileage, van.accidentHistory, van.transmissionType, van.price, dateFormat.format(van.arrival));
				vehicleNotSold.add(vehicle);
			}
		}
		dateSortSold(vehicleSold);
		dateSortNot(vehicleNotSold);
		for (int l = 0; l<vehicleSold.size(); l++) {
			Vehicle vehicle = vehicleSold.get(l);
			if (vehicle.carType.equals("Van")) {
				printWriter.println(vehicle.numberPlate + ", " + vehicle.model + ", " + vehicle.carType + ", " + vehicle.size + ", " + vehicle.colour + ", " + vehicle.mileage + ", " + vehicle.accidentHistory + ", " + vehicle.transmissionType + ", " +vehicle.price + ", " + dateFormat.format(vehicle.arrival)  + ", " + dateFormat.format(vehicle.sold));
			} else {
				printWriter.println(vehicle.numberPlate + ", " + vehicle.model + ", " + vehicle.carType + ", " + vehicle.colour + ", " + vehicle.mileage + ", " + vehicle.accidentHistory + ", " + vehicle.transmissionType + ", " +vehicle.price + ", " + dateFormat.format(vehicle.arrival)  + ", " + dateFormat.format(vehicle.sold));
			}
		}
		printWriter.println("Vehicles not sold:");
		for (int l = 0; l<vehicleNotSold.size(); l++) {
			Vehicle vehicle = vehicleNotSold.get(l);
			if (vehicle.carType.equals("Van")) {
				printWriter.println(vehicle.numberPlate + ", " + vehicle.model + ", " + vehicle.carType + ", " + vehicle.size + ", " + vehicle.colour + ", " + vehicle.mileage + ", " + vehicle.accidentHistory + ", " + vehicle.transmissionType + ", " +vehicle.price + ", " + dateFormat.format(vehicle.arrival));
			} else {
				printWriter.println(vehicle.numberPlate + ", " + vehicle.model + ", " + vehicle.carType + ", " + vehicle.colour + ", " + vehicle.mileage + ", " + vehicle.accidentHistory + ", " + vehicle.transmissionType + ", " +vehicle.price + ", " + dateFormat.format(vehicle.arrival));
			}
			
		} 
		printWriter.println("updated");
		printWriter.close();
	}
	
	//functions for sorting dates
	public static void dateSortSold(ArrayList<Vehicle> vehicleList) {
			Collections.sort(vehicleList, new Comparator<Vehicle>() {
				public int compare(Vehicle v1, Vehicle v2) {
					return v1.sold.compareTo(v2.sold);
				}
			});	
	}
	
	public static void dateSortNot(ArrayList<Vehicle> vehicleList) {
		Collections.sort(vehicleList, new Comparator<Vehicle>() {
			public int compare(Vehicle v1, Vehicle v2) {
				return v1.arrival.compareTo(v2.arrival);
			}
		});	
	}
	
	//searching will check the role they have logged on as and print the unsold cars (either with or without accident history depending on role)
	//there are 3 different types of searching
	public static void search(String role, String model, String transmission) {
		boolean notFound = true;
		if (role.equals("customer")) {
			for (int i = 0; i< carTypes.size(); i++) {
				Car car = carTypes.get(i);
				 if (car.sold == null && car.model.equals(model)&& (transmission.equals(car.colour) || transmission.equals(car.transmissionType))) {
					System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.colour + ", " + car.mileage +  ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
					notFound = false;
				} 
			}

			for (int i = 0; i< vanTypes.size(); i++) {
				Van car = vanTypes.get(i);
				if (car.sold == null && car.model.equals(model) &&transmission.equals(car.colour) || transmission.equals(car.transmissionType)) {
					System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.size + ", "+  car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
					notFound = false;
				} 
			}
			if (notFound == true) {
				System.out.println("Sorry couldn't find a match.");
			}
		} else {
			for (int i = 0; i< carTypes.size(); i++) {
				Car car = carTypes.get(i);
				if (car.sold == null && car.model.equals(model)&& (transmission.equals(car.colour) || transmission.equals(car.transmissionType))) {
					System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
					notFound = false;
				} 
			}

			for (int i = 0; i< vanTypes.size(); i++) {
				Van car = vanTypes.get(i);
				if (car.sold == null && car.model.equals(model) &&transmission.equals(car.colour) || transmission.equals(car.transmissionType)) {
					System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.size + ", "+  car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
					notFound = false;
				} 
			}
			if (notFound == true) {
				System.out.println("Sorry couldn't find a match.");
			}
		}
	}
	
	public static void search(String role, int min, int max) {
		boolean notFound = true;
		if (role.equals("customer")) {
			for (int p = 0; p<vehicleTypes.size(); p++) {
				Vehicle type = vehicleTypes.get(p); 
				if (type.seats >= min && type.seats <= max) {
					for (int i = 0; i< carTypes.size(); i++) {
						Car car = carTypes.get(i);
						if (car.sold == null && type.carType.equals(car.carType)) {
							System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.colour + ", " + car.mileage + ", "  + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
						    notFound = false;
						} 
					}
					for (int i = 0; i< vanTypes.size(); i++) {
						Van car = vanTypes.get(i);
						if (car.sold == null && type.carType.equals(car.carType)) {
							System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.size + ", "+  car.colour + ", " + car.mileage + ", "  + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
						    notFound = false;
						} 
					}
				}
			}
		} else {
			for (int p = 0; p<vehicleTypes.size(); p++) {
				Vehicle type = vehicleTypes.get(p); 
				if (type.seats >= min && type.seats <= max) {
					for (int i = 0; i< carTypes.size(); i++) {
						Car car = carTypes.get(i);
						if (car.sold == null && type.carType.equals(car.carType)) {
							System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
						    notFound = false;
						} 
					}
					for (int i = 0; i< vanTypes.size(); i++) {
						Van car = vanTypes.get(i);
						if (car.sold == null && type.carType.equals(car.carType)) {
							System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.size + ", "+  car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
						    notFound = false;
						} 
					}
				}
			}
		
		}
		if (notFound == true) {
			System.out.println("Sorry, no vehicles found. ");
		}
	}
	public static void search(String role, String size) {
		boolean notFound = true;
		if (role.equals("customer")) {
			for (int i = 0; i< vanTypes.size(); i++) {
				Van van = vanTypes.get(i);
				if (van.sold == null && (van.size.equals(size)|| van.colour.equals(size))) {
					System.out.println(van.numberPlate + ", " + van.model + ", " + van.carType + ", " + van.size + ", "+  van.colour + ", " + van.mileage + ", " + van.transmissionType + ", " +van.price + ", " + dateFormat.format(van.arrival));
				    notFound = false;
				} 
			}
			if (notFound == true) {
				System.out.println("No results, please make sure input is 'small' or 'large' to search vans.");
			}
			for (int i = 0; i< carTypes.size(); i++) {
				Car car = carTypes.get(i);
				if (car.sold == null &&  car.colour.equals(size)) {
					System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", "+  car.colour + ", " + car.mileage + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
				    notFound = false;
				} 
			}
			if (notFound == true) {
				System.out.println("No results, please make sure input is 'small' or 'large' to search vans.");
			}
		} else {
			for (int i = 0; i< vanTypes.size(); i++) {
				Van car = vanTypes.get(i);
				if (car.sold == null && (car.size.equals(size)|| car.colour.equals(size))) {
					System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", " + car.size + ", "+  car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
				    notFound = false;
				} 
			}
			for (int i = 0; i< carTypes.size(); i++) {
				Car car = carTypes.get(i);
				if (car.sold == null &&  car.colour.equals(size)) {
					System.out.println(car.numberPlate + ", " + car.model + ", " + car.carType + ", "+  car.colour + ", " + car.mileage + ", " + car.accidentHistory + ", " + car.transmissionType + ", " +car.price + ", " + dateFormat.format(car.arrival));
				    notFound = false;
				} 
			}
			if (notFound == true) {
				System.out.println("No results, please make sure input is 'small' or 'large' to search vans.");
			}
		}
		
	}
	//this calculates revenue for one date
	public static void calRevenueDate(String day) {
		int revenue = 0;
		for (int i = 0; i<vanTypes.size(); i++) {
			Van car = vanTypes.get(i);
			if (car.sold != null && dateFormat.format(car.sold).equals(day)) {
				revenue += car.price;
			}
		}
		for (int i = 0; i<carTypes.size(); i++) {
			Car car = carTypes.get(i);
			if (car.sold != null && dateFormat.format(car.sold).equals(day)) {
				revenue += car.price;
			}
		}
		System.out.println("For the date " + day + ", the revenue is £" + revenue);
	}
    //this will calculate revenue for the month
	public static void calRevenueMonth(String dayString) throws ParseException {
		int revenue = 0;
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
		Date dayDate = dateFormat2.parse(dayString);
		Calendar dayCal = dateToCalendar(dayDate);
		int monthSpec = dayCal.get(Calendar.MONTH);
		for (int i = 0; i<vanTypes.size(); i++) {
			Van car = vanTypes.get(i);
			
			if (car.sold != null) {
				Calendar dateSold = dateToCalendar(car.sold);
				int monthSold = dateSold.get(Calendar.MONTH);
				if (monthSold == monthSpec) {
					revenue += car.price;
				}
				
			}
		}
		for (int i = 0; i<carTypes.size(); i++) {
			Car car = carTypes.get(i);
			
			if (car.sold != null) {
				Calendar dateSold = dateToCalendar(car.sold);
				int monthSold = dateSold.get(Calendar.MONTH);
				if (monthSold == monthSpec) {
					revenue += car.price;
				}
			}
		}
		System.out.println("For the month " + dayString + ", the revenue is £" + revenue);
	}
	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	//this will calculate revenue between 2 dates
	public static void calRevenue(String dateString_1, String dateString_2) throws ParseException {
		Date date_1 = dateFormat.parse(dateString_1);
		Date date_2 = dateFormat.parse(dateString_2);
		int revenue = 0;

		for (int i = 0; i<vanTypes.size(); i++) {
			Van car = vanTypes.get(i);
			if (car.sold != null && (date_1.compareTo(car.sold)== -1 || date_1.compareTo(car.sold)== 0) && (date_2.compareTo(car.sold)== 1|| date_2.compareTo(car.sold)== 0)) {
				revenue += car.price;
			}
		}
		for (int i = 0; i<carTypes.size(); i++) {
			Car car = carTypes.get(i);
			if (car.sold != null && (date_1.compareTo(car.sold)== -1 || date_1.compareTo(car.sold)== 0) && (date_2.compareTo(car.sold)== 1|| date_2.compareTo(car.sold)== 0) ) {
				revenue += car.price;
			}
		}
		System.out.println("Between the dates " + dateString_1 + " and " + dateString_2 + ", the revenue is £" + revenue);
	}
}