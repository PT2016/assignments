package Entities;

import java.util.ArrayList;
import java.util.Observable;

public class Word extends Observable {

	private String name;
	private ArrayList<String> synonyms;

	public Word(String name, ArrayList<String> synonyms) {
		this.setName(name);
		this.setSynonyms(synonyms);

	}

	public void notifyObserver() {
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(ArrayList<String> synonyms) {
		this.synonyms = synonyms;
	}

	public String toString() {
		return this.getName();
	}
}
