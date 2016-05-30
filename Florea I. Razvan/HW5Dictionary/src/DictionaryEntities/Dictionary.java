package DictionaryEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.ParseException;

import Control.Helper;
import UserInterface.OutputArea;

public class Dictionary implements DictionaryFunctions {

	private Map<String, List<String>> words;
	private Helper helper;

	public Dictionary() {
		helper = new Helper();
		try {
			words = helper.readFromJSONFile();
		} catch (ParseException | java.text.ParseException e) {
			System.out.println("Error at reading from JSON !!!");
		}
	}

	private void printWord(String word) {

		OutputArea area = OutputArea.getInstance();

		for (Map.Entry<String, List<String>> entry : words.entrySet()) {
			if (entry.getKey().equals(word)) {
				area.append("\n>>> Word Found\n");
				area.append(word + " == ");
				for (String syno : entry.getValue()) {
					area.append("( " + syno + " ) ");
				}
			}
		}
	}

	@Override
	public void addWord(String word, List<String> synonyms) {
		assert (isWellFormed() && (word != "")
				&& (!synonyms.equals(null))) : "Assertion error at entering add word !";

		words.put(word, synonyms);

		for (String syno : synonyms) {
			List<String> newSynonyms = new ArrayList<String>();
			newSynonyms.add(word);
			words.put(syno, newSynonyms);
		}

		helper.saveInJSONFile(words);

		assert (isWellFormed()) : "Assertion error at exiting add word !";
	}

	@Override
	public void removeWord(String word) {
		assert (isWellFormed() && (word != "")) : "Assertion error at entering remove word !";

		for (Map.Entry<String, List<String>> entry : words.entrySet()) {
			for (String syno : entry.getValue()) {
				if (syno.equals(word)) {
					entry.getValue().remove(syno);
					if(entry.getValue().contains("")){
						words.remove(entry.getKey());
					}
					break;
				}
			}
		}

		for (Map.Entry<String, List<String>> entry : words.entrySet()) {
			if (entry.getKey().equals(word)) {
				words.remove(entry.getKey());
				break;
			}
		}

		helper.saveInJSONFile(words);

		assert (isWellFormed()) : "Assertion error at exiting remove word !";
	}

	@Override
	public void showWords() {
		
		OutputArea area = OutputArea.getInstance();
		
		int index = 0;
		for (Map.Entry<String, List<String>> entry : words.entrySet()) {
			index++;
			area.append(index + ". " + entry.getKey() + " == ");
			for (String syno : entry.getValue()) {
				area.append("( " + syno + " ) ");
			}
			area.append("\n");
		}

	}

	@Override
	public void addSynonym(String synonym, String relatedKeyWord) {
		assert (isWellFormed() && (!synonym.equals(null))
				&& (!relatedKeyWord.equals(null))) : "Assertion error at entering add synonym !";

		List<String> synos = new ArrayList<String>();
		synos.add(relatedKeyWord);
		words.put(synonym, synos);

		for (Map.Entry<String, List<String>> entry : words.entrySet()) {
			if (entry.getKey().equals(relatedKeyWord)) {
				entry.getValue().add(synonym);
				helper.saveInJSONFile(words);
				break;
			}
		}

		assert (isWellFormed()) : "Assertion error at exiting add synonym !";
	}

	@Override
	public void searchWord(String word) {
		assert (isWellFormed() && (!word.equals(null))) : "Assertion error at entering search word !";

		boolean found = false;

		for (Map.Entry<String, List<String>> entry : words.entrySet()) {
			if (entry.getKey().equals(word)) {
				found = true;
				printWord(word);
			}
		}

		if (!found) {
			char[] spelledWord = word.toCharArray();
			char[] lettersOnly = new char[spelledWord.length];
			int i = 0;
			for (Character chr : spelledWord) {
				if (!chr.equals('*')) {
					lettersOnly[i] = chr;
					i++;
				} else if (chr.equals('*')) {
					word = word.replaceAll("\\?", "[A-Za-z]");
					String beginLetters = String.copyValueOf(lettersOnly).substring(0, i);

					for (Map.Entry<String, List<String>> entry : words.entrySet()) {
						if (entry.getKey().startsWith(beginLetters)) {
							found = true;
							printWord(entry.getKey());
						}
					}
					break;
				}
			}
		}

		if (!found) {
			word = word.replaceAll("\\?", "[A-Za-z]");
			for(Map.Entry<String, List<String>> entry : words.entrySet()){
				if(entry.getKey().matches(word)){
					found = true;
					printWord(entry.getKey());
				}
			}
		}

		if (!found) {
			OutputArea.getInstance().append("\n>>> Word Not Found :(\n");
		}

		assert (isWellFormed()) : "Assertion error at exiting search word !";
	}

	private boolean isWellFormed() {

		boolean isConsistant = true;

		for (Map.Entry<String, List<String>> entry : words.entrySet()) {
			for (String syno : entry.getValue()) {
				boolean synonymDefined = false;
				for (String key : words.keySet()) {
					if (syno.equals(key))
						synonymDefined = true;
				}
				if (!synonymDefined)
					isConsistant = false;
			}
		}

		return isConsistant;
	}
}