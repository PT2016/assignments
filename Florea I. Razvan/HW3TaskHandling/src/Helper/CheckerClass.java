package Helper;

public class CheckerClass {

	public boolean checkTimeFormat(String time) {
		boolean valid = true;
		for (int i = 0; i < 1; i++) {
			if ((time.length() > 8) || (time.length() == 0)) {
				valid = false;
				break;
			}
			int hour = 0;
			int minute = 0;
			int second = 0;
			try {
				hour = Integer.parseInt(time.substring(0, 2));
				minute = Integer.parseInt(time.substring(3, 5));
				second = Integer.parseInt(time.substring(6));
			} catch (NumberFormatException e) {
				valid = false;
				break;
			}
			if ((hour < 0) || (minute < 0) || (second < 0)) {
				valid = false;
				break;
			}
			if ((hour > 23) || (minute > 59) || (second > 59)) {
				valid = false;
				break;
			}
		}
		return valid;
	}

	public boolean checkSecondsFormat(String seconds) {

		boolean valid = true;

		for (int i = 0; i < 1; i++) {
			int value = 0;
			
			if(seconds.length() == 0){
				valid = false;
				break;
			}
			
			try {
				value = Integer.parseInt(seconds);
			} catch (NumberFormatException e) {
				valid = false;
				break;
			}

			if (value < 0) {
				valid = false;
				break;
			}
		}
		return valid;
	}

}
