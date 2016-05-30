package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.JsonGenerationException;
import model.*;

public class Controller {
	private Dictionary d;
	private WordFactory wf = new WordFactory();

	public Controller() {
		d = Dictionary.getInstance();

	}

	public void initialize() {

		Word w1 = wf.createWord("prieten", "total");
		Word w2 = wf.createWord("amic", "total");
		Word w4 = wf.createWord("trist", "contextual");
		Word w5 = wf.createWord("suparat", "contextual");
		Word w6 = wf.createWord("nefericit", "contextual");

		d.addWord(w1, w2, 1);
		d.addWord(w2, w1, 1);
		d.addWord(w4, w5, 1);
		d.addWord(w4, w6, 1);
		d.addWord(w5, w6, 1);
		d.addWord(w6, w5, 1);

	}

	public String getPreview() {
		String s = "";
		for (Entry<Word, ArrayList<Word>> e : d.getDictionary().entrySet()) {
			for (int i = 0; i < e.getValue().size(); i++)
				s = s + e.getValue().get(i).getWord() + System.lineSeparator();
			s += System.lineSeparator();
		}
		return s;

	}

	public String addWordtoDic(String w, String s, int aux) {
		WordFactory wf = new WordFactory();
		Word word, syn;

		if (aux == 1) {
			word = wf.createWord(w, "contextual");
			syn = wf.createWord(s, "contextual");
		} else {
			word = wf.createWord(w, "total");
			syn = wf.createWord(s, "total");
		}

		return d.addWord(word, syn, aux);
	}

	public String deleteWord(String w, int aux) {
		WordFactory wf = new WordFactory();
		Word word;
		if (aux == 1) {
			word = wf.createWord(w, "contextual");
		} else {
			word = wf.createWord(w, "total");
		}
		return d.removeWord(word);
	}


	public void serialize() {
		this.initialize();
		HashMap<Word, ArrayList<Word>> dic = d.getDictionary();
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("synonymDictionary.json");
		try {
			file.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			mapper.writeValue(file, dic);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void deserialize() {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("synonymDictionary.json");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {

			TypeReference<HashMap<Word, ArrayList<Word>>> typeRef = new TypeReference<HashMap<Word, ArrayList<Word>>>() {
			};
			HashMap<Word, ArrayList<Word>> aux = mapper.readValue(file, typeRef);
			d.setDictionary(aux);
			System.out.println("?");
		} catch (JsonGenerationException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public Dictionary getD() {
		return d;
	}

	public void setD(Dictionary d) {
		this.d = d;
	}
}
