package Dictionary;

import java.util.ArrayList;
import exception.AddWordException;
import exception.DefineException;
import exception.RemoveWordException;

public interface DictionaryInterface {
	
	public void populateDictionary();
	public void serializeDictionary();
	/**
	 * @pre s instanceof String
	 * @pre s != null
	 * @pre isWellFormed == true
	 * 
	 * @param s
	 * @throws AddWordException 
	 * 
	 * @post isWellFormed == true
	 */
	public void addWord(String s) throws AddWordException;
	/**
	 * @pre s instanceof String
	 * @pre s != null
	 * @pre syn instanceof String
	 * @pre syn != null
	 * @pre isWellFormed == true
	 * 
	 * @param s, syn
	 * @throws DefineException 
	 * 
	 * @post isWellFormed == true
	 */
	public void defineWord(String s, String syn) throws DefineException;
	/**
	 * @pre s instanceof String
	 * @pre s != null
	 * @pre isWellFormed == true
	 * 
	 * @param s
	 * @throws RemoveWordException
	 * 
	 * @post isWellFormed == true
	 */
	public void removeWord(String s) throws RemoveWordException;
	/**
	 * @pre s instanceof String
	 * @pre s != null
	 * @pre isWellFormed == true
	 * 
	 * @param s
	 * 
	 * @post @return instanceof ArrayList
	 * @post isWellFormed == true
	 */
	public ArrayList<String> findWord(String s);
}
