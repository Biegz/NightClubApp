package view;

import controller.IO;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launch extends Application{

	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		IO.loadAll();
		new PrimaryView(primaryStage);
		 Application.setUserAgentStylesheet(getClass().getResource("AdjustedStyleSheet.css")
			        .toExternalForm());
		
		//Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
	}

}
