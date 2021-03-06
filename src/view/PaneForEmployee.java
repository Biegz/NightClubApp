package view;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Current;
import controller.EmployeeEvent;
import controller.IO;
import controller.TableListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Address;
import model.Business;
import model.Employee;

public class PaneForEmployee {

	private Employee employee;
	public  TextField addressField;
	public  TextField cityField;
	public  ComboBox stateField;
	public  TextField zipField;
	private TextField tfName;
	private TextField tfTitle;
	private TextField tfSalary;
	private TextField tfSSN;
	private TextField tfEmail;
	private TextField tfPhoneNumber;
	private DatePicker dateOfBirth;
	private TextField employement;
	private ObservableList<Employee> employees;
	private TableView<Employee> employeeTable;
	private TableListener tableListener;
	private Business business;
	
	private PaneForAddress address = new PaneForAddress();
	
	public PaneForEmployee() {
		business = Current.getBusiness();
	}

	public TableColumn getNameColumn() {
		TableColumn dateColumn = new TableColumn("Name");
		dateColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		return dateColumn;
	}

	public TableColumn getTitleColumn() {
		TableColumn eventNameColumn = new TableColumn("Title");
		eventNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("title"));
		return eventNameColumn;
	}

	public void setEmployeeTable(ArrayList<Employee> temp) {
		employees = FXCollections.observableArrayList(temp);
		employeeTable = new TableView<Employee>();
		employeeTable.setEditable(false);
		employeeTable.setMaxHeight(400);
		employeeTable.setMaxWidth(250);
		employeeTable.setPlaceholder(new Label("No Employees"));
		employeeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		employeeTable.getColumns().addAll(getNameColumn(), getTitleColumn());//, getCostColumn());
		employeeTable.setItems(employees);
	}
	
	public TableView<Employee>  getEmployeeTable() {
		employeeTable.setRowFactory(e ->{
			TableRow<Employee> row = new TableRow<Employee>();
			row.setOnMouseClicked(ev ->{
				if(ev.getClickCount() >= 1 && (!row.isEmpty())){  
					Employee rowData = row.getItem();
					EmployeeEvent employeeEvent = new EmployeeEvent(this, rowData);
					if (tableListener != null) {
						tableListener.rowSelected(employeeEvent);
					}
				}
			});
			return row;
		});
		return employeeTable;
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
		createView.getChildren().addAll(name(), title(),date(), 
				salary(), ssn(), email(), phoneNumber(),employement(), buttonCreate("Create"));
		return createView;
	}
	
	private VBox updateView(){
		VBox updateView = new VBox(5);
		updateView.getChildren().addAll(address.getEmployeeUpdatePane(employee), name(), 
				title(), salary(), ssn(), email(), phoneNumber(), temp(), button("Update Info"));
		updateTextFields();
		return updateView;
	}
	
	public HBox date(){
		HBox date = new HBox(5);
		Label dateLabel = new Label("Date:");
		dateOfBirth = new DatePicker();
		dateOfBirth.setValue(LocalDate.now());
		date.getChildren().addAll(dateLabel, dateOfBirth);
		return date;
	}
	
	public HBox employement(){
		HBox e = new HBox(5);
		Label e1 = new Label("Employement status:");
		employement = new TextField();
		e.getChildren().addAll(e1, employement);
		return e;
		
	}
	
	private void updateTextFields(){
		tfName.setText(employee.getName());
		tfTitle.setText(employee.getTitle());
		tfSalary.setText(employee.getSalary());
		tfSSN.setText(employee.getSsn());
		tfEmail.setText(employee.getEmail());
		tfPhoneNumber.setText(employee.getPhoneNumber());
		//temp.getSelectionModel().select(employee.isTemp());
	}
	
	private HBox temp(){
		HBox hbox = new HBox(5);
		Label label = new Label("Temporary or Contractual: ");
		//temp = new ComboBox<>();
		//temp.getItems().addAll("Temporary","Contractual");
		hbox.getChildren().addAll(label);
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
	
//	private void setAddress(){
//		employee.getAddress().setNumber(address.numField.getText());
//		employee.getAddress().setStreet(address.nameField.getText());
//		employee.getAddress().setState((String) address.stateField.getSelectionModel().getSelectedItem());
//		employee.getAddress().setCity(address.cityField.getText());
//		employee.getAddress().setZipcode(address.zipcodeField.getText());
//	}
	
//	private void setInfo(){
//		employee.setName(tfName.getText());
//		employee.setTitle(tfTitle.getText());
//		employee.setSalary(tfSalary.getText());
//		employee.setSsn(tfSSN.getText());
//		employee.setEmail(tfEmail.getText());
//		employee.setPhoneNumber(tfPhoneNumber.getText());
//		//employee.setTemp(temp.getSelectionModel().getSelectedItem());
//		setAddress();
//	}
	
	private Button button(String title){
		Button button = new Button(title);

		button.setOnAction(e -> {
			//setInfo();
			PaneForEmployees employees = new PaneForEmployees();
			MainWindow.setCenter(employees.getPane());
		});

		return button;
	}
	
	private Button buttonCreate(String title){
		Button button = new Button(title);

		button.setOnAction(e -> {
//			
			EmployeeEvent ev = new EmployeeEvent(this, new Employee(getTitle(), getSalary(), getName(), 
					 getSSN(), 
					getPhoneNumber(), getDateOfBirth(), getEmail(), getEmployement()));
			if(tableListener!= null){
				tableListener.addEmployeeButtonClicked(ev);
			}
		});

		return button;
	}
	
	public void setTableListener(TableListener menu){
		this.tableListener = menu;
	}
	
	
	public String getTitle(){
		return tfTitle.getText();
	}
	
	public String getSalary(){
		return tfSalary.getText();
	}
	
	public String getName(){
		return tfName.getText();
	}
	
	public String getAddress(){
		return addressField.getText();
	}
	
	public String getCity(){
		return cityField.getText();
	}
	
	public String getState(){
		return stateField.getPromptText();
	}
	
	public String getZip(){
		return zipField.getText();
	}
	
	public String getSSN(){
		return tfSSN.getText();
	}
	
	public String getPhoneNumber(){
		return tfPhoneNumber.getText();
	}
	
	public LocalDate getDateOfBirth(){
		return dateOfBirth.getValue();
	}
	
	public String getEmail(){
		return tfEmail.getText();
	}
	
	public String getEmployement(){
		return employement.getText();
	}
	

	
		
}
