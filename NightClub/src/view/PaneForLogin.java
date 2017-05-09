package view;

import java.io.File;

import controller.Current;

import controller.SignInUp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class PaneForLogin {

	private Pane loginPane;
	private PasswordField passwordField;
	private TextField userField;
	private ImageView image = new ImageView(new Image("images"+File.separator+"logo3.png"));


	public PaneForLogin(){
		loginPane = new Pane();

	}

	public Pane getPane(){
		loginPane.getChildren().add(launchView());
		return loginPane;
	}
	
	

	private VBox launchView(){
		VBox launchView = new VBox();
		Label singleSpace = new Label("\n");
		launchView.getChildren().addAll(getLogo(),  userInfoQuery(), passwordQuery(), buttonQuery());
		launchView.setPadding(new Insets(10));
		return launchView;
	}
	
	private HBox getTitle(){
		HBox pane = new HBox();
		Label title = new Label("Owlzzz");
		title.setFont(Font.font ("Calibri",FontPosture.ITALIC, 32));
		pane.getChildren().add(title);
		pane.setAlignment(Pos.BASELINE_CENTER);
		
		return pane;
	}
	
	private HBox getLogo(){
		HBox pane = new HBox();
		pane.setAlignment(Pos.BASELINE_CENTER);
		pane.getChildren().addAll(image);
		
		return pane;
	}

	private HBox userInfoQuery(){
		Label userLabel = new Label("Username:");
		userLabel.setFont(new Font(26));
		userField = new TextField();
		HBox userInfoQuery = new HBox();
		userInfoQuery.getChildren().addAll(userLabel, userField);
		userInfoQuery.setPadding(new Insets(5));
		return userInfoQuery;
	}

	private HBox passwordQuery(){
		Label passwordLabel = new Label("Password:");
		passwordLabel.setFont(new Font(26));
		passwordField = new PasswordField();
		HBox passwordQuery = new HBox();
		passwordQuery.getChildren().addAll(passwordLabel, passwordField);
		passwordQuery.setPadding(new Insets(5));
		return passwordQuery;
	}

	private HBox buttonQuery(){
		HBox buttonQuery = new HBox(5);
		buttonQuery.getChildren().addAll(loginButton(), registerButton());
		buttonQuery.setAlignment(Pos.BASELINE_CENTER);
		buttonQuery.setSpacing(20);
		buttonQuery.setPadding(new Insets(20));
		return buttonQuery;
	}

	private Button registerButton(){
		Button registerButton = new Button("Register");

		registerButton.setOnAction(new EventHandler <ActionEvent>() {
			@Override public void handle(ActionEvent e){
				Current.setPreviousPane(loginPane);
				PaneForRegister registerPane = new PaneForRegister();
				PrimaryView.primaryPane.getChildren().clear();
				PrimaryView.primaryPane.setCenter(registerPane.getPane());
			}
		});

		return registerButton;
	}

	private Button loginButton(){
		Button loginButton = new Button("Login");

		loginButton.setOnAction(new EventHandler <ActionEvent>() {
			@Override public void handle(ActionEvent e){
				Current.setPreviousPane(loginPane);
				SignInUp login = new SignInUp();
				try {
					login.login(userField.getText(), passwordField.getText());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		return loginButton;
	}
}
