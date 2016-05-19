package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class PopulateFrame extends JFrame{

	private JPanel contentPanel;
	private JTextField search = new JTextField();
	private JButton searchButton = new JButton("Search");
	private JButton add = new JButton("Add");
	private JButton removeKey = new JButton("Remove Key");
	private JButton removeDefinition = new JButton("Remove Defintion");
	private JButton define = new JButton("Define");
	private JButton done = new JButton("Done");
	private JButton update = new JButton("Update");
	private Object[][] data1;
	private String[] columns1 = { "Word" };
	private JTable table1;
	private Object[][] data2;
	private String[] columns2 = { "Definition" };
	private JTable table2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	
	public PopulateFrame(String title){
		setTitle(title);
		setSize(500, 600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.blue);
		search.setPreferredSize(new Dimension(100, 20));
		contentPanel.add(search);
		contentPanel.add(searchButton);
		contentPanel.add(add);
		contentPanel.add(removeKey);
		contentPanel.add(removeDefinition);
		contentPanel.add(define);
		contentPanel.add(update);
		contentPanel.add(done);
		add(contentPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void refresh(){
		contentPanel.removeAll();
		contentPanel.revalidate();
		this.repaint();
		contentPanel.add(search);
		contentPanel.add(searchButton);
		contentPanel.add(add);
		contentPanel.add(removeKey);
		contentPanel.add(removeDefinition);
		contentPanel.add(define);
		contentPanel.add(update);
		contentPanel.add(done);
		contentPanel.revalidate();
		this.repaint();
	}
	
	public void constructTable1(HashMap<DictionaryEntry, ArrayList<DictionaryEntry>> searchResult){
		refresh();
		data1 = new Object[searchResult.size()][1];
		int index = 0;
		for (DictionaryEntry de : searchResult.keySet()) {
			data1[index][0] = de.toString();
			index++;
		}
		DefaultTableModel tableModel = new DefaultTableModel(data1, columns1) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table1 = new JTable(tableModel);
		table1.setPreferredScrollableViewportSize(new Dimension(400, 200));
		scrollPane1 = new JScrollPane(table1);
		contentPanel.add(scrollPane1);
	}
	
	public void constructTable2(DictionaryEntry de){
		refresh();
		contentPanel.add(scrollPane1);
		data2 = new Object[Dictionary.getInstance().getEntries().get(de).size()][1];
		int index = 0;
		for (DictionaryEntry de2 : Dictionary.getInstance().getEntries().get(de)) {
			data2[index][0] = de2.toString();
			index++;
		}
		DefaultTableModel tableModel = new DefaultTableModel(data2, columns2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table2 = new JTable(tableModel);
		table2.setPreferredScrollableViewportSize(new Dimension(400, 200));
		scrollPane2 = new JScrollPane(table2);
		contentPanel.add(scrollPane2);
	}
	
	public void setAddButtonActionListener(ActionListener a) {
		add.addActionListener(a);
	}
	
	public void setSearchButtonActionListener(ActionListener a) {
		searchButton.addActionListener(a);
	}
	
	public void setRemoveKeyButtonActionListener(ActionListener a) {
		removeKey.addActionListener(a);
	}
	
	public void setRemoveDefinitionButtonActionListener(ActionListener a) {
		removeDefinition.addActionListener(a);
	}
	
	public void setDefineButtonActionListener(ActionListener a) {
		define.addActionListener(a);
	}
	
	public void setUpdateButtonActionListener(ActionListener a) {
		update.addActionListener(a);
	}
	
	public void setDoneButtonActionListener(ActionListener a) {
		done.addActionListener(a);
	}

	public JTextField getSearch() {
		return search;
	}

	public JTable getTable1() {
		return table1;
	}

	public JTable getTable2() {
		return table2;
	}

	public JButton getDefine() {
		return define;
	}
	
}