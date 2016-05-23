package model;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public interface DictionaryProc {
	/*
	 * @pre !dictionary.containsKey(word)&&word!=null&&isWellFormed()
	 * 
	 * @pre explanation!=null && !explanation.equals("")
	 * 
	 * @post (dictionary.size()==dictionary.size()@pre+1)&& isWellFormed()
	 */
	public void addWord(Word word, String explanation);

	/*
	 * @pre !otherwWords.contains(otherWord)&&otherWord!=null&&isWellFormed()
	 * 
	 * @post (otherWords.size()==otherWords.size()@pre+1)&& isWellFormed()
	 */
	public void addOtherWord(String otherWord);

	/*
	 * @pre !otherWord.equals("")&&otherWord!=null&&isWellFormed()
	 * 
	 * @post isWellFormed()
	 */
	public boolean containsOtherWord(String otherWord);

	/*
	 * @pre dictionary.containsKey(word)&&word!=null&&isWellFormed()
	 * 
	 * @post (dictionary.size()==dictionary.size()@pre-1)&& isWellFormed()
	 */
	public void removeWord(Word word);

	/*
	 * @pre !newWord.equals("")&&newWord!=null&&isWellFormed()
	 * 
	 * @post isWellFormed()
	 */
	public boolean containsWord(String newWord);

	/*
	 * @pre dictionary.containsKey(new
	 * Word(word))&&word.equals("")&&word!=null&&isWellFormed()
	 * 
	 * @post isWellFormed()
	 */
	public Word getWord(String word);

}
