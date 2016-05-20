package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DictionaryEntities.Dictionary;

public class ControlFrame extends CustomizedFrame {

	private static final long serialVersionUID = 4966610503382915137L;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 500;
	private static final int POSITION_X = 50;
	private static final int POSITION_Y = 100;

	private JButton addWordButton;
	private JButton removeWordButton;
	private JButton showContentButton;
	private JButton addSynonymButton;
	private JButton searchButton;
	private JButton cleanOutputButton;

	private JTextField inputWordField;
	private JTextField inputSynonymsField;

	private JLabel wordLabel;
	private JLabel synonymsLabel;

	private Dictionary dictionary;

	public ControlFrame() {
		this.adjustFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	void addComponents() {
		
		dictionary = new Dictionary();
		
		addWordButton = new JButton("Add Word");
		removeWordButton = new JButton("Remove Word");
		showContentButton = new JButton("Show Dictionary Content");
		addSynonymButton = new JButton("Add Synonym");
		searchButton = new JButton("Search Word");
		cleanOutputButton = new JButton("Clean Output");

		inputWordField = new JTextField(10);
		inputSynonymsField = new JTextField(25);

		wordLabel = new JLabel("Input a word   ");
		wordLabel.setFont(new Font(null, Font.BOLD + Font.ITALIC, 14));
		wordLabel.setForeground(Color.white);
		
		synonymsLabel = new JLabel("Input word's synonyms   ");
		synonymsLabel.setFont(new Font(null, Font.BOLD + Font.ITALIC, 14));
		synonymsLabel.setForeground(Color.white);
		
		addWordButton.addActionListener(new ButtonActions());
		removeWordButton.addActionListener(new ButtonActions());
		showContentButton.addActionListener(new ButtonActions());
		addSynonymButton.addActionListener(new ButtonActions());
		searchButton.addActionListener(new ButtonActions());
		cleanOutputButton.addActionListener(new ButtonActions());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;

		setLayout(new GridLayout(2, 1));

		/////////////// input panel /////////////////////////////////////////
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		inputPanel.setBackground(Color.gray.darker());

		gbc.anchor = GridBagConstraints.LINE_END;

		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(wordLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(synonymsLabel, gbc);

		gbc.anchor = GridBagConstraints.LINE_START;

		gbc.gridx = 1;
		gbc.gridy = 0;
		inputPanel.add(inputWordField, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		inputPanel.add(inputSynonymsField, gbc);
		/////////////////////////////////////////////////////////////////////////////////////

		///////////////// buttons panel //////////////////////////////////////
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		buttonsPanel.setBackground(Color.LIGHT_GRAY);
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonsPanel.add(addWordButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		buttonsPanel.add(removeWordButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		buttonsPanel.add(showContentButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		buttonsPanel.add(cleanOutputButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		buttonsPanel.add(addSynonymButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		buttonsPanel.add(searchButton, gbc);

		add(inputPanel);
		add(buttonsPanel);

	}
	
	@Override
	void setTheSize() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
	}

	@Override
	void setTheLocation() {
		setLocation(POSITION_X, POSITION_Y);
		
	}

	@Override
	void setTheTitle() {
		setTitle("Dictionary of English Synonyms !");
		
	}

	private class ButtonActions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(addWordButton)) {
				addWordAction();
			} else if (event.getSource().equals(removeWordButton)) {
				removeWordAction();
			} else if (event.getSource().equals(showContentButton)) {
				dictionary.showWords();
			} else if (event.getSource().equals(addSynonymButton)) {
				addSynonymAction();
			} else if (event.getSource().equals(searchButton)) {
				searchAction();
			} else if(event.getSource().equals(cleanOutputButton)) {
				OutputArea.getInstance().setText(null);
			}
		}

		private void addWordAction() {
			String inputWord = inputWordField.getText();

			List<String> synonyms = new ArrayList<String>();

			char[] inputSynonyms = inputSynonymsField.getText().toCharArray();
			String synonym = new String();
			for (Character chr : inputSynonyms) {
				if ((!chr.equals(' ')) && (!chr.equals('.'))) {
					synonym = synonym + chr;
				} else {
					if (!synonym.equals(null))
						synonyms.add(synonym);
					synonym = "";
				}
			}

			dictionary.addWord(inputWord, synonyms);

			inputWordField.setText(null);
			inputSynonymsField.setText(null);
		}

		private void removeWordAction() {
			String inputWord = inputWordField.getText();
			dictionary.removeWord(inputWord);
			
			inputWordField.setText(null);
		}
		
		private void addSynonymAction() {
			String inputWord = inputWordField.getText();
			String synonym = inputSynonymsField.getText();
			
			dictionary.addSynonym(synonym, inputWord);
			
			inputWordField.setText(null);
			inputSynonymsField.setText(null);
		}
		
		private void searchAction() {
			String inputWord = inputWordField.getText();
			dictionary.searchWord(inputWord);
			
			inputWordField.setText(null);
		}

	}
}
