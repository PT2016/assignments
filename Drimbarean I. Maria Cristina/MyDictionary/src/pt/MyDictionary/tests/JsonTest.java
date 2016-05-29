package pt.MyDictionary.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;

import pt.MyDictionary.model.Dictionary;
import pt.MyDictionary.model.Word;

public class JsonTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		Dictionary dic = new Dictionary();

		try {
			JSONArray a = (JSONArray) parser.parse(new FileReader("test1.json"));
			for (Object o : a) {
				JSONObject word = (JSONObject) o;
				String synonymString = new String();
				String w = (String) word.get("word");
				// loop array of synonyms
				JSONArray synonyms = (JSONArray) word.get("synonyms");
				for (Object object : synonyms) {
					synonymString += object;
					synonymString += ",";
				}
				synonymString = synonymString.substring(0, synonymString.length() - 1);
				String d = (String) word.get("description");
				Word theWord = new Word(w, synonymString, d);
				System.out.println(theWord.toString());
				dic.add(theWord);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray array = new JSONArray();
		for (Entry<String, Word> entry : dic.getWords().entrySet()) {
			JSONObject obj = new JSONObject();
			obj.put("word", entry.getValue().getWord());

			JSONArray list = new JSONArray();
			for (int j = 0; j < entry.getValue().getSynonyms().length; j++) {
				list.add(entry.getValue().getSynonyms()[j]);
			}
			obj.put("synonyms", list);
			obj.put("description", entry.getValue().getDescription());

			System.out.print(obj);
            array.add(obj);
		}
		try {

			FileWriter file = new FileWriter("test1.json");
			file.write(array.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
