package view;

import java.time.LocalDate;

import controller.Current;
import controller.IO;
import controller.MenuController;
import controller.SignInUp;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Genre;

public class PaneForCustomer {

	private HBox customerPane;
	public static TextField firstField;
	public static TextField lastField;
	public static DatePicker birthdayField;
	public static TextField genderField;
	public static ComboBox<Genre> genreBox;
	private String username;
	private String password;
	private static PaneForUser user = new PaneForUser();
	private Label error;
	private VBox view;

	public PaneForCustomer(String username, String password) {
		this.username = username;
		this.password = password;
		customerPane = new HBox();
	}
	
	public PaneForCustomer(){
		customerPane = new HBox();
		
	}

	public HBox getCreatePane() {
//		customerPane.setPadding(new Insets(145,0,0,290));
		customerPane.getChildren().add(view());
		return customerPane;
	}

	public Pane getUpdatePane() {
		customerPane.getChildren().add(updateView());
		return customerPane;

	}
	
	public Pane getCustomerEditPane() {
		customerPane.getChildren().add(updateView());
		return customerPane;

	}

	private VBox updateView() {
		view = new VBox(5);
		view.getChildren().addAll(user.getUpdatePane(), first(), last(), birthday(), 
				gender(), genre(), updateButton());
		view.setPadding(new Insets(5));
		updateTextFields();
		return view;
	}

	private VBox view() {
		view = new VBox(5);
		view.getChildren().addAll(user.getCreatePane(), first(), last(), birthday(), gender(), 
				genre(), registerButton());
		view.setPadding(new Insets(5));
		return view;
	}
	
	private HBox birthday(){
		HBox hbox = new HBox(5);
		birthdayField = new DatePicker();
		birthdayField.setPromptText("Select a date");
		birthdayField.setValue(LocalDate.now().minusYears(18));
		hbox.getChildren().addAll(birthdayField);
		return hbox;
	}
	
	private HBox genre(){
		HBox genre = new HBox(5);
		Label genreLabel = new Label("Favorite Genre:\t\t");
		genreBox = new ComboBox<>();
		genreBox.getItems().addAll(Genre.values());
		genre.getChildren().addAll(genreLabel, genreBox);
		return genre;
	}

	private HBox first() {
		HBox first = new HBox(5);
		Label firstLabel = new Label("First Name:\t\t");
		firstField = new TextField();
		first.getChildren().addAll(firstLabel, firstField);
		return first;
	}

	private HBox last() {
		HBox last = new HBox(5);
		Label lastLabel = new Label("Last Name:\t\t");
		lastField = new TextField();
		last.getChildren().addAll(lastLabel, lastField);
		return last;
	}

//	private HBox age() {
//		HBox age = new HBox(5);
//		Label ageLabel = new Label("Age:\t\t\t\t");
//		ageField = new TextField();
//		age.getChildren().addAll(ageLabel, ageField);
//		return age;
//	}

	private HBox gender() {
		HBox gender = new HBox(5);
		Label genderLabel = new Label("Gender:\t\t\t");
		genderField = new TextField();
		gender.getChildren().addAll(genderLabel, genderField);
		return gender;
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
	
	private void updateTextFields(){
		firstField.setText(Current.getCustomer().getFirstName());
		lastField.setText(Current.getCustomer().getLastName());
		birthdayField.setValue(Current.getCustomer().getBirthday());
		genderField.setText(Current.getCustomer().getGender());
		genreBox.getSelectionModel().select(Current.getCustomer().getFavGenre());
	}

	private Button registerButton() {
		Button registerButton = new Button("Register");

		registerButton.setOnAction(e -> {
			if(testFields()){
				SignInUp register = new SignInUp();
				MenuController menuController = new MenuController(register);
				register.registerCustomer(username, password);
			}
		});

		return registerButton;
	}

	private Button updateButton() {
		Button updateButton = new Button("Update");
		
		updateButton.setOnAction(e ->{
			if(testFields()){
				saveIt();
				MainWindow.setCenter(null);

			}
		});
		
		return updateButton;
	}
	
	public void saveIt(){
		user.saveIt();
		Current.getCustomer().setFirstName(firstField.getText());
		Current.getCustomer().setLastName(lastField.getText());
		Current.getCustomer().setGender(genderField.getText());
		Current.getCustomer().setFavGenre(genreBox.getSelectionModel().getSelectedItem());
		Current.getCustomer().setBirthday(birthdayField.getValue());
		IO.saveUsers();
		
	}
	
	public static PaneForUser getUser(){
		return user;
	}
	
	public Boolean testFields(){
		if(user.testFields() || 
				firstField.getText().isEmpty() || 
				lastField.getText().isEmpty() ||
				genderField.getText().isEmpty() ||
				genreBox.getSelectionModel().isEmpty()){
			error("Must Enter All Fields!");
			return false;
		} else if(birthdayField.getValue().isAfter(LocalDate.now())) {
			error("Your Birthday Cannot Be In The Future!");
			return false;
		} else if(birthdayField.getValue().isAfter(LocalDate.now().minusYears(18))){
			error("Must Be 18 Years or Older!");
			return false;
		} else {
			return true;
		}
	}
}
