package Control;

import javax.swing.JLabel;

public class DisplayControl {

	private JLabel[] tasks;
	private JLabel[] servers;

	public DisplayControl(JLabel[] tasks, JLabel[] servers) {
		this.tasks = tasks;
		this.servers = servers;
	}

	public void resetDisplay() {
		for (JLabel label : tasks)
			label.setVisible(false);
		for (JLabel label : servers)
			label.setVisible(false);
	}

	public void displayServers(int serverNumber) {
		servers[serverNumber].setVisible(true);
	}
	
	public void displayTask(String taskDescription, int taskNumber) {
		tasks[taskNumber].setText(taskDescription);
		tasks[taskNumber].setVisible(true);
	}
	
	public void hideTask(int taskNumber){
		tasks[taskNumber].setVisible(false);
	}
	
}