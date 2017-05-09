package view;

import controller.Current;
import controller.EventController;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EstablishmentHLPane {

	private MainWindow mainWindow;
	private HBox main = new HBox();
	private VBox pane1 = new VBox();
	private VBox pane2 = new VBox();
	private VBox employeePane = new VBox();
	private HBox buttonsPane = new HBox();

	public Pane getHyperlinkPane() {

		pane1.getChildren().addAll(getEventsHl(), getFinanceHl(), getEmployeeHl());
		main.getChildren().addAll(pane1);

		main.setBorder(
				new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

		return main;

	}
	
	

	public Hyperlink getMyEventsHl() {
		Pane4Events pane4Events = new Pane4Events();
		EventController eventController = new EventController(pane4Events);
		System.out.println("Event Controller object Just Created!");

		Hyperlink viewMyHl = new Hyperlink("View My Events");

		viewMyHl.setOnAction(e -> {
			main.getChildren().clear();
			pane2.getChildren().clear();
			buttonsPane.getChildren().clear();

			buttonsPane.getChildren().addAll(pane4Events.getAddButton(), pane4Events.getDeleteButton(),
					pane4Events.getUpdateButton(), pane4Events.getRefreshButton());
			pane2.getChildren().addAll(pane4Events.getMyEventsTable(), buttonsPane);

			main.getChildren().addAll(pane1, pane2);

		});

		return viewMyHl;

	}

	public Hyperlink getAllEventsHl() {
		Pane4Events pane4Events = new Pane4Events();

		Hyperlink viewAllHl = new Hyperlink("View All Events");

		viewAllHl.setOnAction(e -> {
			main.getChildren().clear();

			pane2.getChildren().clear();

			pane2.getChildren().add(pane4Events.getTable());
			main.getChildren().addAll(pane1, pane2);

		});

		return viewAllHl;

	}
	
	public Hyperlink employeeAdd(){
		Hyperlink employeeAdd = new Hyperlink("Add Employee");
		
		employeeAdd.setOnAction( e -> {
			PaneForEmployee employee = new PaneForEmployee();
			MainWindow.setCenter(employee.getCreatePane());
		});
		
		return employeeAdd;
	}
	
	public Hyperlink employeesView(){
		Hyperlink employeesView = new Hyperlink("View Employees");
		
		employeesView.setOnAction( e -> {
			PaneForEmployees employees = new PaneForEmployees();
			MainWindow.setCenter(employees.getPane());
		});
		
		return employeesView;
	}

	public Hyperlink getEmployeeHl() {
		Hyperlink employeeHl = new Hyperlink("Employee");

		employeeHl.setOnAction(e -> {
			mainWindow.setCenter(null);
			main.getChildren().clear();
			pane2.getChildren().clear();
			pane2.getChildren().addAll(employeeAdd(), employeesView());
			main.getChildren().addAll(pane1, pane2);
		});

		return employeeHl;

	}

	public Hyperlink getFinanceHl() {
		Hyperlink financeHl = new Hyperlink("Financial Info");

		financeHl.setOnAction(e -> {
			PaneForFinancialInfo info = new PaneForFinancialInfo();
			mainWindow.setCenter(null);
			main.getChildren().clear();
			pane2.getChildren().clear();
			mainWindow.setCenter(info.getPane());

			main.getChildren().addAll(pane1);

		});

		return financeHl;

	}

	public Hyperlink getEventsHl() {
		Hyperlink eventsHl = new Hyperlink("Events");

		eventsHl.setOnAction(e -> {

			mainWindow.setCenter(null);
			main.getChildren().clear();
			pane2.getChildren().clear();

			pane2.getChildren().addAll(getAllEventsHl(), getMyEventsHl());

			main.getChildren().addAll(pane1, pane2);
		});

		return eventsHl;

	}

}
