package controller;

import models.Dictionary;
import view.DictionaryFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
	
		Dictionary dictionary=Dictionary.getInstance();
		new DictionaryFrame(dictionary);
	//	dictionary.addWord("iubire");
	
		//dictionary.showMessage();
		//dictionary.removeWord("iubire");
	//	dictionary.addSynonym("iubire", "dragoste");
	//	dictionary.addSynonym("iubire", "adorare");
	//	dictionary.addWord("barbat");
	//	dictionary.addSynonym("barbat", "mascul");
	//	dictionary.addSynonym("mascul", "el");
	//	dictionary.writeToJson();
	//	dictionary.readFromJson();
	//	dictionary.removeSynonym("dragoste", "iubire");
	//	dictionary.displayWord("iubire");
	//	dictionary.displayWord("dragoste");
	//	dictionary.displayWord("adorare");
	//	dictionary.displayWord("el");
	//	for (String st:dictionary.getDictionaryMap().keySet()){
	//		System.out.println(st +" ");
	//	}
	//	for (String a: dictionary.searchWord("hg")){
	//		System.out.println(a);
	//	}
			
		//dictionary.showMessage();
	}

}
