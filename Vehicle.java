package login.privacy;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehicle {
    
    String carType;
    int doors;
    int seats;
    
    String numberPlate;
	String model;
	String size;
	String colour;
	int mileage;
	String accidentHistory;
	String transmissionType;
	int price;
	Date arrival;
	Date sold;
	
	public Vehicle(String cartype, int doors, int seats) {
		this.carType = cartype;
		this.doors = doors;
		this.seats = seats;
	}

	public Vehicle(String numberPlate, 	String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, 	int price, 	String arrival, String sold) throws ParseException {
		this.carType = cartype;
		this.numberPlate = numberPlate;
		this.model = model;
		this.colour = colour;
		this.mileage = mileage;
		this.accidentHistory = accidentHistory;
		this.transmissionType = transmissionType;
		this.price = price;
		try {
			this.arrival = new SimpleDateFormat("yyyy-MM-dd").parse(arrival);
			this.sold = new SimpleDateFormat("yyyy-MM-dd").parse(sold);
		} catch (ParseException e){
			e.printStackTrace();
		}
	}
	public Vehicle(String numberPlate, 	String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, 	int price, 	String arrival) throws ParseException {
		this.carType = cartype;
		this.numberPlate = numberPlate;
		this.model = model;
		this.colour = colour;
		this.mileage = mileage;
		this.accidentHistory = accidentHistory;
		this.transmissionType = transmissionType;
		this.price = price;
		try {
			this.arrival = new SimpleDateFormat("yyyy-MM-dd").parse(arrival);
		} catch (ParseException e){
			e.printStackTrace();
			
		}
	}
	public Vehicle(String numberPlate, 	String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, 	int price) throws ParseException {
		this.carType = cartype;
		this.numberPlate = numberPlate;
		this.model = model;
		this.colour = colour;
		this.mileage = mileage;
		this.accidentHistory = accidentHistory;
		this.transmissionType = transmissionType;
		this.price = price;
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = dateFormat.format(currentDate);
		this.arrival = dateFormat.parse(format);
	}
	public Vehicle(String numberPlate, String model, String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, 	int price, 	String arrival, String sold) throws ParseException {
		this.carType = cartype;
		this.numberPlate = numberPlate;
		this.model = model;
		this.size = size;
		this.colour = colour;
		this.mileage = mileage;
		this.accidentHistory = accidentHistory;
		this.transmissionType = transmissionType;
		this.price = price;
		try {
			this.arrival = new SimpleDateFormat("yyyy-MM-dd").parse(arrival);
			this.sold = new SimpleDateFormat("yyyy-MM-dd").parse(sold);
		} catch (ParseException e){
			e.printStackTrace();
		}
	}
	public Vehicle(String numberPlate, String model, String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, 	int price, 	String arrival) throws ParseException {
		this.carType = cartype;
		this.numberPlate = numberPlate;
		this.model = model;
		this.size = size;
		this.colour = colour;
		this.mileage = mileage;
		this.accidentHistory = accidentHistory;
		this.transmissionType = transmissionType;
		this.price = price;
		try {
			this.arrival = new SimpleDateFormat("yyyy-MM-dd").parse(arrival);
		} catch (ParseException e){
			e.printStackTrace();
			
		}
	}
	public Vehicle(String numberPlate, String model, String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, 	int price) throws ParseException {
		this.carType = cartype;
		this.numberPlate = numberPlate;
		this.model = model;
		this.size = size;
		this.colour = colour;
		this.mileage = mileage;
		this.accidentHistory = accidentHistory;
		this.transmissionType = transmissionType;
		this.price = price;
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String test = dateFormat.format(currentDate);
		this.arrival = dateFormat.parse(test);
	}
	

}
