package todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.forms.EntryForm;
import todo.service.TodoService;

@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		// パラメータをEntityに変換
		EntryForm form = EntryForm.fromParameter(req);

		// バリデーションチェック
		List<String> errors = validate(form);
		if(errors.size() > 0){
			req.setAttribute("form", form);
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
			return;
		}

		// INSERT文を実行
		TodoService service = new TodoService();
		service.register(form);

		// index.htmlへ遷移
		List<String> successes = new ArrayList<>();
		successes.add("登録しました。");
		session.setAttribute("successes", successes);
		resp.sendRedirect("index.html");
	}

	/**
	 * バリデーションチェック
	 * @param req
	 * @return
	 */
	private List<String> validate(EntryForm form) {

		// formの内容を変数に入れる
		String title = form.getTitle();
		String detail = form.getDetail();
		String importance = form.getImportance();
		String limitDate = form.getLimitDate();

		// エラー配列
		List<String> errors = new ArrayList<>();

		// 題名の必須
		if (title.equals("")) {
			// エラーが発生
			errors.add("題名は必須入力です。");
		}

		// 題名100文字制限
		if(title.length() > 100) {
			errors.add("題名は100文字以内にして下さい。");
		}

		// 詳細500文字制限
		if(detail.length() > 500) {
			errors.add("詳細は500文字以内にして下さい。");
		}

		// 日付の形式（YYYY/MM/DD）
		if(!limitDate.equals("")) {
			try {
				LocalDate.parse(limitDate, DateTimeFormatter.ofPattern("uuuu/MM/dd")
						.withResolverStyle(ResolverStyle.STRICT));
			}catch(Exception e) {
				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}

		// 重要度が1から3のチェック
		if(!importance.equals("1") && !importance.equals("2")
				&& !importance.equals("3")) {
			errors.add("不正なアクセスです。");
		}

		return errors;
	}
}
