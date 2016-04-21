package models;

import java.util.ArrayList;

import parameters.Parameters;

public class Scheduler {
	private ArrayList<Server> server;
	
	
	public Scheduler(){
		server = new ArrayList<>();
		server.add(new Server());
		Thread th = new Thread(server.get(0));
		th.start();
	}
	public int getMinimumServer(){
		int min=Integer.MAX_VALUE;
		int serverIndex=0;
		int i=0;
		for (Server s:server){
		
			if ((s.getWaitingTime()<min)&&(s.getTasks().length<Parameters.clientsLoadperServer)){
				min=s.getWaitingTime();
				serverIndex=i;
			}
			i++;
		}
		return serverIndex;
	}
	public void dispatchTaskOnServer(Task t){
		
		if ((server.get(server.size()-1).getTasks().length>=Parameters.clientsLoadperServer)&&(server.size()<Parameters.maxNoServers)&&(server.size()<=Parameters.maxNoServers)){
			Server s=new Server();
			server.add(s);
			Thread th=new Thread(server.get(server.size()-1));
			th.start();
			Parameters.noOfOpenServers++;
		}
		if (server.get(getMinimumServer()).getTail()!=null){
	    if (server.get(getMinimumServer()).getTail().getFinishTime()>t.getArrivalTime())
		t.setFinishTime(server.get(getMinimumServer()).getTail().getFinishTime()+t.getServiceTime());
	    else t.setFinishTime(t.getArrivalTime()+t.getServiceTime());
		}
		else{
			t.setFinishTime(t.getArrivalTime()+t.getServiceTime());
		}
		server.get(getMinimumServer()).addTask(t);
		
		
		
	}
public ArrayList<Task[]> getCurrentTasks(){
	ArrayList<Task[]> currentTasks=new ArrayList<Task[]>();
	for (Server s:server){
		currentTasks.add(s.getTasks());
	}
	return currentTasks;
}
public ArrayList<Task[]> getAllTasks(){
	ArrayList<Task[]> allTasks=new ArrayList<Task[]>();
	for (Server s:server){
		allTasks.add(s.getAllTasks());
	}
	return allTasks;
}
	public ArrayList<Server> getServer(){
		return server;
	}
	public boolean isEmpty(){
	boolean queueIsEmpty=false;
	for (Server s:server){
		if (s.getTasks().length!=0){
			queueIsEmpty=false;
		}
	}
	return queueIsEmpty;
	}
}
