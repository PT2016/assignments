package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Dictionary extends Observable implements DictionaryInterface {

	private HashMap<String, ArrayList<String>> dictionaryWords;

	public Dictionary() {
		dictionaryWords = new HashMap<String, ArrayList<String>>();
	}

	@Override
	public void addWord(String word) {
		assert isWellFormed();
		assert word != null : "Empty word";
		int size = dictionaryWords.size();

		dictionaryWords.put(word, new ArrayList<String>());
		notifyObservers();

		assert isWellFormed();
		assert dictionaryWords.size() == size + 1 : "Word not added";
	}

	@Override
	public void deleteWord(String word) {
		assert isWellFormed();
		assert word != null;
		int size = dictionaryWords.size();
		int toRemove = 1;

		String deleteWord = word;
		ArrayList<String> deleteSyns = new ArrayList<String>();
		String deleteSyn = null;

		for (String w : dictionaryWords.keySet()) {
			if (w.equals(word)) {
				if (dictionaryWords.get(w).size() > 1) {
					for (String syn : dictionaryWords.get(w)) {
						deleteSyns.add(syn);
						toRemove++;
					}
				} else {
					deleteSyn = dictionaryWords.get(word).get(0);
				}
				deleteWord = word;
			}
		}
		System.out.println("Delete word: " + deleteWord);
		if (deleteSyns.size() > 0) {
			System.out.println("Syns to delete");
			for (String syn : deleteSyns) {
				System.out.println(syn);
			}
		}
		boolean ok = false;
		boolean foundSyn = false;
		if (deleteSyns.size() > 1) {
			for (String syn : deleteSyns) {
				if (dictionaryWords.get(syn).size() > 1) {
					dictionaryWords.get(syn).remove(deleteWord);
				} else {
					dictionaryWords.remove(syn);
				}
			}
			dictionaryWords.remove(deleteWord);
		} else {
			for (String w : dictionaryWords.keySet()) {
				if (w.equals(deleteSyn) && dictionaryWords.get(deleteSyn).size() == 1) {
					dictionaryWords.remove(deleteSyn);

					ok = true;
					break;
				}
			}
			dictionaryWords.remove(deleteWord);
			for (String w : dictionaryWords.keySet()) {
				if (w.equals(deleteSyn) && dictionaryWords.get(deleteSyn).size() > 1) {
					dictionaryWords.get(deleteSyn).remove(deleteWord);
					break;
				}
			}
		}
		if (!ok) {
			for (String w1 : dictionaryWords.keySet()) {
				if (w1.equals(deleteSyn)) {
					dictionaryWords.get(w1).remove(deleteWord);
				}
			}

		}

		assert isWellFormed();
		assert dictionaryWords.size() == size - toRemove;
	}

	@Override
	public void displayWords() {
		for (String str : dictionaryWords.keySet()) {
			dictionaryWords.keySet().stream().filter(s -> s.equals(str)).map(String::toUpperCase).sorted()
					.forEach(System.out::println);
			dictionaryWords.get(str).stream().map(String::toLowerCase).sorted().forEach(System.out::println);
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, ArrayList<String>> getWords() {

		return dictionaryWords;
	}

	@Override
	public void addSynonim(String word, String synonim) {
		assert isWellFormed();
		assert word != null;
		assert synonim != null;
		int size = dictionaryWords.size();
		int synSize = dictionaryWords.get(word).size();

		dictionaryWords.get(word).add(synonim);
		boolean found = false;
		for (String w : dictionaryWords.keySet()) {
			if (w.equals(synonim)) {
				dictionaryWords.get(synonim).add(word);
				found = true;
			}
		}
		if (!found) {
			dictionaryWords.put(synonim, new ArrayList<String>());
			dictionaryWords.get(synonim).add(word);
		}

		notifyObservers();

		assert isWellFormed();
		assert dictionaryWords.size() == size + 1;
		assert dictionaryWords.get(word).size() == synSize + 1;

	}

	@Override
	public ArrayList<String> searchWord(String word) {
		assert isWellFormed();
		assert word != null;

		ArrayList<String> result = new ArrayList<String>();
		if (word.contains("?")) {
			System.out.println("Contains ?");
			int index = word.indexOf("?");
			for (String w : dictionaryWords.keySet()) {
				if ((w.substring(0, index).equals(word.substring(0, index)))) {
					if (w.substring(index + 1, w.length()).equals(word.substring(index + 1, word.length()))) {
						result.add(w);
					}
				}
			}
		} else if (word.contains("*")) {
			int index = word.indexOf("*");

			for (String w : dictionaryWords.keySet()) {
				if (w.substring(0, index).equals(word.substring(0, index))) {
					String w2 = w.substring(index + 1, w.length());
					String w3 = word.substring(index + 1, word.length());

					if (w2.contains(w3)) {
						int length = w2.length() - w3.length();
						boolean ok = true;
						int nr = w2.indexOf(w3.charAt(0));
						int k = 0;
						while (ok == true && k < w3.length()) {
							if (w2.charAt(nr) != w3.charAt(k)) {
								ok = false;
							}
							k++;
							nr++;
						}
						if (ok == true) {
							result.add(w);
						}
					}
				}
			}
		} else if (dictionaryWords.containsKey(word)) {
			int length = (dictionaryWords.get(word).stream().toArray().length);
			System.out.println(length);
			for (int i = 0; i < length; i++) {
				result.add(dictionaryWords.get(word).get(i));
			}
		} else {
			dictionaryWords.put(word, new ArrayList<String>());
		}

		assert isWellFormed();
		assert result != null;

		return result;
	}

	@Override
	public boolean isWellFormed() {
		ArrayList<Boolean> check = new ArrayList<Boolean>();
		boolean ok = true;
		for (String word : dictionaryWords.keySet()) {
			for (String word1 : dictionaryWords.get(word)) {
				check.add(dictionaryWords.containsKey(word1));
			}

		}
		if (check.contains(false)) {
			ok = false;
		}
		return ok;
	}

}
