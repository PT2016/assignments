package Dictionary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import exception.AddWordException;
import exception.DefineException;
import exception.RemoveWordException;

@SuppressWarnings("serial")
public class Dictionary implements DictionaryInterface, Serializable{

	private static Dictionary instance = null;
	private TreeMap<String,ArrayList<String>> dictionaryTree;
	
	protected Dictionary(){
		this.dictionaryTree = new TreeMap<String,ArrayList<String>>();
	}
	
	public static Dictionary getInstance(){
		if(instance == null)
			instance = new Dictionary();
		return instance;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populateDictionary() {
		try
	    {
		    @SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("Words.ser"));
		    this.dictionaryTree = (TreeMap)in.readObject();
	    }
	    catch(IOException e1)
	    {
	    	e1.printStackTrace();
	    }
	    catch(ClassNotFoundException e1)
	    {
	    	e1.printStackTrace();
	    }
	}
	
	public void serializeDictionary() {
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream(new FileOutputStream("Words.ser"));
			out.writeObject(this.dictionaryTree);
			out.close();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}

	public void addWord(String s) throws AddWordException{
		assert(s instanceof String);
		assert(s != null);
		assert(this.isWellFormed() == true);
		
		ArrayList<String> sSynonyms = new ArrayList<>();
		if(dictionaryTree.containsKey(s) != true){
			this.dictionaryTree.put(s, sSynonyms);
		}
		else throw new AddWordException("Word already exits!");
		assert(this.isWellFormed() == true);
	}
	
	public void defineWord(String s, String syn) throws DefineException{
		assert(s instanceof String);
		assert(s != null);
		assert(syn instanceof String);
		assert(syn != null);
		assert(this.isWellFormed() == true);
		ArrayList<String> sSynonyms = new ArrayList<>();
		if(dictionaryTree.containsKey(s) == true){
			sSynonyms = dictionaryTree.get(s);
			sSynonyms.add(syn);
			if(dictionaryTree.containsKey(syn) != true){
				try {
					this.addWord(syn);
				} catch (AddWordException e) {
					e.printStackTrace();
				}
				this.defineWord(syn, s);
			}
			else if(dictionaryTree.containsKey(syn) == true){
				ArrayList<String> aux = new ArrayList<>();
				aux = dictionaryTree.get(syn);
				for(int i = 0; i<aux.size(); i++){
					if(s != aux.get(i)){
						sSynonyms.add(aux.get(i));
						if(!dictionaryTree.get(aux.get(i)).contains(s))
							dictionaryTree.get(aux.get(i)).add(s);
					}
				}
			}
			this.dictionaryTree.put(s, sSynonyms);
			assert(this.isWellFormed() == true);
		}
		else throw new DefineException("Word does not exist in dictionary!");
	}
	
	public void removeWord(String s) throws RemoveWordException {
		assert(s instanceof String);
		assert(s != null);
		assert(this.isWellFormed() == true);
		Set<String> treeKeys = this.dictionaryTree.keySet();
		String save = new String();
		String save1 = new String();
		if(dictionaryTree.containsKey(s) == true){
			for(String aux : treeKeys){
				if(aux.equals(s) == true){
					save = aux;
				}
				else{
					for(int i = 0; i<this.dictionaryTree.get(aux).size(); i++){
						if(this.dictionaryTree.get(aux).get(i).equals(s)){
							this.dictionaryTree.get(aux).remove(i);
							if(this.dictionaryTree.get(aux).size() == 0){
								save1 = aux;
							}
						}
					}
				}
			}
			this.dictionaryTree.remove(save1);
			this.dictionaryTree.remove(save);
		}
		else throw new RemoveWordException("The word does not exist!");
		assert(this.isWellFormed() == true);
	}

	public ArrayList<String> findWord(String s) {
		assert(s instanceof String);
		assert(s != null);
		assert(this.isWellFormed() == true);
		ArrayList<String> found = new ArrayList<>();
		if(s != null){
			String regex = new String();
			regex = s;
			if(regex.contains("?")==true)
				regex = regex.replace("?", ".?");
			if(regex.contains("*")==true)
				regex = regex.replace("*", ".*");
			for(String aux : this.dictionaryTree.keySet()){
				if(aux.matches(regex))
					found.add(aux);
			}
		}
		assert(found instanceof ArrayList);
		assert(this.isWellFormed() == true);
		return found;
	}

	public boolean isConsistent(){
		Set<String> aux = this.dictionaryTree.keySet();
		ArrayList<String> syn = new ArrayList<>();
		for(String s : aux){
			syn = dictionaryTree.get(s);
			for(int i = 0; i<syn.size(); i++){
				if(!this.dictionaryTree.containsKey(syn.get(i))){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isWellFormed(){
		if(this.isConsistent() == false)
			return false;
		return true;
	}
	
	public TreeMap<String,ArrayList<String>> getTreeMap(){
		return this.dictionaryTree;
	}
	
	public ArrayList<String> getSynonyms(String s) {
		return this.dictionaryTree.get(s);
	}
}
