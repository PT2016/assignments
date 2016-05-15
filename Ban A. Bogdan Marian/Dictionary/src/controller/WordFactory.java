package controller;

import java.util.List;

import model.ActualWord;
import model.NullWord;

public class WordFactory {
	public IWord getWord(String key,List<String> values){
		if (key==null){
			return null;
		} else if (key.equals("EMPTY")){
			return new NullWord();
		} else{
			return new ActualWord(key,values);
		}
	}
}
