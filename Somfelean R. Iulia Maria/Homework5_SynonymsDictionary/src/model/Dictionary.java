package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Dictionary implements DicInterface {
	private static HashMap<Word, ArrayList<Word>> dictionary;

	private Dictionary() {
		this.dictionary = new HashMap<Word, ArrayList<Word>>();
	}

	public static Dictionary getInstance() {
		Dictionary d = new Dictionary();
		return d;
	}

	public HashMap<Word, ArrayList<Word>> getDictionary() {
		return dictionary;
	}

	public void setDictionary(HashMap<Word, ArrayList<Word>> dictionary) {
		this.dictionary = dictionary;
	}

	public String addWord(Word w, Word s, int aux) {
		assert isWord(w) == true;
		assert isWord(s) == true;

		WordFactory wf = new WordFactory();

		if (dictionary.containsKey(w)) {
			if (aux == 1) {

				wf.createWord(w.toString(), "contextual");

			} else {
				wf.createWord(w.toString(), "total");
			}
			dictionary.get(w).add(s);
			return "Sinonim adaugat";
		} else {
			ArrayList<Word> syn = new ArrayList<Word>();
			syn.add(s);
			int dim = dictionary.size();
			this.dictionary.put(w, syn);
			assert dim < dictionary.size();
			return "Word added";
		}
	}

	public String removeWord(Word w) {

		assert isWord(w) == true;

		int dim = dictionary.size();
		if (dictionary.containsKey(w)) {
			dictionary.remove(w);
			assert dim > dictionary.size();
			return "Word removed";
		} else
			return "Word does not exist";
	}

	public String getSynonyms(Word w) {
		String s = "";
		ArrayList<Word> syn = new ArrayList<Word>();
		syn = dictionary.get(w);
		for (int i = 0; i < syn.size(); i++)
			s = s + syn.get(i).getWord() + ", ";
		return s;
	}

	public HashMap<Word, ArrayList<Word>> searchWord(String text) {
		assert isText(text) == true;
		HashMap<Word, ArrayList<Word>> l = new HashMap<Word, ArrayList<Word>>();
		for (Entry<Word, ArrayList<Word>> e : dictionary.entrySet())
			if (e.getKey().matchWord(text))
				l.put(e.getKey(), e.getValue());
		return l;
	}

	public boolean isWellFormed() {
		for (Entry<Word, ArrayList<Word>> e : dictionary.entrySet()) {
			ArrayList<Word> l = e.getValue();
			for (int i = 0; i < l.size(); i++) {
				Word w = l.get(i);
				if (!dictionary.containsKey(w))
					return false;
			}
		}
		return true;
	}

	public static boolean isWord(Word w) {
		String s = "?!+-()&^%$#@=";
		String wd = w.getWord();
		for (int i = 0; i < wd.length(); i++) {
			String sub = "" + wd.charAt(i);
			if (s.contains(sub))
				return false;
		}
		return true;
	}

	public static boolean isText(String s) {
		String nr = "123456789";
		for (int i = 0; i < nr.length(); i++) {
			String sub = "" + nr.charAt(i);
			if (s.contains(sub))
				return false;
		}
		return true;
	}

	public String toString() {
		return dictionary.entrySet().stream().map(entry -> entry.getKey() + " = " + entry.getValue())
				.collect(Collectors.joining("; ", "[", "]"));
	}
}
