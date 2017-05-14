package view;

import java.time.LocalDate;

import controller.Current;
import controller.IO;
import controller.SignInUp;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaneForBusiness {

	private HBox businessPane;
	private MainWindow mainWindow;
	public static TextField nameField;
	private String username;
	private String password;
	private Label error;
	private VBox view;
	private static PaneForUser user = new PaneForUser();

	public PaneForBusiness(){
		businessPane = new HBox();
	}

	public PaneForBusiness(String username, String password) {
		this.username = username;
		this.password = password;
		businessPane = new HBox();
	}

	public HBox getCreatePane() {
		businessPane.setPadding(new Insets(145,0,0,290));
		businessPane.getChildren().add(view());
		return businessPane;
	}

	public Pane getUpdatePane() {
		businessPane.getChildren().add(updateView());
		return businessPane;
	}

	private VBox view() {
		view = new VBox(5);
		view.getChildren().addAll(user.getCreatePane(), name(), registerButton());
		view.setPadding(new Insets(5));
		return view;
	}

	private VBox updateView() {
		view = new VBox(5);
		view.getChildren().addAll(user.getUpdatePane(), name(), updateButton());
		view.setPadding(new Insets(5));
		updateTextFields();
		return view;
	}

	private HBox name() {
		HBox name = new HBox(5);
		Label nameLabel = new Label("Club Name:\t\t");
		nameField = new TextField();
		name.getChildren().addAll(nameLabel, nameField);
		return name;
	}
	
	private void error(String message){
		if(view.getChildren().contains(error)){
			error.setText(message);
		} else {
			error = new Label(message);
			error.setTextFill(Color.web("#FF0000"));
			view.getChildren().add(error);
		}
	}
	
	public Boolean testFields(){
		if(user.testFields() || 
				nameField.getText().isEmpty()){
			error("Must Enter All Fields!");
			return false;
			
		} else {
			return true;
		}
	}

	private Button registerButton() {
		Button registerButton = new Button("Register");

		registerButton.setOnAction(e -> {
			SignInUp register = new SignInUp();
			register.registerBusiness(username, password);
		});

		return registerButton;
	}

	private Button updateButton() {
		Button updateButton = new Button("Update");

		updateButton.setOnAction(e -> {
			saveIt();
			MainWindow main = new MainWindow();
			PrimaryView.changePane(main.getBusinessWindow());
		});

		return updateButton;
	}

	public void saveIt(){
		user.saveIt();
		Current.getBusiness().setName(nameField.getText());
		IO.saveUsers();
	}

	private void updateTextFields(){
		nameField.setText(Current.getBusiness().getName());
	}

	public static PaneForUser getUser(){
		return user;
	}



}
