package model;

public class WordFactory {
		public Word createWord(String key, String type){
			
			if (key==null){
				return null;
			} else if (type.matches("contextual")){
				return new WordContext(key);
			} else 
				return new WordTotal(key);
			
			
		}
	}

