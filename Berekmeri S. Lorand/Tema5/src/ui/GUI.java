/**
 * 
 */
package ui;

import javax.swing.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import model.Dictionary;

/**
 * @author Lorand
 *
 */
public class GUI implements ActionListener {

	private JFrame Frm;

	private JMenuBar meniu;

	private JMenu Dictionars;
	private JMenu search;
	private JMenu isConsistent;

	private JMenuItem showDictionars;
	private JMenuItem insertDictionar;
	private JMenuItem deleteDictionar;
	private JMenuItem updateDictionar;
	private JMenuItem searchCat;
	private JMenuItem sConsistent;

	private JScrollPane scrollPane;
	private JTable table;

	private JLabel insertWord;
	private JLabel insertSynonym;
	private JLabel insertNewSynonym;
	private JLabel inserare;
	private JLabel actualizare;
	private JTextPane actualizareNota;
	private JTextPane consistent;
	private JTextPane consistentaNota;
	private JLabel stergere;
	private JLabel cautare;

	private JTextField insertWordText;
	private JTextField insertSynonymText;
	private JTextField insertNewSynonymText;
	private JButton insertButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JButton searchButton;

	protected Dictionary dictionary;

	public GUI() {
		i();
	}

	private void i() {

		Frm = new JFrame("Synonyms Dictionary");
		Frm.setBounds(200, 30, 500, 400);
		Frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frm.getContentPane().setLayout(null);

		meniu = new JMenuBar();
		Frm.setJMenuBar(meniu);

		scrollPane = new JScrollPane();

		Dictionars = new JMenu("Dictionary");
		Dictionars.setMnemonic(KeyEvent.VK_F);
		meniu.add(Dictionars);

		showDictionars = new JMenuItem("Dictionary");
		showDictionars.setMnemonic(KeyEvent.VK_E);
		showDictionars.addActionListener(this);
		Dictionars.add(showDictionars);

		insertDictionar = new JMenuItem("Add word");
		insertDictionar.setMnemonic(KeyEvent.VK_E);
		insertDictionar.addActionListener(this);
		Dictionars.add(insertDictionar);

		updateDictionar = new JMenuItem("Update word");
		updateDictionar.setMnemonic(KeyEvent.VK_E);
		updateDictionar.addActionListener(this);
		Dictionars.add(updateDictionar);

		deleteDictionar = new JMenuItem("Delete word");
		deleteDictionar.setMnemonic(KeyEvent.VK_E);
		deleteDictionar.addActionListener(this);
		Dictionars.add(deleteDictionar);

		insertSynonym = new JLabel("Synonym");
		insertSynonym.setVisible(false);
		insertSynonym.setBounds(40, 170, 100, 20);
		Frm.getContentPane().add(insertSynonym);

		insertWord = new JLabel("Word");
		insertWord.setVisible(false);
		insertWord.setBounds(40, 130, 100, 20);
		Frm.getContentPane().add(insertWord);

		inserare = new JLabel("Please insert the word and his synonym!");
		inserare.setVisible(false);
		inserare.setBounds(60, 40, 500, 50);
		Frm.getContentPane().add(inserare);

		insertSynonymText = new JTextField();
		insertSynonymText.setVisible(false);
		insertSynonymText.setBounds(115, 170, 150, 30);
		Frm.getContentPane().add(insertSynonymText);

		insertWordText = new JTextField();
		insertWordText.setVisible(false);
		insertWordText.setBounds(115, 130, 150, 30);
		Frm.getContentPane().add(insertWordText);

		insertButton = new JButton("Insert");
		insertButton.setBounds(200, 225, 120, 30);
		insertButton.setVisible(false);
		insertButton.setMnemonic(KeyEvent.VK_E);
		insertButton.addActionListener(this);
		Frm.getContentPane().add(insertButton);

		actualizare = new JLabel("Insert the word which you want to update!");
		actualizare.setVisible(false);
		actualizare.setBounds(60, 40, 500, 50);
		Frm.getContentPane().add(actualizare);

		actualizareNota = new JTextPane();
		actualizareNota.setText(
				"! Pentru a actualiza un cuvant din dictionar e nevoie de introduceti cuvantul la care doriti sa ii schimbati sinonimul, sinonimul vechi din dictionar si sinonimul nou");
		actualizareNota.setVisible(false);
		actualizareNota.setBounds(10, 285, 440, 100);
		actualizareNota.setOpaque(false);
		Frm.getContentPane().add(actualizareNota);

		updateButton = new JButton("Update");
		updateButton.setBounds(200, 250, 120, 30);
		updateButton.setVisible(false);
		updateButton.setMnemonic(KeyEvent.VK_E);
		updateButton.addActionListener(this);
		Frm.getContentPane().add(updateButton);

		insertNewSynonym = new JLabel("New synonym");
		insertNewSynonym.setVisible(false);
		insertNewSynonym.setBounds(40, 210, 100, 20);
		Frm.getContentPane().add(insertNewSynonym);

		insertNewSynonymText = new JTextField();
		insertNewSynonymText.setVisible(false);
		insertNewSynonymText.setBounds(115, 210, 150, 30);
		Frm.getContentPane().add(insertNewSynonymText);

		deleteButton = new JButton("Delete");
		deleteButton.setBounds(200, 250, 120, 30);
		deleteButton.setVisible(false);
		deleteButton.setMnemonic(KeyEvent.VK_E);
		deleteButton.addActionListener(this);
		Frm.getContentPane().add(deleteButton);

		stergere = new JLabel("Write the word which you want to delete from the dictionary");
		stergere.setVisible(false);
		stergere.setBounds(60, 40, 500, 50);
		Frm.getContentPane().add(stergere);

		search = new JMenu("Search");
		search.setMnemonic(KeyEvent.VK_F);
		meniu.add(search);

		searchCat = new JMenuItem("Search word");
		searchCat.setMnemonic(KeyEvent.VK_E);
		searchCat.addActionListener(this);
		search.add(searchCat);

		searchButton = new JButton("Search");
		searchButton.setBounds(200, 250, 120, 30);
		searchButton.setVisible(false);
		searchButton.setMnemonic(KeyEvent.VK_E);
		searchButton.addActionListener(this);
		Frm.getContentPane().add(searchButton);

		cautare = new JLabel("Insert what you want to search");
		cautare.setVisible(false);
		cautare.setBounds(60, 40, 500, 50);
		Frm.getContentPane().add(cautare);

		isConsistent = new JMenu("Properties");
		isConsistent.setMnemonic(KeyEvent.VK_F);
		isConsistent.addActionListener(this);

		sConsistent = new JMenuItem("It is consistent?");
		sConsistent.setMnemonic(KeyEvent.VK_E);
		sConsistent.addActionListener(this);
		isConsistent.add(sConsistent);

		consistent = new JTextPane();
		consistent.setVisible(false);
		consistent.setBounds(60, 120, 440, 100);
		consistent.setOpaque(false);
		Frm.getContentPane().add(consistent);

		consistentaNota = new JTextPane();
		consistentaNota.setText(
				"!Un dictionar este consistent atunci cand putem gasi cuvant=sinonim si sinonim= cuvant. De exemplu: trist=suparat, dar si suparat=trist");
		consistentaNota.setVisible(false);
		consistentaNota.setBounds(10, 285, 440, 100);
		consistentaNota.setOpaque(false);
		Frm.getContentPane().add(consistentaNota);

		meniu.add(isConsistent);

		dictionary = Dictionary.getInstance();
		dictionary.readsynonyms();

		Frm.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == showDictionars) {
			clearScreen();

			table = showSynonyms();
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(85, 60, 300, 270);
			scrollPane.setVisible(true);
			Frm.getContentPane().add(scrollPane);

		}

