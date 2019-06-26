package todo.utils;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class HtmlUtils {
	public static String formatImportance(int importance) {
		if (importance == 1) {
			return "★";
		} else if (importance == 2) {
			return "★★";
		} else if (importance == 3) {
			return "★★★";
		} else {
			return "";
		}
	}

	public static String formatLimitDate(Date date) {
		if (date == null) {
			return "";
		}

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return date.toLocalDate().format(format);
	}

	public static String checkImportance(String param, String value) {
		if (param.equals("") && value.equals("3")) {
			return "checked";
		} else if (param.equals(value)) {
			return "checked";
		} else {
			return "";
		}
	}
}
