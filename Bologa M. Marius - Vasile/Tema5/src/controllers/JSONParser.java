package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import models.Dictionary;

public class JSONParser {

	public static void writeInJson(Dictionary d) {
		Gson gson = new Gson();

		String json = gson.toJson(d);

		try {
			FileWriter writer = new FileWriter("dictionary.json");
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json);

	}

	public static Dictionary readFromJson() {
		Gson gson = new Gson();

		try {

			BufferedReader br = new BufferedReader(new FileReader("dictionary.json"));
			Dictionary dictionary = gson.fromJson(br, Dictionary.class);
			return dictionary;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
