package controllers;

import com.google.gson.Gson;
import models.*;
import views.*;  

public class Main {

	public static void main(String[] args){
		Dictionary.deserialize();
		new MainFrameController(new MainFrame("Dictionary"));
	}
	
}
