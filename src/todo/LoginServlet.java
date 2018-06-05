package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.beans.User;
import todo.utils.DBUtils;

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

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// バリデーションチェック
		List<String> errors = validate(email, password);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
			return;
		}

		// 関連チェック
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();

			String sql = "SELECT id, email, password, name "
					+ "FROM users "
					+ "WHERE email = ? AND password = MD5(?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				// emailとpasswordが正しいとき

				// ログイン処理
				// （セッションにログイン情報を保存する）
				User user = new User(rs.getInt("id"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("name"));

				session.setAttribute("user", user);
				resp.sendRedirect("index.html");

			} else {
				// どちらかが間違っているとき
				errors.add("メールアドレスかパスワードが間違っています。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	private List<String> validate(String email, String password) {
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
