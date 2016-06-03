/**
 * 
 */
package model;

/**
 * @author Lorand
 *
 */
public interface DictionaryProc {

	/**
	 * 
	 * @pre dictionary!=null
	 * @post
	 * @param
	 * @return
	 */
	public void readsynonyms();

	/**
	 * 
	 * @pre
	 * @post dictionary!= null
	 * @param
	 * @return
	 */
	public void writesynonyms();

	/**
	 * 
	 * @pre word!=null && synonym!=null
	 * @post new dictionary.size()= dictionary.size()+1
	 * @param word
	 *            has the synonym synonym
	 * @return
	 */
	public void addsysnonyms(String word, String synonym);

	/**
	 * 
	 * @pre word!=null && synonym!=null
	 * @post
	 * @param word
	 *            change its old synonym with a new synonym
	 * @return
	 */
	public void updatesynonyms(String word, String oldSynonym, String newSynonym);

	/**
	 * 
	 * @pre word!=null
	 * @post new dictionary.size()= dictionary.size()-1
	 * @param the
	 *            word we want to delete from the dictionary
	 * @return
	 */
	public void deletesysnonyms(String word);

	/**
	 * 
	 * @pre !dictionar.isEmpty()
	 * @post
	 * @param
	 * @return
	 */
	public boolean isConsistent();

}
