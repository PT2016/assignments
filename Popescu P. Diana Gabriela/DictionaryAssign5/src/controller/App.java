package controller;

import view.DictionaryFrame;

public class App implements Runnable{

public DictionaryFrame dictionaryFrame;
	
	public App()
	{
		dictionaryFrame = new DictionaryFrame();
	}
	@Override
	public void run() {
		try {
			new Controller(dictionaryFrame);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
