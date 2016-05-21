package models;

import java.util.ArrayList;

public interface DictionaryInterface {
	/**
	 * Method for adding a new synonym
	 * 
	 * @invariant isWellFormed()
	 * @pre word != null && synonym!=null
	 * @post getNumberOfSynonims(word)!=0 && getNumberOfSynonims(word) == getNumberOfSynonims(word)@pre + 1
	 * @post get(word).contains(synonim)
	 * @invariant isWellFormed()
	 * @param word
	 * @param synonym
	 * 
	 */

	public void addSynonim(String word, String synonym);

	/**
	 * Method for deleting a new synonim
	 * 
	 * @invariant isWellFormed()
	 * @pre word != null && synonym!=null
	 * @pre dictionary.get(word).contains(synonim)
	 * @post getNumberOfSynonims(word) == getNumberOfSynonims(word)@pre - 1
	 * @post !(dictionary.get(word).contains(synonim))
	 * @invariant isWellFormed()
	 * @param word
	 * @param synonym
	 * 
	 */

	public void deleteSynonim(String word, String synonim);

	/**
	 * Method for adding a new word
	 * 
	 * @invariant isWellFormed()
	 * @pre word != null && ss!=null
	 * @post getNumberOfWords() == getNumberOfWords()@pre + 1
	 * @post getNumberOfSynonims(word)==ss.size()
	 * @invariant isWellFormed()
	 * @param word
	 * @param ss
	 */

	public void addWordAndSynonim(String word, ArrayList<String> ss);

	/**
	 * Method for deleting a word
	 * 
	 * @invariant isWellFormed()
	 * @pre word!=null
	 * @pre dictionary.containsKey(word)
	 * @post getNumberOfWords() == getNumberOfWords()@pre - 1
	 * @post !(dictionary.containsKey(word))
	 * @invariant isWellFormed()
	 * @param word
	 */

	public void deleteWord(String word);

	public ArrayList<String> search(String s);

}
