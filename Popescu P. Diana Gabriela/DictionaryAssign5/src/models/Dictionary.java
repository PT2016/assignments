package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Dictionary implements DictionaryProc {

	public Map<String, ArrayList<String>> hashMap;
	/*
	 * public Dictionary() { //hashMap = new HashMap<>(); this.populate(); }
	 */

	@Override
	public void addSynonim(String key, String synonim) {
		assert isConsistent() : "not consistent";
		assert key != null : "key should not be null";
		assert synonim != null : "synonim should not be null";
		if (hashMap.containsKey(key)) {
			if (!hashMap.containsKey(synonim)) { // set like~

				ArrayList<String> aux = new ArrayList<>();
				aux.add(key);
				hashMap.put(synonim, aux);

				ArrayList<String> syns = this.getSyn(key);
				int auxssize = syns.size();
				for (int auxi = 0; auxi < auxssize; auxi++) {
					this.copy(syns.get(auxi), synonim);
					this.copy(synonim, syns.get(auxi));
				}
				this.copy(key, synonim);
			}
		} else {
			if (hashMap.containsKey(synonim)) { // set like~

				ArrayList<String> aux = new ArrayList<>();
				aux.add(synonim);
				hashMap.put(key, aux);

				ArrayList<String> syns = this.getSyn(synonim);
				int auxssize = syns.size();
				for (int auxi = 0; auxi < auxssize; auxi++) {
					this.copy(syns.get(auxi), key);
					this.copy(key, syns.get(auxi));
				}
				this.copy(synonim, key);
			} else {
				ArrayList<String> aux = new ArrayList<>();
				aux.add(synonim);
				hashMap.put(key, aux);
				this.copy(synonim, key);
			}
		}
		assert isConsistent() : "not consistent";
	}

	@Override
	public void deleteSynonim(String key) {
		assert isConsistent() : "not consistent";
		assert key != null : "key should not be null";
		int oldsize = hashMap.get(key).size();
		if (hashMap.containsKey(key)) {
			ArrayList<String> syns = this.getSyn(key);

			for (int auxx = 0; auxx < syns.size(); auxx++) {
				this.delete(syns.get(auxx), key);
			}
			hashMap.remove(key);
		}
		assert oldsize == hashMap.get(key).size() - 1 : "updating size";
		assert isConsistent() : "not consistent";
	}

	@Override
	public ArrayList<String> searchWord(String key) {
		// * (any string, including null) and ? (one character)
		assert isConsistent() : "not consistent";
		assert hashMap != null : "dictonary is empty";

		ArrayList<String> matchingWords = new ArrayList<>();
		int[] valueStar = new int[key.length()];
		for (int i = 0; i < valueStar.length; i++) {
			valueStar[i] = 0;
		}

		for (int i = 0; i < key.length(); i++) {
			if (((key.charAt(i) == '?') || (key.charAt(i) == '*')) && i == 0) {
				key.replace(key.charAt(i), '+');
				key = '.' + key;
			} else if (key.charAt(i) == '?') {
				key = key.substring(0, i) + "." + key.substring(i + 1, key.length());
			}

			else if (key.charAt(i) == '*') {
				key = key.substring(0, i) + "." + key.substring(i + 1, key.length());
				valueStar[i]++;
			}

			System.out.println("word: " + key);
		}
		try {
			for (int i = 0; i < valueStar.length - 1; i++) {
				if (valueStar[i] != 0) {
					key = key.substring(0, i + 1) + "*" + key.substring(i + 2, key.length());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Pattern pattern = Pattern.compile(key);
			for (String candidate : hashMap.keySet()) {
				Matcher matcher = pattern.matcher(candidate);
				if (matcher.find()) {
					matchingWords.add(candidate);
				}
			}
		} catch (PatternSyntaxException e) {
			System.out.println("Incorrect pattern syntax: " + key);
		}

		assert isConsistent() : "not consistent";
		return matchingWords;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void populate() {
		hashMap = new HashMap<>();
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("dictionary"));
			JSONObject jsonObject = (JSONObject) obj;
			Set<String> keys = (Set<String>) jsonObject.keySet();

			for (String key : keys) {
				ArrayList<String> synonims = new ArrayList<String>();
				JSONArray jsonArray = (JSONArray) jsonObject.get(key);
				Iterator<String> iterator = jsonArray.iterator();
				while (iterator.hasNext()) {
					synonims.add(iterator.next());
				}
				hashMap.put(key, synonims);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		System.out.println("dicionary populated");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save() { // serialize
		JSONObject jsonObject = new JSONObject();
		for (Entry<String, ArrayList<String>> entry : hashMap.entrySet()) {
			JSONArray jsonArray = new JSONArray();
			for (String synonim : entry.getValue()) {
				jsonArray.add(synonim);
			}
			jsonObject.put(entry.getKey(), jsonArray);
		}
		try {
			File file = new File("dictionary");
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);

			fileWriter.write((jsonObject.toJSONString()));
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("new dictionary saved");
	}

	@Override
	public boolean isConsistent() {
		for (String key : hashMap.keySet()) {
			for (String synonim : hashMap.get(key)) {
				if (!hashMap.containsKey(synonim)) {
					return false;
				}
			}
		}
		return true;
		/*
		 * boolean state = true; state = hashMap.entrySet().stream() .allMatch(e
		 * -> e.getValue().stream().allMatch(s ->
		 * hashMap.get(s).contains(e.getKey()))); return state;
		 */
	}

	@Override
	public void copy(String key, String synonim) {
		assert key != null : "key should not be null";
		assert synonim != null : "synonim should not be null";
		if (hashMap.containsKey(key)) {
			if (!hashMap.get(key).contains(synonim)) { // set like
				hashMap.get(key).add(synonim);
			}
		} else {
			ArrayList<String> aux = new ArrayList<>();
			aux.add(synonim);
			hashMap.put(key, aux);
		}
	}

	@Override
	public void delete(String key, String synonim) {
		assert key != null : "key should not be null";
		assert synonim != null : "synonim should not be null";
		if (hashMap.containsKey(key)) {
			if (hashMap.get(key).contains(synonim)) {
				hashMap.get(key).remove(synonim);
			}
		}
	}

	@Override
	public Set<String> getKeys() {
		return hashMap.keySet();
	}

	@Override
	public ArrayList<String> getSyn(String key) {
		return hashMap.get(key);
	}

	@Override
	public int getSize() {
		return hashMap.size();
	}
}
