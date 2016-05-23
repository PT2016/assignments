package Application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class Simulate implements Runnable {
	
	public static final int nrOfCheckouts=3;
	private int runTime=20;
	private int maxProcessingTime=12;
	private int minProcessingTime=3;
	private Checkout[] queue = new Checkout[nrOfCheckouts];
	Thread[] thread = new Thread[3];
	Date date = new Date();
	
	public Simulate(){
		fileWrite("\n\n");
		fileWrite(date.toString());
		fileWrite("\n\n");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the total running time: ");
		runTime=input.nextInt();
		System.out.print("Enter the minimum processing time: ");
		minProcessingTime=input.nextInt();
		System.out.print("Enter the maximum processing time: ");
		maxProcessingTime=input.nextInt();
		input.close();
		for(int i=0;i<nrOfCheckouts;i++){
			queue[i] = new Checkout(i);
			thread[i] = new Thread(queue[i]);
			thread[i].start();
		}
	}
	
	public int leastPopulatedQueue(){
		int min=999;
		int index=0;
		int[] a=new int[nrOfCheckouts];
		for(int i=0;i<nrOfCheckouts;i++){
			a[i]=queue[i].getQueueSize();
		}
		for(int i=0;i<nrOfCheckouts;i++){
			if(min>a[i]){
				min=a[i];
				index=i;
			}
		}
		return index;
		
	}
	
	public void fileWrite(String s){
		try(FileWriter fw = new FileWriter("log.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(s);

			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}
	
	public int randomProcessingTime(){
		return (minProcessingTime+(int)(Math.random()*(maxProcessingTime-minProcessingTime)));
	}
	
	@Override
	public void run() {
		int currentTime = 0;

		while(currentTime<runTime){
			Client c = new Client(currentTime,currentTime,randomProcessingTime());
			queue[leastPopulatedQueue()].addClient(c);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime++;
		}
		for(int i=0;i<nrOfCheckouts;i++){
			queue[i].computeAverageTime();
		}
		System.exit(0);
	}

}
