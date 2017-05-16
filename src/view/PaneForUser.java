package view;

import controller.Current;
import controller.IO;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaneForUser {
	
	public TextField emailField;
	private PaneForAddress address = new PaneForAddress();
	private Label error;
	private VBox view;
	
	public Pane getCreatePane(){
		Pane pane = new Pane();
		pane.getChildren().addAll(view());
		return pane;
	}
	
	public Pane getUpdatePane(){
		Pane pane = new Pane();
		pane.getChildren().add(updateView());
		return pane;
	}

	private VBox view(){
		view = new VBox(5);
		view.getChildren().addAll(email(), address.getCreatePane());
		return view;
	}
	
	private VBox updateView(){
		view = new VBox(5);
		view.getChildren().addAll(email(), address.getUpdatePane());
		updateTextField();
		return view;
	}
	
	private HBox email(){
		HBox email = new HBox(5);
		Label emailLbl = new Label("Email:\t\t\t");
		emailField = new TextField();
		email.getChildren().addAll(emailLbl, emailField);
		return email;
	}
	
	//Will display an error message you give, invisible before there is an error
	private void error(String message){
		if(view.getChildren().contains(error)){
			error.setText(message);
		} else {
			error = new Label(message);
			error.setTextFill(Color.web("#FF0000"));
			view.getChildren().add(error);
		}
	}
	
	/*
	 * checks to see if address's fields are satisfied
	 * checks to see if all of it's fields are full
	 */
	public boolean testFields(){
		if (address.testFields() || emailField.getText().isEmpty()){
			error("Must Enter All Fields!");
			return true;
		} else {
			return false;
		}
	}
	
	//when viewing an already existing user, it gets the already existing content
	public void updateTextField(){
		emailField.setText(Current.getUser().getEmail());
	}
	
	//saves everything in the fields
	public void saveIt(){
		address.saveInfo();
		Current.getUser().setEmail(emailField.getText());
		IO.saveUsers();
	}
	
}
