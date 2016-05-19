package order.management.main;

import order.management.IO.LogInFrame;

public class App implements Runnable{

	public LogInFrame logInFrame;
	
	public App()
	{
		logInFrame = new LogInFrame();
	}
	@Override
	public void run() {
		new Controller(logInFrame);
	}

}
