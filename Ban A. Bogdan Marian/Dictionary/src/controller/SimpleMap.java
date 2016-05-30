package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface SimpleMap {
	

	/**
	 * @pre  key != null && isAlpha(values.get(0))==true;
	 * @post isConsistent() == true;
	 */
	
	public void addElement(String key, List<String> values);


	/**
	 * @pre key != null;
	 * @post isConsistent() == true;
	 */
	
	public void removeElement(String key);


	/**
	 * @pre key != null && hashMap.isEmpty() == false;
	 * @post isConsistent()==true;
	 */
	
	public String searchElement(String key);
	
	/**
	 * @pre fileName.endsWith(".json") && fileName != null;
	 * @post isConsistent()==true;
	 */
	
	public void populate(String fileName) throws IOException;

	/**
	 * @pre fileName != null && fileName.isFile();
	 * @post isConsistent()==true;
	 */
	
	public void saveToFile(File fileName);
	
	/**
	 * @pre  key != null && isAlpha(values.get(0))==true;
	 * @post isConsistent() == true;
	 */

	public void updateElement(String key, List<String> values);
}
