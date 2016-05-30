package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import models.Task;

public class SimulatorFrame {

	JFrame frame = new JFrame();
	JPanel panelCounter;
	JPanel framePanel;
	JPanel centrePanel;
	JPanel panel = new JPanel();
	JTextField counter;
	private int[] parameters;
	private JTextField SimulationTimetext;
	private JTextField MaxServiceTimetext;
	private JTextField MinServiceTimetext;
	private JTextField MaxArrivalTimetext;
	private JTextField MinArrivalTimetext;
	private JTextField noQueuestext;

	private JButton startButton;

	public SimulatorFrame() {
		frame.setLayout(new BorderLayout());

		JPanel inputBoxes = new JPanel(new GridLayout(2, 3));

		JPanel panel1 = new JPanel(new GridLayout(1, 2));
		JLabel noQueues = new JLabel("Queues");
		noQueuestext = new JTextField();
		panel1.add(noQueues);
		panel1.add(noQueuestext);
		inputBoxes.add(panel1);

		JPanel panel2 = new JPanel(new GridLayout(1, 2));
		JLabel MinArrivalTime = new JLabel("Min Arrival Time");
		MinArrivalTimetext = new JTextField();
		panel2.add(MinArrivalTime);
		panel2.add(MinArrivalTimetext);
		inputBoxes.add(panel2);

		JPanel panel3 = new JPanel(new GridLayout(1, 2));
		JLabel MaxArrivalTime = new JLabel("Max Arrival Time");
		MaxArrivalTimetext = new JTextField();
		panel3.add(MaxArrivalTime);
		panel3.add(MaxArrivalTimetext);
		inputBoxes.add(panel3);

		JPanel panel4 = new JPanel(new GridLayout(1, 2));
		JLabel MinServiceTime = new JLabel("Min Service Time");
		MinServiceTimetext = new JTextField();
		panel4.add(MinServiceTime);
		panel4.add(MinServiceTimetext);
		inputBoxes.add(panel4);

		JPanel panel5 = new JPanel(new GridLayout(1, 2));
		JLabel MaxServiceTime = new JLabel("Max Service Time");
		MaxServiceTimetext = new JTextField();
		panel5.add(MaxServiceTime);
		panel5.add(MaxServiceTimetext);
		inputBoxes.add(panel5);

		JPanel panel6 = new JPanel(new GridLayout(1, 2));
		JLabel SimulationTime = new JLabel("Simulation Time");
		SimulationTimetext = new JTextField();
		panel6.add(SimulationTime);
		panel6.add(SimulationTimetext);
		inputBoxes.add(panel6);

		frame.setSize(1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centrePanel = new JPanel();

		centrePanel.add(panel);
		startButton = new JButton("Start");
		centrePanel.add(startButton);

		frame.add(centrePanel, BorderLayout.CENTER);
		frame.add(inputBoxes, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	public void displayData(ArrayList<Task[]> tasks) {

		panel.removeAll();
		panel.revalidate();
		for (Task[] t : tasks) {
			JList<Task> jtasks = new JList<Task>(t);
			JScrollPane sp = new JScrollPane(jtasks);
			sp.setPreferredSize(new Dimension(300, 400));
			panel.add(sp);
		}

		panel.repaint();
		panel.revalidate();

	}

	public JButton getStartButton() {
		return startButton;
	}

	public int[] getParameters() {
		parameters = new int[6];

		parameters[0] = Integer.parseInt(MinArrivalTimetext.getText());
		parameters[1] = Integer.parseInt(MaxArrivalTimetext.getText());
		parameters[2] = Integer.parseInt(MinServiceTimetext.getText());
		parameters[3] = Integer.parseInt(MaxServiceTimetext.getText());
		parameters[4] = Integer.parseInt(SimulationTimetext.getText());
		parameters[5] = Integer.parseInt(noQueuestext.getText());
		return parameters;
	}

	public void displayStatistics(int[] averageWaitingTime, int[] averageServiceTime, int peakHour) {
		panel.removeAll();
		panel.revalidate();
		for (int i = 0; i < parameters[5]; i++) {
			JTextField sp = new JTextField();
			sp.setText("Average waiting time: " + Integer.toString(averageWaitingTime[i]) + "Average service time: "
					+ Integer.toString(averageServiceTime[i]));
			JTextField sp1 = new JTextField();
			sp1.setText("Peak hour: "+Integer.toString(peakHour));
			panel.add(sp);
		}

		panel.repaint();
		panel.revalidate();

	}
}
