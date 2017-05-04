package view;

import controller.Current;
import controller.IO;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneForUser {
	
	public TextField emailField;
	private PaneForAddress address = new PaneForAddress();
	
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
		VBox view = new VBox(5);
		view.getChildren().addAll(email(), address.getCreatePane());
		return view;
	}
	
	private VBox updateView(){
		VBox view = new VBox(5);
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
	
	public void updateTextField(){
		emailField.setText(Current.getUser().getEmail());
	}
	
	public void saveIt(){
		address.saveInfo();
		Current.getUser().setEmail(emailField.getText());
		IO.saveUsers();
	}
	
}
