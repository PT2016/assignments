package models;

import java.util.HashSet;

public interface DictionaryProc {
	/*
	 * @pre word!=null
	 * @pre dictionaryMap.containsKey(word) == false;
	 * @pre wellFormed
	 * @post dictionaryMap.containsKey(word);
	 * @post @pre dictionaryMap.size() ==@post dictionaryMap.size() + 1;
	 * @post wellFormed
	 */
    public void addWord(String word);
    /*
	 * @pre word!=null
	 * @pre dictionaryMap.containsKey(word) ;
	 * @pre wellFormed
	 * @post dictionaryMap.containsKey(word)==false;
	 *@post @pre dictionaryMap.size() ==@post dictionaryMap.size() -1;
	 *@post wellFormed
	 */
    public void removeWord(String word);
    /*
	 * @pre word!=null
	 * @pre synonym!=null
	 * @pre dictionaryMap.containsKey(word) == false
	 * @pre wellFormed
	 * @post dictionaryMap.containsKey(synonym)
	 * @post  dictionaryMap.get(word).contains(synonym)
	 * @postdictionaryMap.get(synonym).contains(word)
	 * @post wellFormed
	 */
    public void addSynonym(String word,String synonym);
    /*
   	 * @pre word!=null
   	 * @pre synonym!=null
   	 * @pre dictionaryMap.containsKey(word)
   	 * @pre wellFormed
   	 * @post dictionaryMap.containsKey(synonym)
   	 * @post  dictionaryMap.get(word).contains(synonym)==false
   	 * @postdictionaryMap.get(synonym).contains(word)==false
   	 * @post wellFormed
   	 */
    public void removeSynonym(String word,String synonym);
    /*
   	 * @pre word!=null
   	 * @pre dictionaryMap.containsKey(word)
   	 * @pre wellFormed
   	 * @post dictionaryMap.containsKey(word)
   	 * @post wellFormed
     * @post entri!=null;
   	 */
    public String displayWord(String word);
    /*
   	 * @pre word!=null
   	 * @pre wellFormed
   	 * @post wellFormed
     * @post matchingWords!=null;
   	 */
    public HashSet<String> searchWord(String key);
}
