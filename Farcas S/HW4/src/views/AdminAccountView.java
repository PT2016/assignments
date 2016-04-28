package views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import models.*;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdminAccountView extends Frame{

	private Person p;
	private JButton remove = new JButton("Remove");
	private JButton add = new JButton("Add");
	private Object[][] data;
	private String[] columns = {"AccountID", "Type", "Balance", "Expire Date"};
	private JTable table;
	
	public AdminAccountView(String title, Person p){
		super(title);
		this.p = p;
		constructTable();
		contentPanel.add(add);
		contentPanel.add(remove);
	}
	
	public void constructTable(){
		ArrayList<Account> accounts = Bank.getInstance().getInfo().get(p);
		data = new Object[accounts.size()][4];
		int index = 0;
		for(Account a: accounts){
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
	
	public void setRemoveButtonActionListener(ActionListener a) {
		remove.addActionListener(a);
	}
	
	public void setAddButtonActionListener(ActionListener a) {
		add.addActionListener(a);
	}

	public JTable getTable() {
		return table;
	}

	public Person getP() {
		return p;
	}

	public JButton getAdd() {
		return add;
	}
	
}
