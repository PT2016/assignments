package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Dictionary implements DictionaryProc {

	private static Dictionary dictionary = new Dictionary();
	private HashMap<String, ArrayList<String>> dictionaryMap;
	private String message;

	private FileWriter writer;

	private Dictionary() {
		dictionaryMap = new HashMap<String, ArrayList<String>>();

	}

	@Override
	public void addWord(String word) {
		assert word != null;
		assert dictionaryMap.containsKey(word) == false;
		assert wellFormed();
		int sizeWord = dictionaryMap.size();
		dictionaryMap.put(word, new ArrayList<String>());
		// message += "\nAdded word " + word;
		assert dictionaryMap.containsKey(word);
		assert dictionaryMap.size() == sizeWord + 1;
		assert wellFormed();
	}

	@Override
	public void removeWord(String word) {
		assert word!=null;
		assert (dictionaryMap.containsKey(word));
		assert wellFormed();
		int sizeWord = dictionaryMap.size();
		dictionaryMap.remove(word);
		message += "\nRemoved word " + word;

		for (String tempWord : dictionaryMap.keySet()) {
			Iterator<String> tempSyn = dictionaryMap.get(tempWord).iterator();
			while (tempSyn.hasNext()) {
				String synonym = tempSyn.next();
				if (synonym.compareTo(word) == 0) {
					tempSyn.remove();
					// message += "\nRemoved synonym " + word + " from word " +
					// tempWord;
				}

			}

		}
		assert (dictionaryMap.containsKey(word) == false);
		assert dictionaryMap.size() == sizeWord - 1;
		assert wellFormed();
	}

	@Override
	public void addSynonym(String word, String synonym) {
		assert word != null;
		assert synonym != null;
		assert wellFormed();
		assert dictionaryMap.containsKey(word);

		dictionaryMap.get(word).add(synonym);

		if (dictionaryMap.containsKey(synonym) == false)
			dictionaryMap.put(synonym, new ArrayList<String>());
		dictionaryMap.get(synonym).add(word);

		for (String syn : dictionaryMap.get(word)) {
			if (syn.equals(synonym) == false) {
				dictionaryMap.get(syn).add(synonym);
				dictionaryMap.get(synonym).add(syn);
			}
		}

		// message+="\n Synonym "+synonym +" was added to word "+word +"and
		// viceversa";
		assert dictionaryMap.containsKey(synonym);
		assert dictionaryMap.get(word).contains(synonym);
		assert dictionaryMap.get(synonym).contains(word);
		assert wellFormed();

	}

	@Override
	public void removeSynonym(String word, String synonym) {
		assert word != null;
		assert synonym != null;
		assert dictionaryMap.containsKey(word);
		assert wellFormed();
		dictionaryMap.get(word).remove(synonym);
		dictionaryMap.get(synonym).remove(word);
		for (String syn : dictionaryMap.get(word)) {
			dictionaryMap.get(syn).remove(synonym);
			dictionaryMap.get(synonym).remove(syn);
		}
		// message+="\n Synonym "+synonym +" was removed from word "+word +"and
		// viceversa";
		assert dictionaryMap.get(word).contains(synonym) == false;
		assert dictionaryMap.get(synonym).contains(word) == false;
		assert wellFormed();
	}

	@Override
	public String displayWord(String word) {
		assert word!=null;
		assert dictionaryMap.containsKey(word);
		assert wellFormed();
		String entri = "\n";
		entri += word + " = ";
		for (String syn : dictionaryMap.get(word)) {
			entri += syn + " , ";
		}
		assert dictionaryMap.containsKey(word);
		assert wellFormed();
		assert entri!=null;
		return entri;
	}

	@Override
	public HashSet<String> searchWord(String key) {
		assert key != null;
		assert wellFormed();
		boolean found = false;
		HashSet<String> matchingWords = new HashSet<String>();
		for (String word : dictionaryMap.keySet()) {
			String pref;
			String suff;
			boolean st = false;
			boolean en = false;
			if (key.contains("*")) {
				int index = key.indexOf("*");
				if (index != 0) {
					pref = key.substring(0, index);
					st = word.startsWith(pref);
				}
				if (index != key.length()) {
					suff = key.substring(index + 1, key.length());
					en = word.endsWith(suff);
				}
				if (index == 0) {
					if (en == true) {
						matchingWords.add(word);
						found = true;
					}
				} else if (index == word.length() - 1) {
					if (st == true) {
						matchingWords.add(word);
						found = true;
					}
				} else if ((st) && (en)) {
					matchingWords.add(word);
					found = true;
				}
			} else if (key.contains("?")) {
				if (word.length() == key.length()) {
					int index = key.indexOf("?");
					pref = key.substring(0, index);
					suff = key.substring(index + 1, key.length());

					if (pref.equals(word.substring(0, index))) {
						if (suff.equals(word.substring(index + 1, word.length()))) {
							matchingWords.add(word);
							found = true;
						}
					}
				}
			} else {
				if (word.equals(key)){
					matchingWords.add(key);
					found = true;
				}
			}
		}
		
        if (found==false) matchingWords.add("No word matches your search.");
        assert wellFormed();
        assert matchingWords!=null;
        return matchingWords;
	}

	public static Dictionary getInstance() {
		return dictionary;
	}

	public static void setInstance(Dictionary dictionary) {
		Dictionary.dictionary = dictionary;
	}

	public HashMap<String, ArrayList<String>> getDictionaryMap() {
		return dictionaryMap;
	}

	public void setDictionaryMap(HashMap<String, ArrayList<String>> dictionaryMap) {
		this.dictionaryMap = dictionaryMap;
	}

	public void showMessage() {

		System.out.println(message);
	}

	public boolean wellFormed() {
		boolean isWellFormed = true;
		for (String word : dictionaryMap.keySet())
			for (String syn : dictionaryMap.get(word)) {
				if (dictionaryMap.containsKey(syn) == false)

					isWellFormed = false;

			}
		return isWellFormed;

	}
	

public void writeToJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonFromDictionary = mapper.writeValueAsString(dictionaryMap);
			writer = new FileWriter("dictionary.txt");
			writer.write(jsonFromDictionary);
			writer.close();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readFromJson() {
		ObjectMapper mapper = new ObjectMapper();
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new FileReader("dictionary.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dictionaryMap = (HashMap<String, ArrayList<String>>) mapper.readValue(buffer, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}
