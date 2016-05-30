package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import model.Task;

public class Server implements Runnable {
	private BlockingQueue<Task> queue;
	private AtomicInteger nrOfActiveTasks;

	public Server(int nrOfTasks) {
		queue = new ArrayBlockingQueue<Task>(nrOfTasks);
	}

	public void addTask(Task task) {
		queue.add(task);
		nrOfActiveTasks.incrementAndGet();
	}

	@Override
	public void run() {

	}

	public int getNumberOfActiveTasks() {
		return queue.size();
	}

	public BlockingQueue<Task> getQueue() {
		return this.queue;
	}
}
