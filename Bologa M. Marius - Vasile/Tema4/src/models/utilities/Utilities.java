package models.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utilities {
	static Random random = new Random();

	public static String getDateRandom(int dat) {
		Calendar date = Calendar.getInstance(); 
		int numberOfDaysToAdd = random.nextInt(dat); 
		date.add(Calendar.DAY_OF_YEAR, numberOfDaysToAdd); 
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = date.getTime();
		String datea = df.format(dateobj);
		return datea;
	}

}
