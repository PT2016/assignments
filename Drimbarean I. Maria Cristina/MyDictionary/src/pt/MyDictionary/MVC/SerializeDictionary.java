package pt.MyDictionary.MVC;

import java.io.*;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import pt.MyDictionary.model.Dictionary;
import pt.MyDictionary.model.Word;

public class SerializeDictionary {
  private Dictionary dictionary=new Dictionary();
  public SerializeDictionary(Dictionary dictionary){
	  this.dictionary=dictionary;
  }
  public SerializeDictionary(){
	  loadJson();
  }
    public void loadJson(){
    	 JSONParser parser = new JSONParser();

		    try {
		        JSONArray a = (JSONArray) parser.parse(new FileReader("test.json"));
		        for (Object o : a)
		        {
		            JSONObject word = (JSONObject) o;
                 String synonymString=new String();
		            String w = (String) word.get("word");
		            // loop array of synonyms
		            JSONArray synonyms = (JSONArray) word.get("synonyms");
		            for (Object object : synonyms) {
						synonymString += object;
						synonymString += ",";
		            }
		            synonymString=synonymString.substring(0, synonymString.length()-1);
		            String d = (String) word.get("description");
		            Word theWord=new Word(w,synonymString,d);
		            dictionary.add(theWord);
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
    }
    @SuppressWarnings("unchecked")
	public void saveJson(){
    	JSONArray array = new JSONArray();
		for (Entry<String, Word> entry : dictionary.getWords().entrySet()) {
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

			FileWriter file = new FileWriter("test.json");
			file.write(array.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

    }
	public void save() {
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream("dictionary.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(dictionary);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in dictionary.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}

	public void load() {
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("dictionary.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         dictionary = (Dictionary) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("File not found class not found");
	         c.printStackTrace();
	         return;
	      }		
	}
/**
 * @return the dictionary
 */
public Dictionary getDictionary() {
	return dictionary;
}
/**
 * @param dictionary the dictionary to set
 */
public void setDictionary(Dictionary dictionary) {
	this.dictionary = dictionary;
}
  
}
