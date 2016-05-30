package Entities;

import java.util.Observable;
import java.util.Observer;

public class WordObserver implements Observer {

	
	@Override
	public void update(Observable word, Object arg1) {
		Word w = (Word) word;
		System.out.println("New word added:" + w.toString());
		
	}

}
