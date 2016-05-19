package main;

import controller.Controller;
import models.Dictionary;
import view.View;

public class MainClass {

	public static void main(String[] args) {

		View view = new View();
		Dictionary dictionary = new Dictionary();
		Controller controller = new Controller();
		controller.addModel(dictionary);
		controller.addView(view);
		view.addController(controller);

	}

}
