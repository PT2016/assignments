package pack;

import java.util.logging.Logger;

public class MainClass {
	
	public static void main(String[] args){
		Logger logger = Logger.getLogger(MainClass.class.getName());
		Shop shop = new Shop();
		Thread shopThread = new Thread(shop);
		shopThread.start();
	}

}
