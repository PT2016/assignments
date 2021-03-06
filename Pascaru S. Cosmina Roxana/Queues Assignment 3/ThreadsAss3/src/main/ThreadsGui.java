package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import scheduler.Client;
import scheduler.Scheduler;
import shop.Counter;
import shop.Shop;

public class ThreadsGui implements ActionListener {

	JFrame frame = new JFrame("Shop");
	JPanel shopPanel = new JPanel();
	JPanel countersPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JPanel openPanel = new JPanel();

	private int openCounters;
	private ArrayList<Counter> counters;
	private Scheduler scheduler;
	private Shop shop;

	public ThreadsGui(int openCounters, Scheduler scheduler, Shop shop) {
		this.scheduler = scheduler;
		this.shop = shop;
		this.openCounters = openCounters;
		counters = new ArrayList<Counter>();
		frame.setSize(700, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		infoPanel.setLayout(new GridLayout(openCounters, 1));
		countersPanel.setLayout(new GridLayout(openCounters, 1));
		openPanel.setLayout(new GridLayout(openCounters, 1));
		shopPanel.setLayout(new GridLayout(1, 3));

		for (int i = 0; i < openCounters; i++) {
			JLabel label = new JLabel(" Counter " + i);
			infoPanel.add(label);
			JButton closeButton = new JButton("Close");
			closeButton.setActionCommand("close" + i);
			closeButton.addActionListener(this);
			JPanel buttonPanel = new JPanel(new FlowLayout());
			buttonPanel.add(closeButton);
			openPanel.add(buttonPanel);
		}

		shopPanel.add(openPanel);
		shopPanel.add(infoPanel);
		shopPanel.add(countersPanel);
		frame.add(shopPanel);

	}

	public void displayData(ArrayList<Counter> counters) {
		this.counters = counters;
		countersPanel.removeAll();
		countersPanel.revalidate();

		for (int i = 0; i < counters.size(); i++) {
			JList<Client> clients = new JList<Client>(counters.get(i).getClientsArray());
			JScrollPane clientsScroll = new JScrollPane(clients);

			countersPanel.add(clientsScroll);
		}

		countersPanel.repaint();
		countersPanel.revalidate();

	}

	public void displayConclusions() {
		String displayString = new String();
		displayString = "Average times:\nServing time:\n";
		for (int i = 0; i < shop.getAverageServingTimes().length; i++) {
			displayString += "Counter " + i + " = " + shop.getAverageServingTimes()[i] + "\n";
		}
		displayString += "\nWaiting time:\n";
		for (int i = 0; i < shop.getAverageWaitingTimes().length; i++) {
			displayString += "Counter " + i + " = " + shop.getAverageWaitingTimes()[i] + "\n";
		}
		displayString += "\nPeak hour " + scheduler.getPeakHour();
		JOptionPane.showMessageDialog(frame, displayString);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < openCounters; i++) {
			if (e.getActionCommand().equals("close" + i)) {
				System.out.println("Counter " + i + " closed");
				counters.get(i).setClosed(true);

				BlockingQueue<Client> clients = new LinkedBlockingQueue<Client>();
				clients = counters.get(i).getClientsList();

				while (clients.size() > 0) {
					for (Client c : clients) {
						counters.get(i).getClientsList().remove(c);
						scheduler.sendClientToCounter(c, c.getArrivalTime());
					}
				}
			}
		}

	}

}