		if (e.getSource() == insertDictionar) {
			clearScreen();

			insertWord.setVisible(true);
			insertSynonym.setVisible(true);

			inserare.setVisible(true);
			insertWordText.setVisible(true);
			insertSynonymText.setVisible(true);

			insertButton.setVisible(true);

		}

		if (e.getSource() == insertButton) {

			String word = insertWordText.getText();
			String synonym = insertSynonymText.getText();

			dictionary.addsysnonyms(word, synonym);
			dictionary.writesynonyms();

		}

		if (e.getSource() == updateDictionar) {

			clearScreen();
			insertWord.setVisible(true);
			insertSynonym.setVisible(true);
			insertNewSynonym.setVisible(true);
			insertWordText.setVisible(true);
			insertSynonymText.setVisible(true);
			insertNewSynonymText.setVisible(true);
			actualizare.setVisible(true);
			actualizareNota.setVisible(true);
			updateButton.setVisible(true);

		}

		if (e.getSource() == updateButton) {
			String word = insertWordText.getText();
			String oldSynonym = insertSynonymText.getText();
			String newSynonym = insertNewSynonymText.getText();

			dictionary.updatesynonyms(word, oldSynonym, newSynonym);

			dictionary.writesynonyms();
		}

		if (e.getSource() == deleteDictionar) {
			clearScreen();
			insertWord.setVisible(true);
			insertWordText.setVisible(true);
			deleteButton.setVisible(true);
			stergere.setVisible(true);

		}

