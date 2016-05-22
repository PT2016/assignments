package Entities;

import java.util.ArrayList;

public class LongWord extends Word {

	public LongWord(String name, ArrayList<String> synonyms) {
		super(name, synonyms);
		System.out.println("New long-word created");
	}

}
