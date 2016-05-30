package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.SimpleHashMap;
import controller.TableModel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	private JButton search = new JButton("Search");
	private JButton addWord = new JButton("Add Word");
	private JButton deleteWord = new JButton("Del Word");
	private JButton open = new JButton("Open");
	private JButton save = new JButton("Save");
	private JButton update = new JButton("Update");
	private JButton empty = new JButton("Empty");
	private JLabel searchLabel = new JLabel("Search:");
	private JButton exit = new JButton("Log out");
	private JSeparator bar1 = new JSeparator(JSeparator.HORIZONTAL);
	private JSeparator bar2 = new JSeparator(JSeparator.VERTICAL);
	private JSeparator bar3 = new JSeparator(JSeparator.HORIZONTAL);
	private JLabel statistics = new JLabel("Statistics:");
	private JLabel searchResults = new JLabel("Search results:");
	private JLabel consistent = new JLabel("Consistency:");
	private JLabel nrWords = new JLabel("Nr. of words:");
	private File file;
	private JTextField textField = new JTextField();
	private JTextField consistentField = new JTextField("   ?");
	private JTextField nrWordsField = new JTextField();
	private JTextArea textArea = new JTextArea();
	private JButton showAll = new JButton("Show All");
	SimpleHashMap hashMap;

	public MainPanel(JFrame frame, SimpleHashMap hashMap) {
		this.hashMap = hashMap;
		this.setLayout(null);
		setBound(this.searchLabel, new Rectangle(10, 10, 90, 30));
		add(this.searchLabel);

		TableModel tableModel = new TableModel(hashMap);
		JTable table = new JTable(tableModel);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		setBound(this.textField, new Rectangle(60, 16, 120, 20));
		add(this.textField);

		setBound(this.search, new Rectangle(184, 16, 83, 20));
		add(this.search);
		search(frame, table);

		setBound(this.bar1, new Rectangle(0, 50, 275, 10));
		add(bar1);

		setBound(this.bar3, new Rectangle(0, 300, 400, 10));
		add(bar3);

		setBound(this.bar2, new Rectangle(275, 0, 10, 400));
		add(bar2);

		setBound(this.addWord, new Rectangle(290, 16, 90, 30));
		add(this.addWord);
		addWord(frame, table);
		addWord.setEnabled(false);

		setBound(this.deleteWord, new Rectangle(290, 56, 90, 30));
		add(this.deleteWord);
		deleteWord(frame, table);
		deleteWord.setEnabled(false);

		setBound(this.open, new Rectangle(290, 96, 90, 30));
		add(this.open);
		open(frame, table);

		setBound(this.save, new Rectangle(290, 136, 90, 30));
		add(this.save);
		save(frame, table);
		save.setEnabled(false);

		setBound(this.update, new Rectangle(290, 176, 90, 30));
		add(this.update);
		updateWord(frame, table);
		update.setEnabled(false);

		setBound(this.empty, new Rectangle(290, 216, 90, 30));
		add(this.empty);
		empty(frame, table);
		empty.setEnabled(false);

		setBound(this.exit, new Rectangle(290, 256, 90, 30));
		add(this.exit);
		addExit(frame);

		setBound(this.statistics, new Rectangle(305, 295, 90, 30));
		add(this.statistics);

		setBound(this.consistent, new Rectangle(280, 312, 90, 30));
		add(this.consistent);

		consistentField.setEditable(false);
		setBound(this.consistentField, new Rectangle(355, 318, 30, 20));
		add(this.consistentField);

		setBound(this.nrWords, new Rectangle(280, 335, 90, 30));
		add(this.nrWords);

		nrWordsField.setText("   " + Integer.toString(hashMap.getTotalEntriesForTable()));
		nrWordsField.setEditable(false);
		setBound(this.nrWordsField, new Rectangle(355, 340, 30, 20));
		add(this.nrWordsField);
		
		setBound(this.searchResults, new Rectangle(10, 298, 90, 30));
		add(this.searchResults);

		JPanel area = new JPanel();
		area.setBackground(Color.GRAY);
		area.setLayout(new BorderLayout());
		frame.add(area);

		area.setBounds(new Rectangle(8, 65, 260, 225));
		add(area);

		// scroll.setBounds(175, 20, 300, 300);
		JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scroll.setViewportView(table);
		area.add(scroll, BorderLayout.CENTER);
		
		setBound(this.showAll, new Rectangle(184, 303, 83, 20));
		add(this.showAll);
		showAll(frame, table);
		
		
		JScrollPane scrollArea = new JScrollPane(textArea);
		scrollArea.setBounds(new Rectangle(8, 325, 260, 38));
		textArea.setEditable(false);
		add(scrollArea);

	}

	public void setBound(Component comp, Rectangle bounds) {
		comp.setBounds(bounds);
	}

	private void search(JFrame frame, JTable table) {
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = hashMap.searchElement(textField.getText());
				textArea.setText(result);
				if (textArea.getText().equals("clear")){
					textArea.setText("");
				}
				((TableModel) table.getModel()).updateModel();
				table.updateUI();
			}
		});
	}
	
	private void showAll(JFrame frame, JTable table) {
		showAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText() != null && !textArea.getText().equals("")){
					JOptionPane.showMessageDialog(frame,textArea.getText());
				} else {
					JOptionPane.showMessageDialog(frame,"Nothing to show.");
				}
				
			}
		});
	}

	private void addWord(JFrame frame, JTable table) {
		addWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel nameLabel = new JLabel("Name of the word: ");
				JTextField name = new JTextField();
				JLabel synonimLabel = new JLabel("Synonim: ");
				JTextField synonim = new JTextField();
				Object[] ob = { nameLabel, name, synonimLabel, synonim };
				int result = JOptionPane.showConfirmDialog(null, ob, "Adding word", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if (hashMap.getHashMap().containsKey(name.getText())) {
						JOptionPane.showMessageDialog(frame,
								"Word is already in dictionary. Did you mean updating it?");
					} else {
						hashMap.addElement(name.getText(), Arrays.asList(synonim.getText().split(",")));
						if(hashMap.isAlpha(synonim.getText())){
						JOptionPane.showMessageDialog(frame, "New word added");
						}
					}
					check();
					((TableModel) table.getModel()).updateModel();
					table.updateUI();
				}
			}
		});
	}

	private void updateWord(JFrame frame, JTable table) {
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					JLabel synonimLabel = new JLabel("Synonims: ");
					JTextField synonim = new JTextField();
					Object[] ob = { synonimLabel, synonim };
					int result = JOptionPane.showConfirmDialog(null, ob, "Updating word", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						hashMap.updateElement((String) table.getValueAt(table.getSelectedRow(), 0),
								Arrays.asList(synonim.getText().split(",")));
						if(hashMap.isAlpha(synonim.getText())){
						JOptionPane.showMessageDialog(frame, "New synonim added");
						}
					}
					check();
					((TableModel) table.getModel()).updateModel();
					table.updateUI();
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a word!");
				}

			}
		});
	}

	private void deleteWord(JFrame frame, JTable table) {
		deleteWord.addActionListener(new ActionListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					String deletedElement = (String) table.getValueAt(table.getSelectedRow(), 0);
					hashMap.removeElement(deletedElement);
					Iterator it = hashMap.getHashMap().entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry entry = (Map.Entry) it.next();
						if (((List)entry.getValue()).contains(deletedElement)){
							((List)entry.getValue()).remove(deletedElement);
						}
					}
					((TableModel) table.getModel()).updateModel();
					check();
					table.updateUI();
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a word!");
				}
			}
		});

	}

	private void empty(JFrame frame, JTable table) {
		empty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hashMap.getHashMap().clear();
				check();
				((TableModel) table.getModel()).updateModel();
				table.updateUI();
			}
		});
	}

	private void check() {
		nrWordsField.setText("   " + Integer.toString(hashMap.getTotalEntriesForTable()));
		if (hashMap.isConsistent()) {
			consistentField.setText(" Yes");
		} else {
			consistentField.setText(" No");
		}

	}

	private void open(JFrame frame, JTable table) {
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(getComponent(0)) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					try {
						hashMap.populate(file.getName());
						addWord.setEnabled(true);
						deleteWord.setEnabled(true);
						update.setEnabled(true);
						empty.setEnabled(true);
						save.setEnabled(true);
					} catch (IOException er) {
						er.printStackTrace();
					}
					check();
					((TableModel) table.getModel()).updateModel();
					table.updateUI();
				}
			}
		});
	}

	private void save(JFrame frame, JTable table) {
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hashMap.saveToFile(file);
				check();
				((TableModel) table.getModel()).updateModel();
				table.updateUI();
			}
		});
	}

	private void addExit(JFrame frame) {
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
