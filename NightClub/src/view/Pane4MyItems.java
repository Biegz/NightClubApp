package view;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Pane4MyItems {
	private Label eventLabel;
	private Label ticketLabel;
	private Label tableLabel;
	
	public Pane4MyItems() {
		
	}
	
	public GridPane mainGrid() {
		GridPane mainGrid = new GridPane();
		mainGrid.setPadding(new Insets(15,10,15,10));
		mainGrid.addColumn(0, headerLabel(), getEventLabel(), new Label(), itemGrid());
		
		return mainGrid;
	}

	public Label headerLabel() {
		Label headerLabel = new Label("Your tickets and tables");
		headerLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 26));
		//headerLabel.setPadding(new Insets(10,10,15,10));
		return headerLabel;
	}
	
	public Label getEventLabel() {
		return eventLabel;
	}

	public void setEventLabel(String name, LocalDate date) {
		this.eventLabel = new Label("for "+name+" on "+ date.getDayOfMonth()+ " "+date.getMonth()+" "+date.getYear());
		eventLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 26));
	}


	public GridPane itemGrid() {
		GridPane itemGrid = new GridPane();
		itemGrid.addColumn(0, getTicketLabel(), getTableLabel());
		itemGrid.addColumn(1, returnTicketBtn(), returnTableBtn());
		itemGrid.setHgap(15);
		itemGrid.setVgap(20);
		return itemGrid;
	}
	
	public Label getTicketLabel() {
		return ticketLabel;
	}

	public void setTicketLabel(int amount, double cost, double total) {
		this.ticketLabel = new Label(amount+" Tickets [@: $"+cost+ "] Total: "+total);
		ticketLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
	}

	public Label getTableLabel() {
		return tableLabel;
	}

	public void setTableLabel(int amount, double cost, double total) {
		this.tableLabel = new Label(amount+" Tables [@: $"+cost+ "] Total: "+total);
		ticketLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

	}
	
	public Button returnTicketBtn() {
		Button returnTicketBtn =  new Button("Return Tickets");
		return returnTicketBtn;
	}
	
	public Button returnTableBtn() {
		Button returnTableBtn =  new Button("Return Tables");
		return returnTableBtn;
	}
	
	
}
