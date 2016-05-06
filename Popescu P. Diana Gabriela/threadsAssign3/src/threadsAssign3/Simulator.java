package threadsAssign3;

public class Simulator implements Runnable{
	private static int minArrivalInterval = 1; //minimum arrival interval between clients
	private static int maxArrivalInterval = 1; //maximum arrival interval between clients
	private static int threshold = 3;
	private static int maxNrOfServers = 10;
	private static int finishTime = 10;
	private static int minProcessTime = 1;
	private static int maxProcessTime = 5;

	private static float averageWaitingTime = 0;
	private static int averageWaitingTimeCounter = 0;
	private static float serviceTime = 0;
    private static float serviceTimeCounter = 0;
	private static int peakHour = 0;
	private static int peakHourTasks = 0;

	private static int currentTime = 0;
	private static int timeToGenerateTask = 1;

	private static Scheduler scheduler;
	private static SimulatorFrame simulatorFrame;

	public static void main(String[] args) { //MAIN
		simulatorFrame = new SimulatorFrame();
	}
	
	@Override
	public void run() {
		while (currentTime < finishTime) {
			currentTime++;
			if (timeToGenerateTask == currentTime) {
				timeToGenerateTask += (int) (Math.random() * (maxArrivalInterval - minArrivalInterval) + minArrivalInterval);
				int processTime = (int) (Math.random() * (maxProcessTime - minProcessTime) + minProcessTime);
				Task task = new Task(currentTime, processTime);
				EventsLog.log(String.format(task.toString() + " arrived"));
				scheduler.dispatchTaskOnServer(task);
                computeServiceTime(processTime);
			}
			simulatorFrame.displayData(scheduler.getTasksFromScheduler());
			simulatorFrame.showCurrentTime();
			computePeakHour();
			sleep();
		}
		while(!scheduler.isDone()) {
			finishTasks();
		}
		simulatorFrame.displayData(scheduler.getTasksFromScheduler());
		simulatorFrame.showReports(averageWaitingTime, serviceTime, peakHour);
		sleep();
		EventsLog.close(averageWaitingTime, serviceTime, peakHour);
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void finishTasks() {
		currentTime++;
		simulatorFrame.displayData(scheduler.getTasksFromScheduler());
		simulatorFrame.showCurrentTime();
		sleep();
	}

	public static int getThreshold() {
		return threshold;
	}

	public static void setThreshold(int threshold) {
		Simulator.threshold = threshold;
	}

	public static int getMaxNrOfServers() {
		return maxNrOfServers;
	}

	public static void setMaxNrOfServers(int maxNrOfServers) {
		Simulator.maxNrOfServers = maxNrOfServers;
	}

	public static void setMinArrivalInterval(int minArrivalInterval) {
		Simulator.minArrivalInterval = minArrivalInterval;
	}

	public static void setMaxArrivalInterval(int maxArrivalInterval) {
		Simulator.maxArrivalInterval = maxArrivalInterval;
	}

	public static void setFinishTime(int finishTime) {
		Simulator.finishTime = finishTime;
	}

	public static void setMinProcessTime(int minProcessTime) {
		Simulator.minProcessTime = minProcessTime;
	}

	public static void setMaxProcessTime(int maxProcessTime) {
		Simulator.maxProcessTime = maxProcessTime;
	}

	public static void setScheduler(Scheduler scheduler) {
		Simulator.scheduler = scheduler;
	}

	public static void computeAverageWaitingTime(int arrivalTime){
		int waitTime = currentTime - arrivalTime;
		averageWaitingTime = ((averageWaitingTimeCounter * averageWaitingTime) + waitTime) / ++averageWaitingTimeCounter;
	}

    public static void computeServiceTime(int processTime){
        serviceTime = ((serviceTimeCounter * serviceTime) + processTime) / ++serviceTimeCounter;
    }

	private void computePeakHour(){
		if (scheduler.nrOfTasks() > peakHourTasks) {
			peakHour = currentTime;
			peakHourTasks = scheduler.nrOfTasks();
		}
	}

	public static int getCurrentTime() {
		return currentTime;
	}

	public static void resetParameters() {
		setMinArrivalInterval(1);
		setMaxArrivalInterval(1);
		setMinProcessTime(1);
		setMaxProcessTime(5);
		setMaxNrOfServers(10);
		setThreshold(3);
		setFinishTime(10);
		averageWaitingTime = 0;
		averageWaitingTimeCounter = 0;
		serviceTime = 0;
        serviceTimeCounter = 0;
		peakHour = 0;
		peakHourTasks = 0;
		currentTime = 0;
		timeToGenerateTask = 1;
		Scheduler.resetNrOfOpenServers();
		Scheduler.resetServers();
	}
}
