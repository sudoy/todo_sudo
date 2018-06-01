package todo.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import todo.beans.Todo;

public class HtmlUtils {
	public static String test() {
		return "hogehoge";
	}

	public static String formatImportance(Todo todo) {
		if (todo.getImportance() == 1) {
			return "★";
		} else if (todo.getImportance() == 2) {
			return "★★";
		} else if (todo.getImportance() == 3) {
			return "★★★";
		} else {
			return "";
		}
	}

	public static String formatLimitDate(Todo todo) {
		LocalDate limit = todo.getLimitDate();
		if (limit == null) {
			return "";
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return limit.format(dtf);
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
