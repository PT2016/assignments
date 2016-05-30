package threadsAssign3;

import java.util.ArrayList;

public class Scheduler {
	private static ArrayList<Server> servers;
    private static int nrOfOpenServers = 1;

    public Scheduler() {
        createAndStartServers();
    }

    private static void createAndStartServers() {
        servers = new ArrayList<Server>(Simulator.getMaxNrOfServers());
        for (int i = 0; i < Simulator.getMaxNrOfServers(); i++) {
            Server server = new Server(i + 1);
            servers.add(server);
        }
        Thread thread = new Thread(servers.get(0));
        thread.start();
    }

    public void dispatchTaskOnServer(Task task) {
        if (servers.get(nrOfOpenServers - 1).hitThreshold() && nrOfOpenServers < Simulator.getMaxNrOfServers()) {
            EventsLog.log(String.format("Opened server %d", nrOfOpenServers));
            nrOfOpenServers++;
            Thread thread = new Thread(servers.get(nrOfOpenServers - 1));
            thread.start();
            equalizeQueues();
        }
        servers.get(getSmallestQueue()).addTask(task);
        //servers.get(0).addTask(task);
    }

    private void equalizeQueues() {
    	ArrayList<Task> tasksToMove = new ArrayList<Task>();
        int averageNrOfTasks = averageNrOfTasks();
        for (int i = 0; i < nrOfOpenServers; i++)
            if (servers.get(i).getNrOfTasks() > averageNrOfTasks)
                tasksToMove.addAll(servers.get(i).getTasksToMove(servers.get(i).getNrOfTasks() - averageNrOfTasks));
        int serverCounter = 0;
        for (Task task : tasksToMove) {
            while (servers.get(serverCounter % nrOfOpenServers).getNrOfTasks() >= averageNrOfTasks)
                serverCounter++;
            servers.get(serverCounter % nrOfOpenServers).addTask(task);
        }
    }

    private int averageNrOfTasks() {
        int averageNrOfTasks = 0;
        for (int i = 0; i < nrOfOpenServers; i++)
            averageNrOfTasks += servers.get(i).getNrOfTasks();
        averageNrOfTasks /= nrOfOpenServers;
        return averageNrOfTasks;
    }

    private int getSmallestQueue() {
        int result = 0;
        int smallestQueue = servers.get(0).getNrOfTasks();
        for (int i = 1; i < nrOfOpenServers; i++)
            if (servers.get(i).getNrOfTasks() < smallestQueue) {
                smallestQueue = servers.get(i).getNrOfTasks();
                result = i;
            }
        return result;
    }

    public ArrayList<String[]> getTasksFromScheduler() {
    	ArrayList<String[]> tasksOnServers = new ArrayList<>(10);
        for (Server server : servers) {
            tasksOnServers.add(server.getTasks());
        }
        return tasksOnServers;
    }

    public boolean isDone() {
        for (Server server : servers) {
            if (!server.isEmpty())
                return false;
        }
        return true;
    }

    public int nrOfTasks(){
        int nrOfTasks = 0;
        for (Server server : servers)
            nrOfTasks += server.getNrOfTasks();
        return nrOfTasks;
    }

    public static void resetNrOfOpenServers() {
        Scheduler.nrOfOpenServers = 1;
    }

    public static void resetServers() {
        servers = null;
        createAndStartServers();
    }
}
