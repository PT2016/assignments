import static org.junit.Assert.*;

import java.util.ArrayList;

import models.ProxyDictionary;

public class Test {

	private ProxyDictionary dictionary = new ProxyDictionary();
	private String key = "prieten";
	private String syn = "amic";

	@org.junit.Test
	public void test() {
		boolean bool = searchWord(key, syn);
		assert bool == true : "fail";
		if(!bool)
			fail("fail");
	}

	public void addSynonim(String key, String synonim) {
		dictionary.addSynonim(key, synonim);
	}

	public void deleteSynonim(String key) {
		dictionary.deleteSynonim(key);
	}

	public void copy(String key, String synonim) {
		dictionary.copy(key, synonim);
	}

	public void delete(String key, String synonim) {
		dictionary.delete(key, synonim);
	}

	public boolean searchWord(String key, String synonim) {
		dictionary.addSynonim(key, synonim);
		ArrayList<String> result = dictionary.searchWord(synonim);
		if (!result.contains(key))
			return false;
		return true;
	}
}
