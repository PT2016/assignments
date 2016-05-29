package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Server has a BlockingQueue of Tasks, in order to process them in a FIFO
 * manner
 * 
 * @author Dariana Lupea
 *
 */
public class Server implements Runnable {
	private final static Logger LOGGER = Logger.getLogger(Server.class.getName());

	private BlockingQueue<Task> tasksQueue;
	private int simulationTime;
	private int currentTime;
	private int serverCode;
	private int totalServiceTime;
	private int totalWaitingTime;
	private int numberOfTasks;

	public Server(int simulationTime, int serverCode) {
		this.serverCode = serverCode;
		tasksQueue = new LinkedBlockingQueue<Task>();
		this.simulationTime = simulationTime;
		this.currentTime = 0;
		numberOfTasks = 1;

	}

	/* Returns the queue of the current server */
	public BlockingQueue<Task> getTasksQueue() {
		return tasksQueue;
	}

	/* Sets a certain queue to a Server */
	public void setTasksQueue(BlockingQueue<Task> tasksQueue) {
		this.tasksQueue = tasksQueue;
	}

	/* Returns the number of tasks from a Server */
	public int getNumberOfTasks() {
		return tasksQueue.size();
	}

	/* Check is a server has no tasks */
	public boolean isEmtpyServer() {
		return tasksQueue.isEmpty();
	}

	public void addTaskToServer(Task t, int serviceTime) {

		try {
			t.setServiceTime(t.getServiceTime() + serviceTime);
			tasksQueue.put(t);
			numberOfTasks++;
			LOGGER.info("Task added to server...");
			for (Task currTask : tasksQueue) {
				LOGGER.info(currTask + "at the server: " + serverCode);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.warning("Task cannot be added to server...");
		}
	}

	@Override
	public void run() {
		while (currentTime <= simulationTime) {

			for (Task taskInQueue : tasksQueue) {
				//
				if (taskInQueue.getServiceTime() == currentTime) {
					
					taskInQueue.setFinishTime(currentTime);
					taskInQueue.setWaitingTime();
					totalWaitingTime += taskInQueue.getWaitingTime() ;
					numberOfTasks++;
					totalServiceTime += taskInQueue.getServiceTime() ;

					try {
						tasksQueue.take();
						
					} catch (InterruptedException e) {
						e.printStackTrace();
						LOGGER.setLevel(Level.WARNING);
						LOGGER.warning("Task cannot be added to server...");
					}
				}
			}

			for (Task taskInQueue : tasksQueue) {
				LOGGER.info(taskInQueue + "at the server: " + serverCode);
				System.out.println("Server-" +serverCode +" <--- " + taskInQueue.prettyPrint() );
			}

			try {
				Thread.sleep(2000);// milisecunde
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime++;
		}

	}

	public double getAverageWaitingTime() {
		return (totalWaitingTime / numberOfTasks);
	}
	
	public double getAverageServiceTime() {
		return (totalServiceTime / numberOfTasks);
	}
}
