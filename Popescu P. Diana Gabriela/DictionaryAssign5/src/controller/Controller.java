package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import models.ProxyDictionary;
import view.DictionaryFrame;

public class Controller implements Observer, ActionListener {
	private DictionaryFrame dictionaryFrame;
	private ProxyDictionary dictionary;

	public Controller(DictionaryFrame dictionaryFrame) {
		this.dictionaryFrame = dictionaryFrame;
		dictionaryFrame.removeSynonimButton.addActionListener(this);
		dictionaryFrame.addSynonimButton.addActionListener(this);
		dictionaryFrame.searchFilterButton.addActionListener(this);

		dictionary = new ProxyDictionary();
		dictionary.addObserver(this);
		dictionary.populate();
		dictionaryFrame.updateTable(dictionary);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == dictionaryFrame.searchFilterButton) {
			dictionaryFrame.updateTable(dictionary, dictionaryFrame.jTextField.getText());
		} else if (source == dictionaryFrame.addSynonimButton) {
			dictionary.addSynonim(dictionaryFrame.jTextFieldKey.getText(), dictionaryFrame.jTextFieldSyn.getText());
			dictionaryFrame.updateTable(dictionary);
		} else if (source == dictionaryFrame.removeSynonimButton) {
			if (!dictionaryFrame.jTextFieldKey.getText().isEmpty()) {
				dictionary.deleteSynonim(dictionaryFrame.jTextFieldKey.getText());
				dictionaryFrame.updateTable(dictionary);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		dictionary.save();
	}
}
