package Helpers;

import UI.Admin;
import UI.CreateNew;
import UI.MainMenu;
import UI.Customer;

public class StateManager {

	public static enum State {
		MAINMENU, CREATE, ADMIN, USER
	}
	
	public static State state = State.MAINMENU;
	public static MainMenu mainMenu = new MainMenu();
	public static CreateNew createNew = new CreateNew();
	public static Admin admin = new Admin();
	public static Customer user = new Customer();

	public static void update() {
		switch (state) {
		case MAINMENU:
			resetState();
			mainMenu.frame.setVisible(true);
			break;
		case CREATE:
			resetState();
			createNew.frame.setVisible(true);
			break;
		case ADMIN:
			resetState();
			admin.initializeStock("");
			admin.initializeOrders();
			admin.setName(Account.getName());
			admin.frame.setVisible(true);
			break;
		case USER:
			resetState();
			user.initializeStock("");
			user.setName(Account.getName());
			user.frame.setVisible(true);
			break;
		default:
			break;

		}
	}
	
	private static void resetState() {
		mainMenu.frame.setVisible(false);
		createNew.frame.setVisible(false);
		admin.frame.setVisible(false);
		user.frame.setVisible(false);
	}

	public static void setState(State newState) {
		state = newState;
	}

}
