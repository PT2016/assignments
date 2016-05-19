package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.ActualWord;

public class SimpleHashMap implements SimpleMap {

	private Map<String, List<String>> hashMap = new HashMap<>();
	private WordFactory factory;
	public SimpleHashMap(){
		factory = new WordFactory();
	}

	@Override
	public void addElement(String key, List<String> values) {
		try {
			assert key != null && isAlpha(values.get(0));
		} catch (AssertionError er) {
			JOptionPane.showMessageDialog(null,
					"The new word must have at least one synonim");
			return;
		}
		IWord word = factory.getWord(key, values);
		((Observable) word).addObserver(new NewWordObserver());
		((ActualWord) word).notifyObserver();
		hashMap.put(word.getName(), word.getDescription());
		
		try {
			assert isConsistent();
		} catch (AssertionError er) {
			}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void removeElement(String key) {
		try {
			assert key != null;
		} catch (AssertionError er) {
			JOptionPane.showMessageDialog(null,
					"Error ! Could not remove the element.");
			return;
		}
		Iterator it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (key.equals(pair.getKey())) {
				it.remove();
			}
		}
		try {
			assert isConsistent();
		} catch (AssertionError er) {
			
			}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String searchElement(String key) {
		try {
			assert key != null && hashMap.isEmpty()==false;
		} catch (AssertionError er) {
			JOptionPane.showMessageDialog(null,
					"Error searching ! Either the dictionary is empty or you typed nothing");
			return null;
		}
		Pattern mySearchPattern = Pattern.compile(createPattern(key));
		String s = "";
		Iterator it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();

			if (mySearchPattern.matcher(((String) entry.getKey())).matches()) {
				s += entry.getKey() + " = " + entry.getValue() + "\n";
			}
		}
		
		assert isConsistent();
		
		if (s.equals(""))
			return "The word " + key + " was not found";
		else
			return s;
	}

	

	@Override
	public void populate(String fileName) throws IOException {
		int pane=5;
		try {
			assert fileName.endsWith(".json") && fileName != null;
		} catch (AssertionError er) {
			JOptionPane.showMessageDialog(null, "Could not load file. Please check the file. Creating basic hash map for testing.");
			IWord n = factory.getWord("EMPTY", null);
			hashMap.put(n.getName(), n.getDescription());
			return;
		}
		
		Gson gson = new GsonBuilder().create();
		Type typeOfHashMap = new TypeToken<Map<String, List<String>>>() {
		}.getType();
		hashMap = gson.fromJson(new FileReader(fileName), typeOfHashMap);
	
		try {
			assert isConsistent();
		} catch (AssertionError er) {
			pane = JOptionPane.showConfirmDialog(null,"Warning! Dictionary not consistent.\n"
					+ "Do you still want to open it ?");
			if(pane==1 || pane==2 || pane==-1){
				hashMap.clear();
			}
		}
	}

	@Override
	public void saveToFile(File fileName) {
		int pane = 5;
		try {
			assert fileName != null && fileName.isFile();
		} catch (AssertionError er) {
			JOptionPane.showMessageDialog(null, "No file to save.");
			return;
		}
		
		try {
			assert isConsistent();
		} catch (AssertionError er) {
			pane = JOptionPane.showConfirmDialog(null,
					"WARNING !!! Dictionary not consistent ! \n"
							+ "Continue anyway?");
		}

		if (pane == 0 || pane == 5) {
			try {
				FileOutputStream os = null;
				os = new FileOutputStream(fileName, false);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String temp = gson.toJson(hashMap);
				bw.append(temp);
				bw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateElement(String key, List<String> values) {
		try {
			assert key != null && isAlpha(values.get(0));
		} catch (AssertionError er) {
			JOptionPane.showMessageDialog(null,
					"Error updating ! You must type at least one synonim");
			return;
		}
		hashMap.get(key).addAll(values);
		
		try {
			assert isConsistent();
		} catch (AssertionError er) {
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void showMap() {
		Iterator it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
		}
	}

	@SuppressWarnings("rawtypes")
	public int getTotalEntriesForTable() {
		int i = 0;
		Iterator it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			i++;
			it.next();
		}
		return i;
	}

	@SuppressWarnings("rawtypes")
	public Object[][] getAllEntriesForTable() {
		Object[][] result = new Object[getTotalEntriesForTable()][2];
		int i = 0;
		Iterator it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			result[i][0] = pair.getKey();
			result[i][1] = pair.getValue();
			i++;
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean isConsistent() {
		Iterator it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			for (String synonim : ((List<String>) entry.getValue())) {
				if (!hashMap.containsKey(synonim)) {
					return false;
				}
			}
		}
		return true;
	}

	public String createPattern(String element) {
		String p = "", temp;
		if (!element.contains("*") && !element.contains("?")) {
			for (int i = 0; i < element.length(); i++)
				p += element.charAt(i);
		} else {
			for (int i = 0; i < element.length(); i++) {
				temp = element.charAt(i) + "";
				if (temp.equals("?")) {
					p += "[a-zA-Z]?";
				} else if (temp.equals("*")) {
					p += "[a-zA-Z]*";
				} else {
					p += element.charAt(i);
				}
			}
		}

		return p;
	}

	public Map<String, List<String>> getHashMap() {
		return hashMap;
	}

	public void setHashMap(Map<String, List<String>> hashMap) {
		this.hashMap = hashMap;
	}
	
	
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}

}
