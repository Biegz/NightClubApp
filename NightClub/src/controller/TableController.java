package controller;

import java.time.LocalDate;

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
	
	 
		public TableController(Pane4Table view) {
			this.view = view;
			this.view2 = view2;
			
			
			System.out.println("In the controller!");
			
			view.setPane4EventListener(new Pane4EventListener() {
				
				public void rowSelected(Pane4EventEvent ev) {
					modelEvent = ev.getEvent();
					
					if (Current.getUser() instanceof Customer){
						//If user is a customer
						customerSelectedTheEvent();
					} else {
						if(Current.getBusiness() == modelEvent.getBusiness()){
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
			System.out.println("Im a customer and id like to buy a ticket");
			Current.setEvent(modelEvent);
			view2.setTicketsLeft(modelEvent.getTicketsAvailable());
			view2.setTablesLeft(modelEvent.getTablesAvailable());
			view2.setDate(modelEvent.getDate(),modelEvent.getAddress());
			view2.setEventName(modelEvent.getEventName());
			view2.setImage("http://www.thegarden.com/content/dam/msg/eventImg3/Liberty_201718_328x253.jpg");
			displayEvent();
		}
		
		private void ownerSelectedTheEvent(){
			System.out.println("Im a business and own the even");
			Pane4EventCreation pane = new Pane4EventCreation();
			MainWindow.setCenter(pane.getUpdatePane());

		}
		
		private void businessSelectedTheEvent(){
			System.out.println("Im a business and dont own the event");
			Pane4EventCreation pane = new Pane4EventCreation();
			MainWindow.setCenter(pane.getUpdatePane());

		}
		
		private void displayEvent() {
			MainWindow.setCenter(view2.gridPane());
		}
}
