package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
	private String word;

	public Word() {
		this.word = new String("");
	}

	public Word(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean matchWord(String toSearch) {
		String input = toSearch;
		String patternStr = input.replaceAll("\\*", "\\.*");

		Pattern p = Pattern.compile(patternStr);

		Matcher m = p.matcher(this.word);

		boolean matchess = false;
		if (m.find())
			matchess = true;

		return matchess;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	public String toString() {
		return this.word;
	}
}
