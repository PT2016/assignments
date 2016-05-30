package model;

import java.util.ArrayList;
import java.util.HashMap;

public interface DicInterface {
	/**
	 * @pre  isWord()
	 * @post getSize() = getSize()@pre + 1
	 * @invariant isWellFormed()
	 * @param w word to add
	 * @param s synonym to add
	 * @return status message
	 */
	public String addWord(Word w, Word s, int aux);
	
	/**
	 * @pre isWord()
	 * @post getSize() = getSize()@pre - 1
	 * @param w word to remove
	 * @return
	 */
	public String removeWord(Word w);
	
	/**
	 * @pre isWord()
	 * @post true
	 * @param text
	 * @return
	 */
	public HashMap<Word, ArrayList<Word>> searchWord(String text);
}
