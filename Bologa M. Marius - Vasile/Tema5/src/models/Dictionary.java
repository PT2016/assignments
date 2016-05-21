package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class Dictionary implements DictionaryInterface, Container {
	private HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
	// create an object of Dictionary
	private static Dictionary instance = new Dictionary();

	// make the constructor private so that this class cannot be
	// instantiated
	private Dictionary() {
	}

	// Get the only object available
	public static Dictionary getInstance() {
		return instance;
	}

	public void addSynonim(String word, String synonim) {
		assert isWellFormed();
		assert word != null;
		assert synonim != null;
		int n = getNumberOfSynonims(word);
		if (dictionary.containsKey(word)) {
			dictionary.get(word).add(synonim);
		}
		if (dictionary.containsKey(synonim)) {
			dictionary.get(synonim).add(word);
		} else {
			ArrayList<String> s = new ArrayList<String>();
			s.add(word);
			dictionary.put(synonim, s);
		}
		assert (getNumberOfSynonims(word) != 0) && (getNumberOfSynonims(word) == n + 1);
		assert dictionary.get(word).contains(synonim);
		assert isWellFormed();
	}

	public void deleteSynonim(String word, String synonim) {
		assert isWellFormed();
		assert word != null;
		assert synonim != null;
		assert dictionary.get(word).contains(synonim);
		assert getNumberOfSynonims(word) != 0;
		int n = getNumberOfSynonims(word);
		if (dictionary.containsKey(word)) {
			dictionary.get(word).remove(synonim);
		}
		if (dictionary.containsKey(synonim)) {
			dictionary.get(synonim).remove(word);
		}
		assert (getNumberOfSynonims(word) == n - 1);
		assert !(dictionary.get(word).contains(synonim));
		if (dictionary.get(word).isEmpty()) {
			dictionary.remove(word);
		}
		assert isWellFormed();
	}

	public void addWordAndSynonim(String word, ArrayList<String> ss) {
		assert isWellFormed();
		assert word != null;
		assert ss != null;
		int n = ss.size();
		int m = dictionary.size();
		if (dictionary.containsKey(word)) {
			JOptionPane.showMessageDialog(null,
					"The word exists in the dictionary! If you want to add a new synonim please"
							+ " press the button Add Synonim!",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			return;
		} else {
			dictionary.put(word, ss);
			for (int i = 0; i < ss.size(); i++) {
				if (dictionary.containsKey(ss.get(i))) {
					addSynonim(ss.get(i), word);
				} else {
					ArrayList<String> s = new ArrayList<String>();
					s.add(word);
					dictionary.put(ss.get(i), s);
				}
			}
		}
		assert dictionary.size() == m + ss.size() + 1;
		assert getNumberOfSynonims(word) == n;
		assert isWellFormed();
	}

	public void deleteWord(String word) {
		assert isWellFormed();
		assert word != null;
		assert dictionary.containsKey(word);
		int n = dictionary.size();
		if (dictionary.containsKey(word)) {
			dictionary.remove(word);
		}
		String[] keys = new String[dictionary.size()];
		keys = dictionary.keySet().toArray(keys);
		for (String k : keys) {
			String[] values = new String[dictionary.get(k).size()];
			values = dictionary.get(k).toArray(values);
			for (String s : values) {
				if (s.equals(word)) {
					dictionary.get(k).remove(word);
					if (dictionary.get(k).isEmpty()) {
						dictionary.remove(k);
						n--;
					}
				}

			}
		}
		assert dictionary.size() == n - 1;
		assert !(dictionary.containsKey(word));
		assert isWellFormed();

	}

	public ArrayList<String> search(String s) {
		ArrayList<String> result = new ArrayList<>();
		s = s.replaceAll("\\?", "[a-zA-Z]");
		s = s.replaceAll("\\*", ".*");
		Iterator<Entry<String, ArrayList<String>>> iterator = dictionary.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, ArrayList<String>> entry = iterator.next();
			if ((entry.getKey()).matches(s)) {
				result.add(entry.getKey());
			}
		}
		return result;

	}

	public int getNumberOfSynonims(String word) {
		Iterator<Entry<String, ArrayList<String>>> iterator = dictionary.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, ArrayList<String>> entry = iterator.next();
			if (entry.getKey().equals(word)) {
				return entry.getValue().size();
			}
		}
		return 0;

	}

	public boolean isWellFormed() {
		ArrayList<String> values = getValues();
		String[] keys = new String[dictionary.size()];
		keys = dictionary.keySet().toArray(keys);
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(keys));
		int found = 0;
		for (String v : values) {
			if (!(myList.contains(v))) {
				found = 1;
			}
		}
		if (found == 0) {
			return true;
		} else {
			return false;
		}

	}

	public ArrayList<String> getValues() {
		ArrayList<String> result = new ArrayList<String>();
		Iterator<Entry<String, ArrayList<String>>> iterator = dictionary.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, ArrayList<String>> entry = iterator.next();
			for (String s : entry.getValue()) {
				result.add(s);
			}
		}
		return result;
	}

	public HashMap<String, ArrayList<String>> getDictionary() {
		return dictionary;
	}

	public void setDictionary(HashMap<String, ArrayList<String>> dictionary) {
		this.dictionary = dictionary;
	}

	@Override
	public models.Iterator getIterator(String key) {
		return (models.Iterator) new SynonymsIterator(key);
	}

	private class SynonymsIterator implements models.Iterator {
		int index;
		String key;

		private SynonymsIterator(String key) {
			this.key = key;
		}

		@Override
		public boolean hasNext() {

			if (index < dictionary.get(key).size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			Object c = dictionary.get(key).get(index);
			index++;
			return c;
		}
	}

}
