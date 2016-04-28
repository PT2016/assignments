package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shop.Shop;

public class StartGui implements ActionListener {
	private JFrame frame = new JFrame("Queue Thread");
	private JPanel input = new JPanel();
	private JPanel confirm = new JPanel();

	private JLabel finishTimeLabel = new JLabel("Finish time");
	private JLabel minProcTimeLabel = new JLabel("Min processing time");
	private JLabel maxProcTimeLabel = new JLabel("Max processing time");
	private JLabel openCountersLabel = new JLabel("Open counters");

	private JTextField finishTimeText = new JTextField();
	private JTextField minProcTimeText = new JTextField();
	private JTextField maxProcTimeText = new JTextField();
	private JTextField openCountersText = new JTextField();

	private int nrOfClients;
	private int finishTime;
	private int minProcTime;
	private int maxProcTime;
	private int openCounters;

	private JButton confirmButton = new JButton("Confirm");

	private Shop shop;

	public StartGui() {

		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		input.setLayout(new GridLayout(4, 2));

		input.add(finishTimeLabel);
		input.add(finishTimeText);
		input.add(minProcTimeLabel);
		input.add(minProcTimeText);
		input.add(maxProcTimeLabel);
		input.add(maxProcTimeText);
		input.add(openCountersLabel);
		input.add(openCountersText);

		finishTimeText.addActionListener(this);
		minProcTimeText.addActionListener(this);
		maxProcTimeText.addActionListener(this);
		openCountersText.addActionListener(this);

		confirmButton.setActionCommand("confirm");
		confirmButton.addActionListener(this);
		confirm.setLayout(new FlowLayout());
		confirm.add(confirmButton);

		frame.add(input, BorderLayout.CENTER);
		frame.add(confirm, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("confirm")) {
			finishTime = Integer.parseInt(finishTimeText.getText());
			minProcTime = Integer.parseInt(minProcTimeText.getText());
			maxProcTime = Integer.parseInt(maxProcTimeText.getText());
			openCounters = Integer.parseInt(openCountersText.getText());

			shop = new Shop(finishTime, maxProcTime, minProcTime, openCounters);
			Thread shopThread = new Thread(shop);
			shopThread.start();
		}

	}

	public int getNrOfClients() {
		return nrOfClients;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public int getMinProcTime() {
		return minProcTime;
	}

	public int getMaxProcTime() {
		return maxProcTime;
	}

	public int getOpenCountersNr() {
		return openCounters;
	}

}
