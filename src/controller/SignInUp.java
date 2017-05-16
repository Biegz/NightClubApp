package controller;

import model.Address;
import model.Business;
import model.Customer;
import model.EventsBag;
import model.User;
import model.UsersBag;
import controller.IO;
import controller.tableEvents.ZipWithin15MenuEvent;
import view.MainWindow;
import view.PaneForAddress;
import view.PaneForBusiness;
import view.PaneForCustomer;
import view.PrimaryView;

public class SignInUp {

	private static TableListener tableListener;

	public SignInUp() {
		
	}

	public static void login(String username, String password) throws ClassNotFoundException {
		User logger;
		logger = UsersBag.search(username);
		//if user name exists
		if (logger != null) {
			//if hash matches username's hash
			if (logger.getPasswordHash() == hash(password)) {
				Current.setUser(logger);
				//if business, login
				if (Current.getUser() instanceof Business) {
					if(tableListener!= null){
						tableListener.allEventsLogin();
					}
					PrimaryView.changePane(MainWindow.getBusinessWindow());
				//else customer login
				} else if (Current.getUser() instanceof Customer) {
					if(tableListener!= null){
						tableListener.eventRecommendationLogin();
					}
					PrimaryView.changePane(MainWindow.getCustomerWindow());
				}
			} else {
			}
		} else {
		}
	}

	public void registerBusiness(String username, String password) {
		//Creates a business, saves it, then shows main menu
		Business created = new Business(username, hash(password));
		created.setEmail(PaneForBusiness.getUser().emailField.getText());
		created.setName(PaneForBusiness.nameField.getText());
		created.setAddress(getAddress());
		
		saveUser(created);
		if(tableListener!= null){
			tableListener.allEventsLogin();
		}
		showBusinessMainMenu();
	}

	public void registerCustomer(String username, String password) {
		//Creates a customer, saves it, then shows main menu
		Customer created = new Customer(username, hash(password));
		created.setEmail(PaneForCustomer.getUser().emailField.getText());
		created.setFirstName(PaneForCustomer.firstField.getText());
		created.setLastName(PaneForCustomer.lastField.getText());
		created.setGender(PaneForCustomer.genderField.getText());
		created.setFavGenre(PaneForCustomer.genreBox.getSelectionModel().getSelectedItem());
		created.setBirthday(PaneForCustomer.birthdayField.getValue());
		created.setAddress(getAddress());
		saveUser(created);
		if(tableListener!= null){
			tableListener.allEventsLogin();
		}
		showCustomerMainMenu();
	}

	private Address getAddress() {
		Address address = new Address();
		address.setNumber(PaneForAddress.numField.getText());
		address.setStreet(PaneForAddress.nameField.getText());
		address.setCity(PaneForAddress.cityField.getText());
		address.setState((String) PaneForAddress.stateField.getSelectionModel().getSelectedItem());
		address.setZipcode(PaneForAddress.zipcodeField.getText());
		return address;
	}
	
	private void saveUser(User created) {
		Current.setUser(created);
		UsersBag.add(created);
		IO.saveUsers();
	}

	private static void showCustomerMainMenu() {
		PrimaryView.changePane(MainWindow.getCustomerWindow());
	}

	private static void showBusinessMainMenu() {
		PrimaryView.changePane(MainWindow.getBusinessWindow());
	}

	public static double hash(String string) {
		double hash = 0;
		for (int n = 1; n - 1 < string.length(); n++) {
			//turns password into unique number in base 65,536
			hash += ((double) ((int) string.charAt(n - 1)) * (Math.pow(65536, n)));
		}
		return hash;
	}
	
	public void setTableListener(TableListener login){
		this.tableListener = login;
		}
	}


