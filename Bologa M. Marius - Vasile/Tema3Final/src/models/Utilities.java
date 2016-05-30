package models;

import javax.swing.JOptionPane;

import views.MainFrame;

public class Utilities {
	public static void showResultsPopUp(TaskScheduler scheduler, MainFrame f) {
		int sum=0;
		for (int i = 0; i < scheduler.getServers().length; i++) {
			sum+=scheduler.getAverageServiceTime()[i];
		}
		String result = new String(
				"Simulation time: " + scheduler.getSimulationTime() + " seconds" + "\nAverage waiting time: "
						+ ((float) (TaskScheduler.getWaitingTime()+(float)sum)/(float)scheduler.getNrOfTasks()) + "\nRush hour: " + scheduler.getMaxRushHour());
		JOptionPane.showMessageDialog(null, "Simulation is over!");
		appendToLog(scheduler, "\n\n---->RESULTS<----\n" + result);
		for (int i = 0; i < scheduler.getServers().length; i++) {
			String r = new String("\nEmpty server time for server #" + i + " is: " + (scheduler.getEmptyTime()[i]));
			appendToLog(scheduler, r);
		}
		for (int i = 0; i < scheduler.getServers().length; i++) {
			String res = new String(
					"\nService time for server #" + i + " is: " + (scheduler.getAverageServiceTime()[i]));
			sum+=scheduler.getAverageServiceTime()[i];
			appendToLog(scheduler, res);
		}
		f.setLog(scheduler.getLogText());
	}

	public static void setLogText(TaskScheduler scheduler, String s) {
		synchronized (scheduler.textLog) {
			scheduler.textLog = s;
			scheduler.getFrame().setLog(scheduler.textLog);
		}
	}

	public static void appendToLog(TaskScheduler scheduler, String s) {
		synchronized (scheduler.textLog) {
			scheduler.textLog += s;
		}
	}

	public static Server getOptimalServer(TaskScheduler scheduler) {
		Server[] servers = scheduler.getServers();
		Server optimalServer = servers[0];
		if (servers.length > 1) {
			int min = servers[0].queueSize();
			for (Server curr : servers) {
				if (curr.getRunFlag() != false) {
					if (curr.queueSize() < min) {
						min = curr.queueSize();
						optimalServer = curr;
					}
				}
			}
		}
		return optimalServer;
	}
}
