package q;

import java.util.Scanner;

public class Simulator implements Runnable {
	public static final int NR_SERVERS = 3;
	private int runTime;
	private int maxProcessingTime;
	private int minProcessingtime;
	private Queue[] q = new  Queue[NR_SERVERS];
	
	public Simulator() {
		Read();
		for (int i = 0; i < NR_SERVERS; i++) {
			q[i] = new  Queue(i);
		}

	}

	public void Read(){
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter the minimum processing time of a task");
		minProcessingtime=scan.nextInt();
		System.out.println("Enter the maximum processing time of a task");
		maxProcessingTime=scan.nextInt();
		System.out.println("Enter the amount of time you wish this application to simulate");
		runTime=scan.nextInt();
		scan.close();
	}
	
	public static void main(String[] args) {
		Simulator sim = new Simulator();
		Thread th = new Thread(sim);
		th.start();
	}

	public int selectQueue() {
		int min = 0;
		for (int i = 0; i < NR_SERVERS - 1; i++) {
			if (q[NR_SERVERS - i - 1].getNewServer().isEmpty()) {
				return NR_SERVERS - i - 1;
			} else if (q[i].getNewServer().queueSize() > q[i + 1]
					.getNewServer().queueSize()) {
				min = i + 1;
			}
		}
		return min;
	}


	public void run() {
		int currentTime = 0;
		int sel;
		while (currentTime < runTime) {
			int processTime =  minProcessingtime + (int) (Math.random()*(maxProcessingTime - minProcessingtime));
			Task t = new Task(currentTime, processTime, currentTime);
			sel = selectQueue();
			q[sel].addTask(t);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime++;
		}


		for (int i = 0; i < NR_SERVERS; i++) {
			System.out.println("Average waiting time for queue: "+i+" is "+q[i].getAverageTime());
			System.out.println("Average service time for Server: "+i+" is "+q[i].getNewServer().getAverageServiceTime());
		}
		System.exit(0);
	}
}
