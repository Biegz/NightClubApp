package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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

	public static void changePane(Node node){
		primaryPane.getChildren().clear();
		primaryPane.setCenter(node);
	}

	public static void anchor(Node node){
		primaryPane.getChildren().clear();
		AnchorPane anchor = new AnchorPane();
		Node temp = node;
		anchor.getChildren().addAll(temp);
		anchor.setTopAnchor(temp, height / 3);
		anchor.setLeftAnchor(temp, width / 3);
		primaryPane.setCenter(anchor);
	}

}
