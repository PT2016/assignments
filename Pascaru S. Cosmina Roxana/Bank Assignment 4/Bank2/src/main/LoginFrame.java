package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Account;
import models.Bank;
import panels.AdminPanel;
import panels.CustomerPanel;

public class LoginFrame extends JFrame implements ActionListener {

	private Bank bank = new Bank();

	private JPanel loginPanel = new JPanel();
	private JButton customer = new JButton("Customer");
	private JButton admin = new JButton("Admin");

	public LoginFrame(Bank bank) {
		this.bank = bank;
		System.out.println(bank.getAccounts().size());
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		customer.setActionCommand("customer");
		customer.addActionListener(this);
		admin.setActionCommand("admin");
		admin.addActionListener(this);

		loginPanel.setLayout(new GridLayout(1, 2));
		loginPanel.setVisible(true);
		loginPanel.add(customer);
		loginPanel.add(admin);

		add(loginPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("customer")) {
			Account foundAccount = null;
			boolean found = false;
			String pin = JOptionPane.showInputDialog("Enter your PIN");
			for (Account a : bank.getAccounts()) {
				if (pin.equals(a.getPin())) {
					foundAccount = a;
					found = true;
				}
			}
			if (found) {
				setSize(400, 300);
				loginPanel.setVisible(false);
				add(new CustomerPanel(bank, foundAccount));
			}

		} else if (e.getActionCommand().equals("admin")) {
			loginPanel.setVisible(false);
			setSize(850, 500);
			setLocationRelativeTo(null);
			add(new AdminPanel(bank));
		}

	}

}
