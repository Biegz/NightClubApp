package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Pane4EventEvent;
import controller.Pane4EventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Event;
import model.EventsBag;
import view.MainWindow;
import view.Pane4Table;

public class TableTranslator {

	private TableView<Event> eventsTable;
	private ObservableList<Event> events;
	private Pane4EventListener pane4EventListener;
	private Pane4Table view;
	private EventsBag eventsBag;
	// private PaneForTable pane;

	public TableTranslator() {

	}

	public void getAllEvents(Pane4Table view) {
		this.view = view;
		view.Pane4Table(eventsBag.events);
		// MainWindow.setCenter(pane.show(eventsTable));
	}

	public void getMyEvents(Pane4Table view) {
		this.view = view;
		view.Pane4Table(eventsBag.getCurrentBusinessEvents());
	}

	public void getByZip15(Pane4Table view) {
		this.view = view;
		view.Pane4Table(eventsBag.getEventsWithin15());
	}
	
	public void getByZip50(Pane4Table view) {
		this.view = view;
		view.Pane4Table(eventsBag.getEventsWithin50());
	}
}
