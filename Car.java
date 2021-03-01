package login.privacy;
import java.text.ParseException;

public class Car extends Vehicle {
	
	public Car(String numberPlate, 	String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, 	int price, 	String arrival, String sold) throws ParseException {
		super(numberPlate, model, cartype, colour, mileage, accidentHistory, transmissionType, price, arrival, sold);
	}
	public Car(String numberPlate, 	String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, 	int price, 	String arrival) throws ParseException {
		super(numberPlate, model, cartype, colour, mileage, accidentHistory, transmissionType, price, arrival);
	}
	public Car(String numberPlate, 	String model, String cartype, String colour, int mileage, String accidentHistory, String transmissionType, 	int price) throws ParseException {
		super(numberPlate, model, cartype, colour, mileage, accidentHistory, transmissionType, price);
	}

	public static void main(String[] args) {
		//method();
	}

	
}
