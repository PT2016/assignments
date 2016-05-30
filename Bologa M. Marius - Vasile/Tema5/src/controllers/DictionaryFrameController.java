package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import models.Dictionary;
import views.DictionaryFrame;
import views.MessageDialogs;

public class DictionaryFrameController {
	private DictionaryFrame frame = new DictionaryFrame();
	// illegal construct
	// Compile Time Error: The constructor Dictionary() is not visible
	// private Dictionary dictionary = new Dictionary();

	private Dictionary dictionary = Dictionary.getInstance();

	public DictionaryFrameController() {
		frame.setAddWordActionListener(new AddWordButtonActionListener());
		frame.setAddSynButtonActionListener(new AddSynButtonActionListener());
		frame.setDelWordButtonActionListener(new DelWordButtonActionListener());
		frame.setDeleteSynButtonActionListener(new DeleteSynButtonActionListener());
		frame.setSearchActionListener(new SearchButtonActionListener());
		frame.setPopulateActionListener(new PopulateButtonActionListener());
		this.dictionary = JSONParser.readFromJson();
		// printDictionary();
	}

	public void printDictionary() {
		DefaultTableModel dm = ((DefaultTableModel) DictionaryFrame.getTable().getModel());
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
		Iterator<Entry<String, ArrayList<String>>> iterator = dictionary.getDictionary().entrySet().iterator();
		while (iterator.hasNext()) {
			String syn = "";
			int index = 0;
			Entry<String, ArrayList<String>> entry = iterator.next();
			for (models.Iterator iter = dictionary.getIterator(entry.getKey()); iter.hasNext();) {
				syn += (index + 1) + "." + (String) iter.next() + " ";
				index++;
			}
			Object[] row = { entry.getKey(), syn };
			((DefaultTableModel) DictionaryFrame.getTable().getModel()).addRow(row);

		}
	}

	public class AddWordButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String word[] = MessageDialogs.addWordWindow();
			String[] syn = splitWord(word[1]);
			dictionary.addWordAndSynonim(word[0], new ArrayList<String>(Arrays.asList(syn)));
			JSONParser.writeInJson(dictionary);
			printDictionary();
		}
	}

	public class AddSynButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String word[] = MessageDialogs.addWordWindow();
			dictionary.addSynonim(word[0], word[1]);
			JSONParser.writeInJson(dictionary);
			printDictionary();
		}
	}

	public class DelWordButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String word = MessageDialogs.delWordWindow();
			dictionary.deleteWord(word);
			JSONParser.writeInJson(dictionary);
			printDictionary();
		}
	}

	public class DeleteSynButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String word[] = MessageDialogs.addWordWindow();
			dictionary.deleteSynonim(word[0], word[1]);
			JSONParser.writeInJson(dictionary);
			printDictionary();

		}
	}

	public class SearchButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String word = MessageDialogs.delWordWindow();
			ArrayList<String> data = dictionary.search(word);
			if (data.size() == 0) {
				JOptionPane.showMessageDialog(null, "There is no such word in the dictionary!", "Info",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				String res = "";
				for (int i = 0; i < data.size(); i++) {
					res = res + data.get(i) + "-> ";
					for (models.Iterator iter = dictionary.getIterator(data.get(i)); iter.hasNext();) {
						res = res + (String) iter.next() + ", ";
					}
					res = res.substring(0, res.length() - 2);
					res = res + "\n";
				}
				JOptionPane.showMessageDialog(null, res, "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public class PopulateButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			printDictionary();
		}
	}
	public static String[] splitWord(String s) {
		String[] temp = s.split(", ");
		return temp;
	}
}
