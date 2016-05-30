package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.codehaus.jackson.map.ObjectMapper;

import models.Dictionary;
import view.View;

public class Controller implements ActionListener {

	Dictionary dictionary;
	View view;

	ObjectMapper mapper = new ObjectMapper();

	public Controller() {
		try {
			InputStream input = new FileInputStream("dictionary.json");
			dictionary = mapper.readValue(input, Dictionary.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("search")) {
			String word = view.getWord();
			ArrayList<String> result = dictionary.searchWord(word);
			view.updateResultText(result);
		} else if (e.getActionCommand().equals("addWord")) {
			String word = JOptionPane.showInputDialog("Enter the word");
			dictionary.addWord(word);
			view.update(dictionary, view);

			try {
				mapper.writeValue(new FileOutputStream("dictionary.json"), dictionary);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, view.getUpdate());
		} else if (e.getActionCommand().equals("addSynonim")) {
			String word = JOptionPane.showInputDialog("Enter word");
			String synonim = JOptionPane.showInputDialog("Enter synonim");
			if (dictionary.searchWord(word) != null) {
				dictionary.addSynonim(word, synonim);
			} else {
				dictionary.addWord(word);
				dictionary.addSynonim(word, synonim);
			}

			view.update(dictionary, view);

			try {
				mapper.writeValue(new FileOutputStream("dictionary.json"), dictionary);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, view.getUpdate());
		} else if (e.getActionCommand().equals("viewWords")) {
			ArrayList<String> result = new ArrayList<String>();
			for (String word : dictionary.getWords().keySet()) {
				result.add(word.toUpperCase());
				result.add("\n");
				for (String synonim : dictionary.getWords().get(word)) {
					result.add(synonim);
					result.add("\n");
				}
			}
			view.updateResultText(result);
		} else if (e.getActionCommand().equals("deleteWord")) {
			String word = JOptionPane.showInputDialog("Enter word");
			dictionary.deleteWord(word);
			view.update(dictionary, view);

			try {
				mapper.writeValue(new FileOutputStream("dictionary.json"), dictionary);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, view.getUpdate());
		}

	}

	public void addModel(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public void addView(View view) {
		this.view = view;
	}

}
