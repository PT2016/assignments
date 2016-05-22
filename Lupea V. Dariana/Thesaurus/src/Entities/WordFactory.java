package Entities;

import java.util.ArrayList;

public class WordFactory {

	public Word getWordLength(String word, ArrayList<String> synonyms) {
		if (word.length() < 7) {
			return new ShortWord(word, synonyms);
		} else {
			return new LongWord(word, synonyms);
		}
	}
}
