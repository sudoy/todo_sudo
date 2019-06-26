package todo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String date2String(final Date date) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
}
