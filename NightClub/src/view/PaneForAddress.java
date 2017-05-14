package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Current;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Employee;

public class PaneForAddress {

	private Pane addressPane;
	private Employee employee;
	public static TextField numField;
	public static TextField nameField;
	public static TextField cityField;
	public static ComboBox stateField;
	public static TextField zipcodeField;
	private VBox view;
	private Label error;
	String[] stateArr = new String[]{
	        "Alabama", "Alaska", "Arizona", "Arkansas", "California",
	        "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
	        "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
	        "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
	        "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
	        "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
	        "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma",
	        "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
	        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
	        "Wisconsin", "Wyoming"
	    	};
	List<String> stateList = new ArrayList<String>(Arrays.asList(stateArr));
	
	
	public PaneForAddress(){
		addressPane = new Pane();
	}
	
	public Pane getCreatePane(){
		addressPane.getChildren().add(view());
		return addressPane;
	}
	
	public Pane getUpdatePane(){
		addressPane.getChildren().addAll(view());
		updateTextFields();
		return addressPane;
	}
	
//	public Pane getEmployeeCreatePane(){
//		addressPane.getChildren().add(employeeView());
//		return addressPane;
//	}
	
	public Pane getEmployeeUpdatePane(Employee employee){
		this.employee = employee;
		addressPane.getChildren().add(employeeUpdateView());
		return addressPane;
	}
	
//	private VBox employeeView(){
//		VBox view = new VBox(5);
//		view.getChildren().addAll(stNum(), stName(), city(), state(), zipcode());
//		return view;
//	}
	
	private VBox employeeUpdateView(){
		view = new VBox(5);
		view.getChildren().addAll(stNum(), stName(), city(), state(), zipcode());
		employeeUpdateInfo();
		return view;
	}
	
	private void employeeUpdateInfo(){
		numField.setText(employee.getAddress().getNumber());
		nameField.setText(employee.getAddress().getStreet());
		cityField.setText(employee.getAddress().getCity());
		stateField.getSelectionModel().select(employee.getAddress().getState());
		zipcodeField.setText(employee.getAddress().getZipcode());
	}
	
	private VBox view(){
		view = new VBox(5);
		view.getChildren().addAll(stNum(), stName(), city(), state(), zipcode());
		return view;
	}
	
	private HBox stNum(){
		HBox stNum = new HBox(5);
		Label numLabel = new Label("Street Number:\t");
		numField = new TextField();
		stNum.getChildren().addAll(numLabel, numField);
		return stNum;
	}
	
	private HBox stName(){
		HBox stName = new HBox(5);
		Label nameLabel = new Label("Street Name:\t\t");
		nameField = new TextField();
		stName.getChildren().addAll(nameLabel, nameField);
		return stName;
	}
	
	private HBox city(){
		HBox cityName = new HBox(5);
		Label cityLabel = new Label("City Name:\t\t");
		cityField = new TextField();
		cityName.getChildren().addAll(cityLabel, cityField);
		return cityName;
	}
	
	private HBox state(){
		HBox stateName = new HBox(5);
		Label stateLabel = new Label("State:\t\t\t");
		stateField = new ComboBox();
		stateField.getItems().addAll(stateList);
		stateField.getSelectionModel().select("New York");
		stateName.getChildren().addAll(stateLabel, stateField);
		return stateName;
	}
	
	private HBox zipcode(){
		HBox zipcode = new HBox(5);
		Label zipcodeLabel = new Label("Zipcode:\t\t\t");
		zipcodeField = new TextField();
		zipcode.getChildren().addAll(zipcodeLabel, zipcodeField);
		return zipcode;
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
	
	public boolean testFields(){
		if(numField.getText().isEmpty() ||
				nameField.getText().isEmpty() ||
				cityField.getText().isEmpty() ||
				stateField.getSelectionModel().isEmpty() ||
				zipcodeField.getText().isEmpty()){
			
			error("Must Enter All Fields!");
			return true;
		} else {
			return false;
		}
	}
	
	public void saveInfo(){
		Current.getUser().getAddress().setNumber(numField.getText());
		Current.getUser().getAddress().setStreet(nameField.getText());
		Current.getUser().getAddress().setCity(cityField.getText());
		Current.getUser().getAddress().setState((String) stateField.getSelectionModel().getSelectedItem());
		Current.getUser().getAddress().setZipcode(zipcodeField.getText());
	}
	
	private void updateTextFields(){
		numField.setText(Current.getUser().getAddress().getNumber());
		nameField.setText(Current.getUser().getAddress().getStreet());
		cityField.setText(Current.getUser().getAddress().getCity());
		stateField.getSelectionModel().select(Current.getUser().getAddress().getState());
		zipcodeField.setText(Current.getUser().getAddress().getZipcode());
	}
	
	
}
