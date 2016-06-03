package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.ProxyDictionary;

public class DictionaryFrame {

	private JFrame frame;
	private int NR_ELEM_IN_TABLE = 0;

	public JTextField jTextField;
	public JButton searchFilterButton;
	private JPanel searchPanelButton;

	private JScrollPane jPanelContent;
	public JTable tableAdmin;
	private DefaultTableModel tableModel;

	public JTextField jTextFieldKey;
	public JTextField jTextFieldSyn;
	private JPanel opPanel;
	public JButton addSynonimButton;
	public JButton removeSynonimButton;

	public DictionaryFrame() {
		frame = new JFrame("Dictionary");

		jTextField = new JTextField(30);
		jTextField.setBounds(10, 30, 300, 300);
		jTextField.setBackground(Color.white);
		jTextField.setForeground(Color.black);
		searchFilterButton = new JButton("Search");
		searchPanelButton = new JPanel(new BorderLayout());
		JLabel labelType = new JLabel("Filter:");

		searchPanelButton.add(labelType, BorderLayout.WEST);
		searchPanelButton.add(jTextField, BorderLayout.CENTER);
		searchPanelButton.add(searchFilterButton, BorderLayout.EAST);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("KEY");
		tableModel.addColumn("SYNONIMs");
		tableAdmin = new JTable(tableModel);

		// this.updateTable(Dictionary dictionary);
		tableAdmin.setFillsViewportHeight(true);
		jPanelContent = new JScrollPane(tableAdmin);

		JLabel labelKey = new JLabel("KEY:");
		JLabel labelSyn = new JLabel("synonim:");
		jTextFieldKey = new JTextField(30);
		jTextFieldKey.setBounds(10, 30, 300, 300);
		jTextFieldKey.setBackground(Color.white);
		jTextFieldKey.setForeground(Color.black);

		jTextFieldSyn = new JTextField(30);
		jTextFieldSyn.setBounds(10, 30, 300, 300);
		jTextFieldSyn.setBackground(Color.white);
		jTextFieldSyn.setForeground(Color.black);

		opPanel = new JPanel(new GridLayout(1, 5));
		JPanel otherP = new JPanel(new GridLayout(2, 1));
		addSynonimButton = new JButton("ADD SYNONIM");
		removeSynonimButton = new JButton("REMOVE SYNONIM");

		otherP.add(addSynonimButton);
		otherP.add(removeSynonimButton);
		opPanel.add(labelKey);
		opPanel.add(jTextFieldKey);
		opPanel.add(labelSyn);
		opPanel.add(jTextFieldSyn);
		opPanel.add(otherP);

		JPanel northP = new JPanel(new GridLayout(2, 1));
		northP.add(searchPanelButton);
		northP.add(opPanel);

		frame.setLayout(new BorderLayout());
		frame.add(northP, BorderLayout.NORTH);
		frame.add(jPanelContent, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void updateTable(ProxyDictionary dictionary) {
		this.removeAllRows();
		try {
			NR_ELEM_IN_TABLE = dictionary.getSize();
		} catch (NullPointerException ex) {
			NR_ELEM_IN_TABLE = 0;
		}
		// if (NR_ELEM_IN_TABLE == 0) {
		// JLabel noElemLabel = new JLabel("no product to be displayed");
		// jPanelContent.add(noElemLabel);
		// }
		if (NR_ELEM_IN_TABLE > 0) {
			Set<String> keys = dictionary.getKeys();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();

				ArrayList<String> syns = dictionary.getSyn(key);
				if (syns.size() == 0) {
					tableModel.addRow(new Object[] { key, "-" });
				} else {
					tableModel.addRow(new Object[] { key, syns.toString() });
				}
			}
		}
	}

	public void updateTable(ProxyDictionary dictionary, String searchForID) {
		if (!(searchForID.length() == 0)) { // not empty string
			this.removeAllRows();

			ArrayList<String> keys = dictionary.searchWord(searchForID);
			try {
				NR_ELEM_IN_TABLE = keys.size();
			} catch (NullPointerException ex) {
				NR_ELEM_IN_TABLE = 0;
			}

			if (NR_ELEM_IN_TABLE != 0) {
				for (int aux = 0; aux < NR_ELEM_IN_TABLE; aux++) {
					tableModel.addRow(new Object[] { keys.get(aux), dictionary.getSyn(keys.get(aux)).toString() });
				}
			}
		} else { // empty string
			this.updateTable(dictionary);
		}
	}

	private void removeAllRows() {
		int n = tableModel.getRowCount();
		for (int i = n - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		NR_ELEM_IN_TABLE = 0;
	}
}
