package Application;

public class Main {

	public static void main(String[] args) {
		Simulate sim = new Simulate();
		Thread thread = new Thread(sim);
		thread.start();
	}
	
}
