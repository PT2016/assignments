package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class Dictionary implements Observer, DictionaryProc {
	private static Dictionary instance;
	private Map<Word, String> dictionary;
	private Set<String> otherWords;
	private Iterator<Word> it;
	private Iterator<Entry<Word, String>> iterator;

	private Dictionary() {
		dictionary = new HashMap<Word, String>();

		Word strong = new Word("strong");
		strong.addObserver(this);
		Word power = new Word("power");
		power.addObserver(this);
		Word body = new Word("body");
		body.addObserver(this);
		Word corp = new Word("corp");
		corp.addObserver(this);
		Word strength = new Word("strength");
		strength.addObserver(this);
		Word powerfull = new Word("powerfull");
		powerfull.addObserver(this);

		powerfull.add(strong);
		body.add(powerfull);
		power.add(powerfull);
		body.add(powerfull);
		corp.add(body);
		body.add(corp);
		strength.add(power);
		dictionary.put(strong, "powerfull");
		dictionary.put(powerfull, "with body power");
		dictionary.put(power, "strength");
		dictionary.put(body, "corp");
		dictionary.put(corp, "body");

		otherWords = new HashSet<String>();
		otherWords.add("is");
		otherWords.add("with");
		otherWords.add("to");
		otherWords.add("from");
	}

	public static Dictionary getInstance() {
		if (instance == null) {
			instance = new Dictionary();
		}
		return instance;
	}

	public void addWord(Word word, String explanation) {
		dictionary.put(word, explanation);
	}

	public void addOtherWord(String otherWord) {
		otherWords.add(otherWord);
	}

	public boolean containsOtherWord(String otherWord) {
		return otherWords.contains(otherWord);
	}

	public void removeWord(Word word) {
		if (dictionary.containsKey(word)) {
			dictionary.remove(word);
			word.delete();
		}
	}

	public boolean containsWord(String newWord) {

		Set<Word> set = dictionary.keySet();
		it = set.iterator();
		while (it.hasNext()) {
			if (it.next().getWord().equals(newWord)) {
				return true;
			}
		}
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
		Iterator<Entry<Word, String>> it = dictionary.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Word, String> pair = (Map.Entry<Word, String>) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
		}
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
}
