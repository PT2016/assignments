package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class DictionaryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JPanel topPanel;
	private JPanel btnPanel;
	private JButton addWord, addSynonim, deleteWord, deleteSynonim, search, populate;
	private static JTable table;
	private String header[] = { "Word", "Synonyms" };

	public DictionaryFrame() {
		setTitle("Synonym Dictionary");
		topPanel = new JPanel();
		getContentPane().add(topPanel);
		setSize(650, 550);
		setBackground(Color.gray);
		btnPanel = new JPanel();
		scrollPane = new JScrollPane();
		table = new JTable(0, 2);
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column1 = table.getTableHeader().getColumnModel().getColumn(i);

			column1.setHeaderValue(header[i]);
		}
		scrollPane = new JScrollPane(table);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		topPanel.setLayout(new BorderLayout());
		topPanel.add(btnPanel, BorderLayout.SOUTH);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		addWord = new JButton("Add Word");
		populate = new JButton("Populate");
		addSynonim = new JButton("Add Synonym");
		deleteWord = new JButton("Delete Word");
		deleteSynonim = new JButton("Delete Synonym");
		search = new JButton("Search");
		btnPanel.add(addWord);
		btnPanel.add(addSynonim);
		btnPanel.add(deleteWord);
		btnPanel.add(deleteSynonim);
		btnPanel.add(search);
		btnPanel.add(populate);
		setVisible(true);

	}

	public final void setAddWordActionListener(final ActionListener a) {
		addWord.addActionListener(a);

	}

	public final void setAddSynButtonActionListener(final ActionListener a) {
		addSynonim.addActionListener(a);

	}

	public final void setDelWordButtonActionListener(final ActionListener a) {
		deleteWord.addActionListener(a);

	}

	public final void setDeleteSynButtonActionListener(final ActionListener a) {
		deleteSynonim.addActionListener(a);

	}

	public final void setSearchActionListener(final ActionListener a) {
		search.addActionListener(a);

	}

	public final void setPopulateActionListener(final ActionListener a) {
		populate.addActionListener(a);

	}

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		DictionaryFrame.table = table;
	}

}
