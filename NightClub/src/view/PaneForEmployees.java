package view;

import java.util.ArrayList;

import controller.Current;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Employee;
import model.model4User.model4Establishment.Business;

public class PaneForEmployees {

	private Business business;
	private ArrayList<Employee> employees;

	public PaneForEmployees() {
		business = Current.getBusiness();
		employees = business.getEmployees();
	}

	public Pane getPane(){
		Pane pane = new Pane();
		if (employees != null){
			pane.getChildren().add(view());
		} else {
			PaneForEmployee paneForEmployee = new PaneForEmployee();
			pane.getChildren().add(paneForEmployee.getCreatePane());
		}
		return pane;
	}

	private VBox view() {
		VBox view = new VBox();
		for (Employee e : employees) {
			view.getChildren().add(display(e));
		}
		return view;
	}

	private Pane display(Employee e) {
		Pane pane = new Pane();
		HBox hbox = new HBox(5);
		Label nameLabel = new Label("Name: " + e.getName());
		Label titleLabel = new Label("Title: " + e.getTitle());
		Label salaryLabel = new Label("Salary: $" + e.getSalary());
		Label ssnLabel = new Label("SSN: " + e.getSsn());
		Label emailLabel = new Label("Email: " + e.getEmail());
		Label phoneNumberLabel = new Label("Phone Number: " + e.getPhoneNumber());
		hbox.getChildren().addAll(nameLabel, titleLabel, salaryLabel, ssnLabel, emailLabel, phoneNumberLabel,
				editButton(e));
		pane.getChildren().add(hbox);
		return pane;
	}

	private Button editButton(Employee employee) {
		Button edit = new Button("Edit");

		edit.setOnAction(e -> {
			PaneForEmployee pane = new PaneForEmployee();
			MainWindow.setCenter(pane.getUpdatePane(employee));
		});

		return edit;
	}

}
