package todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.service.TodoService;

@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		// reqの内容を変数に入れる
		String id = req.getParameter("id");

		// バリデーションチェック
		List<String> errors = validate(id);
		if(errors.size() > 0){
			session.setAttribute("errors", errors);
			resp.sendRedirect("index.html");
			return;
		}

		// UPDATE文を実行
		TodoService service = new TodoService();
		service.destroy(Integer.parseInt(id));

		// index.htmlへ遷移
		List<String> successes = new ArrayList<String>();
		successes.add("削除しました。");
		session.setAttribute("successes", successes);
		resp.sendRedirect("index.html");
	}

	private List<String> validate(String id) {
		List<String> errors = new ArrayList<>();

		// idの必須
		if (id == null || id.equals("")) {
			// エラーが発生
			errors.add("不正なアクセスです。");
		}

		return errors;
	}
}
