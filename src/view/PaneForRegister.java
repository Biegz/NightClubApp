package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.UsersBag;

public class PaneForRegister {

	private Pane registerPane;
	public static TextField userField;
	public static PasswordField passwordField1;
	private PasswordField passwordField2;
	private ToggleGroup group;
	private RadioButton customerBtn;
	private RadioButton establishmentBtn;
	private Label exceptionLabel = new Label();

	public PaneForRegister() {
		registerPane = new HBox();
	}

	public Pane getPane(Node n) {
		registerPane.setPadding(new Insets(145, 0, 0, 290));
		registerPane.getChildren().add(registerBox(n));
		
		return registerPane;
	}

	private VBox registerBox(Node n) {
		BackButton back = new BackButton();
		VBox registerBox = new VBox(5);
		registerBox.getChildren().addAll(userInfoQuery(), passwordQuery(), rePasswordQuery(), userSelect(),
				continueButton(), n, back.getButton());
		registerBox.setPadding(new Insets(5));
		return registerBox;
	}

	private HBox userInfoQuery() {
		Label userLabel = new Label("Username:\t\t");
		userField = new TextField();
		HBox userInfoQuery = new HBox();
		userInfoQuery.getChildren().addAll(userLabel, userField);
		userInfoQuery.setPadding(new Insets(5));
		return userInfoQuery;
	}

	private HBox passwordQuery() {
		Label passwordLabel = new Label("Password:\t\t\t");
		passwordField1 = new PasswordField();
		HBox passwordQuery = new HBox();
		passwordQuery.getChildren().addAll(passwordLabel, passwordField1);
		passwordQuery.setPadding(new Insets(5));
		return passwordQuery;
	}

	private HBox rePasswordQuery() {
		Label passwordLabel = new Label("Re-Enter Password:\t");
		passwordField2 = new PasswordField();
		HBox passwordQuery = new HBox();
		passwordQuery.getChildren().addAll(passwordLabel, passwordField2);
		passwordQuery.setPadding(new Insets(5));
		return passwordQuery;
	}

	private HBox userSelect() {

		HBox userSelect = new HBox(5);
		group = new ToggleGroup();

		customerBtn = new RadioButton("Customer");
		customerBtn.setUserData("Customer");
		customerBtn.setToggleGroup(group);
		customerBtn.setSelected(true);

		establishmentBtn = new RadioButton("Establishment");
		establishmentBtn.setUserData("Establishment");
		establishmentBtn.setToggleGroup(group);

		userSelect.getChildren().addAll(customerBtn, establishmentBtn);
		return userSelect;
	}

	private Button continueButton() {
		Button continueButton = new Button("Continue");

		continueButton.setOnAction(e -> {

			if (testFields()) {
				if (group.getSelectedToggle().equals(establishmentBtn)){
					setBusinessView();
				} else {
					setCustomerView();
				}
			}
		});

		return continueButton;
	}
	
	private boolean testFields(){
		if (userField.getText() == null || userField.getText().trim().isEmpty()) {
			error("Must enter a username!");
			return false;
			
		} else if(UsersBag.search(userField.getText()) != null){
			error("Username already exists!");
			return false;
			
		} else if ((passwordField1.getText() == null) || passwordField1.getText().trim().isEmpty()
				|| passwordField2.getText().trim().isEmpty() || (passwordField2.getText() == null)){
			error("Must enter a password!");
			return false;

		} else if (!(passwordField1.getText().equals(passwordField2.getText()))) {
			error("Passwords must match!");
			return false;
			
		}
		return true;
	}
	
	private void error(String message){
		exceptionLabel.setTextFill(Color.web("#FF0000"));
		exceptionLabel.setText(message);
		PaneForRegister registerPane = new PaneForRegister();
		PrimaryView.primaryPane.getChildren().clear();
		PrimaryView.primaryPane.setCenter(registerPane.getPane(exceptionLabel));
	}

	public void setCustomerView(){
		PaneForCustomer customerView = new PaneForCustomer(userField.getText(), passwordField1.getText());
		PrimaryView.changePane(customerView.getCreatePane());
	}

	public void setBusinessView(){
		PaneForBusiness businessView = new PaneForBusiness(userField.getText(), passwordField1.getText());
		PrimaryView.changePane(businessView.getCreatePane());
	}

}
