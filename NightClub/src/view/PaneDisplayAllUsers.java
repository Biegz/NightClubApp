package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.UsersBag;
import model.model4User.User;

public class PaneDisplayAllUsers {
	
	private Pane allUsers;

	public PaneDisplayAllUsers(){
		allUsers = new Pane();
	}
	
	public Pane getPane(){
		allUsers.getChildren().add(view());
		return allUsers;
	}
	
	private VBox view(){
		VBox view = new VBox(5);
		for(User u : UsersBag.users){
			view.getChildren().add(userDisplay(u));
		}
		return view;
	}
	
	private HBox userDisplay(User u) {
		HBox userBox = new HBox(5);
		String name = u.display();
		Label nameLabel = new Label(name);
		userBox.getChildren().add(nameLabel);
		return userBox;
	}
	
}
