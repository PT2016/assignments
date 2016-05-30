package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Bank;
import model.Person;

@SuppressWarnings("serial")
public class SpendingAccountsView extends JFrame implements ActionListener {

	private ArrayList<Object> accounts;
	private JTable table, jT;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Object[][] rows;
	private String[] columns;
	private JPanel operations;
	private JButton removeAccount;
	private Bank bank;
	private Person person;

	public SpendingAccountsView(Person person, ArrayList<Object> accounts) {
		this.setTitle("Accounts data");
		this.setSize(400, 600);
		this.accounts = accounts;
		this.person = person;
		jT = new JTable();
		bank = Bank.getInstance();
		initializeNorthSection();
		removeAccount = new JButton("Remove account");
		removeAccount.addActionListener(this);
		operations = new JPanel();
		operations.add(removeAccount);
		this.add(operations, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeNorthSection() {
		table = createGeneralTable(accounts);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
	}

	private JTable createGeneralTable(Collection<Object> data) {
		int listLength;
		if (data == null) {
			return null;
		}
		Object[] list = data.toArray();
		if (list.length != 0) {
			Field[] fields = list[0].getClass().getDeclaredFields();
			Field.setAccessible(fields, true);
			rows = new Object[list.length][fields.length - 1];
			Object[] row = new Object[fields.length - 1];
			columns = new String[fields.length - 1];
			int i = 0;
			for (Field field : fields) {
				if (i == columns.length) {
					break;
				}
				columns[i++] = field.getName();
			}
			tableModel = new DefaultTableModel(rows, columns);
			tableModel.setRowCount(0);
			jT.setModel(tableModel);
			jT.setEnabled(false);
			listLength = list.length;
			for (i = 0; i < listLength; i++) {
				int j = 0;
				for (Field field : fields) {
					if (j == row.length) {
						break;
					}
					try {
						Object fieldValue = field.get(list[i]);
						row[j++] = fieldValue;
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				tableModel.addRow(row);
			}
		}
		return jT;
	}

	private void updateTable() {
		table = createGeneralTable(bank.getPersonSpendingAccounts(person));
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == removeAccount) {
			String name = JOptionPane.showInputDialog(this, "Give the name of the account.", "Account name",
					JOptionPane.INFORMATION_MESSAGE);
			Double money = Double.valueOf(JOptionPane.showInputDialog(this, "Give the money of the account.", "Money",
					JOptionPane.INFORMATION_MESSAGE));
			bank.removeHolderAssociatedAccount(person, bank.getAccount(person, name, money));
			updateTable();
		}
	}
}
