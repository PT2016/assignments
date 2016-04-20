package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import queues.Scheduler;

public class Gui {
	private JFrame frame = new JFrame();
	private JPanel labelPanel = new JPanel(new GridLayout(5, 1, 30, 30));
	private JPanel inputPanel = new JPanel(new GridLayout(1, 2, 30, 30));
	private JPanel textPanel = new JPanel(new GridLayout(5, 1, 30, 30));
	private JPanel simulationPanel = new JPanel(new GridLayout(1, 3, 50, 50));
	private static JPanel[] server = new JPanel[3];
	private JPanel infoPanel = new JPanel(new BorderLayout());
	private JPanel btnPanel = new JPanel(new GridLayout(1, 2, 30, 30));
	private JLabel minArr = new JLabel("Min arriving time");
	private JLabel maxArr = new JLabel("Max arriving time");
	private JLabel minServ = new JLabel("Min service time");
	private JLabel maxServ = new JLabel("Max service time");
	private JLabel sim = new JLabel("Simulation time");
	private JLabel elapsedTime = new JLabel("0");
	private static JTextField minArrText = new JTextField();
	private static JTextField maxArrText = new JTextField();
	private static JTextField minServText = new JTextField();
	private static JTextField maxServText = new JTextField();
	private static JTextField simText = new JTextField();
	private static JTextArea infoText = new JTextArea();
	private JScrollPane scroll = new JScrollPane(infoText);
	private JLabel srv1 = new JLabel("Server1");
	private JLabel srv2 = new JLabel("Server2");
	private JLabel srv3 = new JLabel("Server3");
	private JButton start = new JButton("Start simulation");
	private int time = 0;
	private Runnable increaseTime = new Runnable() {
		public void run() {
			elapsedTime.setText("Elapsed time: " + time + " seconds");
			time++;
		}
	};
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	private SwingWorker<Integer, Void> worker = new SwingWorker<Integer, Void>() {
		public Integer doInBackground() {
			new Scheduler();
			return 1;
		}
	};

	public Gui() {
		server[0] = new JPanel(new GridLayout(7, 1, 20, 20));
		server[1] = new JPanel(new GridLayout(7, 1, 20, 20));
		server[2] = new JPanel(new GridLayout(7, 1, 20, 20));
		frame.setLayout(new BorderLayout());
		labelPanel.add(minArr);
		labelPanel.add(maxArr);
		labelPanel.add(minServ);
		labelPanel.add(maxServ);
		labelPanel.add(sim);
		inputPanel.add(labelPanel);
		textPanel.add(minArrText);
		textPanel.add(maxArrText);
		textPanel.add(minServText);
		textPanel.add(maxServText);
		textPanel.add(simText);
		inputPanel.add(textPanel);
		frame.add(inputPanel, BorderLayout.NORTH);
		infoPanel.add(scroll, BorderLayout.CENTER);
		infoPanel.add(elapsedTime, BorderLayout.SOUTH);
		frame.add(infoPanel, BorderLayout.CENTER);
		btnPanel.add(start);
		frame.add(btnPanel, BorderLayout.SOUTH);
		server[0].add(srv1);
		server[1].add(srv2);
		server[2].add(srv3);
		simulationPanel.add(server[0]);
		simulationPanel.add(server[1]);
		simulationPanel.add(server[2]);
		frame.add(simulationPanel, BorderLayout.EAST);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executor.scheduleAtFixedRate(increaseTime, 0, 1, TimeUnit.SECONDS);
				worker.execute();
			}
		});
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void addToServer(int index, int i) {
		JLabel lbl = new JLabel("CLIENT " + i);
		lbl.setForeground(new Color((int) (Math.random() * 0x1000000)));
		server[index].add(lbl);
	}

	public static void removeFromServer(int index) {
		server[index].remove(1);
	}

	public static int getMinArrTime() {
		int x = Integer.parseInt(minArrText.getText());
		return x;
	}

	public static int getMaxArrTime() {
		int x = Integer.parseInt(maxArrText.getText());
		return x;
	}

	public static int getMinServTime() {
		int x = Integer.parseInt(minServText.getText());
		return x;
	}

	public static int getMaxServTime() {
		int x = Integer.parseInt(maxServText.getText());
		return x;
	}

	public static int getSimulationTime() {
		int x = Integer.parseInt(simText.getText());
		return x;
	}

	public static void showMessage(String s) {
		System.out.println(s + "\n");
		infoText.append(s + "\n");
		infoText.setCaretPosition(infoText.getDocument().getLength());
	}
}
