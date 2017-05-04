package model.model4Address;

public class ZipException extends Exception{

	
	public ZipException(){
		this("Zip code must be 5 digits long. Enter a 5 digit zip code.");
	}
	
	public ZipException(String message){
		super(message);
	}
}
