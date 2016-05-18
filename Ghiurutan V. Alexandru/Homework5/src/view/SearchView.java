package view;

import java.util.Map.Entry;
import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Word;

public class SearchView extends JFrame {

	private Set<Entry<Word, String>> words;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JTable table;
	private Object[][] rows;
	private Object[] columns = { "Word", "Description" };

	public SearchView(Set<Entry<Word, String>> words) {
		this.setTitle("Search");
		this.setLocationRelativeTo(null);
		this.words = words;
		initializeTable();
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeTable() {
		tableModel = new DefaultTableModel(rows, columns);
		table = new JTable();
		table.setModel(tableModel);
		table.setEnabled(false);
		fillTable();
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
	}

	private void fillTable() {
		if (words.size() != 0) {
			rows = new Object[words.size()][2];
			Iterator<Entry<Word, String>> it = words.iterator();
			while (it.hasNext()) {
				Entry<Word, String> newWord = it.next();
				Object[] row = { newWord.getKey().getWord(), newWord.getValue() };
				tableModel.addRow(row);
				;
			}
		}
	}

}
