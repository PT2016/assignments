package models;

import java.util.concurrent.ArrayBlockingQueue;

public class Server implements Runnable {
	private ArrayBlockingQueue<Task> tasks;
	private volatile int totalEmptyTime = 0;
	private TaskScheduler scheduler;
	private volatile Boolean runFlag = true;
	private int ID;

	public Server(int id, TaskScheduler scheduler, int size) {
		this.ID = id;
		this.scheduler = scheduler;
		this.tasks = new ArrayBlockingQueue<>(size);
	}

	@Override
	public void run() {
		while (runFlag) {
			if (!tasks.isEmpty()) {
				try {
					Thread.sleep(tasks.peek().getServingTime() * 1000);
					if (tasks.peek() != null) {
						Utilities.appendToLog(scheduler,
								"<-- Task " + tasks.peek() + " left at:" + (scheduler.getCurrentCycle() - 1) + "\n");
						TaskScheduler.addWaitingTime(scheduler.getCurrentCycle() - tasks.peek().getArrivalTime()
								+ tasks.peek().getServingTime());
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					try {
						tasks.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				totalEmptyTime++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Boolean getRunFlag() {
		return runFlag;
	}

	public void stopExecution() {
		synchronized (runFlag) {
			runFlag = false;
		}
	}

	public int queueSize() {
		synchronized (tasks) {
			return tasks.size();
		}
	}

	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	public synchronized int getTotalWaitingTime() {
		return totalEmptyTime;
	}

	public Task[] getTasks() {
		Task[] t = new Task[tasks.size()];
		return tasks.toArray(t);
	}

	public void addTasks(Task t) {
		try {
			tasks.put(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteTasks() {
		try {
			tasks.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isAlive() {
		return runFlag;
	}

	public int getID() {
		return ID;
	}
}
