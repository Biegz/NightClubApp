package controller;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.text.Text;
import model.Customer;
import model.Event;
import model.EventsBag;
import view.MainWindow;
import view.Pane4Event;
import view.Pane4EventCreation;
import view.Pane4Table;

public class TableController implements Observer{

	private Pane4Table view;
	private Pane4Event view2;
	
	private TableListener tableListener;
	
	private Event modelEvent;
	private EventsBag modelBag;
	private ExpandEventController expandEventController;
	
	
	 
		public TableController(Pane4Table view) {
			this.view = view;
			this.view2 = new Pane4Event();
			
			expandEventController = new ExpandEventController(view2);
			
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
							businessSelectedTheEvent();
	
						} else {
							//if user is a business and does NOT own the event
							businessSelectedTheEvent();

						}
					}
				}
			});

		}
		
		private void customerSelectedTheEvent(){
			modelEvent.addObserver(this);
			Current.setEvent(modelEvent);
			view2.setTicketsLeft(modelEvent.getTicketsAvailable());
			view2.setTablesLeft(modelEvent.getTablesAvailable());
			view2.setDate(modelEvent.getDate(),modelEvent.getAddress().display());
			view2.setEventName(modelEvent.getEventName()+" hosted by: "+ modelEvent.getBusiness().getName());
			view2.setImage(Current.getEvent().getGenre().getImage());
			view2.setGenre(modelEvent.getGenre());
			displayEvent(view2.getBuyTicketBtn());
		}
		
		private void ownerSelectedTheEvent(){
			modelEvent.addObserver(this);
			Pane4EventCreation pane = new Pane4EventCreation();
			MenuController controller = new MenuController(pane);
			MainWindow.setCenter(pane.getUpdatePane());

		}
		
		private void businessSelectedTheEvent(){
			modelEvent.addObserver(this);
			Current.setEvent(modelEvent);
			view2.setEventName(modelEvent.getEventName()+" hosted by: "+ modelEvent.getBusiness().getName());
			view2.setTicketsLeft(modelEvent.getTicketsAvailable());
			view2.setTablesLeft(modelEvent.getTablesAvailable());
			view2.setDate(modelEvent.getDate(),modelEvent.getAddress().display());
			view2.setImage(Current.getEvent().getGenre().getImage());
			view2.setGenre(modelEvent.getGenre());
			displayEvent(new Text(""));

		}
		

		
		private void displayEvent(Node n1) {
			MainWindow.setCenter(view2.gridPane(n1));
		}

		@Override
		public void update(Observable event, Object arg1) {
			redisplay();
		}
		
		private void redisplay(){
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
}
