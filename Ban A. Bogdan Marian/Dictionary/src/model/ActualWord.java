package model;

import java.util.List;
import java.util.Observable;

import controller.IWord;

public class ActualWord extends Observable implements IWord {
	private String name;
	private List<String> description;

	public ActualWord(String name, List<String> description) {
		this.name = name;
		this.description = description;
		
	}
	
	public void notifyObserver(){
		setChanged();
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.IWord#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.IWord#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.IWord#getDescription()
	 */
	@Override
	public List<String> getDescription() {
		return description;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.IWord#setDescription(java.util.List)
	 */
	@Override
	public void setDescription(List<String> description) {
		this.description = description;
	}
}
