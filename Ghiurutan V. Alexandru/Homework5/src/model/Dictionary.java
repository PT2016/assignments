package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.InOut;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class Dictionary implements Observer, DictionaryProc {
	private static Dictionary instance;
	private HashMap<Word, String> dictionary;
	private Set<String> otherWords;
	private Iterator<Word> it;
	private Iterator<Entry<Word, String>> iterator;
	private InOut inOut;

	private Dictionary() {

	}

	public void start() {
		inOut = new InOut();
		dictionary = inOut.readDictionary();
		otherWords = inOut.readOtherWords();
		printContent();
	}

	public static Dictionary getInstance() {
		if (instance == null) {
			instance = new Dictionary();
		}
		return instance;
	}

	public void addWord(Word word, String explanation) {
		assert (!dictionary.containsKey(word)) : "Word already in dictionary";
		assert (word != null) : "Invalid word.";
		assert (!explanation.equals("")) : "Empty description.";
		assert (explanation != null) : "Invalid word.";
		assert (isWellFormed()) : "Invariant error.";
		int preSize = dictionary.size();
		dictionary.put(word, explanation);
		inOut.writeDictionary();
		assert (dictionary.size() == preSize + 1) : "Not valid size in dictionary,";
		assert (isWellFormed()) : "Invariant error.";
	}

	public void addOtherWord(String otherWord) {
		assert (otherWord != null) : "Inavlid word";
		assert (!otherWord.equals("")) : "Invalid word.";
		assert (isWellFormed()) : "Invariant error.";
		otherWords.add(otherWord);
		inOut.writeOtherWords();
		assert (isWellFormed()) : "Invariant error.";
	}

	public boolean containsOtherWord(String otherWord) {
		assert (otherWord != null) : "Inavlid word";
		assert (isWellFormed()) : "Invariant error.";
		return otherWords.contains(otherWord);
	}

	public void removeWord(Word word) {
		assert (dictionary.containsKey(word)) : "Not in dictionary.";
		assert (word != null) : "Invalid word.";
		assert (isWellFormed()) : "Invariant error.";
		int preSize = dictionary.size();
		dictionary.remove(word);
		word.delete();
		inOut.writeDictionary();
		assert (preSize - 1 == dictionary.size()) : "Error at size.";
		assert (isWellFormed()) : "Invariant error";
	}

	public boolean containsWord(String newWord) {
		assert (!newWord.equals("")) : "Empty word.";
		assert (newWord != null) : "Null word";
		assert (isWellFormed()) : "Invariant error.";
		Set<Word> set = dictionary.keySet();
		it = set.iterator();
		while (it.hasNext()) {
			if (it.next().getWord().equals(newWord)) {
				assert (isWellFormed()) : "Invariant error.";
				return true;
			}
		}
		assert (isWellFormed()) : "Invariant error.";
		return false;
	}

	public Word getWord(String word) {
		Set<Word> set = dictionary.keySet();
		it = set.iterator();
		while (it.hasNext()) {
			Word currentWord = it.next();
			if (currentWord.getWord().equals(word)) {
				return currentWord;
			}
		}
		return null;
	}

	public void printContent() {
		System.out.println("The content of the dictionary is:");
		/*
		 * Iterator<Entry<Word, String>> it = dictionary.entrySet().iterator();
		 * while (it.hasNext()) { Map.Entry<Word, String> pair =
		 * (Map.Entry<Word, String>) it.next(); System.out.println(pair.getKey()
		 * + " = " + pair.getValue()); }
		 */
		dictionary.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).forEach(System.out::println);
	}

	public int getTotalNrOfWords() {
		return dictionary.size();
	}

	public Set<Entry<Word, String>> getAllWordsInDictionary() {
		return dictionary.entrySet();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Word> deleteList = (ArrayList<Word>) arg;
		for (Word word : deleteList) {
			dictionary.remove(word);
		}
	}

	public HashMap<Word, String> getDictionary() {
		return dictionary;
	}

	public HashSet<String> getOtherWords() {
		return (HashSet<String>) otherWords;
	}

	public Set<Entry<Word, String>> getSearchResults(String searchWord) {
		Set<Entry<Word, String>> result = new HashSet<Entry<Word, String>>();
		String pattern = searchWord.replaceAll("\\?", "(.)");
		pattern = pattern.replaceAll("\\*", "(.*)");
		Pattern r = Pattern.compile(pattern);
		iterator = dictionary.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Word, String> current = iterator.next();
			Matcher m = r.matcher(current.getKey().getWord());
			if (m.find()) {
				if (m.group(0).equals(current.getKey().getWord()))
					result.add(current);
			}
		}
		return result;
	}

	public boolean isWellFormed() {
		Iterator<Entry<Word, String>> iterator = dictionary.entrySet().iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getKey().getWord().equals("")) {
				return false;
			}
		}
		return true;
	}
}
