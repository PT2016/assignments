package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.Dictionary;

public class DictionaryFrame implements ActionListener {
	JButton btnAddWord ;
	JButton btnRemoveWord ;
	JTextField textWord;
	JTextField textSynonym;
	JTextField textRWord;
	JTextField textRSynonym;
	JTextField textSearchWord;
	JButton btnAddSynonym;
	JButton btnRemoveSynonym;
	JButton btnSearchWord;
	JButton btnListAll;
	Dictionary dictionary;
	JTextArea results;
	public DictionaryFrame(Dictionary dictionary){
//		ObjectMapper mapper = new ObjectMapper();
        this.dictionary=dictionary;
        dictionary.readFromJson();
		JFrame frame=new JFrame();
		JPanel framewithin=new JPanel(new GridLayout(3,1));
		JPanel upperframe=new JPanel();
		
		ImageIcon myPicture = new ImageIcon(getClass().getResource("titlu.jpg"));
		JLabel picLabel = new JLabel(myPicture);
		
		upperframe.add(picLabel);
		framewithin.add(upperframe);
		JPanel middleframe=new JPanel();
		middleframe.setLayout(null);
		
		textWord = new JTextField();
		textSynonym = new JTextField();
		textRWord = new JTextField();
		textRSynonym = new JTextField();
		textSearchWord = new JTextField();

		// create JButtons
	
		btnAddWord = new JButton("Add Word");
		btnAddWord.setBounds(10,0,150, 25);
		btnAddWord.setBackground(new Color(255, 255, 230));
		btnAddWord.addActionListener(this);
		middleframe.add(btnAddWord);
		btnRemoveWord = new JButton("Remove Word");
		btnRemoveWord.setBounds(10,30,150, 25);
		btnRemoveWord.setBackground(new Color(255, 255, 230));
		btnRemoveWord.addActionListener(this);
	    middleframe.add(btnRemoveWord);
		btnAddSynonym = new JButton("Add Synonym");
		btnAddSynonym.setBounds(320,0, 150, 25);
		btnAddSynonym.setBackground(new Color(255, 255, 230));
		btnAddSynonym.addActionListener(this);
		middleframe.add(btnAddSynonym);
		btnRemoveSynonym = new JButton("Remove Synonym");
		btnRemoveSynonym.setBackground(new Color(255, 255, 230));
		btnRemoveSynonym.setBounds(320,30,150, 25);
		btnRemoveSynonym.addActionListener(this);
		middleframe.add(btnRemoveSynonym);
		btnSearchWord= new JButton("Search");
		btnSearchWord.setBackground(new Color(255, 255, 230));
		btnSearchWord.setBounds(10,60,150, 25);
		btnSearchWord.addActionListener(this);
		middleframe.add(btnSearchWord);
		btnListAll= new JButton("List All");
		btnListAll.setBackground(new Color(255, 255, 230));
		btnListAll.setBounds(320,60,300, 25);
		btnListAll.addActionListener(this);
		middleframe.add( btnListAll);
		
		textWord.setBounds(165, 0, 150, 25);
		textWord.setBackground(new Color(255, 255, 230));
		middleframe.add( textWord);
		textSynonym.setBounds(475,0, 150, 25);
		textSynonym.setBackground(new Color(255, 255, 230));
		middleframe.add( textSynonym);
		textRWord.setBounds(165, 30, 150, 25);
		textRWord.setBackground(new Color(255, 255, 230));
		middleframe.add(textRWord);
		textRSynonym.setBounds(475,30, 150, 25);
		textRSynonym.setBackground(new Color(255, 255, 230));
		middleframe.add(textRSynonym);
		textSearchWord.setBounds(165, 60, 150, 25);
		textSearchWord.setBackground(new Color(255, 255, 230));
		middleframe.add(textSearchWord);
	
		
		
		framewithin.add(middleframe);

		JPanel lowerframe=new JPanel();
		lowerframe.setLayout(null);
		results=new JTextArea();
		JScrollPane pane = new JScrollPane(results);
		pane.setBounds(10, 0, 610, 220);
		pane.getViewport().setBackground(new Color(232, 189, 128));
		pane.setBackground(new Color(232, 189, 128));
		
		lowerframe.add(pane);
		
		framewithin.add(lowerframe);
		frame.add(framewithin)
;		frame.setSize(650, 700);
	//	frame.getContentPane().setBackground(new Color(162, 104, 42));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAddWord){	
			String word=textWord.getText();
			dictionary.addWord(word);
			if (dictionary.getDictionaryMap().keySet().contains(word)) JOptionPane.showMessageDialog(null, "Successful operation");
			else JOptionPane.showMessageDialog(null, "Something went wrong");
			dictionary.writeToJson();
		}
		if (e.getSource()==btnAddSynonym){
			String word=textWord.getText();
			String synonym=textSynonym.getText();
			dictionary.addSynonym(word, synonym);
			if (dictionary.getDictionaryMap().get(word).contains(synonym)) JOptionPane.showMessageDialog(null, "Successful operation");
			else JOptionPane.showMessageDialog(null, "Something went wrong");
			dictionary.writeToJson();
		}
		if (e.getSource()==btnRemoveWord){
			String word=textRWord.getText();
			dictionary.removeWord(word);
			if (dictionary.getDictionaryMap().containsKey(word)==false) JOptionPane.showMessageDialog(null, "Successful operation");
			else JOptionPane.showMessageDialog(null, "Something went wrong");
			dictionary.writeToJson();
		}
		if (e.getSource()==btnRemoveSynonym){
			String word=textRWord.getText();
			String synonym=textRSynonym.getText();
			dictionary.removeSynonym(word, synonym);
			if (dictionary.getDictionaryMap().get(word).contains(synonym)==false) JOptionPane.showMessageDialog(null, "Successful operation");
			else JOptionPane.showMessageDialog(null, "Something went wrong");
			dictionary.writeToJson();
		}
		if (e.getSource()==btnSearchWord){
			String word=textSearchWord.getText();
			HashSet<String> matches=dictionary.searchWord(word);
			String res=" ";
			Font ft=new Font("Arial",Font.BOLD,14);
			results.setFont(ft);
			for (String st:matches){
			    res=dictionary.displayWord(st)+"\n";
			    results.append(res);
			    System.out.println(dictionary.displayWord(st));
			}

		}
		if (e.getSource()==btnListAll){
			String res=" ";
			Font ft=new Font("Arial",Font.BOLD,14);
			results.setFont(ft);
			for (String st:dictionary.getDictionaryMap().keySet()){
				res=dictionary.displayWord(st)+ "\n";
				results.append(res);
			}
		
		}
	}
}
