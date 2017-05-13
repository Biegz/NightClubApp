package view;

import controller.Current;
import controller.TableListener;
import controller.tableEvents.PastEvent;
import controller.tableEvents.UpcomingEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CustomerHLPane {
	private MainWindow mainWindow;
	private HBox main = new HBox();
	private VBox pane1 = new VBox();
	private VBox pane2 = new VBox();
	private VBox employeePane = new VBox();
	private HBox buttonsPane = new HBox();
	
	private TableListener tableListener;

	public Pane getHyperlinkPane() {
		pane1.getChildren().addAll(getEditAccountHl(), getUpcomingEventsHl(), getPastEventsHl());
		main.getChildren().addAll(pane1);

		main.setBorder(
				new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

		return main;

	}
	
	public Hyperlink getEditAccountHl(){
		Hyperlink editAccountHl = new Hyperlink("Edit Account");
		PaneForCustomer pane = new PaneForCustomer();
		editAccountHl.setOnAction(e ->{
			main.getChildren().clear();
			pane1.getChildren().clear();
			pane2.getChildren().clear();
			
			pane2.getChildren().addAll(pane.getUpdatePane());
			pane1.getChildren().addAll(getEditAccountHl(), getUpcomingEventsHl(), getPastEventsHl());
			main.getChildren().addAll(pane1);
		});
		return editAccountHl;
	}
	
	
	public Hyperlink getUpcomingEventsHl(){
		Hyperlink upcomingEventsHl = new Hyperlink("Upcoming Events");
		upcomingEventsHl.setOnAction(e ->{
			main.getChildren().clear();
			pane1.getChildren().clear();
			pane2.getChildren().clear();
			
			pane1.getChildren().addAll(getEditAccountHl(), getUpcomingEventsHl(), getPastEventsHl());
			main.getChildren().addAll(pane1);

			UpcomingEvent ev = new UpcomingEvent(this, Current.getCustomer());
			if(tableListener!= null){
				tableListener.upcomingEventsClicked(ev);
			}
			
		});
		return upcomingEventsHl;
	}
	
	public Hyperlink getPastEventsHl(){
		Hyperlink pastEventsHl = new Hyperlink("Past Events");
		pastEventsHl.setOnAction(e ->{
			main.getChildren().clear();
			pane1.getChildren().clear();
			pane2.getChildren().clear();
			
			pane1.getChildren().addAll(getEditAccountHl(), getUpcomingEventsHl(), getPastEventsHl());
			main.getChildren().addAll(pane1);

			
			PastEvent ev = new PastEvent(this, Current.getCustomer());
			if(tableListener!= null){
				tableListener.pastEventsClicked(ev);
			}
		});
		return pastEventsHl;
	}
	
	public void setTableListener(TableListener hl){
		this.tableListener = hl;
	}
	
	
}
