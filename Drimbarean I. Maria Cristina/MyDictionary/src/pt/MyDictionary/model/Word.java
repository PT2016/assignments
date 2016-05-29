package pt.MyDictionary.model;

import java.io.Serializable;
import java.util.Arrays;

public class Word implements Serializable,Comparable{
private static final long serialVersionUID = -4099424961175654384L;
private String word;
private String[] synonyms;
private String description;
public Word(String word,String synonyms, String description){
	this.word=word;
	this.synonyms=normalize(synonyms);
	this.description=description;
}
public Word(String word){
	this.word=word;
}
//return only a string of synonyms
protected String getSynonymsString(){
	String s = new String();
	for(int i=0; i<synonyms.length; i++){
		s+=synonyms[i];
		if(i!=(synonyms.length-1))
			s+=" ";
	}
	return s;
}
//get the synonyms from input
private String[] normalize(String synonym){
	synonym = synonym.replaceAll(","," ");
	return synonym.split(" ");}
/**
 * @return the word
 */
public String getWord() {
	return word;
}
/**
 * @param word the word to set
 */
public void setWord(String word) {
	this.word = word;
}
/**
 * @return the synonims
 */
public String[] getSynonyms() {
	return synonyms;
}
/**
 * @param synonims the synonims to set
 */
public void setSynonyms(String[] synonyms) {
	this.synonyms = synonyms;
}
/**
 * @return the description
 */
public String getDescription() {
	return description;
}
/**
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Word [word=" + word + ", synonyms=" + Arrays.toString(synonyms) + ", description=" + description + "]";
}
@Override
public int compareTo(Object arg0) {
	return 0;
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((word == null) ? 0 : word.hashCode());
	return result;
}
/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Word other = (Word) obj;
	if (word == null) {
		if (other.word != null)
			return false;
	} else if (!word.equals(other.word))
		return false;
	return true;
}

}
