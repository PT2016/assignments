package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui {
	private JButton addWord = new JButton("Add word");
	private JButton addSyn = new JButton("Add synonym for word");
	private JButton removeWord = new JButton("Remove word");
	private JButton searchWord = new JButton("Search word");
	private JButton printDictionary = new JButton("Print dictionary");
	private JButton ser = new JButton("Serialize");
	private JButton deser = new JButton("Deserialize");
	
	public Gui(){
		JFrame frame = new JFrame("Dictionary");
		JPanel panel = new JPanel();
		
		panel.add(deser);
		panel.add(addWord);
		panel.add(removeWord);
		panel.add(addSyn);
		panel.add(searchWord);
		panel.add(printDictionary);
		panel.add(ser);
		panel.setLayout(new GridLayout(7,1));
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	public JButton getAddButton(){
		return this.addWord;
	}
	
	public JButton getRemoveButton(){
		return this.removeWord;
	}
	
	public JButton getSearchWord(){
		return this.searchWord;
	}
	
	public JButton getPrintButton(){
		return this.printDictionary;
	}
	
	public JButton getAddSynButton(){
		return this.addSyn;
	}
	
	public JButton getSerButton(){
		return this.ser;
	}
	
	public JButton getDeserButton(){
		return this.deser;
	}
}
