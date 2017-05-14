package controller;

import controller.tableEvents.PastEvent;
import controller.tableEvents.UpcomingEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.model4User.model4Customer.Customer;
import view.CustomerHLPane;
import view.MainWindow;
import view.Pane4Table;

public class CustomerAccountController {

	private CustomerHLPane view;
	private Pane4Table view2;
	private TableTranslator translator;
	private TableController tableController;
	private Customer modelCustomer;
	private ExpandEventController expandEventController;
	
	public CustomerAccountController(CustomerHLPane view) {
		this.view = view;
		this.view2 = new Pane4Table();
		translator = new TableTranslator();
		tableController = new TableController(view2);
		expandEventController = new ExpandEventController(view2);
		
		view.setTableListener(new TableListener() {
		public void upcomingEventsClicked(UpcomingEvent ev) {
			modelCustomer = ev.getCustomer();
			view2.setMyEventsTable(translator.getMyUpcomingEvents(modelCustomer));
			Label upcoming = new Label("My Upcoming Events");
			displayEvents(view2.getMyEventsTable(), upcoming);
		}
		
		public void pastEventsClicked(PastEvent ev){
			modelCustomer = ev.getCustomer();
			view2.setMyEventsTable(translator.getMyPastEvents(modelCustomer));
			Label past = new Label("My Past Events");
			displayEvents(view2.getMyEventsTable(), past);
		}
		
//		public void editAccountClicked(EditAccountEvent ev){
//			modelCustomer = ev.getCustomer();
//			//display edit account pane
//		}
		
		
	});
	
	
}	

public void displayEvents(Node temp, Label temp2) {
	VBox pane = new VBox();
	VBox headerPane = new VBox();
	
	temp2.setFont(new Font(32));
	headerPane.getChildren().addAll(temp2);
	headerPane.setAlignment(Pos.TOP_CENTER);
	pane.setSpacing(5);
	pane.setPadding(new Insets(7.5, 0, 0, 0));
	pane.getChildren().addAll(headerPane, temp);
	
	
	MainWindow.setLeft(pane);
}

public void displayEditAccount(Node temp) {
	MainWindow.setCenter(temp);
}

}

