package q;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server implements Runnable {

	private BlockingQueue<Task> bq=new LinkedBlockingQueue<Task>();
	private int serverNr;
	private int averageServiceTime=0;
	private int nrTasks =0 ;
	int nr=0;
	
	public Server(int serverNr){
		this.serverNr=serverNr;

	}
	
	public boolean isEmpty() {
		return bq.isEmpty();
	}

	
	public void addTask(Task t) {
		bq.add(t);
		
		System.out.println("Task: "+ t.getnr()+ " added to queue "+ serverNr + " with processing time  "+ t.getpTime()+" at the arival time "+ t.getaTime());
	}
	
	public int queueSize() {
		return bq.size();
	}
	
	
	@Override
	public void run() {
		while (true) {
			try {
				Task t = bq.take();
				System.out.println("Server"+serverNr +": "+"Task "+t.getnr()+" is beying processed");
				Thread.sleep(t.getpTime()*100);
				averageServiceTime=averageServiceTime+t.getpTime();
				nrTasks++;
				System.out.println("Task " + t.getnr()+" was processed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public float getAverageServiceTime() {
		return (float) (averageServiceTime/nrTasks);
	}

}
