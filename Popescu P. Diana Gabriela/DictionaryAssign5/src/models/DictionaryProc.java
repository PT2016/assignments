package models;

import java.util.ArrayList;
import java.util.Set;

public interface DictionaryProc {

	/**
	 * @param key
	 * @param synonym
	 * @pre key != null
	 * @pre synonym != null
	 * @post dictionary.hashMap.get(key).size()= dictionary.hashMap.get(key).size()@pre + 1
	 * @invariant isConsistent
	 */
	public void addSynonim(String key, String synonim);

	/**
	 * @param key
	 * @param synonym
	 * @pre key != null
	 * @pre synonym != null
	 * @post dictionary.hashMap.get(key).size()= dictionary.hashMap.get(key).size()@pre - 1
	 * @invariant isConsistent
	 */
	public void deleteSynonim(String key);
	
	/**
	 * @param key
	 * @param synonym
	 * @pre key != null
	 * @pre synonym != null
	 * @post dictionary.hashMap.get(key).size()= dictionary.hashMap.get(key).size()@pre + 1
	 */
	public void copy(String key, String synonim);
	
	/**
	 * @param key
	 * @param synonym
	 * @pre key != null
	 * @pre synonym != null
	 * @post dictionary.hashMap.get(key).size()= dictionary.hashMap.get(key).size()@pre - 1
	 */
	public void delete(String key, String synonim);

	/**
	 * @param key
	 * @pre key != null
	 * @post dictionary= dictionary@pre
	 * @invariant isConsistent
	 */
	public ArrayList<String> searchWord(String key);//regex
	
	public void populate();//deserialization
	public void save();//serialization
	
	/**
	 * invariant:
	 */
	public boolean isConsistent();
	
	public Set<String> getKeys();
	public ArrayList<String> getSyn(String key);
	public int getSize();
}
