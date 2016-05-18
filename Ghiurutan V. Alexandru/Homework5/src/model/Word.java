package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Word extends Observable {

	private String word;
	private List<Word> dependencies;

	public Word(String word) {
		setWord(word);
		dependencies = new ArrayList<Word>();
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void add(Word newWord) {
		dependencies.add(newWord);
	}

	public void delete() {
		setChanged();
		notifyObservers(dependencies);
	}

	@Override
	public String toString() {
		return "Word: " + word;
	}
}
