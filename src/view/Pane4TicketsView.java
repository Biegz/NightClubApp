package view;
//package view;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import controller.CheckoutButtonEvent;
//import controller.ClickEventEvent;
//import controller.Current;
//import controller.Pane4EventEvent;
//import controller.Pane4EventListener;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableRow;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import model.Event;
//import model.EventsBag;
//import model.Ticket;
//
//public class Pane4TicketsView {
//	
//	private TableView<Event> myEventsTable;
//	private ObservableList<Event> myEvents;
//	private Pane4EventListener pane4EventListener;
//
//	
//	public Pane4TicketsView(){
//		
//	}
//	
//	public VBox ticketBox() {
//		VBox ticketBox = new VBox(10);
//		ticketBox.setPadding(new Insets(15,10,10,10));
//		ticketBox.getChildren().addAll(ticketsLabel(),getMyEventsTable());
//		return ticketBox;
//	}
//	
//	public HBox ticketsLabel() {
//		HBox pane = new HBox();
//		pane.setAlignment(Pos.BASELINE_CENTER);
//		Label ticketsLabel = new Label("Your Events");
//		pane.getChildren().addAll(ticketsLabel);
//		ticketsLabel.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 22));
//		return pane;
//	}
//	
//	public TableColumn getDateColumn() {
//		TableColumn dateColumn = new TableColumn("Date");
//		//dateColumn.impl_setWidth(80);
//		dateColumn.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("date"));
//		return dateColumn;
//	}
//
//	public TableColumn getEventNameColumn() {
//		TableColumn eventNameColumn = new TableColumn("Event Name");
//		//eventNameColumn.impl_setWidth(100);
//		eventNameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
//		return eventNameColumn;
//	}
//	
////	public TableColumn getCostColumn() {
////		TableColumn costColumn = new TableColumn("Cost");
////		costColumn.impl_setWidth(60);
////		costColumn.setCellFactory(new PropertyValueFactory<Ticket, Double>("cost"));
////		return costColumn;
////		
////	}
//	
//	
//	
//	public void setMyEventsTable(List<Event> list) {
//		myEvents = FXCollections.observableArrayList(list);
//		myEventsTable = new TableView<Event>();
//		myEventsTable.setEditable(false);
//		myEventsTable.setMaxHeight(400);
//		myEventsTable.setMaxWidth(200);
//		myEventsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//		myEventsTable.getColumns().addAll(getDateColumn(), getEventNameColumn());//, getCostColumn());
//		myEventsTable.setItems(myEvents);
//	}
//	
//	public TableView getMyEventsTable(){
//		myEventsTable.setRowFactory(e ->{
//			TableRow<Event> row = new TableRow<Event>();
//			row.setOnMouseClicked(ev ->{
//				if(ev.getClickCount() >= 1 && (!row.isEmpty())){  //set so if you double click it will delete that event, will change to delete event button soon
//					System.out.println("Detected clicks tickets");
//					Event rowData = row.getItem();
//					ClickEventEvent clickEvent = new ClickEventEvent(this,Current.getCustomer(), rowData);
//					if (pane4EventListener != null) {
//						pane4EventListener.eventRowSelected(clickEvent);
//					}
//				}
//			});
//			return row;
//		});
//		return myEventsTable;
//	}
//	
//	public void setPane4EventListener(Pane4EventListener pane) {
//		this.pane4EventListener = pane;
//	}
//		
//	
//}
