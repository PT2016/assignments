package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;
import models.Dictionary;

public class View implements Observer {

	private String dictionaryUpdate;
	private Dictionary dictionary;

	JTextField word = new JTextField();
	JButton search = new JButton("Search word");
	JButton addWord = new JButton("Add word");
	JButton addSynonim = new JButton("Add synonim");
	JTextArea resultText = new JTextArea();
	JButton viewWords = new JButton("View words");
	JButton deleteWord = new JButton("Delete word");

	public View() {
		JFrame frame = new JFrame("Synonims");
		frame.setLayout(new BorderLayout());
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.setBackground(Color.WHITE);

		search.setActionCommand("search");
		addWord.setActionCommand("addWord");
		addSynonim.setActionCommand("addSynonim");

		panel.add(word);
		panel.add(search);
		panel.add(addWord);
		panel.add(addSynonim);
		
		JPanel bottom = new JPanel();
		
		viewWords.setActionCommand("viewWords");
		deleteWord.setActionCommand("deleteWord");
		
		bottom.add(viewWords);
		bottom.add(deleteWord);

		panel.setVisible(true);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(resultText, BorderLayout.CENTER);
		frame.add(bottom, BorderLayout.SOUTH);

	}

	@Override
	public void update(Observable obs, Object obj) {
		dictionaryUpdate = "\n" + "Updated dictionary";
	}

	public String getUpdate() {
		return dictionaryUpdate;
	}

	public void addController(Controller controller) {
		word.addActionListener(controller);
		search.addActionListener(controller);
		addWord.addActionListener(controller);
		addSynonim.addActionListener(controller);
		viewWords.addActionListener(controller);
		deleteWord.addActionListener(controller);
	}

	public void addModel(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public String getWord() {
		return word.getText();
	}

	public void updateResultText(ArrayList<String> text) {
		resultText.setText("");
		;
		for (String s : text) {
			resultText.append(s);
		}

	}

}
