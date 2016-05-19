package tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Person;

public class ClientsTableModel extends AbstractTableModel {

	private String[] columnNames = { "First Name", "Last Name", "ID" };

	private ArrayList<Person> clients;

	public ClientsTableModel() {
		clients = new ArrayList<Person>();
	}

	public ClientsTableModel(ArrayList<Person> clients) {
		this.clients = clients;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return clients.size();
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Person client = getClient(row);
		switch (column) {
		case 0:
			return client.getFirstName();
		case 1:
			return client.getLastName();
		case 2:
			return client.getID();
		}
		return client;
	}

	public Person getClient(int row) {
		return clients.get(row);
	}

	public void addCustomer(Person client) {
		insertClient(getRowCount(), client);
	}

	public void insertClient(int row, Person client) {
		clients.add(row, client);
		fireTableRowsInserted(row, row);
	}

	public void removeClient(int row) {
		clients.remove(row);
		fireTableRowsDeleted(row, row);
	}

}
