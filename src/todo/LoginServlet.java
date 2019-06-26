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

import todo.forms.LoginForm;
import todo.service.AuthService;

@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		// パラメータをFormに変換
		LoginForm form = LoginForm.fromParameter(req);

		// バリデーションチェック
		List<String> errors = validate(form);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
			return;
		}

		// ログインチェック
		AuthService service = new AuthService();
		LoginForm login = service.check(form);

		if(login != null) {
			// email, パスワードが合っているとき
			session.setAttribute("login", login);
			resp.sendRedirect("index.html");

		} else {
			// どちらかが間違っているとき
			errors.add("メールアドレスかパスワードが間違っています。");
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		}
	}

	private List<String> validate(LoginForm form) {
		// formから値を取り出す
		String email = form.getEmail();
		String password = form.getPassword();

		// エラーメッセージ
		List<String> errors = new ArrayList<>();

		// メールアドレスの必須
		if (email.equals("")) {
			// エラーが発生
			errors.add("メールアドレスは必須入力です。");
		}

		// パスワードの必須
		if (password.equals("")) {
			// エラーが発生
			errors.add("パスワードは必須入力です。");
		}

		return errors;
	}
}
