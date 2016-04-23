package Model;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskGenerator {

	private int minArrival, maxArrival, minService, maxService;
	private final static Logger LOGGER = Logger.getLogger(TaskGenerator.class.getName());

	// values from input
	public TaskGenerator(int minArrival, int maxArrival, int minService, int maxService) {
		this.minArrival = minArrival;
		this.maxArrival = maxArrival;
		this.minService = minService;
		this.maxService = maxService;

	}

	/* Creates a new task with the given time */
	public Task generateTask() {
		int randomArrival = ThreadLocalRandom.current().nextInt(minArrival, maxArrival);
		int randomService = ThreadLocalRandom.current().nextInt(minService, maxService);
		Task newTask = new Task(randomArrival, randomService);
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Generated new task...");
		return newTask;
	}

}
