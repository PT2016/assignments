package models;

import java.util.ArrayList;

public interface DictionaryInterface {

	/**
	 * 
	 * @pre word != null
	 * @post dictionaryWords.size() = dictionaryWords.size()@pre + 1
	 * 
	 */
	public void addWord(String word);

	/**
	 * 
	 * @pre word != null
	 * @post dictionaryWords.size() = dictionaryWords.size()@pre - 1
	 */
	public void deleteWord(String word);

	public void displayWords();

	/**
	 * 
	 * @pre word != null
	 * @pre synonim != null
	 * @post dictionaryWords.get(word).size() =
	 *       dictionaryWords.get(word).size()@pre + 1
	 * @post dictionaryWords.size() = dictionaryWords.size@pre + 1
	 */
	public void addSynonim(String word, String synonim);

	/**
	 * 
	 * @pre word != null
	 * @post result != null
	 */
	public ArrayList<String> searchWord(String word);

	public boolean isWellFormed();
}
