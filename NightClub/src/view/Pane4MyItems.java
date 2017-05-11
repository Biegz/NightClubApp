package view;

import java.time.LocalDate;

import controller.CheckoutButtonEvent;
import controller.Current;
import controller.Pane4EventListener;
import controller.ReturnTicketEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Pane4MyItems {
	private Label eventLabel;
	private Label ticketLabel;
	private Label tableLabel;
	public int tableAmount;
	public int ticketAmount;
	private Pane4EventListener pane4EventListener;

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
		itemGrid.addColumn(0, getTicketLabel(), getTableLabel(), returnTicketBtn());
		//itemGrid.addColumn(1, returnTicketBtn(), returnTableBtn());
		itemGrid.setHgap(15);
		itemGrid.setVgap(20);
		return itemGrid;
	}
	
	public Label getTicketLabel() {
		return ticketLabel;
	}

	public void setTicketLabel(int amount, double cost, double total) {
		ticketAmount = amount;
		this.ticketLabel = new Label(amount+" Tickets [@: $"+cost+ "] Total: "+total);
		ticketLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
	}

	public Label getTableLabel() {
		return tableLabel;
	}

	public void setTableLabel(int amount, double cost, double total) {
		tableAmount = amount;
		this.tableLabel = new Label(amount+" Tables [@: $"+cost+ "] Total: "+total);
		ticketLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

	}
	
	public Button returnTicketBtn() {
		Button returnTicketBtn =  new Button("Return Order");
		returnTicketBtn.setOnAction(
		        new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	Stage primaryStage = new Stage();
		                final Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(primaryStage);
		                GridPane dialogVbox = new GridPane();
		                dialogVbox.setAlignment(Pos.CENTER);
		                dialogVbox.setVgap(30);
		                Button confirmBtn = new Button("Return");
		                confirmBtn.setOnAction(e-> {
		                    Stage stage = (Stage) confirmBtn.getScene().getWindow();
		                    stage.close();
		                	ReturnTicketEvent ev = new ReturnTicketEvent(this, Current.getEvent(), Current.getCustomer(), ticketAmount);
		        				if (pane4EventListener != null) {
		        					pane4EventListener.returnEventClicked(ev);
		        				}
		                });
		                Button cancelBtn = new Button("Cancel");
		                cancelBtn.setOnAction(e-> {
		                	Stage stage = (Stage) confirmBtn.getScene().getWindow();
		                    stage.close();		                
		                });
		                HBox btnBox = new HBox(15);
		                btnBox.getChildren().addAll(confirmBtn, cancelBtn);
		                dialogVbox.addColumn(0, new Label("Are you sure? "+Current.getEvent().getEventName()), btnBox);
		                
		                Scene dialogScene = new Scene(dialogVbox, 400, 320);
		                dialog.setScene(dialogScene);
		                dialog.show();
		            }
		         });/*.setOnAction(e-> {
			ReturnTicketEvent ev = new ReturnTicketEvent(this, Current.getEvent(), Current.getCustomer(), ticketAmount);
			System.out.println("IS NULL?");
				if (pane4EventListener != null) {
					System.out.println("NOT NULL");
					pane4EventListener.returnEventClicked(ev);
				}
			});*/
		return returnTicketBtn;
	}
	
	public void setPane4EventListener(Pane4EventListener pane) {
		this.pane4EventListener = pane;
	}
	
}
