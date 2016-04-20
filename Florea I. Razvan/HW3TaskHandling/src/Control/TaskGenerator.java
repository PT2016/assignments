package Control;

import java.util.Random;

import Entities.Task;

public class TaskGenerator {

	private Random random;

	public Task generateTask (int minArrival, int maxArrival, int minService, int maxService) {
		
		random = new Random();
		
		int arrivalTime = minArrival + random.nextInt(maxArrival - minArrival);
		int serviceTime = minService + random.nextInt(maxService - minService);

		return (new Task(arrivalTime, serviceTime));
	}
}
