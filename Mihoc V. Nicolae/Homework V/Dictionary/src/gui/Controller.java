package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import Dictionary.Dictionary;
import exception.AddWordException;
import exception.DefineException;
import exception.RemoveWordException;

public class Controller {
	private Dictionary d;
	public Controller(){
		
		Gui g = new Gui();
		d = Dictionary.getInstance();
		
		g.getDeserButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d.populateDictionary();
			}
		});
		g.getAddButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = (String)JOptionPane.showInputDialog("The word you want to add: ");
				try {
					d.addWord(s);
					JOptionPane.showMessageDialog(null, "The word '"+s+"' was added.");
				} catch (AddWordException e1) {
					JOptionPane.showMessageDialog(null,"Word already exits!");
					e1.printStackTrace();
				}
			}
		});
		
		g.getSearchWord().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = (String)JOptionPane.showInputDialog("The word you want to search");
				ArrayList<String> aux = new ArrayList<String>();
				aux = d.findWord(s);
				JFrame frame = new JFrame("Search result");
				JTextArea text = new JTextArea();
				JScrollPane scroll = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.add(scroll);
				text.setLineWrap(true);
				text.setText(aux.toString());
				frame.pack();
				frame.setSize(400,300);
			}
		});
		
		g.getPrintButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame tableFrame = new JFrame();
				JPanel tablePanel = new JPanel();
				String[] columnName = {"Word","Synonyms"};
				DefaultTableModel table = new DefaultTableModel(columnName,0);
				Set<String> treeKeys = d.getTreeMap().keySet();
				JTable t = new JTable();
				for(String aux : treeKeys){
					String word = aux;
					ArrayList<String> syn = new ArrayList<>();
					syn = d.getSynonyms(word);
					Object[] data = {word,syn.toString()};
					table.addRow(data);
					t.setModel(table);
				}
				JScrollPane scroll = new JScrollPane(t);
				tablePanel.add(scroll);
				tableFrame.getContentPane().add(BorderLayout.CENTER,tablePanel);
				tableFrame.pack();
				tableFrame.setVisible(true);
			}
		});
		
		g.getAddSynButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = (String)JOptionPane.showInputDialog("Add synonym for: ");
				String syn = (String)JOptionPane.showInputDialog("The synonym you want to add: ");
				try {
					d.defineWord(s, syn);
					JOptionPane.showMessageDialog(null, "Synonym added.");
				} catch (DefineException e1) {
					JOptionPane.showMessageDialog(null,"The word does not exist in dictionary!");
					e1.printStackTrace();
				}
			}
		});
		
		g.getRemoveButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = (String)JOptionPane.showInputDialog("The word you want to delete: ");
				try {
					d.removeWord(s);
					JOptionPane.showMessageDialog(null, "The word '"+s+"' was deleted.");
				} catch (RemoveWordException e1) {
					JOptionPane.showMessageDialog(null,"Word does not exit!");
					e1.printStackTrace();
				}
			}
		});
		
		g.getSerButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d.serializeDictionary();
				JOptionPane.showMessageDialog(null,"Dictionary serialized!");
			}
		});
		
	}
}
