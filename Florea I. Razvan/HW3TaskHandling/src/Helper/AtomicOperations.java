package Helper;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicOperations {
	public AtomicInteger totalWaitingTime;
	public AtomicInteger numberOfTasks;
	
	
	public AtomicOperations(){
		totalWaitingTime = new AtomicInteger(0);
		numberOfTasks = new AtomicInteger(0);
	}
	
}