		if (e.getSource() == deleteButton) {
			String word = insertWordText.getText();

			dictionary.deletesysnonyms(word);
			dictionary.writesynonyms();
		}

		if (e.getSource() == searchCat) {
			clearScreen();
			insertWord.setVisible(true);
			insertWordText.setVisible(true);
			searchButton.setVisible(true);
			cautare.setVisible(true);

		}

		if (e.getSource() == searchButton) {
			table = search(insertWordText.getText());
			if (table == null) {

				JOptionPane.showMessageDialog(null, "Word does not exist");
			} else {
				clearScreen();
				scrollPane = new JScrollPane(table);
				scrollPane.setBounds(85, 60, 300, 100);
				scrollPane.setVisible(true);
				Frm.getContentPane().add(scrollPane);
			}

		}
		if (e.getSource() == sConsistent) {
			clearScreen();
			consistentaNota.setVisible(true);
			if (dictionary.isConsistent() == true) {

				consistent.setVisible(true);
				consistent.setText("The dictionary is consistent");
			} else {
				consistent.setVisible(true);
				consistent.setText("The dictionary is not consistent");
			}
		}

	}

	protected DefaultTableModel buildTableModel(Dictionary dictionary) {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Word");
		columnNames.add("Synonym");
		Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();

		Set<Map.Entry<String, String>> entrySet = dictionary.dictionary.entrySet();
		Iterator<Map.Entry<String, String>> i = entrySet.iterator();

		while (i.hasNext()) {
			Map.Entry<String, String> word = i.next();
			String key = word.getKey();
			String value = word.getValue();
			Vector<Object> list = new Vector<Object>();
			list.add(key);
			list.add(value);
			rowData.add(list);

		}
		return new DefaultTableModel(rowData, columnNames);

	}

	private JTable showSynonyms() {
		JTable table = new JTable(buildTableModel(dictionary));

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		return table;

	}

	private JTable search(String word) {
		JTable table;

		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Word");
		columnNames.add("Synonym");
		Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();

		String searchedWord = word;

		searchedWord = searchedWord.replace("*", ".*");
		searchedWord = searchedWord.replace("?", ".?");

		Iterator<String> i = dictionary.dictionary.keySet().iterator();

		while (i.hasNext()) {

			String key = i.next();

			if (key.matches(searchedWord)) {
				Vector<Object> list = new Vector<Object>();
				String value = dictionary.dictionary.get(key);
				list.add(key);
				list.add(value);

				rowData.add(list);
			}
		}

		if (rowData.size() == 0)
			return null;

		table = new JTable(new DefaultTableModel(rowData, columnNames));

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		return table;

	}

	private void clearScreen() {

		scrollPane.setVisible(false);
		insertWord.setVisible(false);
		insertSynonym.setVisible(false);
		inserare.setVisible(false);

		insertWordText.setVisible(false);
		insertSynonymText.setVisible(false);

		insertButton.setVisible(false);
		insertNewSynonym.setVisible(false);

		insertNewSynonymText.setVisible(false);
		actualizare.setVisible(false);
		actualizareNota.setVisible(false);
		updateButton.setVisible(false);
		deleteButton.setVisible(false);
		stergere.setVisible(false);
		searchButton.setVisible(false);
		cautare.setVisible(false);
		consistent.setVisible(false);
		consistentaNota.setVisible(false);

	}

}
