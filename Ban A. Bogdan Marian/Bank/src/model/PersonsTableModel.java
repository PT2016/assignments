package model;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import bank.Bank;

@SuppressWarnings("serial")
public class PersonsTableModel extends AbstractTableModel {
	private Bank bank;
	private String[] columnNames = { "Person", "Person ID", "Account ID", "Account Type", "Sum" };
	private Object[][] data;
	DefaultTableModel model = new DefaultTableModel(data,columnNames);
	JTable table = new JTable(model);

	public PersonsTableModel(Bank bank) {
		this.bank = bank;
		data = bank.getAllEntriesForTable();
	}
	
	public PersonsTableModel(Bank bank,Person person) {
		this.bank = bank;
		data = bank.getPersonEntriesForTable(person);
	}
	
	public void updateModel(Person person) {
		data = bank.getPersonEntriesForTable(person);
	}

	public void updateModel() {
		data = bank.getAllEntriesForTable();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public boolean isCellEditable(int row, int col) {
		if (col == 4) {
			return true;
		}
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		bank.writeAccountData((Long) getValueAt(row, 1), (Long) getValueAt(row, 2), (Long) value);
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	public void removeRow(int selectedRow) {
		
	    fireTableRowsDeleted(selectedRow, selectedRow);
	    fireTableStructureChanged();
	}

}
