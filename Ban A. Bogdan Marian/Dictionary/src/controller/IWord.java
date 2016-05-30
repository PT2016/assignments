package controller;

import java.util.List;

public interface IWord {

	String getName();

	void setName(String name);

	List<String> getDescription();

	void setDescription(List<String> synonims);
}