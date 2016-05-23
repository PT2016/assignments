package Application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Checkout implements Runnable {

	private BlockingQueue<Client> queue = new LinkedBlockingQueue<Client>();
	private int totalTime;
	private int nrOfClients;
	private int checkoutNo;
	
	public Checkout(int checkoutNo){
		this.checkoutNo=checkoutNo;
	}
	
	public void fileWrite(String s){
		try(FileWriter fw = new FileWriter("log.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(s);

			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void addClient(Client c){
		queue.add(c);
		fileWrite("Client #"+c.getNoId()+" added to the checkout "+checkoutNo+" arrived at "+c.getArrivalTime()+" carrying "+c.getProcessingTime()+" items.");
		System.out.println("Client #"+c.getNoId()+" added to the chekout "+checkoutNo+" arrived at "+c.getArrivalTime()+" carrying "+c.getProcessingTime()+" items.");
	}
	
	@Override
	public void run(){
		while(true){
			try{
				Client c = queue.take();
				fileWrite("Checkout #"+checkoutNo+": is processing client #"+c.getNoId());
				System.out.println("Checkout #"+checkoutNo+": is processing client #"+c.getNoId());
				Thread.sleep(c.getProcessingTime()*50);
				totalTime+=c.getProcessingTime();
				nrOfClients++;
				fileWrite("Client #"+c.getNoId()+" was processed.");
				System.out.println("Client #"+c.getNoId()+" was processed.");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void computeAverageTime(){
		float average = totalTime/nrOfClients;
		fileWrite("Average waiting time for checkout#"+checkoutNo+" is: "+average);
		System.out.println("Average waiting time for checkout#"+checkoutNo+" is: "+average);
	}
	
	public int getQueueSize(){
		return queue.size();
	}
	
}
