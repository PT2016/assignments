package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import controller.*;
import model.*;
import javax.swing.JCheckBox;

public class View {

	private JFrame frame;
	private JTextField textFieldSearch;
	private JTable table;
	private JTextField textFieldCuvant;
	private JTextField textFieldSinonim;
	private JTextField textFieldCuvSt;
	private JLabel lblCuvant_1;
	private JLabel lblCuvant_2;
	private JButton btnSterge;
	private JButton btnPrevizualizare;
	private JTable tableSearch;
	private JButton btnVerificaConsistenta;
	private JCheckBox checkBox2;

	/**
	 * Launch app.
	 */
	public void getView() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize dictionary.
	 */
	public View() {
		initialize();
	}

	
	private void initialize()  {
		Controller c = new Controller();
		c.serialize();
		c.deserialize();
		Dictionary d = c.getD();
		frame = new JFrame();
		frame.setBounds(130, 100, 738, 447);
		frame.setBackground(Color.BLUE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblDictionar = new JLabel("Dictionar de sinonime");
		lblDictionar.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);

		JLabel lblCuvant = new JLabel("Cuvant");
		DefaultTableModel dms = new DefaultTableModel();
		dms.addColumn("Cuvant");
		dms.addColumn("Sinonime");
		tableSearch = new JTable(dms);
		tableSearch.setModel(dms);
		dms.addRow(new Object[] { "Cuvant", "Sinonime" });
		JLabel lblSinonim = new JLabel("Sinonim");
		JButton btnCauta = new JButton("Cauta");
		
		btnCauta.addActionListener((e) -> 
		{ 
			// Java 8 way: 
			System.out.println("Lambda expressions were used!"); 
			String wordToFind = textFieldSearch.getText();
			dms.setRowCount(1);
			HashMap<Word, ArrayList<Word>> hs = d.searchWord(wordToFind);
			if(hs.size()==0)
				JOptionPane.showMessageDialog(frame, "Nicio potrivire!");
			for (Entry<Word, ArrayList<Word>> en : hs.entrySet()) {
				Word w = en.getKey();
				String word = w.getWord();
				String s = d.getSynonyms(w);
				dms.addRow(new Object[] { word, s });
			}
		});

		btnCauta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String wordToFind = textFieldSearch.getText();
				dms.setRowCount(1);
				HashMap<Word, ArrayList<Word>> hs = d.searchWord(wordToFind);
				if(hs.size()==0)
					JOptionPane.showMessageDialog(frame, "Nicio potrivire!");
				for (Entry<Word, ArrayList<Word>> en : hs.entrySet()) {
					Word w = en.getKey();
					String word = w.getWord();
					String s = d.getSynonyms(w);
					dms.addRow(new Object[] { word, s });
				}
			}
		});

		textFieldCuvant = new JTextField();
		textFieldCuvant.setColumns(10);

		textFieldSinonim = new JTextField();
		textFieldSinonim.setColumns(10);
		JCheckBox chckbx1 = new JCheckBox("Contextual");
		
		checkBox2 = new JCheckBox("Total");
		JButton btnAdauga = new JButton("Adauga");
		btnAdauga.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String word = textFieldCuvant.getText();
				String syn = textFieldSinonim.getText();
				int aux=0;
				if(chckbx1.isSelected()==true){
					aux=1;
				}
				
				JOptionPane.showMessageDialog(frame, c.addWordtoDic(word, syn,aux));
			}
		});

		textFieldCuvSt = new JTextField();
		textFieldCuvSt.setColumns(10);

		lblCuvant_1 = new JLabel("Cuvant");
		lblCuvant_2 = new JLabel("Cuvant");
		JLabel lblTip = new JLabel("Tip");

		btnSterge = new JButton("Sterge");
		btnSterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = textFieldCuvSt.getText();
				int aux=0;
				if(chckbx1.isSelected()==true){
					aux=1;
				}
				
				JOptionPane.showMessageDialog(frame, c.deleteWord(s, aux));
			}
		});

		btnPrevizualizare = new JButton("Previzualizare");
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("Cuvant");
		dm.addColumn("Sinonime");
		table = new JTable(dm);
		table.setModel(dm);
		dm.addRow(new Object[] { "Cuvant", "Sinonime" });
		btnPrevizualizare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.setRowCount(1);
				
				for (Entry<Word, ArrayList<Word>> en : d.getDictionary().entrySet()) {
					Word w = en.getKey();
					String word = w.getWord().toString();
					String s = d.getSynonyms(w);
					dm.addRow(new Object[] { word, s });
				}
				d.toString();
			}
		});

		btnVerificaConsistenta = new JButton("Verifica consistenta");
		btnVerificaConsistenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = d.isWellFormed();
				if (b == true)
					JOptionPane.showMessageDialog(frame, "Dictionarul este consistent");
				else
					JOptionPane.showMessageDialog(frame, "Dictionarul nu este consistent");
			}
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCuvant)
								.addComponent(btnCauta)
								.addComponent(tableSearch, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPrevizualizare)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnVerificaConsistenta)
										.addGap(99))
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(chckbx1)
													.addComponent(checkBox2))
												.addGap(18)
												.addComponent(btnAdauga))
											.addComponent(lblTip)))
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addGap(32)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnSterge)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblCuvant_2)
												.addGap(18)
												.addComponent(textFieldCuvSt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addComponent(textFieldCuvant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textFieldSinonim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSinonim)
										.addComponent(lblCuvant_1)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(111)
							.addComponent(lblDictionar)))
					.addGap(96))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDictionar)
							.addGap(24)
							.addComponent(lblCuvant))
						.addComponent(btnVerificaConsistenta))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCauta)
							.addGap(18)
							.addComponent(tableSearch, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPrevizualizare)
								.addComponent(lblCuvant_1)
								.addComponent(textFieldCuvant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSinonim)
								.addComponent(textFieldSinonim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblTip)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbx1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(checkBox2))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(40)
											.addComponent(btnAdauga)))
									.addGap(33)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCuvant_2)
										.addComponent(textFieldCuvSt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(26)
									.addComponent(btnSterge))
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
							.addGap(9)))
					.addGap(62))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
