package pt.MyDictionary.model;

public interface DictionaryInterface {

	/**
	 * @param word
	 * @pre word != NULL;
	 * @post noWord == noWord@pre + 1;
	 */
	void add(String word);	
	/**
	 * @param word
	 * @pre word != NULL;
	 * @post noWord == noWord@pre + 1;
	 */
	void add(Word word);
	/**
	 * @param word
	 * @pre word != NULL;
	 * @post noWord == noWord@pre - 1;
	 */
	void remove(String word);

	/**
	 * @param word
	 * @pre word != NULL;
	 * @return wordReturned;
	 */
	Word search(String word);
	/**
	 * @pre word != NULL;
	 * @param word
	 * @return ok;
	 */
	boolean exists(String word);
	/**
	 * See if all synonyms of a word are in the dictionary as keys
	 * @return ok;
	 */
	boolean checkConsistency();
	
}
