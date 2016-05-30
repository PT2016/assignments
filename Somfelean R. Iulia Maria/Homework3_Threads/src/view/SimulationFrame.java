package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.CashRegister;
import model.ClientGenerator;
import utilities.Constants;

public class SimulationFrame extends JFrame {

	private CashRegister[] cashRegisters;

	private GridBagConstraints gbc;

	private QueuePanel queue1Panel;
	private QueuePanel queue2Panel;
	private QueuePanel queue3Panel;

	private LoggerPanel lp;

	public SimulationFrame(CashRegister[] cashRegisters) {

		super("Queues simulation");
		this.setCashRegisters(cashRegisters);

		this.setSize(Constants.X_FRAME, Constants.Y_FRAME);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());

		initializeComponents();

		draw(0, cashRegisters[0]);
		draw(1, cashRegisters[1]);
		draw(2, cashRegisters[2]);
	}

	public SimulationFrame() {
		super("Simulation in progress...");
		this.setSize(Constants.X_FRAME, Constants.Y_FRAME);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());

		initializeComponents();
	}

	public void initializeComponents() {

		this.gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.insets = new Insets(10, 10, 0, 10);
		this.queue1Panel = new QueuePanel(0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 2;
		gbc.ipadx = 780;
		gbc.ipady = 60;
		this.add(queue1Panel, gbc);

		gbc.insets = new Insets(10, 10, 0, 10);
		this.queue2Panel = new QueuePanel(1);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 2;
		gbc.ipadx = 780;
		gbc.ipady = 60;
		this.add(queue2Panel, gbc);

		gbc.insets = new Insets(10, 10, 0, 10);
		this.queue3Panel = new QueuePanel(2);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 2;
		gbc.ipadx = 780;
		gbc.ipady = 60;
		this.add(queue3Panel, gbc);

		gbc.insets = new Insets(0, 10, 10, 10);
		this.lp = new LoggerPanel();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.ipadx = 780;
		gbc.ipady = 320;
		this.add(lp, gbc);

	}

	public void draw(int id, CashRegister cashRegister) {

		switch (id) {
		case 0:
			this.getQueuePanel(0).drawElements(cashRegister);
			break;
		case 1:
			this.getQueuePanel(1).drawElements(cashRegister);
			break;
		default:
			this.getQueuePanel(2).drawElements(cashRegister);
			break;
		}

	}

	public LoggerPanel getLoggerPanel() {
		return this.lp;
	}

	public CashRegister[] getCashRegisters() {
		return cashRegisters;
	}

	public void setCashRegisters(CashRegister[] cashRegisters) {
		this.cashRegisters = cashRegisters;
	}

	public QueuePanel getQueuePanel(int i) {

		switch (i) {
		case 0:
			return queue1Panel;
		case 1:
			return queue2Panel;
		default:
			return queue3Panel;
		}
	}

}
