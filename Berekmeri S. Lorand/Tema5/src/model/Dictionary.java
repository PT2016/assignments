/**
 * 
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Lorand
 *
 */
public class Dictionary implements DictionaryProc, java.io.Serializable {

	public HashMap<String, String> dictionary = new HashMap<String, String>();

	// create an object of SingleObject
	private static Dictionary instance = new Dictionary();

	// make the constructor private so that this class cannot be
	// instantiated
	private Dictionary() {
	}

	// Get the only object available
	public static Dictionary getInstance() {
		return instance;
	}

	@Override
	public void readsynonyms() {

		assert dictionary != null;
		try {

			FileInputStream out = new FileInputStream("src/dictionary.ser");
			ObjectInputStream in = new ObjectInputStream(out);
			dictionary = (HashMap<String, String>) in.readObject();
			in.close();
			out.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error");

		}

	}

	@Override
	public void writesynonyms() {
		// boolean ok=false;
		try {
			FileOutputStream out = new FileOutputStream("src/dictionary.ser");
			ObjectOutputStream in = new ObjectOutputStream(out);
			in.writeObject(dictionary);
			in.close();
			out.close();
			// ok=true;

		} catch (IOException e) {
			e.printStackTrace();

		}

		assert dictionary != null;

	}

	@Override
	public void addsysnonyms(String word, String synonym) {
		assert word != null || synonym != null;
		int size = dictionary.size();

		dictionary.put(word, synonym);
		dictionary.put(synonym, word);

		assert dictionary.size() == size + 2;

	}

	@Override
	public void updatesynonyms(String word, String oldSynonym, String newSynonym) {
		assert word != null || oldSynonym != null || newSynonym != null;
		int size = dictionary.size();

		dictionary.replace(word, oldSynonym, newSynonym);

		assert dictionary.size() == size;

	}

	@Override
	public void deletesysnonyms(String word) {
		assert word != null;
		int size = dictionary.size();

		dictionary.remove(word);

		assert dictionary.size() == size - 1;

	}

	@Override
	public boolean isConsistent() {
		assert !(dictionary.isEmpty());
		boolean consistent = true;

		Set<Map.Entry<String, String>> entrySet = dictionary.entrySet();
		Iterator<Map.Entry<String, String>> i = entrySet.iterator();

		while (i.hasNext()) {
			Map.Entry<String, String> word = i.next();
			String key = word.getKey();
			String value = dictionary.get(key);

			if (!dictionary.containsKey(value)) {

				consistent = false;
			}

		}
		isWellFormed();
		return consistent;
	}

	public boolean isWellFormed() {

		int size = dictionary.size();
		int nrKey = 0;

		Set<Map.Entry<String, String>> entrySet = dictionary.entrySet();
		Iterator<Map.Entry<String, String>> i = entrySet.iterator();

		while (i.hasNext()) {
			Map.Entry<String, String> word = i.next();
			nrKey++;

		}

		if (size == nrKey) {
			System.out.println(size + " " + nrKey);
			return true;
		} else
			return false;

	}

}
