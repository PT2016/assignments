package Control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Helper {

	@SuppressWarnings("unchecked")
	public void saveInJSONFile(Map<String, List<String>> dictionaryData) {

		JSONObject obj = new JSONObject();

		for (Map.Entry<String, List<String>> entry : dictionaryData.entrySet()) {
			obj.put(entry.getKey(), entry.getValue());
		}

		try {

			File file = new File("dictionary.json");
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);

			fileWriter.write(obj.toString());
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, List<String>> readFromJSONFile() throws org.json.simple.parser.ParseException, ParseException {
		
		Map<String, List<String>> dictionary = new TreeMap<String, List<String>>();
		JSONParser parser = new JSONParser();
		
		try {

			Object obj =  parser.parse(new FileReader("dictionary.json"));

			JSONObject jsonObject = (JSONObject) obj;

			Set<String> keys = (Set<String>) jsonObject.keySet();

			for (String key : keys) {
				List<String> values = new ArrayList<String>();
				JSONArray arr = (JSONArray) jsonObject.get(key);
				Iterator<String> iterator = arr.iterator();
				while (iterator.hasNext()) {
					values.add(iterator.next());
				}
				dictionary.put(key, values);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dictionary;
	}
}
