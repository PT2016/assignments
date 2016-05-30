package controller;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {
	private String[] columnNames = { "Word", "Synonim" };
	private Object[][] data;
	DefaultTableModel model = new DefaultTableModel(data, columnNames);
	JTable table = new JTable(model);
	private SimpleHashMap hashMap;

	public TableModel(SimpleHashMap hashMap) {
		this.hashMap = hashMap;
		data = hashMap.getAllEntriesForTable();
	}

	public void updateModel() {
		data = hashMap.getAllEntriesForTable();
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
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	public void removeRow(int selectedRow) {

		fireTableRowsDeleted(selectedRow, selectedRow);
		fireTableStructureChanged();
	}

}
