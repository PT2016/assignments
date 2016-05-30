package pt.MyDictionary.tests;

import pt.MyDictionary.model.WildcardMatch;

public class TestWildcard {
	public static void main(String[] args) {
       WildcardMatch comp=new WildcardMatch();
       if (comp.compare("acum","a?um"))
    	   System.out.println("true");
       else System.out.println("false");
	}
}
