package model;

import java.util.Collections;
import java.util.List;

import controller.IWord;

public class NullWord implements IWord {

	@Override
	public String getName() {
		return "EMPTY";
	}

	@Override
	public void setName(String name) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDescription() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void setDescription(List<String> description) {

	}

}
