package pt.MyDictionary.MVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import pt.MyDictionary.model.*;
import pt.MyDictionary.strategy.*;

public class DataAcess {
	private Interface view;
	private SerializeDictionary serialize;
	
	Add add;
	Remove remove;
	public DataAcess(Interface v, SerializeDictionary m){
		this.view = v;
		this.serialize = m;
		updateList();
		//serialize.execute();
		addListeners();
		updateConsistency();

	}
	
	class Addition implements ActionListener{
 		@Override
 		public void actionPerformed(ActionEvent arg0) {  	
 			add = new Add();
 			add.AddActionListener(new AddExe());
 		}
 	}
	class AddExe implements ActionListener{
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			String word = add.getWordF();
 			String synonyms = add.getSynonymF();
 			String description = add.getDescriptionF();
 			
 			Word c = new Word(word,synonyms,description);
 			serialize.getDictionary().add(c);
 			
 			view.updateList();
 			updateList();
 			add.getFrame().dispose();
 			updateConsistency();
 			view.ForSearchActionListener(new Search());
 			view.addMause();
 			//addListeners();
 		}
 	}	
	class Removal implements ActionListener{
 		@Override
 		public void actionPerformed(ActionEvent arg0) {  	
 			remove = new Remove();
 			remove.AddActionListener(new RemoveExe());
 		}
 	}
	class RemoveExe implements ActionListener{
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			String word =remove.getWordF();
 			
 			serialize.getDictionary().remove(word);
 			
 			view.updateList();
 			updateList();
 			
 			remove.getFrame().dispose();
 			updateConsistency();
 			view.ForSearchActionListener(new Search());
 			view.addMause();
 			//addListeners();
 		}
 	}	
	class Save implements ActionListener{
 		@Override
 		public void actionPerformed(ActionEvent arg0) {  	
 			serialize.saveJson();
 		}
 	}
	
	class Search implements ActionListener{
 		@Override
 		public void actionPerformed(ActionEvent arg0) {  	
 			String word = view.getSearchBar().getText();
 			
 			String toShow = serialize.getDictionary().sSearch(word);
 			
 			view.setPanelText(toShow);
 			/*
 			Cuvant c = model.getDictionar().search(cuvant);
 			
 			String[] sinonime = c.getSinonime();
 			String sinonimeString = new String();
 			for(int i = 0; i<sinonime.length; i++){
 				sinonimeString += sinonime[i];
 				if (i<(sinonime.length - 1))
 					sinonimeString += ", ";
 			}
 			
 			view.setPanouText("\nCuvant: "+c.getCuvant()+"\n\nSinonime: "+sinonimeString+"\n\nExplicatie: "+c.getDescriere());
 			*/
 		}
 	}	

 	
	
private void updateList(){
	Dictionary dictionary = serialize.getDictionary();
	for(Entry<String, Word> entry: dictionary.getWords().entrySet()){
		view.addWord(entry.getValue().getWord());
	}
	
}

private void updateConsistency(){
		boolean ok = serialize.getDictionary().checkConsistency();
		if (ok == true)
			view.setConsistency("Consistency: yes");
		else view.setConsistency("Consistency: no");
}

private void addListeners(){
	view.ForAddActionListener(new Addition());
	view.ForRemoveActionListener(new Removal());
	view.ForSaveActionListener(new Save());
	view.ForSearchActionListener(new Search());
	view.addMause();
}
}
