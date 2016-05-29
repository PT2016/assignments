package pt.MyDictionary.model;

import java.util.TreeMap;
import java.io.Serializable;
import java.util.Map.Entry;

public class Dictionary implements DictionaryInterface,Serializable {
	private static final long serialVersionUID = 3549940696276587051L;
	private TreeMap<String, Word> words;
	private int noWords;

	public Dictionary(TreeMap<String, Word> words) {
		this.words = new TreeMap<String, Word>(words);
		noWords = 0;
	}
	public Dictionary(){
		this.words=new  TreeMap<String,Word>();
	}
	@Override
	public void add(String word) {
		assert word != null : "The input cannot be null!";
		assert isWellFormed() : "State of dictionay is not well formed!";
		int noWordBefore = noWords;
		if (!words.containsKey(word)) {
			words.put(word, new Word(word));
			noWords = words.size();
			assert isWellFormed() : "State of dictionay is not well formed!";
			assert noWords == noWordBefore + 1 : "noWords is not updated corectly";
			assert words.containsKey(word) : "The adding of word has not succeded";
		}
		assert isWellFormed() : "State of dictionay is not well formed!";
	}

	@Override
	public void add(Word word) {
		assert word != null : "The input cannot be null!";
		assert isWellFormed() : "State of dictionay is not well formed!";
		int noWordBefore = noWords;
		if (!words.containsValue(word)) {
			words.put(word.getWord(), word);
			noWords = words.size();
			assert isWellFormed() : "State of dictionay is not well formed!";
			assert noWords == noWordBefore + 1 : "noWords is not updated corectly";
			assert words.containsValue(word) : "The adding of word has not succeded";
		}
		assert isWellFormed() : "State of dictionay is not well formed!";
	}

	@Override
	public void remove(String word) {
		assert word != null : "The input cannot be null!";
		assert isWellFormed() : "State of dictionay is not well formed!";
		int noWordBefore = noWords;
		if (words.containsKey(word)) {
			words.remove(word);
			noWords = words.size();
			assert isWellFormed() : "State of dictionay is not well formed!";
			assert noWords == noWordBefore - 1 : "noWords is not updated corectly";
			assert !words.containsKey(word) : "The removal of word has not succeded";
		}
		assert isWellFormed() : "State of dictionay is not well formed!";
	}

	@Override
	public Word search(String word) {
		assert word != null: "The input cannot be null!";
		assert isWellFormed() : "State of dictionay is not well formed!";
		WildcardMatch compare = new WildcardMatch();
		for (Entry<String, Word> entry : words.entrySet()) {
			if (compare.compare(entry.getKey(), word) == true)
				return entry.getValue();
		}
		assert isWellFormed() : "State of dictionay is not well formed!";
		return null;
	}

	public String sSearch(String word) {
		assert word != null;
		assert isWellFormed() : "State of dictionay is not well formed!";
		String s = new String();

		WildcardMatch compare = new WildcardMatch();

		for (Entry<String, Word> entry : words.entrySet()) {
			if (compare.compare(entry.getKey(), word) == true) {

				String[] synonyms = entry.getValue().getSynonyms();
				String synonymString = new String();
				for (int i = 0; i < synonyms.length; i++) {
					synonymString += synonyms[i];
					if (i < (synonyms.length - 1))
						synonymString += ", ";
				}

				s += "\nWord: " + entry.getValue().getWord() + "\nSynonyms: " + synonymString + "\nDescription: "
						+ entry.getValue().getDescription() + "\n";
			}
		}
		assert isWellFormed() : "State of dictionay is not well formed!";
		return s;
	}

	@Override
	public boolean exists(String word) {
		assert word != null;
		assert isWellFormed() : "State of dictionay is not well formed!";
		if(words.containsKey(word) == false)
			return false;
		return true;
	}

	@Override
	public boolean checkConsistency() {
		for(Entry<String, Word> entry: words.entrySet()){
			for(int i = 0; i<entry.getValue().getSynonyms().length; i++){
				if(words.containsKey(entry.getValue().getSynonyms()[i]) == false)
					return false;
			}
		}
		return true;
	}

	private boolean isWellFormed() {
		for (Entry<String, Word> entry : words.entrySet()) {
			char[] chars = entry.getValue().getWord().toCharArray();
			for (char c : chars) {
				if (Character.isDigit(c)) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * @return the words
	 */
	public TreeMap<String, Word> getWords() {
		return words;
	}
	/**
	 * @param words the words to set
	 */
	public void setWords(TreeMap<String, Word> words) {
		this.words = words;
	}
	/**
	 * @return the noWords
	 */
	public int getNoWords() {
		return noWords;
	}
	/**
	 * @param noWords the noWords to set
	 */
	public void setNoWords(int noWords) {
		this.noWords = noWords;
	}
	
}
