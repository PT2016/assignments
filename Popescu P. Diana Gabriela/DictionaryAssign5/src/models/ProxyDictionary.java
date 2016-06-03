package models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

public class ProxyDictionary extends Observable implements DictionaryProc{

	private Dictionary dictionary = new Dictionary();
	
	@Override
	public void addSynonim(String key, String synonim) {
		dictionary.addSynonim(key, synonim);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void deleteSynonim(String key) {
		dictionary.deleteSynonim(key);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void copy(String key, String synonim) {
		dictionary.copy(key, synonim);
	}

	@Override
	public void delete(String key, String synonim) {
		dictionary.delete(key, synonim);
	}

	@Override
	public ArrayList<String> searchWord(String key) {
		return dictionary.searchWord(key);
	}
	
	@Override
	public void populate() {
		dictionary.populate();
	}

	@Override
	public void save() {
		dictionary.save();
	}

	@Override
	public boolean isConsistent() {
		return dictionary.isConsistent();
	}

	@Override
	public Set<String> getKeys() {
		return dictionary.getKeys();
	}

	@Override
	public ArrayList<String> getSyn(String key) {
		return dictionary.getSyn(key);
	}
	
	@Override
	public int getSize(){
		return dictionary.getSize();
	}
}
