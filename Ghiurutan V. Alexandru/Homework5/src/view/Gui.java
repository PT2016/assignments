package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Dictionary;
import model.Word;

public class Gui extends JFrame implements ActionListener {
	private JPanel operations;
	private JButton add, delete, search;
	private JTable table, jT;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Object[][] rows;
	private Object[] columns = { "Word", "Description" };
	private Dictionary dictionary;
	private String[] selectionValues = { "Word with explanation", "Word without explanation" };
	private String initialSelection = "Word with explanation.";

	public Gui() {
		this.setTitle("Dictionary");
		this.setLocationRelativeTo(null);
		dictionary = Dictionary.getInstance();
		jT = new JTable();
		initializeNorthSection();
		operations = new JPanel();
		operations.setLayout(new BoxLayout(operations, BoxLayout.X_AXIS));

		add = new JButton("Add");
		add.addActionListener(this);

		delete = new JButton("Delete");
		delete.addActionListener(this);

		search = new JButton("Search");
		search.addActionListener(this);

		operations.add(add);
		operations.add(delete);
		operations.add(search);
		this.add(operations, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private void fillTable() {
		if (dictionary.getTotalNrOfWords() != 0) {
			Set<Entry<Word, String>> diction = dictionary.getAllWordsInDictionary();
			rows = new Object[diction.size()][2];
			Iterator<Entry<Word, String>> it = diction.iterator();
			while (it.hasNext()) {
				Entry<Word, String> newWord = it.next();
				Object[] row = { newWord.getKey().getWord(), newWord.getValue() };
				tableModel.addRow(row);
			}
		}
	}

	private void updateTable() {
		tableModel = new DefaultTableModel(rows, columns);
		tableModel.setRowCount(0);
		table.setModel(tableModel);
		Set<Entry<Word, String>> diction = dictionary.getAllWordsInDictionary();
		rows = new Object[diction.size()][2];
		Iterator<Entry<Word, String>> it = diction.iterator();
		while (it.hasNext()) {
			Entry<Word, String> newWord = it.next();
			Object[] row = { newWord.getKey().getWord(), newWord.getValue() };
			tableModel.addRow(row);
		}
	}

	private void initializeNorthSection() {
		tableModel = new DefaultTableModel(rows, columns);
		table = new JTable();
		table.setModel(tableModel);
		table.setEnabled(false);
		fillTable();
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
	}

	private String checkInputWord() {
		String word = String.valueOf(
				JOptionPane.showInputDialog(this, "Please enter the word.", "Input", JOptionPane.INFORMATION_MESSAGE))
				.toLowerCase();
		while (word.equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter a valid word.Please enter again more carefully.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			word = String.valueOf(JOptionPane.showInputDialog(this, "Please enter the word .", "Input",
					JOptionPane.INFORMATION_MESSAGE)).toLowerCase();
		}
		return word;
	}

	private String checkInputDescription(String auxWord) {
		String description = String.valueOf(JOptionPane.showInputDialog(this,
				"Enter the description of the word: " + auxWord + ", that you want in dictionary.", "Input",
				JOptionPane.INFORMATION_MESSAGE)).toLowerCase();
		while (description.equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter a valid word.Please enter again more carefully.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			description = String.valueOf(JOptionPane.showInputDialog(this,
					"Enter the description of the word: " + auxWord + ", that you want in dictionary.", "Input",
					JOptionPane.INFORMATION_MESSAGE)).toLowerCase();
		}
		return description;
	}

	private void addWords(Word newWord) {
		String description = checkInputDescription(newWord.getWord());
		dictionary.addWord(newWord, description);
		description = description.replaceAll("[^A-Za-z-]", " ");
		description = description.trim().replaceAll("\\s+", " ");
		JDialog.setDefaultLookAndFeelDecorated(true);
		String[] descriptionWords = description.split(" ");
		for (String otherWord : descriptionWords) {
			String result = String
					.valueOf(JOptionPane.showInputDialog(this, "Select the type of the word: " + otherWord, "Quiz",
							JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection));
			if (!dictionary.containsWord(otherWord) && result.equals(selectionValues[0])) {
				Word currentWord = new Word(otherWord);
				currentWord.add(newWord);
				currentWord.addObserver(dictionary);
				addWords(currentWord);
			} else if (!dictionary.containsOtherWord(otherWord) && result.equals(selectionValues[1])) {
				dictionary.addOtherWord(otherWord);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			JOptionPane.showMessageDialog(this,
					"You have to add a description for each word you enter and is not in the dictionary.");
			String word = checkInputWord();
			Word newWord = new Word(word);
			newWord.addObserver(dictionary);
			addWords(newWord);
			updateTable();
		} else if (e.getSource() == delete) {
			String word = checkInputWord();
			Word currentWord = dictionary.getWord(word);
			dictionary.removeWord(currentWord);
			updateTable();
		} else if (e.getSource() == search) {
			String searchWord = String.valueOf(JOptionPane.showInputDialog(this,
					"Give the word that you want to search.The character ? substitute a character and * substitute any string.",
					"Search", JOptionPane.INFORMATION_MESSAGE)).toLowerCase();
			new SearchView(dictionary.getSearchResults(searchWord));
		}
	}
}
