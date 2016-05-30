package controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controllers.MainFrameController.AddButtonActionListener;
import controllers.MainFrameController.DefineButtonActionListener;
import controllers.MainFrameController.PopulateButtonActionListener;
import controllers.MainFrameController.RemoveDefinitionButtonActionListener;
import controllers.MainFrameController.RemoveKeyButtonActionListener;
import controllers.MainFrameController.SearchButtonActionListener;
import controllers.MainFrameController.Table1Listener;
import controllers.MainFrameController.UpdateButtonActionListener;
import models.Dictionary;
import models.DictionaryEntry;
import models.IllegalOperationException;
import models.Phrase;
import models.Word;
import views.MainFrame;
import views.PopulateFrame;

public class PopulateController {

	private PopulateFrame frame;

	public PopulateController(PopulateFrame frame) {
		this.frame = frame;
		frame.setSearchButtonActionListener(new SearchButtonActionListener());
		frame.setAddButtonActionListener(new AddButtonActionListener());
		frame.setRemoveKeyButtonActionListener(new RemoveKeyButtonActionListener());
		frame.setRemoveDefinitionButtonActionListener(new RemoveDefinitionButtonActionListener());
		frame.setDefineButtonActionListener(new DefineButtonActionListener());
		frame.setDoneButtonActionListener(new DoneButtonActionListener());
		frame.setUpdateButtonActionListener(new UpdateButtonActionListener());
	}

	public class SearchButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.constructTable1(Dictionary.getInstance().search(frame.getSearch().getText()));
			frame.getTable1().getSelectionModel().addListSelectionListener(new Table1Listener());
		}
	}

	public class Table1Listener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int index = frame.getTable1().getSelectedRow();
			if (index != -1) {
				String selectedData = (String) frame.getTable1().getValueAt(frame.getTable1().getSelectedRow(), 0);
				frame.constructTable2(new Word(selectedData, false));
			}
		}
	}

	public class AddButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String w = frame.getSearch().getText();
			if (frame.getTable1() == null)
				frame.constructTable1(Dictionary.getInstance().search(frame.getSearch().getText()));
			try {
				Dictionary.getInstance().add(new Word(w, false), true);
				DefaultTableModel model = (DefaultTableModel) frame.getTable1().getModel();
				model.addRow(new Object[] { w });
				for (int i = model.getRowCount() - 1; i >= 0; --i) {
					if (model.getValueAt(i, 0).equals(w)) {
						frame.getTable1().setRowSelectionInterval(i, i);
					}
				}
				frame.getDefine().doClick();
			} catch (IllegalOperationException e1) {
				e1.printStackTrace();
			}
		}
	}

	public class DefineButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (frame.getTable1() != null) {
				int index = frame.getTable1().getSelectedRow();
				if (index != -1) {
					if (frame.getTable2() == null)
						frame.constructTable2(new Word(
								(String) frame.getTable1().getValueAt(frame.getTable1().getSelectedRow(), 0), false));
					Object[] options = { "Word", "Phrase" };
					int choice = JOptionPane.showOptionDialog(null, "What type of dictionary entry do you want?",
							"Choose an option", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
							options, options[0]);
					if (choice == 0) {
						String word = JOptionPane.showInputDialog("Please enter your word");
						try {
							Dictionary.getInstance().define(
									new Word((String) frame.getTable1().getValueAt(index, 0), false),
									new Word(word, false), true);
							((DefaultTableModel) frame.getTable2().getModel()).addRow(new Object[] { word });
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (IllegalOperationException e1) {
							e1.printStackTrace();
						}
					} else {
						Phrase p = new Phrase();
						int i, nrOfWords = Integer.parseInt(
								JOptionPane.showInputDialog("How many words would you like the phrase to have?"));
						for (i = 0; i < nrOfWords; i++) {
							int prep = JOptionPane.showOptionDialog(null,
									"Is word no. " + (i + 1) + " a preposition or not?", null,
									JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
							p.add(new Word(JOptionPane.showInputDialog("Please enter your word"),
									prep == 0 ? true : false));
						}
						try {
							Dictionary.getInstance()
									.define(new Word((String) frame.getTable1().getValueAt(index, 0), false), p, true);
							((DefaultTableModel) frame.getTable2().getModel()).addRow(new Object[] { p.toString() });
						} catch (IllegalOperationException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}

	public class RemoveKeyButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (frame.getTable1() != null) {
				int index = frame.getTable1().getSelectedRow();
				if (index != -1)
					try {
						Dictionary.getInstance()
								.removeKey(new Word((String) frame.getTable1().getValueAt(index, 0), false), true);
						((DefaultTableModel) frame.getTable1().getModel()).removeRow(index);
					} catch (IllegalOperationException e1) {
						e1.printStackTrace();
					}
			}
		}
	}

	public class RemoveDefinitionButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (frame.getTable2() != null) {
				int index = frame.getTable2().getSelectedRow();
				if (index != -1) {
					DictionaryEntry definition = null;
					String value = (String) frame.getTable2().getValueAt(index, 0);
					if (value.contains(" ")) {
						definition = new Phrase();
						StringTokenizer st = new StringTokenizer(value);
						while (st.hasMoreTokens()) {
							String prep = st.nextToken();
							if (Dictionary.getInstance().checkIfPreposition(new Word(prep, true))) {
								((Phrase) definition).add(new Word(prep, true));
							} else {
								((Phrase) definition).add(new Word(prep, false));
							}
						}
					} else
						definition = new Word(value, false);
					try {
						Dictionary.getInstance()
								.removeDefinition(
										new Word((String) frame.getTable1()
												.getValueAt(frame.getTable1().getSelectedRow(), 0), false),
										definition, true);
						((DefaultTableModel) frame.getTable2().getModel()).removeRow(index);
					} catch (IllegalOperationException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	public class UpdateButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (frame.getTable1() != null) {
				int index = frame.getTable1().getSelectedRow();
				if (index != -1){
					String update = frame.getSearch().getText();
					Word w = new Word((String) frame.getTable1().getValueAt(index, 0), false);
					w.addObserver(Dictionary.getInstance());
					w.update(update);
					((DefaultTableModel) frame.getTable1().getModel()).removeRow(index);
					((DefaultTableModel) frame.getTable1().getModel()).addRow(new Object[] { update });				
				}
			}
		}	
	}
	
	public class DoneButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!(Dictionary.getInstance().WellFormed()))
				JOptionPane.showMessageDialog(null, "Dictionary is not well formed!");
			else
				frame.dispose();
		}
	}
	
}
