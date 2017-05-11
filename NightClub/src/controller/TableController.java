package controller;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.text.Text;
import model.Event;
import model.model4User.model4Customer.Customer;
import view.MainWindow;
import view.Pane4Event;
import view.Pane4EventCreation;
import view.Pane4Payment;
import view.Pane4Receipt;
import view.Pane4Table;
import view.Pane4TablesTickets;

public class TableController {

	private Pane4Table view;
	private Pane4Event view2;
	private Event modelEvent;
	private ExpandEventController expandEventController;
	
	 
		public TableController(Pane4Table view) {
			this.view = view;
			this.view2 = new Pane4Event();
			expandEventController = new ExpandEventController(view2);
			
			
			System.out.println("In the controller!");
			
			view.setPane4EventListener(new Pane4EventListener() {
				
				public void rowSelected(Pane4EventEvent ev) {
					modelEvent = ev.getEvent();
					Current.setEvent(modelEvent);
					if (Current.getUser() instanceof Customer){
						//If user is a customer
						customerSelectedTheEvent();
					} else {
						if(Current.getBusiness().getUsername().equals(modelEvent.getBusiness().getUsername())){
							//if user is a business and owns the event
							ownerSelectedTheEvent();
							
						} else {
							//if user is a business and does NOT own the event
							businessSelectedTheEvent();

						}
					}
				}
			});

		}
		
		private void customerSelectedTheEvent(){
			modelEvent = Current.getEvent();
			System.out.println("Im a customer and id like to buy a ticket");
			Current.setEvent(modelEvent);
			System.out.println(modelEvent.getTicketsAvailable());
			view2.setTicketsLeft(modelEvent.getTicketsAvailable());
			view2.setTablesLeft(modelEvent.getTablesAvailable());
			view2.setDate(modelEvent.getDate(),modelEvent.getAddress());
			view2.setEventName(modelEvent.getEventName());
			view2.setImage("http://www.thegarden.com/content/dam/msg/eventImg3/Liberty_201718_328x253.jpg");
			displayEvent(view2.getBuyTicketBtn());
		}
		
		private void ownerSelectedTheEvent(){
			System.out.println("Im a business and own the even");
			Pane4EventCreation pane = new Pane4EventCreation();
			MainWindow.setCenter(pane.getUpdatePane());

		}
		
		private void businessSelectedTheEvent(){
			modelEvent = Current.getEvent();
			System.out.println("Im a business and id like to view my competitors event");
			Current.setEvent(modelEvent);
			System.out.println(modelEvent.getTicketsAvailable());
			view2.setTicketsLeft(modelEvent.getTicketsAvailable());
			view2.setTablesLeft(modelEvent.getTablesAvailable());
			view2.setDate(modelEvent.getDate(),modelEvent.getAddress());
			view2.setEventName(modelEvent.getEventName());
			view2.setImage("http://www.thegarden.com/content/dam/msg/eventImg3/Liberty_201718_328x253.jpg");
			displayEvent(new Text(""));

		}
		
		private void displayEvent(Node n1) {
			MainWindow.setCenter(view2.gridPane(n1));
		}
}
