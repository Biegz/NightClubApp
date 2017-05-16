package view;

import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Event;
import model.EventsBag;
import model.User;
import model.UsersBag;

public class PaneDisplayAllEvents {

	private Pane allEvents;

	public PaneDisplayAllEvents(){
		allEvents = new Pane();
	}
	
	public Pane getPane(){
		allEvents.getChildren().add(view());
		return allEvents;
	}
	
	private VBox view(){
		VBox view = new VBox(5);
		for(Event e : EventsBag.events){
			view.getChildren().add(eventDisplay(e));
		}
		return view;
	}
	
	private HBox eventDisplay(Event e) {
		HBox eventBox = new HBox(5);
		String name = e.toString();
		Label nameLabel = new Label(name);
		eventBox.getChildren().add(nameLabel);
		return eventBox;
	}
	
}
