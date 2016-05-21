package model;

import java.util.ArrayList;
import java.util.Observable;

public class Word extends Observable {

	private String word;
	private ArrayList<Word> dependencies;

	public Word() {
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

	private String getDependencies() {
		String result = "";
		for (Word newWord : dependencies) {
			result += newWord;
		}
		return result;
	}

	@Override
	public String toString() {
		return "Word[word:" + getWord() + ",dependency:" + getDependencies() + "]";
	}
}
