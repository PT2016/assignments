package queues;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {

	public static String convertToString(long time) {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time);
	}

	@SuppressWarnings("deprecation")
	public static int getHourFromDate(long time) {
		new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date(time);
		return d.getHours();
	}
}
