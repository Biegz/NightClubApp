package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Pane4MenuBar {

	private HBox pane;
	private MenuBar menuBar;
	
	
	public Pane4MenuBar(){
        menuBar = new MenuBar();
		
		//creates menu items
		Menu file = new Menu("File");
		Menu edit = new Menu("Edit");
		Menu help = new Menu("Find");

		// Create file menu items
		MenuItem itemSave = new MenuItem("Save");
		MenuItem itemOpen = new MenuItem("Open");

		// creates edit menu items
		MenuItem itemAdd = new MenuItem("Add");
		MenuItem itemDelete = new MenuItem("Delete");
		MenuItem itemUpdate = new MenuItem("Update");
		
		//creates help menu items
		MenuItem itemSearch = new MenuItem("Search");
		MenuItem itemManual = new MenuItem("Manual");
		
		//Assigning menu items to their appropriate sub menu items
		file.getItems().addAll(itemSave, itemOpen);
		edit.getItems().addAll(itemAdd, itemDelete, itemUpdate);
		help.getItems().addAll(itemSearch, itemManual);
		
		
		menuBar.getMenus().addAll(file, edit, help);
		
		
		
	}
	
	public Pane getPane(){
		return pane;
	}
	
	
	
	public MenuBar getMenu(){
		return menuBar;
	}
}
