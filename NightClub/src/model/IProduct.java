package model;

public interface IProduct {

	String name = null;
	double price = (Double) null;

	
	public default String getName(){
		return name;
	}
	
	public default double getPrice(){
		return price;
	}	
	
	
}
