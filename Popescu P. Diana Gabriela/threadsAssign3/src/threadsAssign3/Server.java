package threadsAssign3;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
	private int serverID;
	private volatile BlockingQueue<Task> blockingQueue;
	private volatile AtomicInteger waitingTime;
    private String status = "OFF";

	public Server(int serverID) {
        this.serverID = serverID;
		blockingQueue = new LinkedBlockingQueue<>();
		waitingTime = new AtomicInteger(0);
	}

	public String[] getTasks() {
        String[] result = new String[blockingQueue.size() + 1];
        result[0] = String.format("Server #%d - %s", serverID, status);
        Task[] tasks = new Task[blockingQueue.size()];
        blockingQueue.toArray(tasks);
        for (int i = 0; i < blockingQueue.size(); i++){
            result[i + 1] = tasks[i].toString();
        }
        return result;
	}

	@Override
	public void run() {
        status = "ON";
		while (true) {
			Task task;
			try {
				task = blockingQueue.take();
                Simulator.computeAverageWaitingTime(task.getArrivalTime());
                executeTask(task);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

    private synchronized void executeTask(Task task){
        try {
            EventsLog.log(String.format("Stated execution of %s", task.toString()));
            Thread.sleep(task.getProcessTime() * 1000);
            waitingTime.addAndGet((-1) * task.getProcessTime());
            EventsLog.log(String.format("Ended execution of %s", task.toString()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	public void addTask(Task task) {
        EventsLog.log(String.format("%s added to server %d", task.toString(), serverID));
		blockingQueue.add(task);
		waitingTime.addAndGet(task.getProcessTime());
	}
	
	public boolean isEmpty() {
		return blockingQueue.isEmpty();
	}

    public int getNrOfTasks(){
        return blockingQueue.size();
    }

    public boolean hitThreshold() {
        return getNrOfTasks() >= Simulator.getThreshold();
    }

    public ArrayList<Task> getTasksToMove(int nrOfTasks) {
    	ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < nrOfTasks; i++)
            try {
                Task task = blockingQueue.take();
                result.add(task);
                EventsLog.log(String.format("%s moved from server %d", task.toString(), serverID));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return result;
    }
}
