package DictionaryEntities;

import java.util.List;

public interface DictionaryFunctions {

	/**
	 * all the synonyms in the dictionary must also be main words so that the
	 * dictionary is consistent, and word transitivity must be used
	 */

	/**
	 * adds a new word and also its synonyms
	 * 
	 * @pre (word != null) && (synonyms != null) && (isWellFormed())
	 * @post dictionary.size() == (dictionary.size()@pre + 1 + synonyms.size())
	 *       && (isWellFormed())
	 * @invariant isWellFormed()
	 */
	public void addWord(String word, List<String> synonyms);

	/**
	 * removes a word from the dictionary as key and also as synonym if the case
	 * 
	 * @pre (word != null) && (isWellFormed())
	 * @post (dictionary.size() < (dictionary.size()@pre) && (isWellFormed())
	 * @invariant isWellFormed()
	 */
	public void removeWord(String word);

	/**
	 * @pre isWellFormed()
	 * @post isWellFormed()
	 * @invariant isWellFormed()
	 */
	public void showWords();

	/**
	 * add a synonym for a word, so this new added synonym must also be defined
	 * as main word
	 * 
	 * @pre (synonym != null) && (relatedKeyWord != null) && (isWellFormed())
	 * @post (relatedKeyWord.valueSet().size() ==
	 *       (relatedKeyWord.valueSet().size()@pre + 1 ) && (isWellFormed())
	 * @invariant isWellFormed()
	 */
	public void addSynonym(String synonym, String relatedKeyWord);
	
	/**
	 * search for a word, so we see the word as well as its synonyms
	 * 
	 * @pre (word != null) && (isWellFormed())
	 * @post (isWellFormed())
	 * @invariant isWellFormed()
	 */
	public void searchWord(String word);
}
