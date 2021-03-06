package view;

import controller.Current;
import controller.MenuController;
import controller.TableListener;
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
	
	private TableListener tableListener;

	
	

	public Pane getHyperlinkPane() {
		pane1.getChildren().clear();
		pane1.getChildren().addAll(getEditAccount(), getFinanceHl(), getEmployeeHl());
		main.getChildren().clear();
		main.getChildren().addAll(pane1);

		main.setBorder(
				new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

		return main;

	}
	
	public Hyperlink getEditAccount(){
			
		Hyperlink editAccount = new Hyperlink("Edit Account");
		PaneForBusiness pane = new PaneForBusiness();
		editAccount.setOnAction(e ->{
			MainWindow.setCenter(pane.getUpdatePane());
		});
		return editAccount;
	}

	
	public Hyperlink employeeAdd(){
			
		Hyperlink employeeAdd = new Hyperlink("Add Employee");
		PaneForEmployee employees = new PaneForEmployee();
		MenuController controller = new MenuController(employees);
		
		employeeAdd.setOnAction( e -> {
			MainWindow.setCenter(employees.getCreatePane());
		});
		
		return employeeAdd;
	}
	
	public Hyperlink employeesView(){
				
		Hyperlink employeesView = new Hyperlink("View Employees");
		
		employeesView.setOnAction( e -> {
			PaneForEmployee employees = new PaneForEmployee();
			MenuController controller = new MenuController(employees);
			if(tableListener!= null){
				tableListener.viewEmployeeHlClicked();
			}
		});
		
		return employeesView;
	}

	public Hyperlink getEmployeeHl() {
		Hyperlink employeeHl = new Hyperlink("Employee");

		employeeHl.setOnAction(e -> {
			main.getChildren().clear();
			pane2.getChildren().clear();
			pane1.getChildren().clear();
			pane1.getChildren().addAll(getEditAccount(), getFinanceHl(), getEmployeeHl());
			pane2.getChildren().addAll(employeeAdd(), employeesView());
			main.getChildren().addAll(pane1, pane2);
		});

		return employeeHl;

	}

	public Hyperlink getFinanceHl() {
		Hyperlink financeHl = new Hyperlink("Financial Info");

		financeHl.setOnAction(e -> {
			PaneForFinancialInfo info = new PaneForFinancialInfo();
			main.getChildren().clear();
			pane1.getChildren().clear();
			pane2.getChildren().clear();
			pane2.getChildren().addAll(info.getPane());
			pane1.getChildren().addAll(getEditAccount(), getFinanceHl(), getEmployeeHl());

			main.getChildren().addAll(pane1, pane2);

		});

		return financeHl;

	}
	
	public void setTableListener(TableListener menu){
		this.tableListener = menu;
	}



}