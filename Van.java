package login.privacy;
import java.text.ParseException;

public class Van extends Vehicle {

	public Van(String numberPlate, String model, String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, 	int price, 	String arrival, String sold) throws ParseException {
		super(numberPlate, model, cartype, size, colour, mileage, accidentHistory, transmissionType, price, arrival, sold);
	}
	public Van(String numberPlate, String model, String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, 	int price, 	String arrival) throws ParseException {
		super(numberPlate, model, cartype, size, colour, mileage, accidentHistory, transmissionType, price, arrival);

	}
	public Van(String numberPlate, String model, String cartype, String size, String colour, int mileage, String accidentHistory, String transmissionType, 	int price) throws ParseException {
		super(numberPlate, model, cartype, size, colour, mileage, accidentHistory, transmissionType, price);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
