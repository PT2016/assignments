package views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.*;

import models.*;

public class CustomerView extends Frame {

	private Person p;
	private JPanel panel = new JPanel();
	private JTextField amount = new JTextField("Amount");
	private JButton withdraw = new JButton("Withdraw");
	private JButton deposit = new JButton("Deposit");
	private JButton generateReport = new JButton("Generate Report");
	private Object[][] data;
	private String[] columns = { "AccountID", "Type", "Balance", "Expire Date" };
	private JTable table;

	public CustomerView(String title, Person p) {
		super(title);
		this.p = p;
		amount.setPreferredSize(new Dimension(100, 20));
		panel.add(amount);
		panel.add(withdraw);
		panel.add(deposit);
		constructTable();
		contentPanel.add(panel);
		contentPanel.add(generateReport);
	}

	public void constructTable() {
		ArrayList<Account> accounts = Bank.getInstance().getInfo().get(p);
		data = new Object[accounts.size()][4];
		int index = 0;
		for (Account a : accounts) {
			if (a.getExpireDate().before(new Date())) {
				JOptionPane.showMessageDialog(null, "Your account " + a.getId() + " has expired. It has been renewed!");
				Calendar c = Calendar.getInstance();
				c.setTime(a.getExpireDate());
				c.add(Calendar.DATE, 30);
				a.setExpireDate(c.getTime());
			}
			if (a instanceof SavingsAccount) {
				long days = (System.currentTimeMillis() - ((SavingsAccount) a).getLastDayWhenInterestWasCalculated().getTime())
						/ (1000 * 60 * 60 * 24);
				if (days > 0) {
					((SavingsAccount) a).calculateInterest(days);
				}
			}
			data[index][0] = a.getId();
			data[index][1] = a.getType();
			data[index][2] = a.getMoney();
			data[index][3] = a.getExpireDate();
			index++;
		}
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(400, 200));
		JScrollPane scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane);
	}

	public void setWithdrawButtonActionListener(ActionListener a) {
		withdraw.addActionListener(a);
	}

	public void setDepositButtonActionListener(ActionListener a) {
		deposit.addActionListener(a);
	}

	public void setGenerateReportButtonActionListener(ActionListener a) {
		generateReport.addActionListener(a);
	}

	public JTable getTable() {
		return table;
	}

	public JTextField getAmount() {
		return amount;
	}

	public Person getP() {
		return p;
	}

}
