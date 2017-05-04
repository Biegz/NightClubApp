package view;

import controller.Current;
import controller.IO;
import controller.SignInUp;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaneForBusiness {

	private Pane businessPane;
	private MainWindow mainWindow;
	public static TextField nameField;
	private String username;
	private String password;
	private static PaneForUser user = new PaneForUser();

	public PaneForBusiness(){
		businessPane = new Pane();
	}

	public PaneForBusiness(String username, String password) {
		this.username = username;
		this.password = password;
		businessPane = new Pane();
	}

	public Pane getCreatePane() {
		businessPane.getChildren().add(view());
		return businessPane;
	}

	public Pane getUpdatePane() {
		businessPane.getChildren().add(updateView());
		return businessPane;
	}

	private VBox view() {
		VBox view = new VBox(5);
		view.getChildren().addAll(user.getCreatePane(), name(), registerButton());
		view.setPadding(new Insets(5));
		return view;
	}

	private VBox updateView() {
		VBox updateView = new VBox(5);
		updateView.getChildren().addAll(user.getUpdatePane(), name(), updateButton());
		updateView.setPadding(new Insets(5));
		updateTextFields();
		return updateView;
	}

	private HBox name() {
		HBox name = new HBox(5);
		Label nameLabel = new Label("Name of Establishment:\t");
		nameField = new TextField();
		name.getChildren().addAll(nameLabel, nameField);
		return name;
	}

	private Button registerButton() {
		Button registerButton = new Button("Register");

		registerButton.setOnAction(e -> {
			SignInUp register = new SignInUp();
			register.registerBusiness(username, password);
		});

		return registerButton;
	}

	private Button updateButton() {
		Button updateButton = new Button("Update");

		updateButton.setOnAction(e -> {
			saveIt();
			MainWindow main = new MainWindow();
			PrimaryView.changePane(main.getBusinessWindow());
		});

		return updateButton;
	}

	public void saveIt(){
		user.saveIt();
		Current.getBusiness().setName(nameField.getText());
		IO.saveUsers();
	}

	private void updateTextFields(){
		nameField.setText(Current.getBusiness().getName());
	}

	public static PaneForUser getUser(){
		return user;
	}

	public Pane getHyperlinkPane() {

		Pane4Events pane4Events = new Pane4Events();

		HBox main = new HBox();
		VBox pane1 = new VBox();
		VBox pane2 = new VBox();
		HBox buttonsPane = new HBox();

		Hyperlink eventsHl = new Hyperlink("Events");
		Hyperlink financeHl = new Hyperlink("Financial Info");
		Hyperlink employeeHl = new Hyperlink("Employee");

		Hyperlink viewAllHl = new Hyperlink("View All Events");
		Hyperlink viewMyHl = new Hyperlink("View My Events");

		pane1.getChildren().addAll(eventsHl, financeHl, employeeHl);
		main.getChildren().addAll(pane1);

		eventsHl.setOnAction(e -> {

			mainWindow.setCenter(null);
			main.getChildren().clear();
			pane2.getChildren().clear();

			pane2.getChildren().addAll(viewAllHl, viewMyHl);

			main.getChildren().addAll(pane1, pane2);
		});

		financeHl.setOnAction(e -> {

			mainWindow.setCenter(null);
			main.getChildren().clear();
			pane2.getChildren().clear();

			main.getChildren().addAll(pane1);

		});

		employeeHl.setOnAction(e -> {

			mainWindow.setCenter(null);
			main.getChildren().clear();
			pane2.getChildren().clear();

			main.getChildren().addAll(pane1);
		});

		viewAllHl.setOnAction(e -> {
			main.getChildren().clear();

			pane2.getChildren().clear();

			pane2.getChildren().add(pane4Events.getTable());
			main.getChildren().addAll(pane1, pane2);

		});

		// make sure to change the table this action calls to set to correct
		// table (events for particular business)
		viewMyHl.setOnAction(e -> {
			main.getChildren().clear();
			pane2.getChildren().clear();
			buttonsPane.getChildren().clear();

			buttonsPane.getChildren().addAll(pane4Events.getAddButton(), pane4Events.getDeleteButton(), pane4Events.getUpdateButton());
			pane2.getChildren().addAll(pane4Events.getMyEventsTable(), buttonsPane);

			main.getChildren().addAll(pane1, pane2);

		});

		main.setBorder(
				new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

		return main;

	}

	// public Table
}
