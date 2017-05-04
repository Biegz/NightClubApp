package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Pane4Date {

	private VBox pane;
	
	private Label dateLbl;
	private Label timeLbl;
	
	public Pane4Date(){
		pane = new VBox();
		
		//test Text
		dateLbl = new Label("12/12/2012");
		timeLbl = new Label("9:00pm");
		
		pane.getChildren().addAll(dateLbl, timeLbl);
		
	}
	
	public Pane getPane(){
		return pane;
	}
}
