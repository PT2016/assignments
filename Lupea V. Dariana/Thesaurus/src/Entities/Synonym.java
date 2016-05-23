package Entities;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Synonym implements SynonymInterface {

	private Map<String, ArrayList<String>> dictionary;
	private WordFactory wf;

	public Synonym() {

		dictionary = new HashMap<String, ArrayList<String>>();
		wf = new WordFactory();

	}

	// The dictionary is well-formed if it is consistent
	public boolean isWellFormed() {
		boolean check = false;
		for (Entry<String, ArrayList<String>> entry : dictionary.entrySet()) {
			String word = entry.getKey();
			for (Entry<String, ArrayList<String>> entry2 : dictionary.entrySet()) {
				ArrayList<String> synonyms = entry2.getValue();
				if (synonyms.contains(word)) {
					check = true;
					break;
				}
			}
		}
		return check;
	}

	@Override
	public void addNewWord(String word, ArrayList<String> synonyms) {
		assert isWellFormed() : "The dictionary is not well consistent";
		assert ((word != null) && (word.length() > 0)) : "Incorrect word";
		assert ((synonyms != null) && (synonyms.size() > 0)) : "Incorrect list of synonyms";

		dictionary.put(word, synonyms);
		maintainConsistency(word, synonyms);
		Word newWord = wf.getWordLength(word, synonyms);

		WordObserver o = new WordObserver();
		newWord.addObserver(o);
		newWord.notifyObserver();

		assert (containsWord(word)) : "The word was not added!";
		assert isWellFormed() : "The dictionary is not well consistent";
	}

	@Override
	public void removeWord(String word) {
		assert isWellFormed() : "The dictionary is not well consistent";
		assert ((word != null) && (word.length() > 0)) : "Incorrect word";

		ArrayList<String> synonyms = dictionary.get(word);// get the synonyms
		dictionary.remove(word);
		
		if (synonyms.size() == 1){
			String wordToBeDeleted = synonyms.get(0);
			dictionary.remove(wordToBeDeleted);
		}
		for (String s : synonyms) {
			if (containsWord(s)) {// for each synonym of the deleted word
				dictionary.get(s).remove(word);// remove the word from the
				System.out.println("removed synonym"); // synonym list
			}

		}
		
		assert (!containsWord(word)) : "The word was not deleted!";
		assert isWellFormed() : "The dictionary is not well consistent";
	}

	@Override
	public boolean containsWord(String word) {
		assert isWellFormed() : "The dictionary is not well consistent";
		for (Map.Entry<String, ArrayList<String>> entry : dictionary.entrySet()) {
			if (word.equals(entry.getKey().toString())) {
				return true;
			}
		}
		assert isWellFormed() : "The dictionary is not well consistent";
		return false;
	}

	@Override
	public void populateDictionary(String fileName) {
		assert dictionary.size() == 0 : "HashMap not empty";

		Gson gson = new GsonBuilder().create();
		Type typeOfHashMap = new TypeToken<Map<String, ArrayList<String>>>() {
		}.getType();
		try {
			dictionary = gson.fromJson(new FileReader("Dictionary.json"), typeOfHashMap);
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Dictionary populated");
		System.out.println(dictionary);

		assert dictionary.size() > 0 : "Dictionary not populated";
		assert isWellFormed() : "The dictionary is not well consistent";
	}

	@Override
	public ArrayList<String> searchWord(String pattern) {
		assert isWellFormed() : "The dictionary is not well consistent";
		assert pattern != null : "Invalid pattern";

		ArrayList<String> result = new ArrayList<String>();

		if (pattern.length() > 0) {
			// Transform all characters to lower case
			pattern = pattern.toLowerCase();

			pattern = pattern.replace("*", "[a-zA-Z]*");
			pattern = pattern.replace("?", "[a-zA-Z]");
			pattern = pattern + "[a-zA-Z]*";

			// Find matches
			ArrayList<String> matches = new ArrayList<String>();
			Pattern searchPattern = Pattern.compile(pattern);// create pattern
																// obj
			Matcher patternMatcher;

			for (Map.Entry<String, ArrayList<String>> entry : dictionary.entrySet()) {
				patternMatcher = searchPattern.matcher(entry.getKey());

				if (patternMatcher.matches()) {
					matches.add(entry.getKey());
				}
			}

			result = matches;
		}
		assert result.size() > 0 : "No matching patterns";
		assert isWellFormed() : "The dictionary is not well consistent";
		return result;
	}

	@Override
	public ArrayList<String> searchSynonyms(String word) {
		ArrayList<String> found = new ArrayList<String>();
		String search;
		for (Map.Entry<String, ArrayList<String>> entry : dictionary.entrySet()) {
			search = entry.getKey();
			if (search.equals(word)) {
				found = entry.getValue();
			}
		}
		return found;
	}

	public void saveToJson() {/// as putea sa o pun in helper!!!
		FileOutputStream os = null;
		try {
			os = new FileOutputStream("Dictionary.json", false);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String temp = gson.toJson(dictionary);
			try {
				bw.append(temp);
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void maintainConsistency(String word, ArrayList<String> synonyms) {
		for (String synonym : synonyms) {
			if (!containsWord(synonym)) {
				ArrayList<String> synonymList = new ArrayList<String>();

				for (String s : synonyms) {
					synonymList.add(s);
				}

				synonymList.remove(synonym);// Remove word form its own
											// synonymList
				synonymList.add(word);// add current word as synonym

				dictionary.put(synonym, synonymList);
				System.out.println("maintain consistency");
			}

		}
	}

	public void retrieveInput() {
		Iterator<Entry<String, ArrayList<String>>> it = dictionary.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ArrayList<String>> pair = it.next();
			String word = pair.getKey();
			ArrayList<String> syno = pair.getValue();
			System.out.println("The word is: " + word);
			System.out.println("The synonyms are:");
			for (int i = 0; i < syno.size(); i++) {
				System.out.println(syno.get(i) + " ");
			}
		}
	}

	public String toString() {
		return "Dictionary: [" + dictionary + "]";

	}

}
