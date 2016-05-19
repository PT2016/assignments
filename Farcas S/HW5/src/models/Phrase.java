package models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Phrase extends Observable implements DictionaryEntry {

	@JsonProperty("content")
	private ArrayList<DictionaryEntry> content = new ArrayList<>();

	@JsonCreator
	public Phrase() {
	}

	public void add(DictionaryEntry entry) {
		content.add(entry);
		setChanged();
		notifyObservers();
	}

	public void remove(DictionaryEntry entry) {
		content.remove(entry);
		setChanged();
		notifyObservers();
	}

	public ArrayList<DictionaryEntry> getContent() {
		return content;
	}

	public void setContent(ArrayList<DictionaryEntry> content) {
		this.content = content;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Phrase))
			return false;
		int i, j;
		Phrase o = (Phrase) obj;
		if (o.getContent().size() != content.size())
			return false;
		for (i = 0; i < o.getContent().size(); i++)
			if (!(o.getContent().get(i).equals(content.get(i))))
				return false;
		return true;
	}

	public int hashCode() {
		int i;
		int result = 27;
		for (i = 0; i < content.size(); i++)
			result = 37 * result + content.get(i).hashCode();
		return result;
	}

	public String toString() {
		return content.stream().map(Object::toString).collect(Collectors.joining(" "));
	}
}
