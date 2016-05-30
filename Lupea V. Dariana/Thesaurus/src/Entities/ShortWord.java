package Entities;

import java.util.ArrayList;

public class ShortWord extends Word{

	public ShortWord(String name, ArrayList<String> synonyms) {
		super(name, synonyms);
		System.out.println("New short-word created");
	}

}
