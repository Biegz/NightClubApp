package view;

import controller.Current;
import controller.IO;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Employee;
import model.model4User.model4Establishment.Business;

public class PaneForEmployee {

	private Business business;
	private PaneForAddress address = new PaneForAddress();
	private Employee employee;
	private TextField tfName;
	private TextField tfTitle;
	private TextField tfSalary;
	private TextField tfSSN;
	private TextField tfEmail;
	private TextField tfPhoneNumber;
	private ComboBox<String> temp;
	
	public PaneForEmployee(){
		business = Current.getBusiness();
	}
	
	public Pane getCreatePane(){
		Pane pane = new Pane();
		pane.getChildren().add(createView());
		return pane;
	}
	
	public Pane getUpdatePane(Employee employee){
		this.employee = employee;
		Pane pane = new Pane();
		pane.getChildren().add(updateView());
		return pane;
	}
	
	private VBox createView(){
		VBox createView = new VBox(5);
		createView.getChildren().addAll(address.getCreatePane(), name(), title(), 
				salary(), ssn(), email(), phoneNumber(), temp(), buttonCreate("Create"));
		return createView;
	}
	
	private VBox updateView(){
		VBox updateView = new VBox(5);
		updateView.getChildren().addAll(address.getEmployeeUpdatePane(employee), name(), 
				title(), salary(), ssn(), email(), phoneNumber(), temp(), button("Update Info"));
		updateTextFields();
		return updateView;
	}
	
	private void updateTextFields(){
		tfName.setText(employee.getName());
		tfTitle.setText(employee.getTitle());
		tfSalary.setText(employee.getSalary());
		tfSSN.setText(employee.getSsn());
		tfEmail.setText(employee.getEmail());
		tfPhoneNumber.setText(employee.getPhoneNumber());
		temp.getSelectionModel().select(employee.isTemp());
	}
	
	private HBox temp(){
		HBox hbox = new HBox(5);
		Label label = new Label("Temporary or Contractual: ");
		temp = new ComboBox<>();
		temp.getItems().addAll("Temporary","Contractual");
		hbox.getChildren().addAll(label, temp);
		return hbox;
	}
	
	private HBox title(){
		HBox hbox = new HBox(5);
		Label label = new Label("Title: ");
		tfTitle = new TextField();
		hbox.getChildren().addAll(label, tfTitle);
		return hbox;
	}
	
	private HBox name(){
		HBox hbox = new HBox(5);
		Label label = new Label("Name: ");
		tfName = new TextField();
		hbox.getChildren().addAll(label, tfName);
		return hbox;
	}
	
	private HBox salary(){
		HBox hbox = new HBox(5);
		Label label = new Label("Salary: ");
		tfSalary = new TextField();
		hbox.getChildren().addAll(label, tfSalary);
		return hbox;
	}
	
	private HBox ssn(){
		HBox hbox = new HBox(5);
		Label label = new Label("SSN: ");
		tfSSN = new TextField();
		hbox.getChildren().addAll(label, tfSSN);
		return hbox;
	}
	
	private HBox email(){
		HBox hbox = new HBox(5);
		Label label = new Label("Email: ");
		tfEmail = new TextField();
		hbox.getChildren().addAll(label, tfEmail);
		return hbox;
	}
	
	private HBox phoneNumber(){
		HBox hbox = new HBox(5);
		Label label = new Label("Phone Number: ");
		tfPhoneNumber = new TextField();
		hbox.getChildren().addAll(label, tfPhoneNumber);
		return hbox;
	}
	
	private void setAddress(){
		employee.getAddress().setNumber(address.numField.getText());
		employee.getAddress().setStreet(address.nameField.getText());
		employee.getAddress().setState((String) address.stateField.getSelectionModel().getSelectedItem());
		employee.getAddress().setCity(address.cityField.getText());
		employee.getAddress().setZipcode(address.zipcodeField.getText());
	}
	
	private void setInfo(){
		employee.setName(tfName.getText());
		employee.setTitle(tfTitle.getText());
		employee.setSalary(tfSalary.getText());
		employee.setSsn(tfSSN.getText());
		employee.setEmail(tfEmail.getText());
		employee.setPhoneNumber(tfPhoneNumber.getText());
		employee.setTemp(temp.getSelectionModel().getSelectedItem());
		setAddress();
	}
	
	private Button button(String title){
		Button button = new Button(title);

		button.setOnAction(e -> {
			setInfo();
			PaneForEmployees employees = new PaneForEmployees();
			MainWindow.setCenter(employees.getPane());
		});

		return button;
	}
	
	private Button buttonCreate(String title){
		Button button = new Button(title);

		button.setOnAction(e -> {
			employee = new Employee();
			setInfo();
			business.addEmployee(employee);
			IO.saveUsers();
			MainWindow.setCenter(null);
		});

		return button;
	}
	
	
		
}
