package todo.forms;

import javax.servlet.http.HttpServletRequest;

public class EntryForm {
	private String title;
	private String detail;
	private String importance;
	private String limitDate;

	/**
	 * パラメータからformへの変換
	 * @param req
	 * @return
	 */
	public static EntryForm fromParameter(HttpServletRequest req) {
		String title = req.getParameter("title");
		String detail = req.getParameter("detail");
		String importance = req.getParameter("importance");
		String limitDate = req.getParameter("limit_date");

		EntryForm form = new EntryForm();
		form.setTitle(title);
		form.setDetail(detail);
		form.setImportance(importance);
		form.setLimitDate(limitDate);

		return form;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}
}
