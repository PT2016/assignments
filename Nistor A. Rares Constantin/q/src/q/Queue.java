package q;




public class Queue {
	private Server s;
	Thread th;
	private int totTime;
	private int nrTasks;
	public Queue(int i){
		totTime=0;
		nrTasks=0;
		s = new Server(i);
		th = new Thread(s);
		th.start();
	}
	
	public Server getNewServer() {
		return s;
	}
	
	public void addTask(Task t){
		
		nrTasks++;
		totTime+=t.getpTime();
		s.addTask(t);
	}
	
	
	public float getAverageTime(){
		return (float)(totTime/nrTasks);
	}
}
