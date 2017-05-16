package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrimaryView {

	private static int size = 64;	//Magnitude of how large you wish the window to be. Maintains 16:9 aspect ratio
	private static double width = 16 * size;
	private static double height = 9 * size;

	public static Stage primaryStage;
	public static Scene primaryScene;
	public static BorderPane primaryPane;
	private PaneForLogin loginPane = new PaneForLogin();

	public PrimaryView(Stage primaryStage){
		PrimaryView.primaryStage = primaryStage;
		primaryStage.setTitle("Night Club App By: Biegz, Rinaldi, and Sweeney.");
		primaryStage.setScene(primaryScene());
		anchor(loginPane.getPane());
		primaryStage.show();
	}

	private Scene primaryScene(){
		primaryScene = new Scene(primaryPane(), width, height);
		return primaryScene;
	}

	private BorderPane primaryPane(){
		primaryPane = new BorderPane();
		primaryPane.setCenter(null);
		return primaryPane;
	}

	//changes center to whatever node you give it
	public static void changePane(Node node){
		primaryPane.getChildren().clear();
		primaryPane.setCenter(node);
	}

	//Sets whatever node you give it to the center of the screen
	public static void anchor(Node node){
		primaryPane.getChildren().clear();
		AnchorPane anchor = new AnchorPane();
		Node temp = node;
		anchor.getChildren().addAll(temp);
		AnchorPane.setTopAnchor(temp, height / 5.75);
		AnchorPane.setLeftAnchor(temp, width / 3.5);
		primaryPane.setCenter(anchor);
	}
	
	public double getHeight(){
		return height;
	}
	
	public double getWidth(){
		return width;
	}

}
