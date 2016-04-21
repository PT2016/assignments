package models;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

	private Task tail;
	private BlockingQueue<Task> bq;
	private BlockingQueue<Task> allTasks;
	private AtomicInteger waitingTime;

	public Server() {
		waitingTime = new AtomicInteger(0);
		bq = new LinkedBlockingQueue<>();
		 allTasks= new LinkedBlockingQueue<>();
	}

	@Override
	public void run() {
		while (true) {
			Task t;
			try {
				t = bq.take();
				Thread.sleep(t.getServiceTime()*1000);
				waitingTime.addAndGet((-1) * t.getServiceTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
   public int getWaitingTime(){
	   return waitingTime.get();
   }
	public void addTask(Task t) {
		bq.add(t);
		allTasks.add(t);
		tail=t;
		waitingTime.addAndGet(t.getServiceTime());

	}
	
	public Task[] getTasks(){
		
		Task[] task = new Task[bq.size()];
		bq.toArray(task);
		
		return task;
	}
public Task[] getAllTasks(){
		
		Task[] allTaskArray = new Task[allTasks.size()];
		allTasks.toArray(allTaskArray );
		
		return allTaskArray ;
	}
	public boolean isEmpty(){
		return bq.isEmpty();
	}
	public Task getTail(){
		return tail;
	}
	
}
