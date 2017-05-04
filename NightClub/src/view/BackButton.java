package view;

import controller.Current;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class BackButton {
	
	private Button back;
	private Pane previous;
	
	public BackButton() {
		previous = Current.getPreviousPane();
	}

	public Button getButton(){
		back = new Button("Back");
		back.setOnAction(e -> {
			PrimaryView.anchor(previous);;
		});
		return back;
	}
}
