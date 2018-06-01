package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import todo.utils.DBUtils;

@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// reqの内容を変数に入れる
		String title = req.getParameter("title");
		String detail = req.getParameter("detail");
		String importance = req.getParameter("importance");
		String limitDate = req.getParameter("limit_date");

		// バリデーションチェック
		List<String> errors = validate(title, limitDate, importance);
		if(errors.size() > 0){
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Connectionの取得
			con = DBUtils.getConnection();

			// SQL実行
			String sql = ""
					+ "INSERT INTO todos (title, detail, importance, limit_date) "
					+ "VALUES (?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, detail);
			ps.setString(3, importance);
			ps.setString(4, limitDate.equals("") ? null : limitDate);

			ps.executeUpdate();

			// index.htmlへ遷移
			resp.sendRedirect("index.html");

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			// Cnnectionクローズ
			DBUtils.close(con, ps);
		}
	}

	private List<String> validate(String title, String limitDate, String importance) {
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
