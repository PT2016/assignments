package controller;

import java.util.Observable;
import java.util.Observer;



public class NewWordObserver implements Observer {

	@Override
	public void update(Observable o, Object arg1) {
		IWord word = (IWord) o;
		System.out.println("New Word added: " + word.getName() + " with its synonim(s): " + word.getDescription());
	}

}
