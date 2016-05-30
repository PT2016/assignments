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

import controller.InOut;
import model.Dictionary;
import model.Word;

public class Gui extends JFrame implements ActionListener {
	private JPanel operations;
	private JButton add, delete, search, displayOthers;
	private JTable table, jT;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Object[][] rows;
	private Object[] columns = { "Word", "Description" };
	private Dictionary dictionary;
	private String[] selectionValues = { "Word with explanation", "Word without explanation" };
	private String initialSelection = "Word with explanation.";
	private InOut inOut;

	public Gui() {
		this.setTitle("Dictionary");
		this.setLocationRelativeTo(null);
		dictionary = Dictionary.getInstance();
		dictionary.start();
		inOut = new InOut();
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

		displayOthers = new JButton("Display words without description");
		displayOthers.addActionListener(this);

		operations.add(add);
		operations.add(delete);
		operations.add(search);
		operations.add(displayOthers);
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
			if (result.equals(selectionValues[0])) {
				if (!dictionary.containsWord(otherWord)) {
					Word currentWord = new Word();
					currentWord.setWord(otherWord);
					currentWord.add(newWord);
					currentWord.addObserver(dictionary);
					addWords(currentWord);
				} else {
					Word existingWord = new Word();
                    existingWord.setWord(newWord.getWord());
					dictionary.getWord(otherWord).add(existingWord);
				}
			} else if (!dictionary.containsOtherWord(otherWord) && result.equals(selectionValues[1])) {
				dictionary.addOtherWord(otherWord);
			}
		}
	}

	private void displayOtherWords() {
		Object[] columns = { "Words without description" };
		Object[][] rows = null;
		DefaultTableModel tableModel = new DefaultTableModel(rows, columns);
		JTable table = new JTable();
		table.setModel(tableModel);
		table.setEnabled(false);
		Set<String> otherWords = dictionary.getOtherWords();
		if (otherWords.size() != 0) {
			rows = new Object[otherWords.size()][1];
			Iterator<String> it = otherWords.iterator();
			while (it.hasNext()) {
				String newWord = it.next();
				Object[] row = { newWord };
				tableModel.addRow(row);
			}
		}
		JOptionPane.showMessageDialog(this, new JScrollPane(table));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			JOptionPane.showMessageDialog(this,
					"You have to add a description for each word you enter and is not in the dictionary.");
			String word = checkInputWord();
			Word newWord = new Word();
			newWord.setWord(word);
			newWord.addObserver(dictionary);
			addWords(newWord);
			inOut.writeDictionary();
			inOut.writeOtherWords();
			updateTable();
		} else if (e.getSource() == delete) {
			String word = checkInputWord();
			Word currentWord = dictionary.getWord(word);
			dictionary.removeWord(currentWord);
			inOut.writeDictionary();
			inOut.writeOtherWords();
			updateTable();
		} else if (e.getSource() == search) {
			String searchWord = String.valueOf(JOptionPane.showInputDialog(this,
					"Give the word that you want to search.The character ? substitute a character and * substitute any string.",
					"Search", JOptionPane.INFORMATION_MESSAGE)).toLowerCase();
			new SearchView(dictionary.getSearchResults(searchWord));
		} else if (e.getSource() == displayOthers) {
			displayOtherWords();
		}
	}
}
