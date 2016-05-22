package controller;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.*;
import model.Dictionary;
import model.Word;

public class InOut {
	private Dictionary dictionary;

	public InOut() {
	}

	public void writeDictionary() {
		dictionary = Dictionary.getInstance();
		XMLEncoder encoder = null;
		try {

			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("words.xml")));

		} catch (FileNotFoundException fileNotFound) {

			System.out.println("ERROR: While Creating or Opening the File words.xml");

		}
		encoder.writeObject(dictionary.getDictionary());
		encoder.close();
	}

	@SuppressWarnings("unchecked")
	public HashMap<Word, String> readDictionary() {
		dictionary = Dictionary.getInstance();
		HashMap<Word, String> result = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("words.xml")));

			// @SuppressWarnings("unchecked")
			result = (HashMap<Word, String>) decoder.readObject();

			decoder.close();

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: While Creating or Opening the File words.xml");
		}
		HashMap<Word, String> res = new HashMap<Word, String>();
		Iterator<Entry<Word, String>> it = result.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Word, String> aux = it.next();
			ArrayList<Word> dep = aux.getKey().getDependencies();
			Word newWord = new Word();
			newWord.addObserver(dictionary);
			newWord.setWord(aux.getKey().getWord());
			for (Word word : dep) {
				Word depen = new Word();
				depen.setWord(word.getWord());
				newWord.add(depen);
			}
			res.put(newWord, aux.getValue());
		}
		return res;
	}

	public void writeOtherWords() {
		dictionary = Dictionary.getInstance();
		XMLEncoder encoder = null;
		try {

			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("others.xml")));

		} catch (FileNotFoundException fileNotFound) {

			System.out.println("ERROR: While Creating or Opening the File others.xml");

		}
		encoder.writeObject(dictionary.getOtherWords());
		encoder.close();
	}

	@SuppressWarnings("unchecked")
	public HashSet<String> readOtherWords() {
		HashSet<String> result = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("others.xml")));

			result = (HashSet<String>) decoder.readObject();

			decoder.close();

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: While Creating or Opening the File others.xml");
		}
		return result;
	}
}
