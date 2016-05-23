package GUI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Entities.Synonym;
import Helper.UserInputChecker;

public class Frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton populateBtn, addBtn, removeBtn, existsBtn, saveBtn, searchBtn, synonymBtn, clearBtn;
	private JTextField txtSearch, txtSynonym;
	private JRadioButton userButton, managerButton;
	private ButtonGroup group;
	boolean user, manager;
	private JLabel backgroundLbl;
	UserInputChecker inputChecker;
	Synonym syno;

	public Frame() {
		super("Thesaurus");
		inputChecker = new UserInputChecker();
		syno = new Synonym();

		ImageIcon iconBank = new ImageIcon("Resources/wordImage.png");
		Image takeImage = iconBank.getImage();
		Image resizedImg = takeImage.getScaledInstance(900, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(resizedImg);
		backgroundLbl = new JLabel();
		backgroundLbl.setIcon(newImage);
		setContentPane(backgroundLbl);
		pack();

		populateBtn = new JButton("Populate");
		addBtn = new JButton("Add");
		removeBtn = new JButton("Remove");
		existsBtn = new JButton("Find word");
		saveBtn = new JButton("Save");
		searchBtn = new JButton("Search");
		synonymBtn = new JButton("Synonym of: ");
		clearBtn = new JButton("Clean ");

		populateBtn.setBounds(100, 150, 120, 25);
		addBtn.setBounds(530, 230, 160, 25);
		removeBtn.setBounds(100, 210, 120, 25);
		existsBtn.setBounds(700, 180, 120, 25);
		saveBtn.setBounds(100, 180, 120, 25);
		searchBtn.setBounds(700, 150, 120, 25);
		synonymBtn.setBounds(400, 150, 120, 25);
		clearBtn.setBounds(400, 180, 120, 25);

		populateBtn.addActionListener(this);
		addBtn.addActionListener(this);
		removeBtn.addActionListener(this);
		existsBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		clearBtn.addActionListener(this);

		userButton = new JRadioButton("User");
		userButton.addActionListener(this);
		userButton.setBounds(20, 20, 60, 30);
		getContentPane().add(userButton);

		managerButton = new JRadioButton("Dictionary manager");
		managerButton.addActionListener(this);
		managerButton.setBounds(700, 15, 150, 30);
		getContentPane().add(managerButton);

		group = new ButtonGroup();
		user = false;
		manager = false;
		group.add(userButton);
		group.add(managerButton);
		txtSearch = new JTextField();
		txtSynonym = new JTextField();
		txtSearch.setBounds(530, 150, 160, 25);
		txtSynonym.setBounds(530, 180, 160, 40);

		setLayout(null);
		add(populateBtn);
		add(addBtn);
		add(removeBtn);
		add(existsBtn);
		add(saveBtn);
		add(searchBtn);
		add(synonymBtn);
		add(clearBtn);
		add(txtSearch);
		add(txtSynonym);

		setVisible(true);
		setSize(900, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object c = (Object) event.getSource();

		if (c == userButton) {
			user = true;
			manager = false;
		}

		if (c == managerButton) {
			user = false;
			manager = true;
		}
		/// user
		if (user == true) {
			populateBtn.setEnabled(false);
			addBtn.setEnabled(false);
			removeBtn.setEnabled(false);
			saveBtn.setEnabled(false);

			existsBtn.setEnabled(true);
			searchBtn.setEnabled(true);
			clearBtn.setEnabled(true);

		} else if (manager == true) {
			populateBtn.setEnabled(true);
			addBtn.setEnabled(true);
			removeBtn.setEnabled(true);
			saveBtn.setEnabled(true);

			existsBtn.setEnabled(true);
			searchBtn.setEnabled(true);
			clearBtn.setEnabled(true);
		}

		if (event.getSource() == addBtn) {
			String word = inputChecker.checkString(txtSearch);
			ArrayList<String> synonyms = inputChecker.checkArrayOfStrings(txtSynonym);

			if (word != null && synonyms != null) {
				syno.addNewWord(word, synonyms);
				// syno.maintainConsistency(word, synonyms);
				JOptionPane.showMessageDialog(this, "New word added: " + word);
			}
		}

		if (event.getSource() == removeBtn) {
			String word = inputChecker.checkString(txtSearch);
			syno.removeWord(word);
			JOptionPane.showMessageDialog(this, "Word removed: " + word);

		}

		if (event.getSource() == existsBtn) {
			ArrayList<String> result = new ArrayList<String>();
			String word = inputChecker.checkString(txtSearch);
			String newString = " ";
			result = syno.searchWord(word);
			for (int i = 0; i < result.size(); i++) {

				// System.out.println("after search:\n");
				System.out.println(result.get(i));
				newString += result.get(i) + ",";
			}
			newString = newString.substring(0, newString.length() - 1);
			txtSynonym.setText(newString);
		}

		if (event.getSource() == populateBtn) {
			syno.populateDictionary("dictionary.json");
			JOptionPane.showMessageDialog(this, "Dictionary populated!");
		}

		if (event.getSource() == saveBtn) {
			syno.saveToJson();
		}

		if (event.getSource() == clearBtn) {
			txtSearch.setText(" ");
			txtSynonym.setText(" ");
		}

		if (event.getSource() == searchBtn) {
			ArrayList<String> result = new ArrayList<String>();
			String word = inputChecker.checkString(txtSearch);
			String newString = " ";
			result = syno.searchSynonyms(word);
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					System.out.println(result.get(i));
					newString += result.get(i) + ",";
				}
				newString = newString.substring(0, newString.length() - 1);
				txtSynonym.setText(newString);
			}
			else{
				JOptionPane.showMessageDialog(this, "Sorry! This word does not exist in the dictionary...");
			}
		}

	}

}
