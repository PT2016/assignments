package model;
import java.util.Date;
import java.util.Random;

import utilities.Constants;
import view.QueuePanel;

public class ClientGenerator implements Runnable{

	private QueuesManager queuesManager;
	
	private long arrivalTime;
	
	int countArrivalTime = 0;
	private long minArrivalTime, maxArrivalTime;
	private long minServiceTime, maxServiceTime;

	private Random randGenerator;

	static int nrOfClients =0;

	static int counter = 0;
	public ClientGenerator(long minServiceTime, long maxServiceTime, long minArrivalTime, long maxArrivalTime, long arrivalTime, QueuesManager queuesManager){
		
		this.randGenerator = new  Random();
		
		this.minServiceTime = minServiceTime;
		this.maxServiceTime = maxServiceTime;
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.arrivalTime =  arrivalTime;
		
		this.queuesManager = queuesManager;
		
		this.nrOfClients ++;
		
		Thread clientGen = new Thread(this);
		clientGen.start();
		
		
	}
	public void generateClient() throws InterruptedException {
	
			String firstName, lastName;
			long serviceTime;
			
			Thread.sleep(arrivalTime *100);
			
			if (queuesManager.isOpen()) {
				
				//System.out.println("CURRENT THREAD: " +Thread.currentThread().getName());
				Date d = new Date(System.currentTimeMillis());
				
				while(countArrivalTime < arrivalTime){
					++countArrivalTime;
				}
				if (countArrivalTime == arrivalTime) {

					System.out.println("hereClientGenerator");
					
					firstName = generateFirstName();
					lastName = generateLastName();
					
					serviceTime = minServiceTime
							+ ((long) (randGenerator.nextDouble() * (maxServiceTime - minServiceTime + 1)));
					//++nrOfClients;
					System.out.println("NRCLIENTS:" + nrOfClients);
					Client client = new Client(this.nrOfClients, firstName, lastName, arrivalTime, serviceTime);
					
					queuesManager.placeClientAtAQueue(client);
				
					System.out.println(d.getHours() +":"+d.getMinutes()+":"+d.getSeconds() +" "+ client);
					
					
					
					arrivalTime = minArrivalTime
							+ ((long) (randGenerator.nextDouble() * (maxArrivalTime - minArrivalTime + 1)));
					
					this.countArrivalTime = 0;
					
					counter++;
					if(counter > 15){
						queuesManager.blockQueue1();
					}
					new ClientGenerator(this.getMinServiceTime(), this.getMaxServiceTime(),  this.getMinArrivalTime(), this.getMaxArrivalTime(),arrivalTime ,this.queuesManager);
					
				}
				
			} 
			else {

				countArrivalTime = 0;
				nrOfClients = 0;
			} 
	}
	public String generateFirstName() {

		int index = randGenerator.nextInt(Constants.FIRST_NAME.length);
		return Constants.FIRST_NAME[index];
	}

	public String generateLastName() {

		int index = randGenerator.nextInt(Constants.LAST_NAME.length);
		return Constants.LAST_NAME[index];
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public long getMinArrivalTime() {
		return minArrivalTime;
	}

	public void setMinArrivalTime(long minArrivalTime) {
		this.minArrivalTime = minArrivalTime;
	}

	public long getMaxArrivalTime() {
		return maxArrivalTime;
	}

	public void setMaxArrivalTime(long maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}

	public long getMaxServiceTime() {
		return maxServiceTime;
	}

	public void setMaxServiceTime(long maxServiceTime) {
		this.maxServiceTime = maxServiceTime;
	}

	public long getMinServiceTime() {
		return minServiceTime;
	}

	public void setMinServiceTime(long minServiceTime) {
		this.minServiceTime = minServiceTime;
	}

	public Random getRandGenerator() {
		return randGenerator;
	}

	public void setRandGenerator(Random randGenerator) {
		this.randGenerator = randGenerator;
	}

	public int getNrOfClients() {
		return nrOfClients;
	}

	public void setNrOfClients(int nrOfClients) {
		this.nrOfClients = nrOfClients;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.generateClient();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
