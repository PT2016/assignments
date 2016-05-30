package Entities;

import java.util.ArrayList;

public interface SynonymInterface {

	/**
	 * Method used for adding a new word in the dictionary
	 * 
	 * @param word
	 *            The word to be added
	 * @param synonyms
	 *            List of synonyms
	 * 
	 * @invariant isWellFormed()
	 * @pre (word != null) && (word.length() > 0)
	 * @pre (synonyms != null) && (synonyms.size() > 0)
	 * 
	 * @post Synonym.containsWord(word)
	 * @invariant isWellFormed()
	 */
	public void addNewWord(String word, ArrayList<String> synonyms);

	/**
	 * 
	 * Method used for deleting a word from the dictionary
	 * 
	 * @param word
	 *            The word to be removed
	 * 
	 * @invariant isWellFormed
	 * @pre (word != null) && (word.length() > 0)
	 * 
	 * @post !Synonym.containsWord(word)
	 * @invariant isWellFormed
	 */
	public void removeWord(String word);

	/**
	 * Method used for checking if a word is found in the dictionary
	 * 
	 * @param word
	 *            The word to be checked for existence in the dictionary
	 * 
	 * @invariant isWellFormed
	 * 
	 * @return |true|-> if exists or |false| otherwise
	 */
	public boolean containsWord(String word);

	/**
	 * Method used for populating the dictionary
	 * 
	 * @param fileName
	 *            The name of the JSON file which stores the dictionary
	 * @pre dictionary.size() == 0
	 * 
	 * @post dictionary.size() > 0
	 * @invariant isWellFormed
	 */
	public void populateDictionary(String fileName);

	/**
	 * Method used for searching a word in the dictionary, based on a certain
	 * pattern
	 * 
	 * @invariant isWellFormed
	 * @pre pattern != null
	 * 
	 * @post result.size() > 0
	 * @invariant isWellFormed
	 * 
	 * @param pattern
	 *            The pattern to be searched
	 * @return List of matching words
	 */
	public ArrayList<String> searchWord(String pattern);
	
	/**
	 * Method used for searching the synonyms of a certain word
	 * 
	 * @param word The word to be searched
	 * @return the list of synonyms
	 */
	public ArrayList<String> searchSynonyms(String word);
}
