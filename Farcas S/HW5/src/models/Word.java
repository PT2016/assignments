package models;

import java.util.Observable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



public class Word extends Observable implements DictionaryEntry {

	private String content;
	private boolean isPreposition;
	
	public Word(){
	}
	
	@JsonCreator
	public Word(@JsonProperty("content") String content, @JsonProperty("isPreposition") boolean isPreposition){
		this.content = content;
		this.isPreposition = isPreposition;
	}

	public void update(String content){
		setChanged();
		notifyObservers(content);
	}
	
	public String getContent() {
		return content;
	}

	public boolean isPreposition() {
		return isPreposition;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setPreposition(boolean isPreposition) {
		this.isPreposition = isPreposition;
	}

	public boolean equals(Object obj){
		if (!(obj instanceof Word)) return false;
		if(this.content.equals(((Word)obj).getContent()) && (this.isPreposition == ((Word)obj).isPreposition())) return true;
		return false;
	}
	
	public int hashCode(){
		return 37*(37 * 23 + content.hashCode()) + (isPreposition ? 0 : 1);
	}
	
	public String toString(){
		return content;
	}
	
}
