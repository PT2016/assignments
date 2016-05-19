package models;

public interface DictionaryProc {

	/**
	 * @pre entry != null
	 * @pre !(entries.containsKey(entry))
	 * @post entries.containsKey(entry)
	 * @post size() == size()@pre + 1
	 */
	public void add(DictionaryEntry entry, boolean populate) throws IllegalOperationException;
	
	/**
	 * @pre entry != null
	 * @pre entries.containsKey(entry)
	 * @post !(entries.containsKey(entry))
	 * @post size() == size()@pre - 1;
	 */
	public void removeKey(DictionaryEntry entry, boolean populate) throws IllegalOperationException;
	
	/**
	 * @pre entry != null
	 * @pre definition != null
	 * @pre entries.containsKey(entry)
	 * @pre entries.get(entry).contains(definition)
	 * @post entries.containsKey(entry)
	 * @post !(entries.get(entry).contains(definition))
	 * @post entries.get(entry).size() + 1 == entries.get(entry).size()@pre
	 */
	public void removeDefinition(DictionaryEntry entry, DictionaryEntry definition, boolean populate) throws IllegalOperationException;
	/**
	 * @pre entry != null
	 * @pre definition != null
	 * @pre entries.containsKey(entry)
	 * @pre entries.get(entry) != null
	 * @post !(entries.get(entry).isEmpty())
	 * @post entries.get(entry).size() == entries.get(entry).size()@pre + 1
	 */
	public void define(DictionaryEntry entry, DictionaryEntry definition, boolean populate) throws IllegalOperationException;
	
}
