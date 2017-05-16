package controller;

import model.EventsBag; 
import model.UsersBag;
import controller.IO;
import controller.tableEvents.ZipWithin15MenuEvent;
import model.model4Address.Address;
import model.model4User.User;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;
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
		if (logger != null) {
			if (logger.getPasswordHash() == hash(password)) {
				Current.setUser(logger);
				if (Current.getUser() instanceof Business) {
					if(tableListener!= null){
						tableListener.allEventsLogin();
					}
					PrimaryView.changePane(MainWindow.getBusinessWindow());
					//showBusinessMainMenu();
				} else if (Current.getUser() instanceof Customer) {
					if(tableListener!= null){
						tableListener.eventRecommendationLogin();
					}
					PrimaryView.changePane(MainWindow.getCustomerWindow());

					//showCustomerMainMenu();
				}
			} else {
				System.out.println("wrong password");
			}
		} else {
			System.out.println("User name does not exist");
		}
	}

	public void registerBusiness(String username, String password) {
		Business created = new Business(username, hash(password));
		created.setEmail(PaneForBusiness.getUser().emailField.getText());
		created.setName(PaneForBusiness.nameField.getText());
		created.setAddress(getAddress());
		
		saveUser(created);
		if(tableListener!= null){
			System.out.println("not Null for login");
			tableListener.allEventsLogin();
		}
		showBusinessMainMenu();
	}

	public void registerCustomer(String username, String password) {
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
			System.out.println("not Null for login");
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


