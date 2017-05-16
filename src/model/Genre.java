package model;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Genre {
	
//	private ImageView image = new ImageView(new Image("images"+File.separator+"logo3.png"));
	
	HIPHOP("Hip Hop", new Image("images"+File.separator+"Hip Hop.jpg")), 
	DISCO("Disco", new Image("images"+File.separator+"Disco.jpg")), 
	COMEDY("Comdey", new Image("images"+File.separator+"Comedy.jpg")), 
	DANCE("Dance", new Image("images"+File.separator+"Dance.jpg")), 
	LIVE("Live Performance", new Image("images"+File.separator+"Live Performance.jpeg")), 
	ELECTRONIC("Electronic", new Image("images"+File.separator+"Electronic.jpg")), 
	POP("Pop", new Image("images"+File.separator+"Pop.jpg"));
	
	private String genre;
	private Image image;
	
	Genre(String genre, Image image){
		this.genre = genre;
		this.image = image;
	}
	
	public String getString(){
		return genre;
	}
	
	public Image getImage(){
		return image;
	}

}
