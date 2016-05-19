package models;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Dictionary implements DictionaryProc, Observer, Serializable {

	private static Dictionary instance;
	private HashMap<DictionaryEntry, ArrayList<DictionaryEntry>> entries = new HashMap<>();

	private Dictionary() {

	}

	public static Dictionary getInstance() {
		if (instance == null)
			instance = new Dictionary();
		return instance;
	}

	public void add(DictionaryEntry entry, boolean populate) throws IllegalOperationException {
		if (!populate)
			assert WellFormed();
		assert entry != null;
		assert !(entries.containsKey(entry));
		int size = entries.size();
		if (entries.containsKey(entry))
			throw new IllegalOperationException("Entry already there");
		entries.put(entry, new ArrayList<DictionaryEntry>());
		serialize();
		assert entries.containsKey(entry);
		assert size + 1 == entries.size();
		if (!populate)
			assert WellFormed();
	}

	public void removeKey(DictionaryEntry entry, boolean populate) throws IllegalOperationException {
		if (!populate)
			assert WellFormed();
		assert entry != null;
		assert entries.containsKey(entry);
		int size = entries.size();
		int i, size2;
		if (!(entries.containsKey(entry)))
			throw new IllegalOperationException("Entry not there");
		entries.remove(entry);
		for (DictionaryEntry de : entries.keySet()) {
			for (i = 0; i < entries.get(de).size(); i++) {
				DictionaryEntry de2 = entries.get(de).get(i);
				if (de2 instanceof Phrase) {
					if (((Phrase) de2).getContent().contains(entry))
						entries.get(de).remove(de2);
				} else {
					entries.get(de).remove(entry);
				}
			}
		}
		serialize();
		assert !(entries.containsKey(entry));
		assert size - 1 == entries.size();
		if (!populate)
			assert WellFormed();
	}

	public void removeDefinition(DictionaryEntry entry, DictionaryEntry definition, boolean populate)
			throws IllegalOperationException {
		if (!populate)
			assert WellFormed();
		assert entry != null;
		assert definition != null;
		assert entries.containsKey(entry);
		assert entries.get(entry).contains(definition);
		int size = entries.get(entry).size();
		if (!(entries.containsKey(entry)))
			throw new IllegalOperationException("Entry not there");
		if (!(entries.get(entry).contains(definition)))
			throw new IllegalOperationException("Entry not there");
		entries.get(entry).remove(definition);
		serialize();
		assert entries.containsKey(entry);
		assert !(entries.get(entry).contains(definition));
		assert entries.get(entry).size() + 1 == size;
		if (!populate)
			assert WellFormed();
	}

	public void define(DictionaryEntry entry, DictionaryEntry definition, boolean populate)
			throws IllegalOperationException {
		if (!populate)
			assert WellFormed();
		assert entry != null;
		assert definition != null;
		assert entries.containsKey(entry);
		assert entries.get(entry) != null;
		int size = entries.get(entry).size();
		if (!(entries.containsKey(entry)))
			throw new IllegalOperationException("Entry not there");
		entries.get(entry).add(definition);
		if (!(WellFormed()) && !(populate)) {
			entries.get(entry).remove(definition);
			throw new IllegalOperationException("Not a consistent defintion");
		}
		serialize();
		assert !(entries.get(entry).isEmpty());
		assert size + 1 == entries.get(entry).size();
		if (!populate) {
			assert WellFormed();
		}
	}

	public HashMap<DictionaryEntry, ArrayList<DictionaryEntry>> search(String pattern) {
		int i;
		HashMap<DictionaryEntry, ArrayList<DictionaryEntry>> result = new HashMap<>();
		String pattern2 = "";
		boolean begin = true;
		for (i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) == '*')
				pattern2 += ".*";
			else if (pattern.charAt(i) == '?')
				pattern2 += ".{1}";
			else
				pattern2 += pattern.charAt(i);
		}
		for (DictionaryEntry de : entries.keySet())
			if (((Word) de).getContent().matches(pattern2))
				result.put(de, entries.get(de));
		return result;
	}

	public boolean checkIfPreposition(DictionaryEntry preposition) {
		for (DictionaryEntry de : entries.keySet())
			for (DictionaryEntry de2 : entries.get(de))
				if (de2 instanceof Phrase)
					if (((Phrase) de2).getContent().contains(preposition))
						return true;
		return false;
	}

	public void serialize() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		class DictionaryEntrySerializer extends JsonSerializer<DictionaryEntry> {
			@Override
			public void serialize(DictionaryEntry arg0, JsonGenerator arg1, SerializerProvider arg2)
					throws IOException, JsonProcessingException {
				arg1.writeFieldName(String.valueOf(arg0.toString()));
			}
		}
		SimpleModule module = new SimpleModule();
		module.addKeySerializer(DictionaryEntry.class, new DictionaryEntrySerializer());
		mapper.registerModule(module);
		try {
			String json = mapper.writeValueAsString(this);
			FileWriter wr = new FileWriter("Dictionary.json");
			wr.write(json);
			wr.close();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deserialize() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		class ExampleClassKeyDeserializer extends KeyDeserializer {
			@Override
			public Object deserializeKey(final String key, final DeserializationContext ctxt)
					throws IOException, JsonProcessingException {
				return new Word(key, false);
			}
		}

		class ExampleJacksonModule extends SimpleModule {
			public ExampleJacksonModule() {
				addKeyDeserializer(DictionaryEntry.class, new ExampleClassKeyDeserializer());
			}
		}
		mapper.registerModule(new ExampleJacksonModule());
		try {
			BufferedReader br = new BufferedReader(new FileReader("Dictionary.json"));
			instance = mapper.readValue(br, Dictionary.class);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean WellFormed() {
		for (DictionaryEntry de : entries.keySet())
			for (DictionaryEntry de2 : entries.get(de))
				if (de2 instanceof Phrase) {
					for (DictionaryEntry de3 : ((Phrase) de2).getContent())
						if (!(((Word) de3).isPreposition()))
							if (!(entries.containsKey(de3)))
								return false;
				} else if (!(((Word) de2).isPreposition()))
					if (!(entries.containsKey(de2)))
						return false;
		return true;
	}

	@Override
	public void update(Observable o, Object arg) {
		String newContent = (String) arg;
		Word old = (Word) o;
		ArrayList<DictionaryEntry> definition = entries.get(old);
		entries.remove(old);
		for (DictionaryEntry de : entries.keySet())
			for (DictionaryEntry de2 : entries.get(de))
				if (de2 instanceof Phrase)
					for (DictionaryEntry de3 : ((Phrase) de2).getContent()) {
						if (de3.equals(old))
							((Word) de3).setContent(newContent);
						else if (de2.equals(old))
							((Word) de2).setContent(newContent);
					}
				else if (de2.equals(old))
					((Word) de2).setContent(newContent);
		entries.put(new Word(newContent, false), definition);
		serialize();
	}

	public HashMap<DictionaryEntry, ArrayList<DictionaryEntry>> getEntries() {
		return entries;
	}

	public void setEntries(HashMap<DictionaryEntry, ArrayList<DictionaryEntry>> entries) {
		this.entries = entries;
	}

	public String toString() {
		return entries.entrySet().stream().map(entry -> entry.getKey() + " = " + entry.getValue())
				.collect(Collectors.joining("; ", "[", "]"));
	}

}
