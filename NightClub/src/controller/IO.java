package controller;

import java.io.FileNotFoundException;
import java.io.InvalidClassException;

import model.EventsBag;
import model.UsersBag;

public class IO  {

	public IO(){
		
	}
	
	public static void saveAll(){
		UsersBag.save();
		EventsBag.save();
	}
	
	public static void loadAll() throws FileNotFoundException, InvalidClassException{
		try {
			UsersBag.load();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			EventsBag.load();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveUsers(){
		UsersBag.save();
	}
	
	public static void saveEvents(){
		EventsBag.save();
	}

}
