package tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Account;

public class InfoTableModel extends AbstractTableModel {

	private String[] columnNames = { "ID", "TOTAL", "PIN", };
	private ArrayList<Account> accounts;

	public InfoTableModel() {
		accounts = new ArrayList<Account>();
	}

	public InfoTableModel(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return accounts.size();
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Account account = getAccount(row);
		switch (column) {
		case 0:
			return account.getID();
		case 1:
			return account.getTotal();
		case 2:
			return account.getPin();
		}
		return account;
	}

	public Account getAccount(int row) {
		return accounts.get(row);
	}

	public void addAccount(Account account) {
		insertAccount(getRowCount(), account);
	}

	public void insertAccount(int row, Account account) {
		accounts.add(row, account);
		fireTableRowsInserted(row, row);
	}

	public void removeAccount(int row) {
		accounts.remove(row);
		fireTableRowsDeleted(row, row);
	}

}
