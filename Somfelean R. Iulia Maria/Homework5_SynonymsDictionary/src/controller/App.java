package controller;

import view.*;

public class App {

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		View view = new View();
		
		//intialize all
		controller.initialize();
		view.getView();

	}

}
