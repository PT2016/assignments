package views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import models.*;

public class AdminView extends Frame{

	private JButton remove = new JButton("Remove");
	private JButton add = new JButton("Add");
	private JTextField username = new JTextField("Name");
	private JTextField age = new JTextField("Age");
	private JTable table;
	private JPanel addPanel = new JPanel();
	private String[] columns = {"Person", "Age"};
	private Object [][] data;
	private JButton accounts = new JButton("View Accounts");
	private JButton generateReport = new JButton("Generate Report");
	
	public AdminView(String title){
		super(title);
		constructTable();
		username.setPreferredSize(new Dimension(100, 20));
		age.setPreferredSize(new Dimension(100, 20));
		addPanel.add(username);
		addPanel.add(age);
		addPanel.add(add);
		contentPanel.add(addPanel);
		contentPanel.add(remove);
		contentPanel.add(accounts);
		contentPanel.add(generateReport);
	}
	
	public void constructTable(){
		data = new Object[Bank.getInstance().getInfo().keySet().size()][2];
		int index = 0;
		for(Person p: Bank.getInstance().getInfo().keySet()){
			data[index][0] = p.getName();
			data[index][1] = p.getAge();
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
	
	public void setAccountsButtonActionListener(ActionListener a){
		accounts.addActionListener(a);
	}
	
	public void setGenerateReportButtonActionListener(ActionListener a) {
		generateReport.addActionListener(a);
	}

	public JTable getTable() {
		return table;
	}

	public JTextField getUserName() {
		return username;
	}

	public JTextField getAge() {
		return age;
	}
	
}
