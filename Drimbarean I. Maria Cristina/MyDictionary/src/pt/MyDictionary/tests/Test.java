package pt.MyDictionary.tests;

import pt.MyDictionary.MVC.*;
import pt.MyDictionary.model.*;
import pt.MyDictionary.strategy.*;

public class Test {

	public static void main(String[] args) {
		//consistency test
		/*Dictionary d=new Dictionary();
		d.add(new Word("good","nice,funny","I love Java"));
		System.out.println(d.search("good"));
		System.out.println(d.search("good").getSynonyms()[0]);
		d.add(new Word("nice","funny",""));
		d.add(new Word("funny","good",""));
		if (d.checkConsistency())
			System.out.println("Consistent");*/
		//SerializeDictionary model = new SerializeDictionary(d);//new serialization
		//model.save();
		Interface view = new Interface();
		SerializeDictionary model = new SerializeDictionary();
		DataAcess controller = new DataAcess(view, model);

	}

}
