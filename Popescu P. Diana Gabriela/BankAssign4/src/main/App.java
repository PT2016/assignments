package main;

import IO.LogInFrame;

public class App implements Runnable{

	public LogInFrame logInFrame;
	
	public App()
	{
		logInFrame = new LogInFrame();
	}
	@Override
	public void run() {
		try {
			new Controller(logInFrame);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
