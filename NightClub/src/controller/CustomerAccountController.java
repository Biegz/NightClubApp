package controller;

import controller.tableEvents.PastEvent;
import controller.tableEvents.UpcomingEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
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
			displayEvents(view2.getMyEventsTable());
		}
		
		public void pastEventsClicked(PastEvent ev){
			modelCustomer = ev.getCustomer();
			view2.setMyEventsTable(translator.getMyPastEvents(modelCustomer));
			displayEvents(view2.getMyEventsTable());
		}
		
//		public void editAccountClicked(EditAccountEvent ev){
//			modelCustomer = ev.getCustomer();
//			//display edit account pane
//		}
		
		
	});
	
	
}	

public void displayEvents(Node temp) {
	VBox pane = new VBox();
	pane.getChildren().add(temp);
	pane.setAlignment(Pos.TOP_LEFT);
	MainWindow.setLeft(pane);
}

public void displayEditAccount(Node temp) {
	MainWindow.setCenter(temp);
}

}

