package queues;

import gui.*;
import java.util.Random;

public class Client implements Runnable {
	private long arrivalTime;
	private int serviceTime;
	private int id;

	public Client(int x) {
		this.id = x;
		Random rand = new Random();
		int min = Gui.getMinServTime();
		int max = Gui.getMaxServTime();
		this.serviceTime = min + rand.nextInt(max - min);
	}

	public void setArrivalTime(long arrTime) {
		this.arrivalTime = arrTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void run() {
		// Gui.showMessage("Client "+id+" is being served");
		try {
			Thread.sleep(serviceTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Gui.showMessage("Client "+id+" has been served");
	}

	public int getID() {
		return id;
	}
}
