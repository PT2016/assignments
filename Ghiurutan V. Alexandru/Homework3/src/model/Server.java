package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
	private BlockingQueue<Task> queue;
	private AtomicInteger serviceTimeSum;
	private AtomicInteger waitingTimeSum;
	private AtomicInteger emptyQueueTime;
	private Task currentTask;

	public Server(int nrOfTasks) {
		queue = new ArrayBlockingQueue<Task>(nrOfTasks);
		emptyQueueTime = new AtomicInteger(0);
		waitingTimeSum = new AtomicInteger(0);
		serviceTimeSum = new AtomicInteger(0);
	}

	public void addTask(Task task) {
		queue.add(task);
		serviceTimeSum.addAndGet(task.getServiceTime());
	}

	@Override
	public void run() {
		while (true) {
			try {
				currentTask = queue.take();
				currentTask.setFinishTime(TaskGenerator.getCurrentTime());
				currentTask.computeWaitingTime();
				Thread.sleep(currentTask.getServiceTime() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Task[] getTasks() {
		Task[] tasks = queue.toArray(new Task[queue.size()]);
		return tasks;
	}

	public int getNrOfTasks() {
		return queue.size();
	}
}
